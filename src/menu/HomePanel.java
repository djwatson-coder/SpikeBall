// Home Screen for the spikeball game

package menu;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class HomePanel extends JPanel {

    Image image;
    Graphics graphics;

    JButton startGameButton;

    public HomePanel(){
        startGameButton = createButton("START NEW GAME", 50,100,95,30);


    }

    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    public void draw(Graphics g){
//        paddle1.draw(g);
//        paddle2.draw(g);
//        spikeNet.draw(g);
//        ball.draw(g);
//        score.draw(g);
    }

    public JButton createButton(String text, int x, int y, int width, int height){
        JButton button = new JButton(text);
        button.setBounds(x,y,width,height);

        return button;
    }

}
