package model;

public class WalkStateRight extends WalkState {
    
    public WalkStateRight(Player player) {
        super(player);
    }
    
    @Override
    protected String getImageName() {
        return "caminho";
    }
    
    @Override
    protected void changeToWalkStateIdle() {
        getPLAYER().setWalkState(new WalkStateIdle(getPLAYER()));
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
    
    }
}
