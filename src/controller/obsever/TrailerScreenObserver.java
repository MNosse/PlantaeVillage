package controller.obsever;

public interface TrailerScreenObserver {
    void updatePlayerImage(String imageKey);
    void updatePlayerRowUp(int row);
    void updatePlayerRowDown(int row);
    void updatePlayerColumnLeft(int column);
    void updatePlayerColumnRight(int column);
    void enableTeleport(String methodName, int row, int column);
    void disableTeleport();
}
