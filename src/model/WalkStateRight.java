package model;

public class WalkStateRight extends WalkState {
    
    public WalkStateRight(Player player) {
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
    protected void changeToWalkStateRight() {
    
    }
}
