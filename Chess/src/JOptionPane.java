import javax.accessibility.Accessible;
import javax.swing.*;

public class JOptionPane extends JComponent implements Accessible {

    JFrame f;
    private String name;

    JOptionPane(){
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);

        Object[] options = {"Queen", "Rook", "Bishop", "Knight"};
        Object selectionObject = javax.swing.JOptionPane.showInputDialog(frame, "Choose", "Menu", javax.swing.JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        name = selectionObject.toString();

    }

    public String getName(){
        return name.toLowerCase();
    }

    public int getPiece(){
        if (name.equals("Queen"))
            return 5;
        if (name.equals("Rook"))
            return 4;
        if (name.equals("Bishop"))
            return 2;
        if (name.equals("Knight"))
            return 3;

        return 0;
    }

}
