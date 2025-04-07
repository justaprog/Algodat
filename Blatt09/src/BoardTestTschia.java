import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
//Danke an die Tutoren Lukas, Jakob und Alexander, die mich dazu ermutigt haben!
class BoardTestTschia {
    void setBoard(Board board, int[][] toCopy){
        Position myPos = new Position(0,0);
        for(int i=0;i<toCopy.length;++i){
            for(int j=0;j<toCopy[i].length;++j){
                myPos.x = j;
                myPos.y = i;
                board.setField(myPos, toCopy[i][j]);
            }
        }
    }
    @Test
    void manhattan() {
        Board myBoard;
        int[][] data;

        //Test 1
        myBoard = new Board(5,2);
        data = new int[][]{
                {5, 7, 3, 1, 2}, {8, 4, 6, 9, 0}
        };
        setBoard(myBoard, data);
        assertEquals(18, myBoard.manhattan());

        //Test 2
        myBoard = new Board(13,7);
        data = new int[][]{
                {65,7,56,74,83,28,52,61,57,68,6,21,36},
                {3,2,79,89,84,41,35,15,30,53,44,13,88},
                {49,40,31,86,85,33,78,58,34,27,20,10,23},
                {66,45,75,19,22,4,16,67,39,87,46,29,5},
                {47,59,62,17,69,82,80,81,73,43,51,25,24},
                {38,12,64,63,77,32,72,26,90,42,0,55,1},
                {50,37,70,48,18,9,54,8,76,11,60,14,71}
        };
        setBoard(myBoard, data);
        assertEquals(591, myBoard.manhattan());

        //test 3
        myBoard = new Board(12,5);
        data = new int[][]{
                {2,49,50,16,36,53,37,51,45,22,39,42},
                {12,59,47,25,6,41,52,27,7,8,15,54},
                {26,40,11,55,24,13,38,35,48,5,21,4},
                {9,32,18,3,19,56,31,28,17,44,43,30},
                {14,1,34,58,46,10,0,29,33,57,20,23},
        };
        setBoard(myBoard, data);
        assertEquals(327, myBoard.manhattan());

        //test 4
        myBoard = new Board(4,8);
        data = new int[][]{
                {18,29,11,28},
                {9,8,10,25},
                {27,0,15,14},
                {26,16,24,1},
                {4,6,22,13},
                {2,17,20,19},
                {21,30,5,12},
                {7,3,31,23},
        };
        setBoard(myBoard, data);
        assertEquals(119, myBoard.manhattan());

        //test 5
        myBoard = new Board(14,3);
        data = new int[][]{
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14},
                {15,16,17,18,19,20,21,22,23,24,25,26,27,28},
                {29,30,31,32,33,34,35,36,37,38,39,40,41,0},
        };
        setBoard(myBoard, data);
        assertEquals(0, myBoard.manhattan());
    }


}