package ChessGame;

/**
 * Created by rliu on 3/7/17.
 */
public class Chess {
    Board board;
    Player p1;
    Player p2;

    void startGame() {
        board = new Board(8, 8);
        //Piece[] w=new Piece[]{new Piece('w',a)}
        p1 = new Player('w');
        p2 = new Player('b');
        Player activePlayer = startUser(p1);
        Player inActivePlayer = p2;
        while (true) {
            Cell[] move = activePlayer.generateMove(1, 1, 2, 2);

            //check if move is valid and can attack
            while (!board.move(activePlayer, move[0], move[1])) {
                move = activePlayer.generateMove(1, 1, 2, 2);
            }
            if (isLose(inActivePlayer)) {
                //declear winner activePlayer

                break;
            }
            activePlayer = switchActiveUser(activePlayer);
            inActivePlayer = switchActiveUser(inActivePlayer);
        }
    }

    private boolean isLose(Player p) {

        return false;
    }

    public Player switchActiveUser(Player player) {
        player.isActive = !player.isActive;
        return player;
    }

    public Player startUser(Player p) {
        p.isActive = true;
        return p;
    }

}
