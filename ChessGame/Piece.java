package ChessGame;

/**
 * Created by rliu on 3/7/17.
 */
public class Piece {
    char color;
    char type;
    boolean isAttacked;

    //color using int, type using int for simplify
    Piece(char color, char type) {
        this.color = color;
        this.type = type;
        this.isAttacked = false;
    }

    public int hashCode() {
        return type + color;
    }

    public boolean canBeMoveBy(Player p) {
        return this.color == p.color;
    }

    public boolean isValidMove(Cell from, Cell to) {
        isMovingCorrectDirection(from, to);
        if (to.isOccupied() && to.piece.canBeAttackedBy(this)) {


        }
        //only check if the move from to is falls in the rule
        //check to cell is not occupied or is occupied by other color piece which it can attack
        return true;
    }

    //child class overwrite this function
    public boolean isMovingCorrectDirection(Cell from, Cell to) {
        return true;
    }

    //child class overwrite this function
    public boolean canBeAttackedBy(Piece piece) {
        return true;
    }


}
