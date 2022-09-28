package model;

public class CollisionObjectWithImage extends CollisionObject {
    private final String IMAGE_NAME;
    
    public CollisionObjectWithImage(int row, int column, int height, int width, String imageName) {
        super(row, column, height, width);
        IMAGE_NAME = imageName;
    }
    
    public String getIMAGE_NAME() {
        return IMAGE_NAME;
    }
}