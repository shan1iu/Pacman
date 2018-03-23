/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.io.*;
/**
 *
 * @author shan
 */
public class SmallBoard extends Board {

    @Override
    public void setParameters() {
        super.setNumberOfColumns(24);
        super.setNumberOfRows(24);
        super.setNumberOfFruits(3);
        super.setNumberOfGhosts(4);
        
        
        
        super.setStructure(ImportStructureMap("map24.txt"));
    }
    
    private char[][] ImportStructureMap(String filename){
        char[][] Structure = new char[24][24];
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            int row=0,column=0;
            String line;
            while((line=reader.readLine())!= null){
                for(column=0;column<Structure.length;column++){
                    Structure[row][column]=line.charAt(column);
                  
                }
                
                row+=1;
            }
        }catch(IOException e){
            System.out.println("Error locating the file containing map structure.");
            System.exit(0);
        }
        return Structure;
    }
}


