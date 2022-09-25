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
        walkState = new WalkStateIdle(this);
    }
    
    public String getWalkStateImageName() {
        return walkState.getImageName();
    }
    
    public void changeToWalkStateIdle() {
        walkState.changeToWalkStateIdle();
    }
    
    public void changeToWalkStateUp() {
        walkState.changeToWalkStateUp();
    }
    
    public void changeToWalkStateDown() {
        walkState.changeToWalkStateDown();
    }
    
    public void changeToWalkStateLeft() {
        walkState.changeToWalkStateLeft();
    }
    
    public void changeToWalkStateRight() {
        walkState.changeToWalkStateRight();
    }
    
    public void walkUp() {
        row = (row - GlobalVariables.TILE_SIZE);
    }
    
    public void walkDown() {
        row = (row + GlobalVariables.TILE_SIZE);
    }
    
    public void walkLeft() {
        column = (column - GlobalVariables.TILE_SIZE);
    }
    
    public void walkRight() {
        column = (column + GlobalVariables.TILE_SIZE);
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
    
    protected WalkState getWalkState() {
        return walkState;
    }
    
    protected void setWalkState(WalkState walkState) {
        this.walkState = walkState;
    }
}
