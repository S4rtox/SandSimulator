package Elements.Solid.Unmovable;

import java.awt.Color;

import CellularGrid.CellularGrid;
import Elements.Element;
import Elements.ElementType;

public class Stone extends UnmovableSolid{

    public Stone(int x, int y) {
        super(x, y, ElementType.STONE, Color.gray);
    }

    @Override
    public void reactToOther(Element other, CellularGrid grid) {
        return;
    }

    @Override
    public void step(CellularGrid grid) {
        return;
    }

}
