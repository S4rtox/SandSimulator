import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) throws Exception {
        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Sand Simulation");
        
        
        GameInputHandler inputHandler = new GameInputHandler();
        GameEngine engine = new GameEngine(inputHandler);
        GameGraphics gamePanel = new GameGraphics(engine, inputHandler);
        GameControllerManager controllerManager = new GameControllerManager(engine, inputHandler, gamePanel);
        GameLoop gameLoop = new GameLoop(engine, gamePanel, inputHandler, controllerManager);

        
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gameLoop.start();
    }
}
