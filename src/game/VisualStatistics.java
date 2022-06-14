package game;

import java.awt.*;

public class VisualStatistics extends Rectangle {

    static int GAME_WIDTH;
    static int GAME_HEIGHT;

    VisualStatistics(int width, int height){
        GAME_WIDTH = width;
        GAME_HEIGHT = height;
    }

    public void draw(Graphics g, Ball ball, GamePanel.GameState state){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Helvetica", Font.PLAIN, 25));

        // Mid Line
        String ballSpeed = String.valueOf(ball.speed);
        String xSpeed = String.valueOf(ball.xVelocity);
        String ySpeed = String.valueOf(ball.yVelocity);
        g.drawString(ballSpeed, (2 * GAME_WIDTH/3), 20);
        g.drawString(xSpeed, (2 * GAME_WIDTH/3), 50);
        g.drawString(ySpeed, (2 * GAME_WIDTH/3), 80);
        g.drawString(state.name(), (2 * GAME_WIDTH/3), 120);


    }

}
