package game.paddles;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Paddle extends Rectangle{

    int id;
    int yVelocity;
    int xVelocity;
    int speed;

    int power;
    public Boolean server = false;
    public Boolean serving = false;
    BufferedImage body;

    public Paddle(int x, int y, int id){
        super(x, y, 0, 0);
        this.id = id;
        if (this.id == 1){
            server = true;
            serving = true;
        }


    }
    public void createBody(int id) {
    }
    public void setYDirection(int yDirection){
        yVelocity = yDirection;
    }

    public void setXDirection(int xDirection){
        xVelocity = xDirection;
    }
    public void move(){
        y = y + yVelocity;
        x = x + xVelocity;
    }
    public void draw(Graphics g){
        g.drawImage(body,x,y,width,height, null);

    }

}
