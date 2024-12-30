import CellularGrid.CellularGrid;
import CellularGrid.CellularGrid.PendingChange;
import Elements.ElementType;
import Elements.Solid.Movable.Sand;

public class GameEngine {

    private GameInputHandler inputHandler;

    private final int screenWidth = 1280;
    private final int screenHeight = 720;

  
    private final int tileSize = 4;
    
    private final int maxScreenCol = screenWidth/tileSize;

    private final int maxScreenRow =  screenHeight/tileSize;   


    private final CellularGrid cellularGrid;
  

    public CellularGrid getCellularGrid() {
        return cellularGrid;
    }

    public GameEngine(GameInputHandler inputHandler){
        this.inputHandler = inputHandler;
        cellularGrid = new CellularGrid( maxScreenCol,maxScreenRow); 
        cellularGrid.setElement(new Sand(50, 180), 50, 180);
    }

    public void update(double delta){
        cellularGrid.getPendingChanges().add(new PendingChange(new Sand(100, 100), 100, 100));    
        cellularGrid.process();
    }

    public int getMaxScreenCol() {
        return maxScreenCol;
    }

    public int getMaxScreenRow() {
        return maxScreenRow;
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

}
