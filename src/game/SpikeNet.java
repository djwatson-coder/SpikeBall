package game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

public class SpikeNet extends Rectangle {

    Random random;
    int diam;

    int angleRange = 2;

    SpikeNet(int x, int y, int diam){
        super(x,y,diam,diam);
        this.diam = diam;
        random = new Random();

    }

    public double bounce(double currentY){
        // returns a random multiplier to the ball Direction - changes the Y Direction but not the X
        int angles = 100;
        return (currentY + ((random.nextInt(angles) - (angles * 0.5))/(angles * 0.5)) * angleRange);

    }

    public void draw(Graphics g){

        try {
            BufferedImage img = ImageIO.read(new File("bin/SballNet.PNG"));
            int imageWidth = img.getWidth(null);
            int imageHeight = img.getHeight(null);
            g.drawImage(img, x - imageWidth/2 + diam/2, y - imageHeight/2 + diam/2, null);
        } catch (IOException e){
            System.out.println("oh no");
        }

        g.setColor(Color.black);
        g.fillOval(x, y, width, height);

    }



}