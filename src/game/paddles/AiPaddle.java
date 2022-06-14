package game.paddles;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class AiPaddle extends Paddle {

    String imagePath = "bin/greenPlayer.png";
    int startingWidth = 60;
    int startingHeight = 120;

    public enum Difficulty {NOOB, EASY, MEDIUM, HARD, EXPERT}

    Difficulty difficulty;

    int moveSpeed = 1;
    int moveVolatility = 100;

    public AiPaddle(int x, int y, int id, Difficulty difficulty) {
        super(x, y, id);
        this.speed = 20;
        createBody(this.id);
        this.width = startingWidth;
        this.height = startingHeight;
        this.difficulty = difficulty;
        setStats();
    }

    public void createBody(int id){
        try {
            this.body = ImageIO.read(new File(imagePath));
        } catch (IOException e){
            System.out.println("oh no");
        }

    }

    public void setStats(){

        switch (this.difficulty) {
            case NOOB -> {
                this.speed = 1;
                this.moveVolatility = 100;
            }
            case EASY -> {
                this.speed = 3;
                this.moveVolatility = 50;
            }
            case MEDIUM -> {
                this.speed = 5;
                this.moveVolatility = 25;
            }
            case HARD -> {
                this.speed = 7;
                this.moveVolatility = 15;

            }
            case EXPERT -> {
                this.speed = 10;
                this.moveVolatility = 10;

            }
        }

    }

    public void setYDirection(int ballY){
        int paddleMiddle = this.y + (this.height/2);
        int yDiff = paddleMiddle - ballY ;

        if (yDiff > this.moveVolatility){
            yVelocity = -speed;
        }
        else if(yDiff < -this.moveVolatility){
            yVelocity = speed;
        }
        else{
            yVelocity = 0;
        }
    }

    public void move(int ballY){
        // Want the difference between the ballY and the middle of the paddle Y
        setYDirection(ballY);
        y = y + yVelocity;
        serve();
    }
    public void serve(){
        if (serving){
            serving = false;
        }
    }

}


// ToDo Make the AI only move when the ball is hit
