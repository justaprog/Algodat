/**
 * This class implements and evaluates game situations of a TicTacToe game.
 */
public class TicTacToe {

    /**
     * Returns an evaluation for player at the current board state.
     * Arbeitet nach dem Prinzip der Alphabeta-Suche. Works with the principle of Alpha-Beta-Pruning.
     *
     * @param board     current Board object for game situation
     * @param player    player who has a turn
     * @return          rating of game situation from player's point of view
    **/
    public static int alphaBeta(Board board, int player)
    {
        // TODO
        int alpha = -100;
        int beta = 100;
        return spielerMax(board,player,alpha,beta);
    }
    public static int spielerMax(Board board,int player,int alpha,int beta) {
        if (board.isGameWon()) {
            if (board.getwinner() == player){
                return board.nFreeFields() +1;
            }
            return -1 * board.nFreeFields() - 1;
        }
        if (board.nFreeFields() == 0) {
            return 0;
        }
        for (Position move : board.validMoves()) {
            board.doMove(move, player);
            int score = spielerMin(board, -1 * player, alpha, beta);
            board.undoMove(move);
            if (score > alpha) {
                alpha = score;
                if (alpha >= beta) {
                    break;
                }
            }
        }
        return alpha;
    }
    public static int spielerMin(Board board,int player,int alpha,int beta) {
        if(board.isGameWon()){
            if (board.getwinner() == player){
                return -1*board.nFreeFields() -1;
            }else {
                return board.nFreeFields() + 1;
            }
        }
        if(board.nFreeFields()==0){
            return 0;
        }
        for(Position move: board.validMoves()){
            board.doMove(move,player);
            int score = spielerMax(board,-1*player,alpha,beta);
            board.undoMove(move);
            if(score<beta){
                beta = score;
                if (beta<=alpha){
                    break;
                }
            }
        }
        return beta;
    }


    
    /**
     * Vividly prints a rating for each currently possible move out at System.out.
     * (from player's point of view)
     * Uses Alpha-Beta-Pruning to rate the possible moves.
     * formatting: See "Beispiel 1: Bewertung aller Zugmöglichkeiten" (Aufgabenblatt 4).
     *
     * @param board     current Board object for game situation
     * @param player    player who has a turn
    **/
    public static void evaluatePossibleMoves(Board board, int player)
    {
        // TODO
        if(player == 1){
            System.out.println("Evaluation for player 'x':");
        }else{
            System.out.println("Evaluation for player 'o':");
        }
        for (int i = 0;i< board.getN();i++){
            for (int j=0; j< board.getN();j++){
                Position pos = new Position(j,i);
                if(board.getField(pos) == 0){
                    board.doMove(pos, player);
                    System.out.print(spielerMin(board, -1 * player, -100, 100) + " ");
                    board.undoMove(pos);
                }else if(board.getField(pos) == 1){
                    System.out.print("x ");
                }else { System.out.print("o "); }
            }
            System.out.print("\n");
        }
    }
    public static void main(String[] args)
    {
        Board board = new Board(3);
        board.doMove(new Position(2,2),1);
        board.doMove(new Position(1,0),-1);
        board.print();
        TicTacToe.evaluatePossibleMoves(board,1);
        System.out.println(board.isGameWon());
        System.out.println(TicTacToe.alphaBeta(board,1));
        System.out.println("\n");
        System.out.println(TicTacToe.alphaBeta(board,-1));
    }
}

