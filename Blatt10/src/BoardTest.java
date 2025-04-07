import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @ParameterizedTest
    @MethodSource("boardTestDataSameSource")
    public void testSame(BoardTestData data){
        assertEquals(data.getBoard().hashCode(), data.getBoard().hashCode());
        assertEquals(data.getBoard(), data.getBoard());

        Board mb = data.getBoard();
        mb.doMove(mb.validMoves().poll());
        //assertNotEquals(mb.hashCode(), data.getBoard().hashCode());
        assertNotEquals(mb, data.getBoard());
    }

    public static Stream<BoardTestData> boardTestDataSameSource(){
        BoardTestData[] rtn = new BoardTestData[4];

        for(int i = 0; i < 4; i++){
            rtn[i] = new BoardTestData("samples/rrBoard-sample0"+i+".txt");
        }

        return Stream.of(rtn);
    }

    public static class BoardTestData{
            private String boardFile;

            public BoardTestData(String boardFile){
                this.boardFile = boardFile;
            }

            public Board getBoard() {
                try {
                    return new Board(new Scanner(new FileInputStream(boardFile)));
                } catch (FileNotFoundException e) {
                    fail("Test author fucked up");
                    return null;
                }
            }
    }
}
