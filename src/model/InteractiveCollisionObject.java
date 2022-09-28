package model;

import java.util.Iterator;
import java.util.LinkedList;

public class InteractiveCollisionObject extends CollisionObjectWithImage {
    private final LinkedList<String> MESSAGES;
    private Iterator iterator;
    
    public InteractiveCollisionObject(int row, int column, int height, int width, String imageName, LinkedList<String> messages) {
        super(row, column, height, width, imageName);
        MESSAGES = messages;
        iterator = MESSAGES.iterator();
    }
    
    public String readNextLine() {
        if (iterator.hasNext()) {
            return (String) iterator.next();
        } else {
            iterator = MESSAGES.iterator();
            return "";
        }
    }
}
