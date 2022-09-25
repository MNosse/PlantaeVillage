package model;

public class WalkStateUp extends WalkState {
    
    public WalkStateUp(Player player) {
        super(player);
    }
    
    @Override
    protected String getImageIdleName() {
        return "caminho";
    }
    
    @Override
    protected String getImageAnimationName() {
        return "caminho";
    }
    
    @Override
    protected void changeToWalkStateUp() {
    
    }
}
