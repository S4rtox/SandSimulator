package CellularGrid;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import Elements.Element;
import Elements.ElementType;
import Elements.Updatable;
import Elements.Element.Position;
import Elements.Misc.Empty;

//TODO: Reformat everything, with the things I've learned.


public class CellularGrid {

    private Element[][] cell;

    private Element[][] updateCell;

    //Whats to be updated
    private final Set<Element> toUpdate = new HashSet<>();
    //Whats to be updated, but added at the end of each stuff
    private final Set<Element> nextToUpdate = new HashSet<>();
    private final Set<Element> nextToRemove = new HashSet<>();

    private final List<PendingChange> pendingChanges = new ArrayList<>();
    

    public List<PendingChange> getPendingChanges() {
        return pendingChanges;
    }
    

    public Set<Element> getToUpdate() {
        return toUpdate;
    }




    public CellularGrid(int width, int height){
        this.cell = new Element[width][height];
        this.updateCell = new Element[width][height];
        for (int x = 0; x < cell.length; x++) {
            for (int y = 0; y < cell[x].length; y++) {
                cell[x][y] = Empty.getInstance();
            }
        }
    }

    //TODO: so shocker, water doesnt fucking work. ALso sand is wonky, but its a start.
    public void process(){
        
        applyPendingChanges();
        copyGridToUpdateGrid();
        var cellDebugString = getDebugString(cell);
        var updateCellDebugString = getDebugString(updateCell);
        
        //System.out.println("Cell: " + cellDebugString);
       // System.out.println("UpdateCell: " + updateCellDebugString);
        for(Element element : toUpdate){
            
            int x = element.getX();
            int y = element.getY();

            element.reactToOther(getElement(x+1, y), this);
            element.reactToOther(getElement(x-1, y), this);
            element.reactToOther(getElement(x, y+1), this);
            element.reactToOther(getElement(x, y-1), this);
            element.step(this);
        }
        

       /*  var cellDebugString = getDebugString(cell);
        var updateCellDebugString = getDebugString(updateCell);
        
        System.out.println("Cell: " + cellDebugString);
        System.out.println("UpdateCell: " + updateCellDebugString); */
        toUpdate.addAll(nextToUpdate); //Set it to the nextToUpdate
        toUpdate.removeAll(nextToRemove); //Remove the nextToRemove
        nextToUpdate.clear(); //Clear the nextToUpdate
        nextToRemove.clear(); //Clear the nextToRemove


        Element[][] tempCell = cell;
        cell = updateCell;
        updateCell = tempCell;


    }

    private void applyPendingChanges(){
        for (PendingChange pendingChange : pendingChanges) {

            var element = pendingChange.element;
            var x = pendingChange.x;
            var y = pendingChange.y;

            if(!isInBounds(x, y)) continue;
            if(element == null) element = Empty.getInstance();        
            
        
            if(isReplacing(x, y)) {
                var elementToReplace = getElement(x, y);
                if(elementToReplace instanceof Updatable){
                    toUpdate.remove(elementToReplace);
                }
            }  

            cell[x][y] = element;
            if(!(element instanceof Empty)){
                element.setCoordinatesByMatrix(x, y);
                if(element instanceof Updatable){
                    toUpdate.add(element);
                }
            }
        }
        pendingChanges.clear();
    }

