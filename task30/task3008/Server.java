package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message){
        for(Connection connection : connectionMap.values()){
            try {
                connection.send(message);
            } catch (IOException e) {
                System.out.println("Message didn`t send");
            }
        }
    }

    public static void main(String[] args) {
        int port = ConsoleHelper.readInt();
        try(ServerSocket server = new ServerSocket(port)){
            ConsoleHelper.writeMessage("Server is ready");
            while(true){
                Socket socket = server.accept();
                new Handler(socket).start();
                continue;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            Message request = new Message(MessageType.NAME_REQUEST);
            Message response;
            String name;
            do {
                connection.send(request);
                response = connection.receive();
                name = response.getData();
            } while (response.getType() != MessageType.USER_NAME ||
                    name.isEmpty() || connectionMap.containsKey(name));
            connectionMap.put(name, connection);
            connection.send(new Message(MessageType.NAME_ACCEPTED));
            return name;
        }

        private void notifyUsers(Connection connection, String userName) throws IOException{
            for(Map.Entry<String, Connection> map : connectionMap.entrySet()){
                if(!map.getKey().equals(userName)) {
                    try {
                        connection.send(new Message(MessageType.USER_ADDED, map.getKey()));
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            while(true){
                Message text = connection.receive();
                if(text.getType() == MessageType.TEXT){
                    sendBroadcastMessage(new Message(MessageType.TEXT, userName + ": " + text.getData()));
                } else {
                    ConsoleHelper.writeMessage("MessageTypeException");
                }
            }
        }

        @Override
        public void run() {
            ConsoleHelper.writeMessage(socket.getRemoteSocketAddress().toString());
            String userName = null;
            try {
                Connection connection = new Connection(socket);
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                notifyUsers(connection, userName);
                serverMainLoop(connection, userName);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Something wrong ;(");
            } catch (ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Do something better");
            } finally {
                if (userName != null) {
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                }
            }
        }
    }
}
