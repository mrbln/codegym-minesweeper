package com.codegym.games.minesweeper;

import com.codegym.engine.cell.*;
import java.util.Random;
import java.util.*;

public class MinesweeperGame extends Game {
    private static final int SCREEN_SIZE = 8;
    int[][] bombs = new int[8][8];
    private Random rand = new Random();
    
    @Override
    public void initialize() {
        // Set the field size to 7 cells x 9 cells
        setScreenSize(SCREEN_SIZE, SCREEN_SIZE);
        showGrid(true);
        setTurnTimer(250);
        
        // Paint the playing field white
        for (int x = 0; x < SCREEN_SIZE; x++) {
            for (int y = 0; y < SCREEN_SIZE; y++) {
                setCellColor(x, y, Color.GREY);
                bombs[x][y] = 0;
            }
        }
        
        // Set bomb cells
        for (int k = 0; k < SCREEN_SIZE; k++) {
            int x = rand.nextInt(8);
            int y = rand.nextInt(8);
            bombs[x][y] = 1;
        }
    }
    
    @Override
    public void onTurn(int step) {
        int x = rand.nextInt(8);
        int y = rand.nextInt(8);
        
        if (!getCellColor(x,y).equals(Color.GREEN) && !getCellColor(x,y).equals(Color.RED)) {
            paintCell(x,y);
        }
    }
    
    @Override
    public void onMouseLeftClick(int x, int y) {
        paintCell(x,y);
    }
    
    public void paintCell(int x, int y) {
        Integer bombCounter = 0;
        
        if (bombs[x][y] == 1) {
            setCellColor(x, y, Color.RED);
            showMessageDialog(Color.WHITE, "KIZ YAAVRUUM", Color.BLACK, 24);
            stopTurnTimer();
        } 
        else {
            for (int z = x-1; z <= x+1; z++) {
                for (int t = y-1; t <= y+1; t++) {
                    if (z >= 0 && t >= 0) {
                        if (z < SCREEN_SIZE && t < SCREEN_SIZE) {
                            if (bombs[z][t] == 1) {
                               bombCounter++; 
                            }
                        }
                    }
                }
            }
            if (bombCounter == 0) {
                setCellColor(x, y, Color.GREEN);
            } 
            else {
                setCellValueEx(x, y, Color.GREEN, bombCounter.toString(), Color.WHITE, 100);
            }
        }
    }
}