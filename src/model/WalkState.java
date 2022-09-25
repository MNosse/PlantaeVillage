package model;

public abstract class WalkState {
    private final Player PLAYER;
    
    public WalkState(Player player) {
        PLAYER = player;
    }
    
    protected abstract String getImageIdleName();
    
    protected abstract String getImageAnimationName();
    
    protected void changeToWalkStateUp() {
        PLAYER.setWalkState(new WalkStateUp(PLAYER));
    }
    
    protected void changeToWalkStateDown() {
        PLAYER.setWalkState(new WalkStateDown(PLAYER));
    }
    
    protected void changeToWalkStateLeft() {
        PLAYER.setWalkState(new WalkStateLeft(PLAYER));
    }
    
    protected void changeToWalkStateRight() {
        PLAYER.setWalkState(new WalkStateRight(PLAYER));
    }
}
