package chaturanga.piece;

import chaturanga.Alliance;
import chaturanga.board.Board;
import chaturanga.board.BoardUtils;
import chaturanga.board.Move;
import chaturanga.board.Move.JumpedMove;
import chaturanga.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static chaturanga.piece.Piece.PieceType.*;

public class Pawn extends Piece{
    public static final int[] CANDIDATE_MOVE_COORDINATE = {-5, -4, -3, -1, 1, 3, 4, 5};

    public Pawn(final Alliance pieceAlliance, final int piecePosition) {
        super(PAWN,piecePosition,pieceAlliance);
    }
    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {
            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
            if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) || isFourthColumnExclusion(this.piecePosition, currentCandidateOffset)) {
                continue;
            }
            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if (!candidateDestinationTile.isTileOccupied()) {
                    legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public Collection<Move> calculateLegalJumpedMoves(Board board, int oldPiecePosition, Map<Integer, Boolean> isVisited) {
        if (!isVisited.containsKey(this.piecePosition))  isVisited.put(this.piecePosition, true);
        final List<Move> jumpedMove = new ArrayList<>();
        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {
            final int blockedPiecePosition = oldPiecePosition + currentCandidateOffset;

            if (isFirstColumnExclusion(oldPiecePosition, currentCandidateOffset) ||
                    isFourthColumnExclusion(oldPiecePosition, currentCandidateOffset)) {
                continue;
            }

            if (BoardUtils.isValidTileCoordinate(blockedPiecePosition)) {
                final Tile blockedPieceTile = board.getTile(blockedPiecePosition);

                if (blockedPieceTile.isTileOccupied()) {

                    if (isFirstColumnExclusion(blockedPiecePosition, currentCandidateOffset) ||
                            isFourthColumnExclusion(blockedPiecePosition, currentCandidateOffset)) {
                        continue;
                    }

                    final int candidateJumpedCoordinate = blockedPiecePosition + currentCandidateOffset;
                    if (BoardUtils.isValidTileCoordinate(candidateJumpedCoordinate)) {

                        final Tile jumpedCoordinateTile = board.getTile(candidateJumpedCoordinate);
                        if (!jumpedCoordinateTile.isTileOccupied()) {
                            if (!isVisited.containsKey(candidateJumpedCoordinate)) {
                                isVisited.put(candidateJumpedCoordinate, true);
                                jumpedMove.add(new JumpedMove(board, this, candidateJumpedCoordinate));
                                jumpedMove.addAll(calculateLegalJumpedMoves(board, candidateJumpedCoordinate, isVisited));
                            }
                        }
                    }
                }
            }
        }
        return ImmutableList.copyOf(jumpedMove);
    }

    @Override
    public Piece movePiece(Move move) {
        return new Pawn(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -5 || candidateOffset == -1 ||
                candidateOffset == 5);
    }

    private static boolean isFourthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FOURTH_COLUMN[currentPosition] && (candidateOffset == -3 || candidateOffset == 1 || candidateOffset == 5);
    }

    @Override
    public String toString() {
        return PAWN.toString();
    }

}
