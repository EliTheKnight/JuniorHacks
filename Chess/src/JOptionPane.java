import javax.accessibility.Accessible;
import javax.swing.*;

public class JOptionPane extends JComponent implements Accessible {

    JFrame f;
    private String name;

    JOptionPane(){
        //i solved my problem adding the following 2 lines of code...
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);

        Object[] options = {"Queen", "Rook", "Bishop", "Knight"};
        //...and passing `frame` instead of `null` as first parameter
        Object selectionObject = javax.swing.JOptionPane.showInputDialog(frame, "Choose", "Menu", javax.swing.JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        name = selectionObject.toString();

    }

    public String getName(){
        return name.toLowerCase();
    }

}
