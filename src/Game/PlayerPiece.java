package Game;

import Pieces.Piece;

public class PlayerPiece {
    Player player;
    Piece piece;
    public PlayerPiece(Player player, Piece piece) {
        this.player = player;
        this.piece = piece;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
