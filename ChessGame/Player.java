package ChessGame;

/**
 * Created by rliu on 3/7/17.
 */
public class Player {
    boolean isActive;
    char color;
    //HashSet<Piece> pieces;

    Player(char color) {
        isActive = false;
        this.color = color;
        //pieces=new HashSet<>();

//        for(Piece p: ps)
//            pieces.add(p);
    }


    public Cell[] generateMove(int x1, int y1, int x2, int y2) {
        return new Cell[]{new Cell(x1, y1), new Cell(x2, y2)};
    }

    public void removePiece(Piece p) {

    }
}
