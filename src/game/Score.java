package game;

import java.awt.*;

public class Score extends Rectangle{

    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player1;
    int player2;

    Score(int GAME_WIDTH, int GAME_HEIGHT){
        Score.GAME_WIDTH = GAME_WIDTH; // can apply to the class because it is static
        Score.GAME_HEIGHT = GAME_HEIGHT; // can apply to the class because it is static

    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Helvetica", Font.PLAIN, 70));

        // Mid Line
        String player1Score = String.valueOf(player1/10) + String.valueOf(player1%10);
        String player2Score = String.valueOf(player2/10) + String.valueOf(player2%10);
        g.drawString(player1Score, (GAME_WIDTH/2)-85, 50);
        g.drawString(player2Score, (GAME_WIDTH/2)+20, 50);


    }

}
