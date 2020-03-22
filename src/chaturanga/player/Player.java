package chaturanga.player;

import chaturanga.Alliance;
import chaturanga.board.Board;
import chaturanga.piece.Piece;

import java.util.Collection;

public abstract class Player {
    protected final Board board;
    private final boolean isInCheck;

    public Player(final Board board){
        this.board=board;
    }

    public abstract Collection<Piece> getActivePiece();
    public abstract Alliance getAlliance();
    public abstract Player getOpponent();


}
