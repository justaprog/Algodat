import java.util.InputMismatchException;
import java.util.Stack;

import static java.lang.Math.abs;
/**
 * This class represents a generic TicTacToe game board.
 */
public class Board {
    private int n;
    //TODO
    private int freie_felder;
    private int[][] pos_board;
    private Position letzten_zug;
    private int winner;
    /**
     *  Creates Board object, am game board of size n * n with 1<=n<=10.
     */
    public Board(int n)
    {
        // TODO
        if(n<1 || n>10){
            throw new InputMismatchException();
        }
        this.n = n;
        this.freie_felder = n*n;
        pos_board = new int[n][n];
        for (int i = 0;i<n;i++){
            for (int j=0; j<n;j++){
                pos_board[i][j] = 0;
            }
        }
        letzten_zug = new Position(0,0);
        winner = 0;
    }
    
    /**
     *  @return     length/width of the Board object
     */
    public int getN() { return n; }
    public int getwinner() { return this.winner;}
    
    /**
     *  @return     number of currently free fields
     */
    public int nFreeFields() {
        // TODO
        return freie_felder;
    }
    
    /**
     *  @return     token at position pos
     */
    public int getField(Position pos) throws InputMismatchException
    {
        // TODO
        if(pos.x<0 || pos.x>n-1 || pos.y>n-1 || pos.y<0){
            throw new InputMismatchException();
        }
        return pos_board[pos.y][pos.x];
    }

    /**
     *  Sets the specified token at Position pos.
     */    
    public void setField(Position pos, int token) throws InputMismatchException
    {
        // TODO
        if(token>1 || token<-1 ||pos.x>n-1 || pos.y>n-1||pos.x<0||pos.y<0){
            throw new InputMismatchException();
        }
        if(pos_board[pos.y][pos.x] == 0 && token !=0 ){
            freie_felder--;
        }else if(pos_board[pos.y][pos.x] != 0 && token ==0){
            freie_felder++;
        }
        pos_board[pos.y][pos.x] = token;
    }
    
    /**
     *  Places the token of a player at Position pos.
     */
    public void doMove(Position pos, int player)
    {
        // TODO
        if(getField(pos) != 0){
            throw new InputMismatchException();
        }
        setField(pos,player);
        letzten_zug.x = pos.x;
        letzten_zug.y= pos.y;
    }

    /**
     *  Clears board at Position pos.
     */
    public void undoMove(Position pos)
    {
        // TODO
        if (getField(pos) == 0){
            throw new InputMismatchException();
        }
        setField(pos,0);
    }
    
    /**
     *  @return     true if game is won, false if not
     */
    public boolean isGameWon() {
        // TODO
        //horizontal
        if(getField(letzten_zug) !=0) {
            for (int i = 0; i < n; i++) {
                if (pos_board[letzten_zug.y][i] != getField(letzten_zug)) {
                    break;
                }
                if (i == n - 1) {
                    winner = getField(letzten_zug);
                    return true;
                }
            }
            //vertikal
            for (int i = 0; i < n; i++) {
                if (pos_board[i][letzten_zug.x] != getField(letzten_zug)) {
                    break;
                }
                if (i == n - 1) {
                    winner = getField(letzten_zug);
                    return true;
                }
            }
            //diagonal 1
            if (letzten_zug.x == letzten_zug.y) {
                for (int i = 0; i < n; i++) {
                    if (pos_board[i][i] != getField(letzten_zug))
                        break;
                    if (i == n - 1) {
                        winner = getField(letzten_zug);
                        return true;
                    }
                }
            }
            //diagonal 2
            if (letzten_zug.x + letzten_zug.y == n - 1) {
                for (int i = 0; i < n; i++) {
                    if (pos_board[n - 1 - i][i] != getField(letzten_zug))
                        break;
                    if (i == n - 1) {
                        winner = getField(letzten_zug);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     *  @return     set of all free fields as some Iterable object
     */
    public Iterable<Position> validMoves()
    {
        // TODO
        Stack<Position> pos_list = new Stack<>();
        for (int i = 0;i<n;i++){
            for (int j=0; j<n;j++){
                if(pos_board[i][j] == 0){
                    pos_list.push(new Position(j,i));
                }
            }
        }
        return pos_list;
    }

    /**
     *  Outputs current state representation of the Board object.
     *  Practical for debugging.
     */
    public void print()
    {
        // TODO
        for (int i = 0;i<n;i++){
            for (int j=0; j<n;j++){
                if(pos_board[i][j] == 1){
                    System.out.print("x ");
                }else if(pos_board[i][j] == -1){
                    System.out.print("o ");
                }else{
                    System.out.print("0 ");
                }
            }
            System.out.print("\n");
        }

    }

}

