package model;

public class WalkStateLeft extends WalkState {
    
    public WalkStateLeft(Player player) {
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
    protected void changeToWalkStateLeft() {
    
    }
}
