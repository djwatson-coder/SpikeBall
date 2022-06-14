package game;

import game.paddles.AiPaddle;
import game.paddles.HumanPaddle;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{ // So it can run on a thread

    static final int GAME_WIDTH = 1000; // static so that multiple games use the same var. final is a bit
                                        // faster and we won't rename it. Make these UPPERCASE in the name.
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5556));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 60;
    static final int PADDLE_WIDTH = 40;
    static final int PADDLE_HEIGHT = 100;
    static final int SPIKENET_DIAMETER = 50;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    HumanPaddle paddleLeft;
    AiPaddle paddleRight;
    Ball ball;
    Score score;
    VisualStatistics visualStatistics;
    SpikeNet spikeNet;
    enum GameState {SERVING, PLAYING, FINISHED}
    GameState currentState;

    int winningScore = 3;

    Image img = Toolkit.getDefaultToolkit().getImage("bin/turf.jpg");

    public GamePanel(){
        currentState = GameState.SERVING;
        newPaddles();
        newBall();
        newSpikeNet();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        visualStatistics = new VisualStatistics(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        gameThread = new Thread(this); // pass in "this" since we are implementing the runnable interface
        gameThread.start();
    }
    public void newBall(){
        random = new Random();
        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),
                random.nextInt(GAME_HEIGHT-BALL_DIAMETER),
                BALL_DIAMETER);
    }
    public void newPaddles(){
        paddleLeft = new HumanPaddle(0, GAME_HEIGHT/2 - (PADDLE_HEIGHT/2),1); // puts it in the middle
        paddleRight = new AiPaddle(GAME_WIDTH - PADDLE_WIDTH, GAME_HEIGHT/2 - (PADDLE_HEIGHT/2), 2,
                AiPaddle.Difficulty.HARD);
    }
    public void newSpikeNet(){
        spikeNet = new SpikeNet((GAME_WIDTH/2) - (SPIKENET_DIAMETER/2),
                (GAME_HEIGHT/2) - (SPIKENET_DIAMETER/2),
                SPIKENET_DIAMETER);
    }

    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    public void draw(Graphics g){
        g.drawImage(img, 0, 0,GAME_WIDTH,GAME_HEIGHT, null);
        paddleLeft.draw(g);
        paddleRight.draw(g);
        spikeNet.draw(g);
        ball.draw(g);
        score.draw(g);
        visualStatistics.draw(g, ball, currentState);
    }
    public void move(){
        paddleLeft.move();
        paddleRight.move(ball.y + (BALL_DIAMETER/2));
        if (currentState == GameState.SERVING){
            if (paddleLeft.server){
                ball.hold(paddleLeft.x + PADDLE_WIDTH, paddleLeft.y + (PADDLE_HEIGHT/2) - (BALL_DIAMETER/2));
            } else{
                ball.hold(paddleRight.x - PADDLE_WIDTH, paddleRight.y + (PADDLE_HEIGHT/2) - (BALL_DIAMETER/2));
            }
        }
        else if (currentState == GameState.PLAYING){
            ball.move();
        }

    }
    public double[] spikeNetDistance(){

        double xDistance = (GAME_WIDTH * 0.5) - ball.x;
        double yDistance = (GAME_HEIGHT * 0.5) - ball.y;
        double xAngle = xDistance / Math.abs(xDistance);
        double yAngle = yDistance / Math.abs(xDistance);
        return new double[] {xAngle, yAngle};
    }

    public void checkCollision(){
        // stops the paddles from going over the window edges
        if(paddleLeft.y <=0){
            paddleLeft.y=0;
        }
        if(paddleLeft.y >=GAME_HEIGHT - PADDLE_HEIGHT){
            paddleLeft.y=GAME_HEIGHT- PADDLE_HEIGHT;
        }

        if(paddleRight.y <=0){
            paddleRight.y=0;
        }
        if(paddleRight.y >=GAME_HEIGHT - PADDLE_HEIGHT){
            paddleRight.y=GAME_HEIGHT- PADDLE_HEIGHT;
        }

        // Stops the game.Ball from going outside the window edges
        if(ball.y <=0){
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y >=GAME_HEIGHT-BALL_DIAMETER){
            ball.setYDirection(-ball.yVelocity);
        }

        // Checks if the paddle hits the ball
        if (ball.intersects(paddleLeft) | ball.intersects(paddleRight)) {
            ball.currentMovement = Ball.MovementType.HIT;
        }
        if (ball.currentMovement == Ball.MovementType.HIT & currentState != GameState.SERVING){
            double[] distances = spikeNetDistance();
            ball.setXDirection(distances[0]);
            ball.setYDirection(distances[1]);
            ball.currentMovement = Ball.MovementType.HIT;

        }
        if (ball.intersects(spikeNet) & ball.currentMovement == Ball.MovementType.HIT){
            double bounceAngle = spikeNet.bounce(ball.yVelocity);
            ball.setYDirection(bounceAngle);
            ball.currentMovement = Ball.MovementType.BOUNCED;

        }

        // Gives the player 1 points and gives new panels and ball
        if (ball.x <= 0){
            score.player2++;
            paddleLeft.server = false;
            paddleRight.server = true;
            newBall();
            reset_state();
            System.out.println("Player 2: " + score.player2);
        }
        if (ball.x >= GAME_WIDTH-BALL_DIAMETER){
            score.player1++;
            paddleLeft.server = true;
            paddleRight.server = false;
            newBall();
            reset_state();
            System.out.println("Player 1: " + score.player1);
        }

    }

    public void reset_state(){
        // Change the Server
        if (paddleLeft.server) {
            paddleLeft.serving = true;
        } else{
            paddleRight.serving = true;
        }
        currentState = GameState.SERVING;

        // Check if the Game is over
        if (score.player1 == winningScore | score.player2 == winningScore){
            currentState = GameState.FINISHED;
        }
    }

    public void check_service(){
        if (!paddleLeft.serving & !paddleRight.serving){
            currentState = GameState.PLAYING;
        }
    }

    public void run(){
        // Basic game loop
        long lastTime = System.nanoTime(); // ToDo: Check what long value is
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(currentState != GameState.FINISHED){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if (delta >= 1){
                move();
                check_service();
                checkCollision();
                repaint();
                delta--;
                // System.out.println("TEST");
            }
        }


    }

    public class AL extends KeyAdapter{

        public void keyPressed(KeyEvent e){
            paddleLeft.keyPressed(e);

        }

        public void keyReleased(KeyEvent e){
            paddleLeft.keyReleased(e);

        }

    }

}
