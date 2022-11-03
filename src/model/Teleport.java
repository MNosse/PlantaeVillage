package model;

public class Teleport extends CollisionObject {
    private final String TELEPORT_METHOD_NAME;
    
    public Teleport(int row, int column, String teleportMethodName) {
        super(row, column);
        TELEPORT_METHOD_NAME = teleportMethodName;
    }
    
    public String getTELEPORT_METHOD_NAME() {
        return TELEPORT_METHOD_NAME;
    }
}
