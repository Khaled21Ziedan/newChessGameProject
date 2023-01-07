package Game;
import Pieces.King;
import Pieces.Piece;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameChess {
    private final Player black;
    private final Player white;
    private final GameBoard gameBoard;
    private GameStatus gameStatus;
    private Player currentPlayer;
    private Player otherPlayer;
    private BlockConvertor blockConvertor;
    private boolean isCheckmated;

    private List<String> whitePlayerPiecesSet;

    private List<String> blackPlayerPiecesSet;

    public GameChess() {
        this.black = new Player(PlayerColor.BLACK);
        this.white = new Player(PlayerColor.WHITE);

        this.gameBoard = GameBoard.getInstance();
        gameBoard.init(white,black);

        this.currentPlayer = white; // white player begin to play
        this.gameStatus = GameStatus.ACTIVE;

        this.blockConvertor = BlockConvertor.getInstance();
        blockConvertor.initPos();

        this.isCheckmated = false;

        this.whitePlayerPiecesSet = new ArrayList<>();
        this.blackPlayerPiecesSet = new ArrayList<>();
    }
    public Move readMove(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Source : ");
        String source= sc.next();

        System.out.println("Enter Destiny : ");
        String destiny= sc.next();

        return new Move(source,destiny);
    }

    public void toggleTurn() {

        if (currentPlayer == white) {
            System.out.println("Enter your next move (black player)");
            currentPlayer = black;
            otherPlayer = white;
            return;
        }
        System.out.println("Enter your next move (white player)");
        currentPlayer = white;
        otherPlayer = black;
    }
    public boolean playTurn() {

        Move move = readMove();

        while (move == null) {
            System.out.println("try again please enter your next move ");
            move = readMove();
        }
         // check if this key is on the map
        if (!this.gameBoard.getBoardMap().containsKey(move.getSrc())) {
            System.out.println("try again please there is no piece on that tile");
            return false;
        } else {
            PlayerPiece srcPiece = this.gameBoard.getBoardMap().get(move.getSrc());
            // check if this piece is to the current player
            if (srcPiece.getPlayer() != currentPlayer){
                System.out.println("try again please this is not your piece");
                return false;
            }
            // check if this piece to the same player
            PlayerPiece desPiece = null;
            if(this.gameBoard.getBoardMap().containsKey(move.getDes())){
                desPiece = this.gameBoard.getBoardMap().get(move.getDes());
                if(srcPiece.getPlayer() == desPiece.getPlayer()){
                    System.out.println("try again please you can't kill your piece ");
                    return false;
                }
            }

            List<Position> path = new ArrayList<>();
            if (srcPiece.getPiece().checkMove(path,blockConvertor.convertBlockToPosition(move.getSrc()),blockConvertor.convertBlockToPosition(move.getDes()))) {
                if (gameBoard.checkBoardMove(blockConvertor, path, (move.getSrc()), (move.getDes()))) {
                    gameBoard.move(move.getSrc(), move.getDes(), srcPiece);
                } else {
                    System.out.println("try again please there is a piece on your way");
                    return false;
                }
            }
            path.clear();
        }


        // check if the other player was check mated

        Position whiteKingPos = blockConvertor.convertBlockToPosition(this.gameBoard.getWhiteKing());
        Position blackKingPos = blockConvertor.convertBlockToPosition(this.gameBoard.getBlackKing());
        whitePlayerPiecesSet = gameBoard.getWhitePlayerPieces();
        blackPlayerPiecesSet = gameBoard.getBlackPlayerPieces();
        boolean kingUnderAttack;
        Position attackingPiecePosition;

        // if its white player turn check if black king is checkmated else check for black
        List<Position> checkPath = new ArrayList<>();
        if (currentPlayer == white){
            if(King.CheckMated(gameBoard , blockConvertor ,checkPath , whitePlayerPiecesSet, blackKingPos) != null){
                attackingPiecePosition = King.CheckMated(gameBoard ,blockConvertor ,checkPath , whitePlayerPiecesSet, blackKingPos);
                assert attackingPiecePosition != null;
                kingUnderAttack = this.gameBoard.checkBoardMove(blockConvertor,checkPath,blockConvertor.convertPositionToBlock(attackingPiecePosition), blockConvertor.convertPositionToBlock(blackKingPos));
                if(kingUnderAttack)
                    System.out.println("Black King Under Attack");
            }
            whitePlayerPiecesSet.clear();
            blackPlayerPiecesSet.clear();
        } else {
            if(King.CheckMated(gameBoard , blockConvertor,checkPath , blackPlayerPiecesSet, whiteKingPos) != null){
                attackingPiecePosition = King.CheckMated(gameBoard , blockConvertor,checkPath , blackPlayerPiecesSet, whiteKingPos);
                kingUnderAttack = this.gameBoard.checkBoardMove(blockConvertor,checkPath,blockConvertor.convertPositionToBlock(attackingPiecePosition), blockConvertor.convertPositionToBlock(whiteKingPos));
                if(kingUnderAttack)
                    System.out.println("White King Under Attack");
            }
        }

        if (whitePlayerPiecesSet.size() < 2 && blackPlayerPiecesSet.size() < 2){
            gameStatus = GameStatus.DRAW;
        } else if (whitePlayerPiecesSet.size() < 2 ){
            gameStatus = GameStatus.BLACK_WINS;
        } else if (blackPlayerPiecesSet.size() < 2){
            gameStatus = GameStatus.WHITE_WINS;
        } else {
            gameStatus = GameStatus.ACTIVE;
        }
        toggleTurn();
        playTurn();
        return true;
    }
    public void startGame() {
        gameStatus = GameStatus.ACTIVE;
        while (gameStatus == GameStatus.ACTIVE) {
                this.playTurn();
        }  if (gameStatus == GameStatus.BLACK_WINS)
            System.out.println("black player wins!");
           else if (gameStatus == GameStatus.WHITE_WINS)
            System.out.println("white player wins!");
           else
            System.out.println("its a draw");
    }
}


