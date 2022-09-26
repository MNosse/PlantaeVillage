package model;

import view.global.GlobalVariables;

public class WalkStateLeft extends WalkState {
    
    public WalkStateLeft(Player player) {
        super(player);
    }
    
    @Override
    protected String getImageIdleName() {
        return GlobalVariables.PLAYER_IDLE_LEFT_KEY;
    }
    
    @Override
    protected String getImageAnimationName() {
        return GlobalVariables.PLAYER_ANIMATION_LEFT_KEY;
    }
    
    @Override
    protected void changeToWalkStateLeft() {
    
    }
}
