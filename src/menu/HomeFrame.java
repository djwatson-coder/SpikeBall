package menu;

import game.GamePanel;

import java.awt.*;
import javax.swing.*;

public class HomeFrame extends JFrame { // so we can treat the game.GameFrame as a JFrame

    HomePanel homePanel;

    public HomeFrame(){
        homePanel = new HomePanel();
        this.add(homePanel);
        this.setTitle("Spike Ball Home Page");
        this.setResizable(false);
        this.setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack(); // the JFrame adjusts according to the game frame (the panel)
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}

