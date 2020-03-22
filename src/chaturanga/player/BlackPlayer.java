package chaturanga.player;

import chaturanga.Alliance;
import chaturanga.board.Board;
import chaturanga.piece.Piece;

import java.util.Collection;

public class BlackPlayer extends Player {
    public BlackPlayer(final Board board){
        super(board);
    }

    @Override
    public Collection<Piece> getActivePiece() {
        return this.board.getBlackPieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.whitePlayer();
    }


}
