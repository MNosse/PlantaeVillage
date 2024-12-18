package model;

import global.GlobalVariables;

public class WalkStateRight extends WalkState {
    
    public WalkStateRight(Player player) {
        super(player);
    }
    
    @Override
    protected String getImageIdleName() {
        return GlobalVariables.PLAYER_IDLE_RIGHT_KEY;
    }
    
    @Override
    protected String getImageAnimationName() {
        return GlobalVariables.PLAYER_WALKING_RIGHT_KEY;
    }
    
    @Override
    protected void changeToWalkStateRight() {
    
    }
}
