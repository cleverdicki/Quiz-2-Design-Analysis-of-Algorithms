package chaturanga;

public enum  Alliance {
    WHITE{
        @Override
        public boolean isWhite(){
            return true;
        }
        @Override
        public boolean isBlack(){
            return false;
        }
        @Override
        public Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer){
            return whitePlayer;
        }
    }, BLACK {
        @Override
        public boolean isWhite(){
            return false;
        }
        @Override
        public boolean isBlack(){
            return true;
        }
        @Override
        public Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer){
            return blackPlayer;
        };

        public abstract boolean isWhite();

        public abstract boolean isBlack();

        public abstract Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer);
    }
}
