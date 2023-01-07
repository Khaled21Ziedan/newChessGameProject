package Pieces;
import Game.GameBoard;
import Game.Position;

import java.util.List;

public abstract class Piece {
    private Position position ;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    public abstract boolean checkMove(List<Position> path, Position src, Position dest);

}
// template design pattern
