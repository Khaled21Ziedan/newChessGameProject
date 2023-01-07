package Game;

import Pieces.Pawn;
import Pieces.Piece;

import java.util.*;

public class GameBoard {
    private Map<String, PlayerPiece> boardMap;
    private static GameBoard instance; // Singleton Design Pattern
    private Player whitePlayer;
    private Player blackPlayer;
    private String blackKingPosition;
    private PlayerPiece whiteKing;
    private PlayerPiece blackKing;
    private String whiteKingPos;
    private List<String> whitePlayerPieces = new ArrayList<>();
    private List<String> blackPlayerPieces = new ArrayList<>();

    private List<String> whitePawnPromotionPos = new ArrayList<>();

    private List<String> blackPawnPromotionPos = new ArrayList<>();

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public Map<String, PlayerPiece> getBoardMap() {
        return boardMap;
    }

    private GameBoard() {

    }

    public static GameBoard getInstance() {
        if (instance == null) {
            instance = new GameBoard();
        }
        return instance;
    }

    public void init(Player white, Player black) {
        this.whitePlayer = white;
        this.blackPlayer = black;
        initPos();
        whitePawnPromotionPos.add("a1");
        whitePawnPromotionPos.add("b1");
        whitePawnPromotionPos.add("c1");
        whitePawnPromotionPos.add("d1");
        whitePawnPromotionPos.add("e1");
        whitePawnPromotionPos.add("f1");
        whitePawnPromotionPos.add("g1");
        whitePawnPromotionPos.add("h1");

        blackPawnPromotionPos.add("a8");
        blackPawnPromotionPos.add("b8");
        blackPawnPromotionPos.add("c8");
        blackPawnPromotionPos.add("d8");
        blackPawnPromotionPos.add("e8");
        blackPawnPromotionPos.add("f8");
        blackPawnPromotionPos.add("g8");
        blackPawnPromotionPos.add("h8");
    }

    public void initPos() {
        boardMap = new HashMap<String, PlayerPiece>();
        whiteKing = new PlayerPiece(whitePlayer, PieceFactory.createPiece("KING"));
        //white


        boardMap.put("a8", new PlayerPiece(whitePlayer, PieceFactory.createPiece("ROCK")));
        boardMap.put("b8", new PlayerPiece(whitePlayer, PieceFactory.createPiece("KNIGHT")));
        boardMap.put("c8", new PlayerPiece(whitePlayer, PieceFactory.createPiece("BISHOP")));
        boardMap.put("d8", new PlayerPiece(whitePlayer, PieceFactory.createPiece("QUEEN")));
        boardMap.put("e8", whiteKing);
        boardMap.put("f8", new PlayerPiece(whitePlayer, PieceFactory.createPiece("BISHOP")));
        boardMap.put("g8", new PlayerPiece(whitePlayer, PieceFactory.createPiece("KNIGHT")));
        boardMap.put("h8", new PlayerPiece(whitePlayer, PieceFactory.createPiece("ROCK")));
        boardMap.put("a7", new PlayerPiece(whitePlayer, PieceFactory.createPiece("WHITE PAWN")));
        boardMap.put("b7", new PlayerPiece(whitePlayer, PieceFactory.createPiece("WHITE PAWN")));
        boardMap.put("c7", new PlayerPiece(whitePlayer, PieceFactory.createPiece("WHITE PAWN")));
        boardMap.put("d7", new PlayerPiece(whitePlayer, PieceFactory.createPiece("WHITE PAWN")));
        boardMap.put("e7", new PlayerPiece(whitePlayer, PieceFactory.createPiece("WHITE PAWN")));
        boardMap.put("f7", new PlayerPiece(whitePlayer, PieceFactory.createPiece("WHITE PAWN")));
        boardMap.put("g7", new PlayerPiece(whitePlayer, PieceFactory.createPiece("WHITE PAWN")));
        boardMap.put("h7", new PlayerPiece(whitePlayer, PieceFactory.createPiece("WHITE PAWN")));

        //black
        blackKing = new PlayerPiece(blackPlayer, PieceFactory.createPiece("KING"));

        boardMap.put("a2", new PlayerPiece(blackPlayer, PieceFactory.createPiece("BLACK PAWN")));
        boardMap.put("b2", new PlayerPiece(blackPlayer, PieceFactory.createPiece("BLACK PAWN")));
        boardMap.put("c2", new PlayerPiece(blackPlayer, PieceFactory.createPiece("BLACK PAWN")));
        boardMap.put("d2", new PlayerPiece(blackPlayer, PieceFactory.createPiece("BLACK PAWN")));
        boardMap.put("e2", new PlayerPiece(blackPlayer, PieceFactory.createPiece("BLACK PAWN")));
        boardMap.put("f2", new PlayerPiece(blackPlayer, PieceFactory.createPiece("BLACK PAWN")));
        boardMap.put("g2", new PlayerPiece(blackPlayer, PieceFactory.createPiece("BLACK PAWN")));
        boardMap.put("h2", new PlayerPiece(blackPlayer, PieceFactory.createPiece("BLACK PAWN")));
        boardMap.put("a1", new PlayerPiece(blackPlayer, PieceFactory.createPiece("ROCK")));
        boardMap.put("b1", new PlayerPiece(blackPlayer, PieceFactory.createPiece("KNIGHT")));
        boardMap.put("c1", new PlayerPiece(blackPlayer, PieceFactory.createPiece("BISHOP")));
        boardMap.put("d1", new PlayerPiece(blackPlayer, PieceFactory.createPiece("QUEEN")));
        boardMap.put("e1", blackKing);
        boardMap.put("f1", new PlayerPiece(blackPlayer, PieceFactory.createPiece("BISHOP")));
        boardMap.put("g1", new PlayerPiece(blackPlayer, PieceFactory.createPiece("KNIGHT")));
        boardMap.put("h1", new PlayerPiece(blackPlayer, PieceFactory.createPiece("ROCK")));

    }
    public boolean checkBoardMove(BlockConvertor blockConvertor, List<Position> path, String src, String des) {
        for(Position position : path) {
            if (this.boardMap.containsKey(blockConvertor.convertPositionToBlock(position))) {
                return false;
            }
        }
        return true;
    }

    public void move(String src, String des, PlayerPiece playerPiece) {
        this.boardMap.put(des, playerPiece);
        this.boardMap.remove(src);
    }

    public String getWhiteKing() {
        whiteKingPos = GameBoard.getKey(boardMap, whiteKing);
        return whiteKingPos;
    }

    public String getBlackKing(){
        blackKingPosition = GameBoard.getKey(boardMap,blackKing);
        return blackKingPosition;
    }
    public List<String> getWhitePlayerPieces() {
        for (PlayerPiece value : boardMap.values()) {
            if (value.getPlayer() == blackPlayer)
                whitePlayerPieces.add(getKey(boardMap ,value));
                }
        return whitePlayerPieces;
    }
    public List<String> getBlackPlayerPieces() {
        for (PlayerPiece value : boardMap.values())
            if (value.getPlayer() == blackPlayer)
                blackPlayerPieces.add(getKey(boardMap ,value));

        return blackPlayerPieces;
    }
    private static <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;

    }

}
