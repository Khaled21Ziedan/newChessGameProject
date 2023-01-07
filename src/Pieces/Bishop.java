package Pieces;

import Game.Position;

import java.util.List;

public class Bishop extends Piece implements UnJumble {

    @Override
    public boolean checkMove(List<Position> path, Position src, Position dest) {
        boolean isValidMove = Math.abs(src.getX() - dest.getX()) == Math.abs(src.getY() - dest.getY());
        if (!isValidMove) {
            return false;
        }
        int currentX = src.getX();
        int currentY = src.getY();
        return canReach(path, currentX < dest.getX() ? ++currentX : --currentX, currentY < dest.getY() ? ++currentY : --currentY, dest.getX(), dest.getY());
    }

    public boolean canReach(List<Position> path, int currentX, int currentY, int destX, int destY) {
        if (currentX == destX && currentY == destY) {
            return true;
        }
        path.add(new Position(currentY, currentX));
        return canReach(path, currentX < destX ? ++currentX : --currentX, currentY < destY ? ++currentY : --currentY, destX, destY);
    }
}
