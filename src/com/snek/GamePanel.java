package com.snek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

	static final int SCREEN_W = 666;
	static final int SCREEN_H = 666;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_W * SCREEN_H)/UNIT_SIZE;
	static final int DELAY = 75;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 6;
	int applesEaten = 0;
	int appleX;
	int appleY;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;		

	GamePanel() {
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_H, SCREEN_W));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new myKeyAdapter());
		startGame();
	}

    public void startGame() {

    }

    public void paintComponent(Graphics g) {

    }

    public void draw(Graphics g) {

    }

    public void newApple() {
	    
    }

    public void move() {

    }

    public void checkApple() {

    }

    public void checkCollision() {

    }

    public void gameOver(Graphics g) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public class myKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

        }
    }
}
