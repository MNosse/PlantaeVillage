package model;

import view.global.GlobalVariables;

public class WalkStateUp extends WalkState {
    
    public WalkStateUp(Player player) {
        super(player);
    }
    
    @Override
    protected String getImageIdleName() {
        return GlobalVariables.PLAYER_IDLE_TOP_KEY;
    }
    
    @Override
    protected String getImageAnimationName() {
        return GlobalVariables.PLAYER_ANIMATION_TOP_KEY;
    }
    
    @Override
    protected void changeToWalkStateUp() {
    
    }
}
