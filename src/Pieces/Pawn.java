package Pieces;

import Game.PlayerColor;
import Game.Position;

import java.util.List;

public class Pawn extends Piece {
    PlayerColor color;
    boolean isValidMove ;
    boolean whiteFirstAdvanced = true ;
    boolean blackFirstAdvanced = true;
    public Pawn(PlayerColor color) {
        this.color = color;
    }
    @Override
    public boolean checkMove(List<Position> path, Position src, Position dest) {
        if(whiteFirstAdvanced || blackFirstAdvanced){ // first pawn either white or black can move the first play by 2
            if(this.color == PlayerColor.WHITE){
                isValidMove = (dest.getY() - src.getY() == 2 && dest.getX() == src.getX());
                whiteFirstAdvanced = false;
            } else if(this.color == PlayerColor.BLACK){
                isValidMove = (dest.getY() - src.getY() == -2 && dest.getX() == src.getX());
                blackFirstAdvanced = false;
            }
        }
        if(this.color == PlayerColor.WHITE && !whiteFirstAdvanced){ // white player can move only forward by 1 or diagonal by 1
            isValidMove = (dest.getY() - src.getY() == 1 && dest.getX() == src.getX()) ||
        (dest.getY() - src.getY() == 1 && Math.abs(dest.getX() - src.getX()) == 1) ;

        } else if(this.color == PlayerColor.BLACK && !blackFirstAdvanced){ // black player can move only forward by 1 or diagonal by 1
            isValidMove = (dest.getY() - src.getY() == -1 && dest.getX() == src.getX()) ||
        (dest.getY() - src.getY() == -1 && Math.abs(dest.getX() - src.getX()) == 1) ;

        }
        return isValidMove;
    }
}