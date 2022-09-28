package model;

import view.global.GlobalVariables;

public class Player {
    private int row;
    private int column;
    private Plant plant;
    private WalkState walkState;
    
    public Player(int row, int column) {
        this.row = row;
        this.column = column;
        walkState = new WalkStateUp(this);
    }
    
    public String getImageIdleName() {
        return walkState.getImageIdleName();
    }
    
    public String getImageAnimationName() {
        return walkState.getImageAnimationName();
    }
    
    public void walkUp() {
        walkState.changeToWalkStateUp();
        row = (row - 1);
    }
    
    public void walkDown() {
        walkState.changeToWalkStateDown();
        row = (row + 1);
    }
    
    public void walkLeft() {
        walkState.changeToWalkStateLeft();
        column = (column - 1);
    }
    
    public void walkRight() {
        walkState.changeToWalkStateRight();
        column = (column + 1);
    }
    
    public int getRow() {
        return row;
    }
    
    public void setRow(int row) {
        this.row = row;
    }
    
    public int getColumn() {
        return column;
    }
    
    public void setColumn(int column) {
        this.column = column;
    }
    
    public Plant getPlant() {
        return plant;
    }
    
    public void setPlant(Plant plant) {
        this.plant = plant;
    }
    
    protected void setWalkState(WalkState walkState) {
        this.walkState = walkState;
    }
}
