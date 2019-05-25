package com.javarush.task.task35.task3513;

import java.util.*;
import java.util.stream.Collectors;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    private Stack previousStates = new Stack();
    private Stack previousScores = new Stack();
    private boolean isSaveNeeded = true;

    protected int score;
    protected int maxTile;

    public Model() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        resetGameTiles();
        score = 0;
        maxTile = 2;
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove(){
        if (!getEmptyTiles().isEmpty()) return true;
        for (Tile[] gt : gameTiles){
            for (int i = 1; i < gameTiles.length; i++){
                if (gt[i].value == gt[i-1].value) return true;
            }
        }
        for (int i = 0; i < gameTiles.length; i++){
            for (int j = 1; j < gameTiles.length; j++){
                if (gameTiles[j][i].value == gameTiles[j-1][i].value) return true;
            }
        }
        return false;
    }

    public void left(){
        if (isSaveNeeded) saveState(gameTiles);
        int flag = 0;
        for (int i = 0; i < gameTiles.length; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) flag++;
        }
        if (flag != 0) addTile();
        isSaveNeeded = true;
    }

    public void up(){
        saveState(gameTiles);
        rotateField();
        rotateField();
        rotateField();
        left();
        rotateField();
    }

    public void down(){
        saveState(gameTiles);
        rotateField();
        left();
        rotateField();
        rotateField();
        rotateField();
    }

    public void right(){
        saveState(gameTiles);
        rotateField();
        rotateField();
        left();
        rotateField();
        rotateField();
    }

    public void rollback(){
        if (!previousScores.isEmpty() && !previousStates.isEmpty()) {
            gameTiles = (Tile[][]) previousStates.pop();
            score = (int) previousScores.pop();
        }
    }

    public void randomMove(){
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n){
            case 0:
                left();
                break;
            case 1:
                up();
                break;
            case 2:
                right();
                break;
            case 3:
                down();
                break;
        }
    }

    public boolean hasBoardChanged() {
        int sumGameTiles = 0;
        int sumStackTiles = 0;
        if (!previousStates.isEmpty()) {
            Tile[][] stackTile = (Tile[][]) previousStates.peek();
            for (int i = 0; i < FIELD_WIDTH; i++) {
                for (int j = 0; j < FIELD_WIDTH; j++) {
                    sumGameTiles += gameTiles[i][j].value;
                    sumStackTiles += stackTile[i][j].value;
                }
            }
        }
        return sumGameTiles != sumStackTiles;
    }

    public MoveEfficiency getMoveEfficiency(Move move){
        move.move();
        MoveEfficiency moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        if(!hasBoardChanged()) moveEfficiency = new MoveEfficiency(-1, 0, move);
        rollback();
        return moveEfficiency;
    }

    public void autoMove(){
        PriorityQueue<MoveEfficiency> priorityQueue = new PriorityQueue(4, Collections.reverseOrder());
        priorityQueue.offer(getMoveEfficiency(this::left));
        priorityQueue.offer(getMoveEfficiency(this::down));
        priorityQueue.offer(getMoveEfficiency(this::right));
        priorityQueue.offer(getMoveEfficiency(this::up));
        priorityQueue.peek().getMove().move();
    }

    protected void resetGameTiles(){
        for (int i = 0; i < FIELD_WIDTH; i++){
            for (int j = 0; j < FIELD_WIDTH; j++){
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private void addTile(){
        List<Tile> list = getEmptyTiles();
         if(list.size() > 0) {
            list.get((int) (Math.random()*list.size())).value = (Math.random() < 0.9) ? 2 : 4;
        }
    }

    private List<Tile> getEmptyTiles(){
        List<Tile> listTiles = new ArrayList<>();
        for(int i = 0; i < FIELD_WIDTH; i++){
            for (int j = 0; j < FIELD_WIDTH; j++){
                if(gameTiles[i][j].isEmpty()){
                    listTiles.add(gameTiles[i][j]);
                }
            }
        }
        return listTiles;
    }

    private boolean compressTiles(Tile[] tiles){
        boolean compressed = false;

        for(int i = 1; i < tiles.length; i++){

            for (int j = 1; j < tiles.length; j++ ) {
                if(tiles[j-1].isEmpty()&&!tiles[j].isEmpty()){
                    compressed = true;
                    tiles[j-1] = tiles[j];
                    tiles[j] = new Tile();
                }
            }
        }
        return compressed;
    }

    private boolean mergeTiles(Tile[] tiles){
        boolean merged = false;
        for(int i = 1; i < tiles.length; i++){
            if ((tiles[i-1].value == tiles[i].value) && !tiles[i-1].isEmpty() && !tiles[i].isEmpty()){
                tiles[i-1].value *= 2;
                tiles[i] = new Tile();
                maxTile = maxTile > tiles[i-1].value ? maxTile : tiles[i-1].value;
                score += tiles[i].value;
                compressTiles(tiles);
                merged = true;
            }
        }
        return merged;
    }

    private void rotateField (){
        int m = FIELD_WIDTH;
        Tile[][] rot = new Tile[m][m];
        for(int i = 0; i < m; i++){
            for (int j = m-1; j >= 0; j--){
                rot[j][m-1-i] = gameTiles[i][j];
            }
        }
        gameTiles = rot;
    }

    private void saveState (Tile[][] tiles){
        Tile[][] newTile = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++){
            for (int j = 0; j < FIELD_WIDTH; j++){
                newTile[i][j] = new Tile();
                newTile[i][j].value = tiles[i][j].value;
            }
        }
        previousStates.push(newTile);
        previousScores.push(score);
        isSaveNeeded = false;
    }
}
