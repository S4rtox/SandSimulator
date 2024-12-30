package Elements.Gas;

import java.awt.Color;

import CellularGrid.CellularGrid;
import Elements.Element;
import Elements.ElementType;

public abstract class Gas extends Element{

    protected double dispersionRate;
    protected final double dispersionCoeficient;
    protected double dispersionMax;

    public Gas(int x, int y){
        this(x, y, 0.1);
    }

    public Gas(int x, int y, double dispersionRate) {
        super(x, y, ElementType.GAS, Color.LIGHT_GRAY);
        this.dispersionRate = dispersionRate;
        if(dispersionRate == 0) throw new RuntimeException("Dispersion rate cant be 0");
        dispersionCoeficient = Math.log(dispersionRate);
        dispersionMax = Math.log(10);
    }

    @Override
    public void reactToOther(Element other, CellularGrid grid){
        if(other.getElementType() == ElementType.GAS){
            if(other.getY() != this.y) return;
            if(Math.random() <= dispersionCoeficient/dispersionMax)
                grid.moveIfEmpty(other, (other.getX() > this.x ? other.getX() + 1 : other.getX() - 1), other.getY());
        }
    }

}