    //Method for getting a debug string of the grid, empty cells arent shown. Sand cells are represented by S(coordinates)
    public String getDebugString(Element[][] cell){
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < cell.length; x++) {
            for (int y = 0; y < cell[x].length; y++) {
                if(cell[x][y] instanceof Empty) continue;
                sb.append(cell[x][y].getElementType().toString().charAt(0)).append("(").append(x).append(",").append(y).append(") ");
            }
        }
        return sb.toString();
    }

    private void copyGridToUpdateGrid() {
        for (int x = 0; x < cell.length; x++) {
            System.arraycopy(cell[x], 0, updateCell[x], 0, cell[x].length);  
        }
    }

    //!! If you change this, make sure to change the other one too. (applyPendingChanges) - Thanks spaghetti code.
    public boolean setElement(Element element, int x, int y){
        if(!isInBounds(x, y)) return false;
        if(element == null) element = Empty.getInstance();        //If element is null, we are deleting? - Debatable.
        //if(isReplacingUpdate(x,y)) return; //Shouldnt need this yet. little confused for if its needed or not. 
        
        if(isReplacing(x, y)) {
            var elementToReplace = getElement(x, y);
            if(elementToReplace instanceof Updatable){
                nextToRemove.add(elementToReplace);
            }
        }  

        updateCell[x][y] = element;
        if(!(element instanceof Empty)){
            //Shouldnt
            element.setCoordinatesByMatrix(x, y);
            if(element instanceof Updatable){
                if(nextToRemove.contains(element)){
                    nextToRemove.remove(element);
                }   
                nextToUpdate.add(element);
            }
        }
        return true;
    }


    


    private boolean isReplacing(int x, int y){
        if(!isInBounds(x, y)) return false;
        if(!(getElement(x, y) instanceof Empty)) return true;
       return false;
    }

    private boolean isReplacingUpdate(int x, int y){
        if(!isInBounds(x, y)) return false;
        if(!(getElementBuffer(x, y) instanceof Empty)) return true;
       return false;
    }


    public Element getElement(int x, int y) {
        if(!isInBounds(x,y)) return null;
        return cell[x][y];
    }

    public Element getElementBuffer(int x, int y){
        if(!isInBounds(x,y)) return null;
        return updateCell[x][y];
    }           



    public void moveIfEmpty(Element toMove, int x, int y){
        if(getElement(x, y).getElementType() == ElementType.EMPTY){
            moveElement(toMove, x, y);
        }
    }
    
    public void moveElement(Element toMove, int x, int y){
        moveElement(toMove.getX(), toMove.getY(), x, y);
    }

    //TODO: Fix following issue
    /* 

     ALSO replacements may cause the deletion of a movable element in the update queue. Given that there is a moment there are two instances of that same one
     shit happens, there are ways to fix this, but not sure how to - Order may work? inverting the order?(it deletes the last one, and re adds it again.)
     Also using the instance instead of the coordinates for getting it?, might solve a lot of issues. But monkey brain doesnt want another method.
     THIS SHOULD ONLY HAPPEN WHEN ADDING A NEW ONE, related to before issue. <---- Should be solved, but keeping this to remidn me in case.

     Concurrent Modification WILL happen while doing this, given that im updating the Uptate set while doing this. Need to change to a different way of doing this.
     Ask chatgpt if using iterators might cause a update to skip. Not sure of this.
     
    */

    public void moveElement(int previousX, int previousY, int x, int y){
        
        setElement(Empty.getInstance(), previousX, previousY);
        setElement(cell[previousX][previousY], x, y);
    }


    //Pretty important to not fuck this up, order is important.
    //TODO: need a more permanent solution for this order stuff. Do it later lmao
    public void interchangeElement(int previousX, int previousY, int x, int y){

        Element element1 = cell[x][y];
        Element element2 = cell[previousX][previousY];
        
        setElement(null, x, y);
        setElement(null, previousX, previousY);
        setElement(element2, x, y);
        setElement(element1, previousX, previousY);
    }

    //Use 
    public void interchangeElement(Element toMove, int x, int y){
        interchangeElement(toMove.getX(), toMove.getY(), x, y);
    }


    public void interchangeElement(Element toMove, Element changedElement){
        interchangeElement(toMove, changedElement.getX(), changedElement.getY());
    }


    

    public boolean isInBounds(int x, int y){
          return x >= 0 && x < cell.length && y >= 0 && y < cell[0].length;
    }
    

    public record PendingChange(Element element, int x, int y) {
    }

}
