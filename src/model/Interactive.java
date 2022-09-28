package model;

import java.util.Iterator;
import java.util.LinkedList;

public class Interactive {
    private final int ROW;
    private final int COLUMN;
    private final LinkedList<String> MESSAGES;
    private Iterator iterator;
    
    public Interactive(int row, int column, LinkedList<String> messages) {
        ROW = row;
        COLUMN = column;
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
