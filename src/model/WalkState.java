package model;

public abstract class WalkState {
    private final Player PLAYER;
    
    public WalkState(Player player) {
        PLAYER = player;
    }
    
    protected abstract String getImageName();
    
    protected abstract void changeToWalkStateIdle();
    
    protected abstract void changeToWalkStateUp();
    
    protected abstract void changeToWalkStateDown();
    
    protected abstract void changeToWalkStateLeft();
    
    protected abstract void changeToWalkStateRight();
    
    protected Player getPLAYER() {
        return PLAYER;
    }
}
