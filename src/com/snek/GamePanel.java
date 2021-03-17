package com.snek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    //set game panel size
	static final int SCREEN_W = 666;
	static final int SCREEN_H = 666;
	//declares size and amount of individual units in the game space
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_W * SCREEN_H)/UNIT_SIZE;
	//set for the timer (higher number, slower the game)
	static final int DELAY = 75;
	//hold the coordinates of the snake body
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	//set snake body part amount and tracker for apples eaten
	int bodyParts = 6;
	int applesEaten;
	//init variables for setting random points for the apple to gen
	int appleX;
	int appleY;
	//init starting direction for snake
	char direction = 'R';
	//init game state
	boolean running = false;
	Timer timer;
	Random random;		

	GamePanel() {
	    //finish instance of random class
		random = new Random();
		//set panel size, background, and if focusable
		this.setPreferredSize(new Dimension(SCREEN_W, SCREEN_H));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		//add key listener from key adapter for key events
		this.addKeyListener(new myKeyAdapter());
		//starts game on panel open
		startGame();
	}

    public void startGame() {
	    //makes a new apple
        newApple();
        //change game state to true
        running = true;
        //create timer and start timer
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
	    //
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
	    //sets apple color, shape, and location and draws it
	    g.setColor(Color.RED);
	    g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

	    //for loop to loop snake head and body parts and draw them
		for (int i = 0; i < bodyParts; i++) {
			//front of array = head
			if (i==0) {
				g.setColor(Color.GREEN);
			}
			//if not head, its body
			else {
				g.setColor(new Color(45, 180, 0));
			}
			g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);

		}
    }

    public void newApple() {
	    //creates the random x and y coordinates for the apple
	    appleX = random.nextInt((int)(SCREEN_W/UNIT_SIZE)) * UNIT_SIZE;
	    appleY = random.nextInt((int)(SCREEN_H/UNIT_SIZE)) * UNIT_SIZE;
    }

    public void move() {
		//iterates through the body parts of the snake
		for (int i = bodyParts; i > 0; i--) {
			//shifts all coordinates in the array over by one spot
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		//switch statement for changing direction of snake
		switch (direction) {
			case 'U' -> y[0] = y[0] - UNIT_SIZE;
			case 'D' -> y[0] = y[0] + UNIT_SIZE;
			case 'L' -> x[0] = x[0] - UNIT_SIZE;
			case 'R' -> x[0] = x[0] + UNIT_SIZE;
		}
	}

    public void checkApple() {

    }

    public void checkCollision() {

    }

    public void gameOver(Graphics g) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
		//checks if game is running
		if(running) {
			move();
			checkApple();
			checkCollision();
		}
		repaint();
    }

    public class myKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

        }
    }
}
