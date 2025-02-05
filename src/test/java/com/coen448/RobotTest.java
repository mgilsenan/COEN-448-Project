
package com.coen448;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import com.coen448.Robot.Direction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RobotTest {

     Robot robot= new Robot();
    
     
     @BeforeAll
     public static void setupAll() {
         System.out.println("Should Print Before All Tests");
     }

     @Test
     @DisplayName("Should create initial information: i 10")
    void shouldCreateInit(){
         robot.init(10);    
         Assertions.assertFalse(robot.getPen_down());   // check pen inital state: false 
         assertEquals(Direction.NORTH,robot.facing_dir); 
         assertEquals(Arrays.toString(new int[]{0,0}), Arrays.toString(robot.location));
         assertArrayEquals(new int[10][10],robot.floor);
         assertTrue(robot.initialized);
     }

    @Test
    @DisplayName("Should check pen status")
    void shouldcheckPen(){
         robot.init(10);
         assertFalse(robot.getPen_down()); //pen is up
         
     }

    @Test
    @DisplayName("should turn left ,then change direction from inital")
    void checkturnLeft(){
         assertEquals(Direction.NORTH, robot.getFacing_dir()); //inital direction to north
         robot.turnLeft(); 
         assertEquals(Direction.WEST, robot.getFacing_dir());
         robot.turnLeft();
         assertEquals(Direction.SOUTH, robot.getFacing_dir());
         robot.turnLeft();
         assertEquals(Direction.EAST, robot.getFacing_dir());
         robot.turnLeft();
         robot.turnLeft();
         assertEquals(Direction.WEST, robot.getFacing_dir());
         robot.turnLeft();
         robot.turnLeft();
         robot.turnLeft();
         assertEquals(Direction.NORTH, robot.getFacing_dir());

     }



    @Test
    @DisplayName("should turn right ,then change direction from inital")
    void checkturnRight(){
         assertEquals(Direction.NORTH,robot.getFacing_dir()); 
         robot.turnRight();
         assertEquals(Direction.EAST, robot.getFacing_dir());
         robot.turnRight();
         assertEquals(Direction.SOUTH, robot.getFacing_dir());
         robot.turnRight();
         assertEquals(Direction.WEST, robot.getFacing_dir());
         robot.turnRight();
         robot.turnRight();
         assertEquals(Direction.EAST, robot.getFacing_dir());
         robot.turnRight();
         robot.turnRight();
         robot.turnRight();
         assertEquals(Direction.NORTH, robot.getFacing_dir());
     }


    @Test
    @DisplayName("should check movement, 2d array work with row first, so it will shows as y,x")
    void checkMove(){
         robot.init(10);
         robot.moveTo(5);
        
         if(Direction.NORTH==robot.getFacing_dir()){
         assertEquals(Arrays.toString(new int[]{5,0}),Arrays.toString(robot.getLocation())); // int[](y,x)
         };      

         robot.turnRight();
         robot.moveTo(2);
         if(Direction.EAST==robot.getFacing_dir()){
            assertEquals(Arrays.toString(new int[]{5,2}),Arrays.toString(robot.getLocation()));

            };  

         robot.moveTo(15);
         if(Direction.EAST==robot.getFacing_dir()){
            assertEquals(Arrays.toString(new int[]{5,2}),Arrays.toString(robot.getLocation()));
            };      
     }

    @Test
    @DisplayName("should check pen down, so the status is true")
    void checkPendown(){
         robot.init(10);
         assertTrue(!robot.getPen_down());

     }

    @Test
    @DisplayName("Test if print information command works as intended")
    void checkPrintInfo(){

        robot.init(10);
        assertEquals("Position: 0, 0 - Pen: Up - Facing: NORTH", robot.getInfo());

        robot.moveTo(1);
        assertEquals("Position: 0, 1 - Pen: Up - Facing: NORTH", robot.getInfo());
         
        robot.turnRight();
        assertEquals("Position: 0, 1 - Pen: Up - Facing: EAST", robot.getInfo());     
         
        robot.moveTo(2);
        robot.setPen_down(true);
        assertEquals("Position: 2, 1 - Pen: Down - Facing: EAST", robot.getInfo());

        robot.turnLeft();
        robot.moveTo(20);
        assertEquals("Position: 2, 1 - Pen: Down - Facing: NORTH", robot.getInfo()); //check board
     }

    @Test
    @DisplayName("Test if print floor command works as intended")
    void checkPrintFloor(){
        robot.init(10);

        String clean_result = "";
        int[][] clean_floor = new int[10][10];
        //Loop through flipped array to print with 0,0 at bottom right
        for(int i = clean_floor.length - 1; i >= 0; i--){
            for(int j = 0; j < clean_floor[i].length ; j++){
                if (j == 0)
                clean_result += Robot.text_purple + " (" + i + ") " + Robot.text_reset;
                if (clean_floor[i][j] == 1)
                clean_result += " * ";
                else
                clean_result += " - ";
            }
            clean_result += "\n";
        }

        clean_result += "     ";
        for(int i = 0; i < clean_floor.length; i++ )
            clean_result += Robot.text_purple + "(" + i + ")" + Robot.text_reset;

        assertEquals(clean_result, robot.getPrintedFloor());
        robot.setPen_down(true);
        robot.moveTo(1); 

        clean_result = "";
        clean_floor[0][0] = 1;
        clean_floor[1][0] = 1;
        //Loop through flipped array to print with 0,0 at bottom right
        for(int i = clean_floor.length - 1; i >= 0; i--){
            for(int j = 0; j < clean_floor[i].length ; j++){
                if (j == 0)
                clean_result += Robot.text_purple + " (" + i + ") " + Robot.text_reset;
                if (clean_floor[i][j] == 1)
                clean_result += " * ";
                else
                clean_result += " - ";
            }
            clean_result += "\n";
        }

        clean_result += "     ";
        for(int i = 0; i < clean_floor.length; i++ )
        clean_result += Robot.text_purple + "(" + i + ")" + Robot.text_reset;

        assertEquals(clean_result, robot.getPrintedFloor());
    }

    
   /* @Test
    @DisplayName("check path coverage1")
    void checkPathcoverage1(){
        robot.init(-1);
        
    }*/
    @Test
    @DisplayName("check path coverage1-2-3-4-5")
    void checkPathcoverage2(){
        robot.init(10);
        assertEquals(Direction.NORTH,robot.getFacing_dir()); 
         robot.turnRight();
         robot.moveTo(4);
         if(Direction.EAST==robot.getFacing_dir()){
            assertEquals(Arrays.toString(new int[]{0,4}),Arrays.toString(robot.getLocation()));

            }; 

    }
    @Test
    @DisplayName("check path coverage1-2-3-4")
    void checkPathcoverage3(){
        robot.init(10);
        assertEquals(Direction.NORTH,robot.getFacing_dir()); 
         robot.turnRight();
         robot.moveTo(5);
         if(Direction.EAST==robot.getFacing_dir()){
            assertEquals(Arrays.toString(new int[]{0,5}),Arrays.toString(robot.getLocation()));

            }; 

    }
}