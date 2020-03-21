package chaturanga.piece;

import chaturanga.board.Board;
import chaturanga.board.Move;

import java.util.Collection;
import java.util.Map;

public abstract class Piece {
    protected final Alliance pieceAlliance;
    protected int piecePosition;

    public Piece(int piecePosition, Alliance pieceAlliance) {
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
    }

    public int getPiecePosition() {
        return this.piecePosition;
    }

    public void setPiecePosition(int piecePosition) {
        this.piecePosition = piecePosition;
    }
    public abstract Collection<Move> calculateLegalMoves(Board board);

    public abstract Collection<Move> calculateLegalJumpedMoves(Board board, int oldPiecePosition, Map<Integer, Boolean> isVisited);

    public abstract Piece movePiece(Move move);
}
