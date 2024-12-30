package Elements;

import java.awt.Color;

import CellularGrid.CellularGrid;

public abstract class Element {

    protected int x;
    protected int y;
    private Color color;
    private ElementType elementType;

    public Element(int x, int y, ElementType elementType, Color color) {
        this.x = x;
        this.y = y;
        this.elementType = elementType;
        this.color = color;
    }

    public abstract void reactToOther(Element other, CellularGrid grid);

    public abstract void step(CellularGrid grid);


    public boolean corrode(Element cause,CellularGrid grid){
        return false;
    }

    public boolean burn(Element cause, CellularGrid grid){
        return false;
    }

    public Position getPosition(){
        return new Position(this.x, this.y);
    }

    public void setCoordinatesByMatrix(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setYbyMatrix(int y){
        this.y = y;
    }

    public void setXbyMatrix(int x){
        this.x = x;
    }


    public int getX() {
        return x;
    }



    public void setX(int x) {
        this.x = x;
    }



    public int getY() {
        return y;
    }



    public void setY(int y) {
        this.y = y;
    }

    public Color getColor(){
        return color;
    }


    public ElementType getElementType() {
        return elementType;
    }

    public record Position(int x, int y) {}
}
