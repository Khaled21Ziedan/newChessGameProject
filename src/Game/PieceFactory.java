package Game;

import Pieces.*;

public class PieceFactory {

    //use getPiece method to get object of type piece
    public static Piece createPiece(String pieceType) {
        if (pieceType == null) {
            return null;
        }
        if (pieceType.equalsIgnoreCase("ROCK")) {
            return new Rock();
        } else if (pieceType.equalsIgnoreCase("KNIGHT")) {
            return new Knight();
        } else if (pieceType.equalsIgnoreCase("KING")) {
            return new King();
        } else if (pieceType.equalsIgnoreCase("WHITE PAWN")) {
            return new Pawn(PlayerColor.WHITE);
        }else if (pieceType.equalsIgnoreCase("BLACK PAWN")) {
            return new Pawn(PlayerColor.BLACK);
        } else if (pieceType.equalsIgnoreCase("QUEEN")) {
            return new Queen();
        } else if (pieceType.equalsIgnoreCase("BISHOP")) {
            return new Bishop();

        }
        return null;
    }
}
