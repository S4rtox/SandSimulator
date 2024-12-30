import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import Elements.Element;
import Elements.Misc.Empty;
import Elements.Solid.Movable.Sand;

public class GameGraphics extends JPanel{
    //Logica para el tileset?, tal vez tenga que checar esto.

    private GameEngine engine;
    private GameInputHandler inputHandler;

    private int currentFrames = 0;
    
    
    
    public GameGraphics(GameEngine engine, GameInputHandler handler){
        this.engine = engine;
        this.inputHandler = handler;
        this.setPreferredSize(new DimensionUIResource(engine.getScreenWidth(), engine.getScreenHeight()));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
        this.addKeyListener(inputHandler);
        this.addMouseListener(inputHandler);
        this.setFocusable(true);
    }
    
    
    public void update(){
        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;

        //Draw the pixels on screen.
        
        for(int i = 0; i < engine.getMaxScreenCol(); i++){
            for(int j = 0; j < engine.getMaxScreenRow(); j++){
                Element element = engine.getCellularGrid().getElement(i, j);
                if(element == null || element instanceof Empty) continue;
                g2.setColor(element.getColor());
                int yPosition = (engine.getMaxScreenRow() - 1 - j) * engine.getTileSize();
                g2.fillRect(i*engine.getTileSize(), yPosition, engine.getTileSize(), engine.getTileSize());
            }
        }

        
    




        //Show the current framerate

        g2.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(20f));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawString(currentFrames + "", 0,15);
        g2.drawString("Updating: " + engine.getCellularGrid().getToUpdate().size(), 50, 15);
        
        g2.drawString("Actions: ", 350, 15);

        g2.dispose();
    }
    
    
    public void setCurrentFrames(int currentFrames) {
        this.currentFrames = currentFrames;
    }

    @Override
    public Point getMousePosition(){
        Point point = super.getMousePosition();
        if(point == null) return null;
        point.setLocation(point.getX(),this.getHeight() - point.getY()); 
        return point;
    }
    
}    
