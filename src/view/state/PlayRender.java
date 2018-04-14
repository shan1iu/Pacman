/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.state;

import controller.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 * @author shan
 */
public class PlayRender extends RenderingState {

    @Override
    public void display(Graphics2D g2d, Game game) {
        BufferedImage img = null;
        BufferedImage g1 = null;
        BufferedImage g2 = null;
        BufferedImage g3 = null;
        BufferedImage cherry = null;
        BufferedImage star = null;
                try {
                    img = ImageIO.read(new File("pacman_1.png"));
                    g1 = ImageIO.read(new File("ghost1.png"));
                    g2 = ImageIO.read(new File("ghost2.png"));
                    g3 = ImageIO.read(new File("ghost3.png"));
                    cherry = ImageIO.read(new File("cherry.png"));
                    star = ImageIO.read(new File("star.png"));
                    
                } catch (IOException e) {
                    System.out.println("where is the image?");
                }
        for (int i = 0; i < game.getBoard().getStructure().length; i++) {
            for (int j = 0; j < game.getBoard().getStructure()[0].length; j++) {
                int factori = (i + 1) * 20;
                int factorj = (j + 1) * 20;
                
                switch (game.getBoard().getStructure()[i][j]) {
                    case '1': {
                        g2d.setColor(new Color(14, 38, 179));
                        g2d.fillRect(factori, factorj, 15, 15);
                    }
                    break;
                    case '0': {
                        g2d.setColor(new Color(202, 135, 131));
                        factori += 5;
                        factorj += 5;
                        g2d.fillOval(factori, factorj, 5, 5);
                        break;
                    }
                    case 'g': {
                        g2d.setColor(new Color(255, 49, 0));
                        switch (game.getData().getData_level().toString()){
                            case "LEVEL1": g2d.drawImage(g1, factori, factorj, 15, 15, null);
                                           break;
                            case "LEVEL2": g2d.drawImage(g2, factori, factorj, 15, 15, null);
                                           break;
                            case "LEVEL3": g2d.drawImage(g3, factori, factorj, 15, 15, null);
                                           break;
                                           
                        }
                        //g2d.fillOval(factori, factorj, 15, 15);
                        break;
                    }
                    case 'p': {
                        if (game.checkIfPacmanStateIsImmortal()) {
                            g2d.setColor(Color.WHITE);
                        } else {
                            g2d.setColor(new Color(255, 251, 0));
                        }
                        
                        g2d.drawImage(img, factori, factorj, 15, 15, null);
                        //g2d.fillOval(factori, factorj, 15, 15);
                        break;
                    }
                    // if the pacman eats dot, color changes to blank
                    case 'b': {
                        g2d.setColor(new Color(1, 1, 1));
                        g2d.fillOval(factori, factorj, 15, 15);
                        break;
                    }
                    case 's': {
                        g2d.setColor(Color.CYAN);
                        g2d.drawImage(cherry, factori, factorj, 15, 15, null);
                        //g2d.fillOval(factori, factorj, 15, 15);
                        break;
                    }
                    case 'i': {
                        g2d.setColor(Color.GREEN);
                        g2d.drawImage(star, factori, factorj, 15, 15, null);
                        //g2d.fillOval(factori, factorj, 15, 15);
                        break;
                    }
                }
            }
        }
    }

}
