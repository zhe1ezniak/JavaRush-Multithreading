package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BotClient extends Client {

    @Override
    protected SocketThread getSocketThread(){
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole(){
        return false;
    }

    @Override
    protected String getUserName(){
        return "date_bot_" + (int) (Math.random()*100);
    }

    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            super.processIncomingMessage(message);
            String[] splitMessage = message.split(":");
            if(splitMessage.length != 2) return;
            for(int i = 0; i < splitMessage.length; i++){
                if(splitMessage[i] == null) return;
            }
                String name = message.split(":")[0].trim();
                String value = message.split(":")[1].trim();
                SimpleDateFormat simpleDateFormat;
                Calendar calendar = new GregorianCalendar();
                switch(value){
                    case "дата":
                        simpleDateFormat = new SimpleDateFormat("d.MM.YYYY");
                        sendTextMessage("Информация для " + name +
                                ": " + simpleDateFormat.format(calendar.getTime()));
                        break;
                    case "день":
                        simpleDateFormat = new SimpleDateFormat("d");
                        sendTextMessage("Информация для " + name +
                                ": " + simpleDateFormat.format(calendar.getTime()));
                        break;
                    case "месяц":
                        simpleDateFormat = new SimpleDateFormat("MMMM");
                        sendTextMessage("Информация для " + name +
                                ": " + simpleDateFormat.format(calendar.getTime()));
                        break;
                    case "год":
                        simpleDateFormat = new SimpleDateFormat("YYYY");
                        sendTextMessage("Информация для " + name +
                                ": " + simpleDateFormat.format(calendar.getTime()));
                        break;
                    case "время":
                        simpleDateFormat = new SimpleDateFormat("H:mm:ss");
                        sendTextMessage("Информация для " + name +
                                ": " + simpleDateFormat.format(calendar.getTime()));
                        break;
                    case "час":
                        simpleDateFormat = new SimpleDateFormat("H");
                        sendTextMessage("Информация для " + name +
                                ": " + simpleDateFormat.format(calendar.getTime()));
                        break;
                    case "минуты":
                        simpleDateFormat = new SimpleDateFormat("m");
                        sendTextMessage("Информация для " + name +
                                ": " + simpleDateFormat.format(calendar.getTime()));
                        break;
                    case "секунды":
                        simpleDateFormat = new SimpleDateFormat("s");
                        sendTextMessage("Информация для " + name +
                                ": " + simpleDateFormat.format(calendar.getTime()));
                        break;
                }
            }
    }



    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }
}
