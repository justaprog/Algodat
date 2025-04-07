import java.util.LinkedList;

/**
 * ParitialSolution provides at least the functionality which is required
 * for the use in searching for solutions of the game in a search tree.
 * It can store a game situation (Board) and a sequence of mooves.
 */
public class PartialSolution {

    /* TODO */
    /* Add object variables, constructors, methods as required and desired.      */
    private Board board;
    private LinkedList<Move> movese;

    public PartialSolution(Board board) {
        this.board = new Board(board);
        this.movese = new LinkedList<Move>();
    }
    public PartialSolution(PartialSolution that) {
        this.board = new Board(that.board);
        this.movese = new LinkedList<Move>(that.movese);
    }
    public void doMove(Move move) {
        board.doMove(move);
        movese.addLast(move);
    }
    public Board getBoard(){return board;};

    /**
     * Return the sequence of moves which resulted in this partial solution.
     *
     * @return The sequence of moves.
     */
    public LinkedList<Move> moveSequence() {
        /* TODO */
        return movese;
    }

    @Override
    public String toString() {
        String str = "";
        int lastRobot = -1;
        for (Move move : moveSequence()) {
            if (lastRobot == move.iRobot) {
                str += " -> " + move.endPosition;
            } else {
                if (lastRobot != -1) {
                    str += ", ";
                }
                str += "R" + move.iRobot + " -> " + move.endPosition;
            }
            lastRobot = move.iRobot;
        }
        return str;
    }
}

