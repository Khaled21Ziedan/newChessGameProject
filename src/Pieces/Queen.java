package Pieces;

import Game.GameBoard;
import Game.Position;

import java.util.List;

public class Queen extends Piece {


    @Override
    public boolean checkMove(List<Position> path, Position src, Position dest) {
        if(BishopMove(path,src,dest)){
            return true;
        } else if(rockMove(path,src,dest)){
            return true;
        } else if(kingMove(path, src,dest)){
            return true;
        } else {
            return false;
        }
    }
    public boolean BishopMove(List<Position> path, Position src, Position dest){
        boolean isValidMove = Math.abs(src.getX() - dest.getX()) == Math.abs(src.getY() - dest.getY());
        if (!isValidMove) {
            return false;
        }
        int currentX= src.getX();
        int currentY= src.getY();
        return canReachBishop(path,currentX<dest.getX()?++currentX:--currentX, currentY<dest.getY()?++currentY:--currentY,dest.getX(),dest.getY());
    }
    private boolean canReachBishop(List<Position> path, int currentX, int currentY, int destX, int destY ){
        if(currentX == destX && currentY == destY){
            return true;
        }
        path.add(new Position(currentY,currentX));
        return canReachBishop(path,currentX<destX?++currentX:--currentX, currentY<destY?++currentY:--currentY,destX,destY);
    }
    public boolean rockMove(List<Position> path, Position src, Position dest){
    boolean isValidMove = src.getY() == dest.getY() || src.getX() == dest.getX();
        if (!isValidMove) {
        return false;
    }
    int currentX= src.getX();
    int currentY= src.getY();

        return canReachRock(path,currentX<dest.getX()?++currentX :currentX>dest.getX()?--currentX:currentX
                , currentY<dest.getY()?++currentY :currentY>dest.getY()?--currentY:currentY,dest.getX(),dest.getY());

}
    private boolean canReachRock(List<Position> path, int currentX, int currentY, int destX, int destY ){
        if(currentX == destX && currentY == destY){
            return true;
        }
        path.add(new Position(currentY,currentX));
        return canReachRock(path,currentX<destX?++currentX :currentX>destX?--currentX:currentX
                , currentY<destY?++currentY :currentY>destY?--currentY:currentY,destX,destY);

    }
    public boolean kingMove(List<Position> path, Position src, Position dest) {
        if(Math.abs(src.getX() - dest.getX()) < 2 && Math.abs(src.getY()  - dest.getY()) < 2) {
            return true;
        }
        return false;
      }
    }

