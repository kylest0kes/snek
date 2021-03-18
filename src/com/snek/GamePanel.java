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
	char direction = 'D';
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
	    if (running) {
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
	    else {
	    	gameOver(g);
		}
		g.setColor(Color.RED);
		g.setFont(new Font("Courier", Font.PLAIN, 20));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Score: " + applesEaten, 0, g.getFont().getSize());
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
		if ((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts++;
			applesEaten++;
			newApple();
		}
    }

    public void checkCollision() {
		//checking if snake head collided with body
		for (int i = bodyParts; i > 0; i--) {
			if((x[0] == x[i]) && (y[0] == y[i])) {
				running = false;
			}
		}
		//checking if snake head collided with borders (L -> R -> T -> B)
		if (x[0] < 0 || x[0] > SCREEN_W || y[0] < 0 || y[0] > SCREEN_H) {
			running = false;
		}

		//stop timer if game not running
		if (!running) {
			timer.stop();
		}
    }

    public void gameOver(Graphics g) {
		//game over text
		g.setColor(Color.RED);
		g.setFont(new Font("Courier", Font.BOLD, 75));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Game Over :(", (SCREEN_W - metrics.stringWidth("Game Over :("))/2, SCREEN_H/2);

		//play again click option
//		g.setColor(Color.RED);
//		g.setFont(new Font("Courier", Font.BOLD, 55));
//		FontMetrics metrics1 = getFontMetrics(g.getFont());
//		g.drawString("Play Again?", (SCREEN_W - metrics1.stringWidth("Play Again?"))/2, (SCREEN_H/2) + 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
		//checks if game is running
		if(running) {
			move();
			checkApple();
			checkCollision();
		}
		//runs repaint for reset if game state is false
		repaint();
    }

    public class myKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
        	//switch for the different key presses to change snake direction
			switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					//limits player to 90deg turns so they don't run into themselves
					if (direction != 'R') {
						direction = 'L';
					}
					break;
				case KeyEvent.VK_RIGHT:
					//limits player to 90deg turns so they don't run into themselves
					if (direction != 'L') {
						direction = 'R';
					}
					break;
				case KeyEvent.VK_UP:
					//limits player to 90deg turns so they don't run into themselves
					if (direction != 'D') {
						direction = 'U';
					}
					break;
				case KeyEvent.VK_DOWN:
					//limits player to 90deg turns so they don't run into themselves
					if (direction != 'U') {
						direction = 'D';
					}
					break;
			}
        }
    }
}
