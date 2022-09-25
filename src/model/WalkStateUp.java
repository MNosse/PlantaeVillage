package model;

public class WalkStateUp extends WalkState {
    
    public WalkStateUp(Player player) {
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
