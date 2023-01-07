package Pieces;

import Game.*;

import java.util.List;

public class King extends Piece {

    @Override
    public boolean checkMove(List<Position> path, Position src, Position dest) {
        if (Math.abs(src.getX() - dest.getX()) < 2 && Math.abs(src.getY() - dest.getY()) < 2) {
            return true;
        }
        return false;
    }

    public static Position CheckMated(GameBoard gameBoard ,BlockConvertor blockConvertor ,List<Position> checkPath, List<String> pieces, Position KingPos) {
        for (String playerPosition : pieces) {
             Piece attackingPiece = gameBoard.getBoardMap().get(playerPosition).getPiece();
            if (attackingPiece.checkMove(checkPath, blockConvertor.convertBlockToPosition(playerPosition), KingPos)) {
                return blockConvertor.convertBlockToPosition(playerPosition);
            }
        }
        return null;
    }
}

    
