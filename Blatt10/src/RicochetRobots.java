import java.io.FileInputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class RicochetRobots {

    /**
     * Find the shortest move sequence for the given board situation to the goal state,
     * i.e., the designated robot has reached the target field.
     * The task is accomplished by using breadth-first-search. In order to avoid checking
     * the same situations over and over again, each investigated board is put in a hash set.
     *
     * @param board Initial configuration of the game.
     * @return The partial solution containing the the shortest move sequence to the target
     */
    public static PartialSolution bfsWithHashing(Board board) {
        /* TODO */
        PartialSolution esol = new PartialSolution(board);
        HashSet<Board> hset = new HashSet<Board>();
        LinkedList<PartialSolution> psol_queue = new LinkedList<PartialSolution>();
        psol_queue.add(esol);
        while (psol_queue.size() != 0){
            PartialSolution psol = psol_queue.remove();
            Board board_copy = psol.getBoard();
            if(board_copy.targetReached()){
                return psol;
            }
            if(!hset.contains(board_copy)){
                hset.add(board_copy);
                for (Move move: board_copy.validMoves()) {
                    PartialSolution psol_copy = new PartialSolution(psol);
                    psol_copy.doMove(move);
                    if(!hset.contains(psol_copy.getBoard())){
                        psol_queue.add(psol_copy);
                    }
                }
            }
        }
        return null;
    }

    public static void printBoardSequence(Board board, Iterable<Move> moveSequence) {
        int moveno = 0;
        for (Move move : moveSequence) {
            board.print();
            System.out.println((++moveno) + ". Move: " + move);
            board.doMove(move);
        }
        board.print();
    }

    public static void main(String[] args) throws java.io.FileNotFoundException {
//       System.setIn(new FileInputStream("samples/rrBoard-sample00.txt"));
//        System.setIn(new FileInputStream("samples/rrBoard-sample01.txt"));
//        System.setIn(new FileInputStream("samples/rrBoard-sample02.txt"));
        System.setIn(new FileInputStream("samples/rrBoard-sample03.txt"));
        Board board = new Board(new Scanner(System.in));
        long start = System.nanoTime();
        PartialSolution sol = bfsWithHashing(board);
        long duration1 = (System.nanoTime() - start) / 1000;
        if (sol == null) {
            System.out.println("Board is unsolvable.");
        } else {
            printBoardSequence(board, sol.moveSequence());
            System.out.println("Found solution with " + sol.moveSequence().size() + " moves:\n" + sol);
            System.out.println("Computing time: " + duration1 / 1000 + " ms");
        }
    }
}

