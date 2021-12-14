import javax.swing.*;

public class ChessMain {
    public static void main(String[] args) {

        JFrame window = new JFrame("Chess");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, 768, 768 + 22); //(x, y, w, h) 22 due to title bar.

        ChessBoard panel = new ChessBoard(window.getWidth(), window.getHeight());

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);

        window.setVisible(true);
        window.setResizable(false);
    }
}