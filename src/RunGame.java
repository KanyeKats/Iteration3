import Controllers.MenuViewController;
import Core.Display;
import Core.InputDispatcher;
import Core.State;
import Core.StateManager;
import Models.Menu.*;
import Utilities.Constants;
import Views.StartMenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunGame extends JFrame implements ActionListener{

    // Core components.
    private Display display;
    private InputDispatcher inputDispatcher;
    private StateManager stateManager;

    // Game timer.
    private Timer gameTimer;

    public RunGame(){

        // Initialize the game.
        createCoreComponents();
        createTimer();
        initFrame();
        setupEventListeners();
        startFirstState();
        startGame();
    }

    private void createCoreComponents(){

        // Create the core components.
        display = new Display(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        inputDispatcher = new InputDispatcher();
        stateManager = new StateManager(display, inputDispatcher);
    }

    private void createTimer(){

        // Create the timer.
        int delay = 1000 / Constants.FRAME_RATE;
        gameTimer = new Timer(delay, this);
    }

    private void initFrame(){

        // Add the display to the JFrame
        add(display);
        pack();

        // Set options.
        setTitle("Kanye Kats");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupEventListeners(){

        // Setup key event listener.
        display.addKeyListener(inputDispatcher);

        // Setup mouse event listeners.
        addMouseListener(inputDispatcher);
        addMouseMotionListener(inputDispatcher);
    }

    private void startFirstState(){

        // Setup the first state (the start menu)
        Models.Menu.Menu startMenu = Models.Menu.Menu.createStartMenu(stateManager);
        MenuViewController startMenuController = new MenuViewController(stateManager, startMenu);
        StartMenuView startMenuView = new StartMenuView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, startMenu);
        stateManager.setActiveState(new State(startMenuController, startMenuView));
    }

    private void startGame(){

        // Start the timer!
        gameTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        stateManager.tick();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                RunGame game = new RunGame();
                game.setVisible(true);
            }
        });
    }
}
