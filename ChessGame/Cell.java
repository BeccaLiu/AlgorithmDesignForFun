package ChessGame;

/**
 * Created by rliu on 3/7/17.
 */
public class Cell {
    int i;
    int j;
    Piece piece;

    Cell(int i, int j) {
        this.i = i;
        this.j = j;
        piece = null;
    }

    public boolean isOccupied() {
        return piece != null;
    }

    public void Occupied(Piece piece) {
        this.piece = piece;
    }

    public void removePiece() {
        Piece piece = getPiece();
        int color = piece.color;

        piece = null;
    }

    public Piece getPiece() {
        return piece;
    }

}
