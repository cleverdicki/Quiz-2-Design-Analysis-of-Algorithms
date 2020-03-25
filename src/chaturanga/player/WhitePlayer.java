package chaturanga.player;

import chaturanga.Alliance;
import chaturanga.board.Board;
import chaturanga.board.Move;
import chaturanga.piece.Piece;

import java.util.Collection;

public class WhitePlayer extends  Player {
    public WhitePlayer(final Board board, final Collection<Move> whiteStandardLegalMoves, final Collection<Move> blackStandardLegalMoves) {
        super(board, whiteStandardLegalMoves, blackStandardLegalMoves);
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.blackPlayer();
    }

    @Override
    public Collection<Piece> getActivePiece() {
        return this.board.getWhitePieces();
    }
}
