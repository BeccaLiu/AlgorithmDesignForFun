package ChessGame;

/**
 * Created by rliu on 3/7/17.
 */
public class Board {
    int width;
    int height;
    Cell[][] board;

    Board(int width, int height) {
        this.width = width;
        this.height = height;
        Cell[][] board = new Cell[height][width];
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                board[i][j] = new Cell(i, j);
    }

    //fill the board
    //board[i][j].piece=new Piece(1, 'P');
    //board[i][j].piece=new Piece(2, 'P');
    public void fillBoard(Board b) {

    }

    public boolean move(Player p, Cell from, Cell to) {
        if (from.i > 0 && from.i < height && from.j > 0 && from.j < width) {
            //is both cell inside the board
            from.piece.canBeMoveBy(p);
            from.piece.isValidMove(from, to); //is to is other color piece or
            isObstacle(from, to);
            movePiece(from, to);
        }
        return false;
    }

    public void isObstacle(Cell from, Cell to) {

    }

    public void movePiece(Cell from, Cell to) {
        if (to.isOccupied()) {
            to.piece.isAttacked = true;
            to.removePiece();
            //remove piece from other player
            to.Occupied(from.piece);
            from.removePiece();
        } else {
            to.Occupied(from.piece);
            from.removePiece();
        }
    }


}
