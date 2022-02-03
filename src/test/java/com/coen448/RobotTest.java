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
            assertEquals(Arrays.toString(new int[]{5,9}),Arrays.toString(robot.getLocation()));
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
        String pen = "";

        if (robot.getPen_down()){
            pen = "Down";
        } else
            pen = "Up";

        robot.init(10);
        assertEquals("Position: 0, 0 - Pen: Up - Facing: NORTH", String.format("Position: %1$d, %2$d - Pen: %3$s - Facing: %4$s", robot.getLocation()[1], robot.getLocation()[0], pen, robot.getFacing_dir()));

        robot.moveTo(1);
        assertEquals("Position: 0, 1 - Pen: Up - Facing: NORTH", String.format("Position: %1$d, %2$d - Pen: %3$s - Facing: %4$s", robot.getLocation()[1], robot.getLocation()[0], pen, robot.getFacing_dir()));
         
        robot.turnRight();
        assertEquals("Position: 0, 1 - Pen: Up - Facing: EAST", String.format("Position: %1$d, %2$d - Pen: %3$s - Facing: %4$s", robot.getLocation()[1], robot.getLocation()[0], pen, robot.getFacing_dir()));
         
        
         
        robot.setPen_down(true);

        if (robot.getPen_down()){
            pen = "Down";
        } else
            pen = "Up";

        assertEquals("Position: 0, 1 - Pen: Down - Facing: EAST", String.format("Position: %1$d, %2$d - Pen: %3$s - Facing: %4$s", robot.getLocation()[1], robot.getLocation()[0], pen, robot.getFacing_dir()));
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
                clean_result += " (" + i + ") ";
                if (clean_floor[i][j] == 1)
                clean_result += " * ";
                else
                clean_result += " - ";
            }
            clean_result += "\n";
        }

        clean_result += "     ";
        for(int i = 0; i < clean_floor.length; i++ )
        clean_result += "(" + i + ")";

        String result = "";
        //Loop through flipped array to print with 0,0 at bottom right
        for(int i = robot.getFloor().length - 1; i >= 0; i--){
            for(int j = 0; j < robot.getFloor()[i].length ; j++){
                if (j == 0)
                    result += " (" + i + ") ";
                if (robot.getFloor()[i][j] == 1)
                    result += " * ";
                else
                    result += " - ";
            }
                result += "\n";
        }

        result += "     ";
        for(int i = 0; i < robot.getFloor().length; i++ )
        result += "(" + i + ")";

        assertEquals(clean_result, result);
        robot.setPen_down(true);
        robot.moveTo(1);        

        result = "";
        //Loop through flipped array to print with 0,0 at bottom right
        for(int i = robot.getFloor().length - 1; i >= 0; i--){
            for(int j = 0; j < robot.getFloor()[i].length ; j++){
                if (j == 0)
                    result += " (" + i + ") ";
                if (robot.getFloor()[i][j] == 1)
                    result += " * ";
                else
                    result += " - ";
            }
                result += "\n";
        }

        result += "     ";
        for(int i = 0; i < robot.getFloor().length; i++ )
        result += "(" + i + ")";

        clean_result = "";
        clean_floor[0][0] = 1;
        clean_floor[1][0] = 1;
        //Loop through flipped array to print with 0,0 at bottom right
        for(int i = clean_floor.length - 1; i >= 0; i--){
            for(int j = 0; j < clean_floor[i].length ; j++){
                if (j == 0)
                clean_result += " (" + i + ") ";
                if (clean_floor[i][j] == 1)
                clean_result += " * ";
                else
                clean_result += " - ";
            }
            clean_result += "\n";
        }

        clean_result += "     ";
        for(int i = 0; i < clean_floor.length; i++ )
        clean_result += "(" + i + ")";

        assertEquals(clean_result, result);
    }

}
