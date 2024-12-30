package Elements.Solid.Unmovable;

import java.awt.Color;

import Elements.ElementType;
import Elements.Solid.Solid;

public abstract class UnmovableSolid extends Solid{

    public UnmovableSolid(int x, int y, ElementType elementType, Color color) {
        super(x, y, elementType, color);
    }

}
