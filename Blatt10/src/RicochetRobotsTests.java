import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class RicochetRobotsTests {

    @ParameterizedTest
    @MethodSource("ricochetRobotsDataSource")
    @Timeout(1)
    public void test(RicochetRobotsData data){
        ByteArrayOutputStream sysOutStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(sysOutStream));

        String exp = data.expected();

        Board board = data.getBoard();
        PartialSolution sol = RicochetRobots.bfsWithHashing(board);
        if(exp == null){
            assertNull(sol);
        }else {
            RicochetRobots.printBoardSequence(board, sol.moveSequence());
            System.out.println("Found solution with " + sol.moveSequence().size() + " moves:\n" + sol);

            String act = sysOutStream.toString().replace("\r", "");

            assertEquals(exp, act);
        }
    }

    public static Stream<RicochetRobotsData> ricochetRobotsDataSource(){
        RicochetRobotsData[] rtn = new RicochetRobotsData[4];

        for(int i = 0; i < 4; i++){
            rtn[i] = new RicochetRobotsData("samples/rrBoard-sample0"+i+".txt", "samples/rrBoard-sample0"+i+"-output.txt");
        }

        return Stream.of(rtn);
    }

    public static class RicochetRobotsData extends BoardTest.BoardTestData {
        private String expectedFile;

        public RicochetRobotsData(String boardFile, String expectedFile){
            super(boardFile);
            this.expectedFile = expectedFile;
        }

        public String expected(){
            try {
                return new String(Files.readAllBytes(Paths.get(expectedFile)), StandardCharsets.UTF_8).replace("\r", "");
            } catch (IOException e) {
                return null;
            }
        }
    }
}
