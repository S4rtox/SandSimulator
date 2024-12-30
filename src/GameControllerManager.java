import java.awt.Point;

import javax.swing.JPanel;

import CellularGrid.CellularGrid.PendingChange;
import Elements.Element;
import Elements.ElementType;
import Elements.Liquid.Water;
import Elements.Solid.Movable.Sand;

public class GameControllerManager {
    private final GameEngine gameEngine;
    private final GameInputHandler inputHandler;
    private final GameGraphics currentScreen;

    private int currentMode = 0;
    public int getActionsNow() {
        return actionsNow;
    }









    public void setActionsNow(int actionsNow) {
        this.actionsNow = actionsNow;
    }


    private int actionsNow = 0;

    //0 = Sand   1 = Water
    


    public GameControllerManager(GameEngine gameEngine, GameInputHandler inputHandler, GameGraphics currentScreen) {
        this.gameEngine = gameEngine;
        this.inputHandler = inputHandler;
        this.currentScreen = currentScreen;
        inputHandler.addKeyTypedListener( (event) -> {
            if(getIntegerFromInput(event.getKeyChar()) != null){
                setControllerMode(getIntegerFromInput(event.getKeyChar()));
            }
        });
    }







    

    public void processInput(){
        if(inputHandler.isLeftMousePressed()){
            actionsNow++;
            Point mousePos = currentScreen.getMousePosition();
           
            if(mousePos == null) return;
            int tileSize = gameEngine.getTileSize();
            gameEngine.getCellularGrid().getPendingChanges().add(new PendingChange(getModeElement(currentMode,mousePos.x/tileSize, mousePos.y/tileSize),mousePos.x/tileSize,mousePos.y/tileSize));
        }
    }

    public void setControllerMode(int mode){
        this.currentMode = mode;
    }

    public static Integer getIntegerFromInput(char input) {
            // Verifica si el carácter es un dígito
            if (Character.isDigit(input)) {
                return Character.getNumericValue(input);
            }
        return null;
    }


    //Never ask how getInstance work
    //its dogshit
    //but it will work
    // please dont cause problems later

    //It caused problems later :c
    private Element getModeElement(int number, int x, int y){
            Element element;
            ElementType[] allElementTypes = ElementType.values();
            if(number < 0 || number >= allElementTypes.length){
                element = ElementType.SAND.getInstance(x, y);
            }else{
                element = allElementTypes[number].getInstance(x, y);
            }
            return element;
        }

}
