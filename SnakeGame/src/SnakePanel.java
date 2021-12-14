import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakePanel extends JPanel {

    private Timer timer;
    private Snake snake;
    private int length;
    private boolean win, lose;

    public SnakePanel(int width, int height){
        setBounds(0, 0, width, height);
        snake = new Snake();
        length = 5;
        timer = new Timer(1000/60, e -> update());
        timer.start();
        win = false;
        lose = false;
        setupKeyListener();
    }

    public void update(){

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

    }

    public void setupKeyListener(){

        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }
            @Override
            public void keyReleased(KeyEvent e) {

            }

        });
    }

    public boolean getLose(){
        return lose;
    }

    public void setLose(boolean status){
        lose = status;
    }
}
