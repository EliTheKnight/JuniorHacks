import java.awt.event.KeyEvent;

public class Snake {

    private int length, x, y;
    private boolean left, right, up, down;
    Snake(){
        x = 360;
        y = 240;
        length = 5;
    }

    public void pressed(int keyCode){
        if(keyCode == KeyEvent.VK_A){
            left = true;
        }
        else if(keyCode == KeyEvent.VK_D){
            right = true;
        }
        else if(keyCode == KeyEvent.VK_W){
            up = true;
        }
        else if(keyCode == KeyEvent.VK_S){
            down = true;
        }
    }

    public void move(){
        if(left){

        }
    }


}
