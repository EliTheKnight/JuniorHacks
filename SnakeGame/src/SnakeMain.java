import javax.swing.*;

public class SnakeMain {
        public static void main(String[] args) {

            JFrame window = new JFrame("Space Invaders");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setBounds(0, 0, 720, 720 + 22); //(x, y, w, h) 22 due to title bar.

            SnakePanel panel = new SnakePanel(window.getWidth(), window.getHeight());

            panel.setFocusable(true);
            panel.grabFocus();

            window.add(panel);

            window.setVisible(true);
            window.setResizable(false);
        }
    }
