package Elements.Liquid;

import java.awt.Color;

import CellularGrid.CellularGrid;
import Elements.Element;
import Elements.ElementType;

public class Water extends Liquid{

    public Water(int x, int y) {
        super(x, y, ElementType.WATER, Color.cyan);
    }

    @Override
    public void reactToOther(Element other, CellularGrid grid) {
        return;
    }


}
