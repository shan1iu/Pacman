package controller;

import controller.state.GameContext;
import controller.state.PlayState;
import controller.state.SelectionState;
import model.GameData;
import model.SmallBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Firstly this is the panel and we initiliaze the board here.dodrawing() contains different cases with respect to the flags.
//Flags are about the game states, timer is working every 40msec and running the paintComponent(g) which is a common function of the ActionListener.
//

public class BoardInit extends JPanel implements ActionListener {
    private GameData gameData = new GameData();
    private Timer timer = new Timer(50, this);

    public BoardInit() {
        initBoard();
    }

    // getter and setter
    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    private void initBoard() {
        setFocusable(true);
        setBackground(Color.black);
        setDoubleBuffered(true);
        gameData.setData_state(GameData.gameStates.SELECTION);
        timer.start();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    /*
     * This method would run every 40ms(depends on timer).
     * */
    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        GameContext context = new GameContext();
        if (gameData.getData_state() == GameData.gameStates.SELECTION) {
            addKeyListener(SelectionAdapter);
            SelectionState selectionState = new SelectionState();
            context.setGs(selectionState);
            context.doAction(g2d);
        } else if (gameData.getData_state() == GameData.gameStates.PLAY) {
            removeKeyListener(SelectionAdapter);
            PlayState playState = new PlayState();
            context.setGs(playState);
            context.doAction(g2d, gameData);
        }
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    /*
     * This method is automatically generated by the actionlistener
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    KeyAdapter SelectionAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == 's' || key == 'S') {
//                System.out.println("=====Small Board is chosen=====");
                gameData.setData_state(GameData.gameStates.PLAY);
                gameData.setData_boardSize(GameData.boards.SMALL);
            } else if (key == 'm' || key == 'M') {
//                System.out.println("=====Medium Board is chosen=====");
                gameData.setData_state(GameData.gameStates.PLAY);
                gameData.setData_boardSize(GameData.boards.MEDIUM);
            } else if (key == 'l' || key == 'L') {
//                System.out.println("=====Large Board is chosen=====");
                gameData.setData_state(GameData.gameStates.PLAY);
                gameData.setData_boardSize(GameData.boards.LARGE);
            }
        }
    };


}


