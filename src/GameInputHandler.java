import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class GameInputHandler implements KeyListener, MouseListener {

    private List<MouseClickListener> mouseClickListeners = new ArrayList<>();
    private List<KeyTypedEvent> keyTypedListeners = new ArrayList<>();
    private boolean leftMousePressed = false, rightMousePressed = false, middleMousePressed = false;

   
    @Override
    public void keyTyped(KeyEvent e) {
        for(KeyTypedEvent listener : keyTypedListeners){
            listener.event(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
     }


    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            leftMousePressed = true;  
        }
        if(e.getButton() == MouseEvent.BUTTON2){
            rightMousePressed = true;
        }
        if(e.getButton() == MouseEvent.BUTTON3){
            middleMousePressed = true;
        }
        
     }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            leftMousePressed = false; 
        }
        if(e.getButton() == MouseEvent.BUTTON2){
            rightMousePressed = false;
        }
        if(e.getButton() == MouseEvent.BUTTON3){
            middleMousePressed = false;
        }
     
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
     }

    @Override
    public void mouseClicked(MouseEvent e) {
        for(MouseClickListener listener : mouseClickListeners){
            listener.onMouseClicked();
        }
    }

    public void addMouseClickListener(MouseClickListener listener){
        mouseClickListeners.add(listener);
    }

    public void removeMouseClickListener(MouseClickListener listener){
        mouseClickListeners.remove(listener);
    }

    public void addKeyTypedListener(KeyTypedEvent listener){
        keyTypedListeners.add(listener);
    }

    public void removeKeyTypedListener(KeyTypedEvent listener){
        keyTypedListeners.remove(listener);
    }
    

    public boolean isLeftMousePressed() {
        return leftMousePressed;
    }

    public boolean isRightMousePressed() {
        return rightMousePressed;
    }

    public boolean isMiddleMousePressed() {
        
        return middleMousePressed;
    }



    public interface KeyTypedEvent {
        public void event(KeyEvent event);
    }
}
