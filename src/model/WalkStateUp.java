package model;

import global.GlobalVariables;

public class WalkStateUp extends WalkState {
    
    public WalkStateUp(Player player) {
        super(player);
    }
    
    @Override
    protected String getImageIdleName() {
        return GlobalVariables.PLAYER_IDLE_UP_KEY;
    }
    
    @Override
    protected String getImageAnimationName() {
        return GlobalVariables.PLAYER_WALKING_UP_KEY;
    }
    
    @Override
    protected void changeToWalkStateUp() {
    
    }
}
