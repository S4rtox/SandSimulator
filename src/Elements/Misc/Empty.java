package Elements.Misc;

import CellularGrid.CellularGrid;
import Elements.Element;
import Elements.ElementType;

public class Empty extends Element{

    public static Empty instance;

    private Empty(int x, int y) {
        super(x, y, ElementType.EMPTY, null);
    }

    //Epic singleton moment.
    public static Empty getInstance(){
        if(instance == null){
            instance = new Empty(-1, -1);
        }
        return instance;
    }

    @Override
    public void reactToOther(Element other, CellularGrid grid) {
        // TODO Auto-generated method stub
        return;
    }


    @Override
    public void step(CellularGrid grid) {}

    @Override
    public void setCoordinatesByMatrix(int x, int y) {
    }
    

 

}
