package chaturanga.piece;

import chaturanga.Alliance;
import chaturanga.board.Board;
import chaturanga.board.Move;

import java.util.Collection;
import java.util.Map;

public abstract class Piece {
    protected final PieceType pieceType;
    protected final Alliance pieceAlliance;
    protected int piecePosition;
    protected final int cachedHashCode;

    public Piece(final int piecePosition, final Alliance pieceAlliance, final PieceType pieceType) {
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        this.cachedHashCode = computeHashCode();
        this.pieceType = pieceType;
    }

    private int computeHashCode() {
        int result = pieceType.hashCode();
        result = 31 * result + pieceAlliance.hashCode();
        result = 31 * result + piecePosition;
        return result;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Piece)) {
            return false;
        }
        final Piece otherPiece = (Piece) other;
        return piecePosition == otherPiece.getPiecePosition() && pieceType == otherPiece.getPieceType() &&
                pieceAlliance == otherPiece.getPieceAlliance();
    }

    @Override
    public int hashCode() {
        int result = pieceType.hashCode();
        result = 31 * result + pieceAlliance.hashCode();
        result = 31 * result + piecePosition;
        return result;
    }

    public int getPiecePosition() {
        return this.piecePosition;
    }

    public void setPiecePosition(int piecePosition) {
        this.piecePosition = piecePosition;
    }

    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }

    public PieceType getPieceType() {
        return this.pieceType;
    }

    public int getPieceValue() {
        return this.pieceType.getPieceValue();
    }

    public abstract Collection<Move> calculateLegalMoves(Board board);

    public abstract Collection<Move> calculateLegalJumpedMoves(Board board, int oldPiecePosition, Map<Integer, Boolean> isVisited);

    public abstract Piece movePiece(Move move);

    public enum PieceType {
        PAWN(1,"P" );

        private String pieceName;
        private int pieceValue;

        PieceType(final int pieceValue,final String pieceName) {
            this.pieceName = pieceName;
            this.pieceValue = pieceValue;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }

        public int getPieceValue() {
            return this.pieceValue;
        }
        public void setPieceValue(int pieceValue) {
            this.pieceValue = pieceValue;
        }
    }
}
