package game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Ball extends Rectangle {

    Random random;
    double xVelocity = 0;
    double yVelocity = 0;

    int speed = 8;

    int diam;

    enum MovementType {HIT, BOUNCED}

    MovementType currentMovement;


    Ball(int x, int y, int diam){
        super(x,y,diam,diam);
        this.diam = diam;
        currentMovement = MovementType.HIT;
    }

    public void setXDirection(double velocity){
        xVelocity = velocity;
    }

    public void setYDirection(double velocity){
        yVelocity = velocity;
    }

    public void move(){

        x += xVelocity * speed;
        y += yVelocity * speed;
    }

    public void hold(int serverX, int serverY){
        x = serverX;
        y = serverY;
    }

    public void draw(Graphics g){
        // g.setColor(Color.yellow);
        // g.fillOval(x, y, width, height);
        try {
            BufferedImage img = ImageIO.read(new File("bin/SballBall.PNG"));
            g.drawImage(img, x, y,diam, diam, null);
        } catch (IOException e){
            System.out.println("oh no");
        }

    }

}
