package com.coen448;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NewRobotTest {

    private final ByteArrayOutputStream ouput = new ByteArrayOutputStream();
    private final PrintStream firstOuput = System.out;


	@BeforeEach
    void setUp() {
        System.setOut(new PrintStream(ouput));
    }

    @AfterEach
    void tearDown() {
        System.setOut(firstOuput);
    }

	@Test
	void initializeFloorTest(){
        Robot robot= new Robot();

        robot.init(10);    
        
        assertArrayEquals(new int[10][10],robot.floor);

        assertTrue(robot.initialized);
	}
    
    @Test
	void canMoveForwardTest() {
        Robot robot= new Robot();

        robot.init(10); 

        robot.moveTo(5);

        assertEquals("Position: 0, 5 - Pen: Up - Facing: NORTH", robot.getInfo());

        robot.init(10);    
    }

    @Test
	void canMoveForwardFearthestNorthTest() {
        Robot robot= new Robot();

        robot.init(10);   
        
        robot.moveTo(10);

        String expected = "Cannot move that far in this direction";

		assertEquals(expected.strip(), ouput.toString().strip());
    }

    @Test
	void canMoveForwardFearthestEastTest() {
        Robot robot= new Robot();

        robot.init(10);   

        robot.turnRight();
        
        robot.moveTo(10);

        String expected = "Cannot move that far in this direction";

		assertEquals(expected.strip(), ouput.toString().strip());    
    }

    @Test
	void canMoveForwardFearthestSouthTest() {
        Robot robot= new Robot();

        robot.init(10);   

        robot.turnLeft();

        robot.turnLeft();
        
        robot.moveTo(10);

        String expected = "Cannot move that far in this direction";

		assertEquals(expected.strip(), ouput.toString().strip());  
    }

    @Test
	void canMoveForwardFearthestWestTest() {
        Robot robot= new Robot();

        robot.init(10);   

        robot.turnLeft();
        
        robot.moveTo(10);

        String expected = "Cannot move that far in this direction";

		assertEquals(expected.strip(), ouput.toString().strip());   
    }

    @Test
	void printFloorEmptyTest() {
        Robot robot= new Robot();

        robot.init(10);

        String result = "";

        int[][] floor = new int[10][10];
       
        for(int i = floor.length - 1; i >= 0; i--){
            for(int j = 0; j < floor[i].length ; j++){
                if (j == 0)
                result += Robot.text_purple + " (" + i + ") " + Robot.text_reset;
                if (floor[i][j] == 1)
                result += " * ";
                else
                result += " - ";
            }
            result += "\n";
        }

        result += "     ";
        for(int i = 0; i < floor.length; i++ )
            result += Robot.text_purple + "(" + i + ")" + Robot.text_reset;

        assertEquals(result, robot.getPrintedFloor());
    }

    @Test
	void printFloorWithShapeTest() {
        Robot robot= new Robot();

        robot.init(10);

        robot.setPen_down(true);

        robot.moveTo(1); 

        String result = "";

        int[][] floor = new int[10][10];
        floor[0][0] = 1;
        floor[1][0] = 1;
      
        for(int i = floor.length - 1; i >= 0; i--){
            for(int j = 0; j < floor[i].length ; j++){
                if (j == 0)
                result += Robot.text_purple + " (" + i + ") " + Robot.text_reset;
                if (floor[i][j] == 1)
                result += " * ";
                else
                result += " - ";
            }
            result += "\n";
        }

        result += "     ";
        for(int i = 0; i < floor.length; i++ )
        result += Robot.text_purple + "(" + i + ")" + Robot.text_reset;

        assertEquals(result, robot.getPrintedFloor());
    }


}
