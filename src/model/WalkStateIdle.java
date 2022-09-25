package model;

public class WalkStateIdle extends WalkState {
    
    public WalkStateIdle(Player player) {
        super(player);
    }
    
    @Override
    protected String getImageName() {
        return "caminho";
    }
    
    @Override
    protected void changeToWalkStateIdle() {
    
    }
    
    @Override
    protected void changeToWalkStateUp() {
        getPLAYER().setWalkState(new WalkStateUp(getPLAYER()));
    }
    
    @Override
    protected void changeToWalkStateDown() {
        getPLAYER().setWalkState(new WalkStateDown(getPLAYER()));
    }
    
    @Override
    protected void changeToWalkStateLeft() {
        getPLAYER().setWalkState(new WalkStateLeft(getPLAYER()));
    }
    
    @Override
    protected void changeToWalkStateRight() {
        getPLAYER().setWalkState(new WalkStateRight(getPLAYER()));
    }
}
