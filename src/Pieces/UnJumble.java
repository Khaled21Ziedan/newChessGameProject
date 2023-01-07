package Pieces;

import Game.Position;

import java.util.List;

public interface UnJumble {
    abstract boolean canReach(List<Position> path, int currentX, int currentY, int destX, int destY);
}
