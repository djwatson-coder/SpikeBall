package game;// This is the Frame around the panel

import game.GamePanel;
import menu.HomePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameFrame extends JFrame { // so we can treat the game.GameFrame as a JFrame

    GamePanel gamePanel;

    BufferedImage img;

    public GameFrame(){
        gamePanel = new GamePanel();
        this.add(gamePanel);
        this.setTitle("Spike Ball Game");
        this.setResizable(false);
        this.setBackground(Color.GREEN);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack(); // the JFrame adjusts according to the game frame (the panel)
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }


}
