package model;

public class WalkStateDown extends WalkState {
    
    public WalkStateDown(Player player) {
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
    protected void changeToWalkStateDown() {
    
    }
}
