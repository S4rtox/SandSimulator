package Elements.Solid.Movable;

import java.awt.Color;

import CellularGrid.CellularGrid;
import Elements.Element;
import Elements.ElementType;

public class Sand extends MovableSolid{

    public Sand(int x, int y) {
        super(x, y, ElementType.SAND, Color.orange);
    }

    @Override
    public void reactToOther(Element other, CellularGrid grid) {
        // TODO Auto-generated method stub
        return;
    }


}
