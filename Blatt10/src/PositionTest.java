import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class PositionTest {
    @Test
    public void testEqualTrue() {
        int num = 1000;
        ThreadLocalRandom tlr = ThreadLocalRandom.current();

        for (int i = 0; i < num; i++) {
            Position p1 = new Position(tlr.nextInt(), tlr.nextInt());
            assertEquals(p1, new Position(p1.x, p1.y));
        }
    }

    @Test
    public void testEqualFalse() {
        int num = 1000;
        ThreadLocalRandom tlr = ThreadLocalRandom.current();

        for (int i = 0; i < num; i++) {
            Position p1 = new Position(tlr.nextInt(), tlr.nextInt());
            assertNotEquals(p1, new Position(p1.x + 1, p1.y));
            assertNotEquals(p1, new Position(p1.x - 1, p1.y));
            assertNotEquals(p1, new Position(p1.x, p1.y + 1));
            assertNotEquals(p1, new Position(p1.x, p1.y - 1));

            assertNotEquals(p1, new Position(p1.x + 1, p1.y + 1));
            assertNotEquals(p1, new Position(p1.x - 1, p1.y + 1));
            assertNotEquals(p1, new Position(p1.x + 1, p1.y + 1));
            assertNotEquals(p1, new Position(p1.x + 1, p1.y - 1));

            assertNotEquals(p1, new Position(p1.x + 1, p1.y - 1));
            assertNotEquals(p1, new Position(p1.x - 1, p1.y - 1));
            assertNotEquals(p1, new Position(p1.x - 1, p1.y + 1));
            assertNotEquals(p1, new Position(p1.x - 1, p1.y - 1));
        }
    }

    @Test
    public void testEqualWrongType() {
        assertNotEquals(new Position(187, 187), "According to all known laws of aviation, there is no way a bee should be able to fly.");
    }


    @ParameterizedTest
    @MethodSource("posTestDataSource")
    public void testHashCode(PosTestData data) {
        if (data.getPos().hashCode() == data.hashCode()) {
            // this is ok
        } else {
            assertEquals(data.hash, data.getPos().hashCode(), "This is how i did it.");
        }

    }

    public static Stream<PosTestData> posTestDataSource() {
        return Stream.of(new PosTestData[]{
                new PosTestData(1, 1, 21),
                new PosTestData(0, 0, 0),
                new PosTestData(0, 5, 100),
                new PosTestData(5, 5, 105),
                new PosTestData(10, 0, 10),
        });
    }

    public static class PosTestData {
        private int x;
        private int y;
        public int hash;

        public PosTestData(int x, int y, int hash) {
            this.x = x;
            this.y = y;
            this.hash = hash;
        }

        public Position getPos() {
            return new Position(x, y);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
