public class GameLoop implements Runnable{

    private GameEngine engine;
    private GameInputHandler inputHandler;
    private GameGraphics graphics;
    private GameControllerManager controllerManager;
    private boolean isRunning = false;

    
    private int maxFrames = 60;
    
    public GameLoop(GameEngine engine, GameGraphics graphics, GameInputHandler inputHandler, GameControllerManager controllerManager) {
        this.engine = engine;
        this.graphics = graphics;
        this.inputHandler = inputHandler;
        this.controllerManager = controllerManager;
    }
    
    public void start(){
        isRunning = true;
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double secondsForFrames = 1_000_000_000 / maxFrames;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / secondsForFrames;
            lastTime = now;
            while(delta >= 1) {
                //Stupid logic here
                engine.update(delta);
                controllerManager.processInput();
                graphics.update();
                delta--;
                frames++;
                
                if(System.currentTimeMillis() - timer > 1000) {
                    graphics.setCurrentFrames(frames);
                    timer += 1000;
                    frames = 0;
                    
                }
            }   
        }        
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
    
    public boolean isRunning() {
        return isRunning;
    }
    
}
