package chaturanga.player.ai;

import chaturanga.board.Board;
import chaturanga.board.Move;

import java.util.ArrayList;
import java.util.List;

public class Minimax implements MoveStrategy {
    private final BoardEvaluator boardEvaluator;
    private final int searchDepth;
    public MiniMax(final int searchDepth) {
        this.boardEvaluator = new StandardBoardEvaluator();
        this.searchDepth = searchDepth;
    }

    @Override
    public Move execute(Board board) {
        Move bestMove = null;
        int highestSeenValue = Integer.MIN_VALUE;
        int lowestSeenValue = Integer.MAX_VALUE;
        int currentValue;
        List<Move> bestMoveAI = new ArrayList<>();
        int numMoves = board.currentPlayer().getLegalMoves().size();
        for (final Move move : board.currentPlayer().getLegalMoves()) {
            final MoveTransition moveTransition = board.currentPlayer().makeMove(move);
            if (moveTransition.getMoveStatus().isDone()) {
                currentValue = board.currentPlayer().getAlliance().isWhite() ? min(move,move ,moveTransition.getTransitionBoard(), this.searchDepth - 1,Integer.MIN_VALUE,Integer.MAX_VALUE) : max(move,move,moveTransition.getTransitionBoard(), this.searchDepth - 1,Integer.MIN_VALUE,Integer.MAX_VALUE);
                if (board.currentPlayer().getAlliance().isWhite() && currentValue >= highestSeenValue) {
                    if (currentValue > highestSeenValue) {
                        bestMoveAI.clear();
                        bestMoveAI.add(move);
                    }
                    else{
                        bestMoveAI.add(move);
                    }
                    highestSeenValue = currentValue;
                } else if (board.currentPlayer().getAlliance().isBlack() && currentValue<=lowestSeenValue) {
                    if (currentValue < lowestSeenValue) {
                        bestMoveAI.clear();
                        bestMoveAI.add(move);
                    }
                    else{
                        bestMoveAI.add(move);
                    }
                    lowestSeenValue = currentValue;
                }
            }
        }
        bestMove = getRandomMove(bestMoveAI);
        return bestMove;
    }
    
}
