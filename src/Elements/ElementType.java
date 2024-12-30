package Elements;

import java.awt.Color;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import Elements.Gas.Gas;
import Elements.Liquid.Water;
import Elements.Misc.Empty;
import Elements.Solid.Movable.Sand;
import Elements.Solid.Unmovable.Stone;



public enum ElementType {
    EMPTY(Empty.class),
    SAND(Sand.class),
    WATER(Water.class),
    GAS(Gas.class),
    STONE(Stone.class);
    

    private final Class<? extends Element> ClassType;
    
    private ElementType(Class<? extends Element> classType){
        this.ClassType = classType;
    }




    //TODO: this shit doesnt work for gasses, for some reason.
    //This shit is so scuffed lmao
    public Element getInstance(int x, int y){
        if(this.equals(ElementType.EMPTY)){
            return Empty.getInstance();
        }

        
        try {
            Constructor<? extends Element> constructor = ClassType.getConstructor(int.class, int.class);
            return constructor.newInstance(x, y);
        } catch (SecurityException  | InstantiationException  | NoSuchMethodException| IllegalAccessException | InvocationTargetException  e) {
            System.out.println(" This shit got fucked by trying to instantiate class ");
            e.printStackTrace();
        }
        return Empty.getInstance();
    }

}
