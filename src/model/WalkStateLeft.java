package model;

public class WalkStateLeft extends WalkState {
    
    public WalkStateLeft(Player player) {
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
    
    }
    
    @Override
    protected void changeToWalkStateRight() {
        getPLAYER().setWalkState(new WalkStateRight(getPLAYER()));
    }
}
