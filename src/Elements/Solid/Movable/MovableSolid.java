package Elements.Solid.Movable;


import java.awt.Color;

import CellularGrid.CellularGrid;
import Elements.Element;
import Elements.ElementType;
import Elements.Updatable;
import Elements.Liquid.Liquid;
import Elements.Misc.Empty;
import Elements.Solid.Solid;

public abstract class MovableSolid extends Solid implements Updatable{

    public MovableSolid(int x, int y, ElementType elementType, Color color) {
        super(x, y, elementType, color);
        
    }

    @Override
    public void step(CellularGrid grid){
        Element below = grid.getElement(x, y-1);
        

        if(below instanceof Empty){
            grid.moveElement(this, x, y-1);  
        }
        
        else if(below instanceof Solid){
            //Check diagonally
            Element belowRight = grid.getElement(x+1, y-1);
            Element belowLeft = grid.getElement(x-1, y-1);
            if (belowLeft instanceof Empty){
                grid.moveElement(this, x-1, y-1);
            }else if (belowRight instanceof Empty){
                grid.moveElement(this, x+1, y-1);
            }
        }

        else if (below instanceof Liquid){
            grid.interchangeElement(this, below);
        }
        
        
    }

}
