package Pieces;

import Game.Position;
import java.util.List;

public class Knight extends Piece {

    @Override
    public boolean checkMove(List<Position> path, Position src, Position dest) {
        if(Math.abs(src.getY() - dest.getY()) == 2 && Math.abs(src.getX() - dest.getX()) == 1 ) {
            return true;
        }
        //Check if there is a sideways L shape
        if(Math.abs(src.getX() - dest.getX()) == 2 && Math.abs(src.getY() - dest.getY()) == 1 ) {
            return true;
        }
        return false;
    }
}


