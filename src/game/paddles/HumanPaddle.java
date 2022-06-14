package game.paddles;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class HumanPaddle extends Paddle{

    String imagePath = "bin/yellowPlayer.png";
    int startingWidth = 40;
    int startingHeight = 80;

    public HumanPaddle(int x, int y, int id) {
        super(x, y, id);
        this.speed = 10;
        createBody(this.id);
        this.width = startingWidth;
        this.height = startingHeight;
    }

    public void keyPressed(KeyEvent e){

        if(e.getKeyCode()==KeyEvent.VK_W){
                    setYDirection(-speed);
                    move();
        }
        if(e.getKeyCode()==KeyEvent.VK_S){
                    setYDirection(speed);
                    move();
        }
        if(e.getKeyCode()==KeyEvent.VK_A){
            setXDirection(-speed);
            move();
        }
        if(e.getKeyCode()==KeyEvent.VK_D){
            setXDirection(speed);
            move();
        }
        if(e.getKeyCode()==KeyEvent.VK_SPACE & serving){
                    serving = false;
        }

    }
    public void keyReleased(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_W) {
            setYDirection(0);
            move();
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            setYDirection(0);
            move();
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            setXDirection(0);
            move();
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            setXDirection(0);
            move();
        }
    }
    public void createBody(int id){
        try {
            this.body = ImageIO.read(new File(imagePath));
        } catch (IOException e){
            System.out.println("oh no");
        }

    }


}
