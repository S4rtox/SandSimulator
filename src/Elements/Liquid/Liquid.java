package Elements.Liquid;

import java.awt.Color;

import CellularGrid.CellularGrid;
import Elements.Element;
import Elements.ElementType;
import Elements.Updatable;
import Elements.Misc.Empty;
import Elements.Solid.Solid;

public abstract class Liquid extends Element implements Updatable{

    private boolean randomSlideDirection = false;

    public Liquid(int x, int y, ElementType elementType, Color color) {
        super(x, y, elementType,color);
    }

    @Override
    public void step(CellularGrid grid){
        Element below = grid.getElement(x, y-1);
        

        if(below instanceof Empty){
            grid.moveElement(this, x, y-1);  
        }else {
            int offset = -1 ;
            Element sideElement = grid.getElement(this.x + offset, y);


            if(sideElement instanceof Empty){
                grid.moveIfEmpty(this, this.x + offset, y);
            }else{
                randomSlideDirection = !randomSlideDirection;
            }


        }
        
    }

}
