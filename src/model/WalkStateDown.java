package model;

import global.GlobalVariables;

public class WalkStateDown extends WalkState {
    
    public WalkStateDown(Player player) {
        super(player);
    }
    
    @Override
    protected String getImageIdleName() {
        return GlobalVariables.PLAYER_IDLE_DOWN_KEY;
    }
    
    @Override
    protected String getImageAnimationName() {
        return GlobalVariables.PLAYER_WALKING_DOWN_KEY;
    }
    
    @Override
    protected void changeToWalkStateDown() {
    
    }
}
