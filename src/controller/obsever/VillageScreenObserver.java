package controller.obsever;

public interface VillageScreenObserver {
    void updatePlayerImage(String imageKey);
    void updatePlayerRowUp(int row);
    void updatePlayerRowDown(int row);
    void updatePlayerColumnLeft(int column);
    void updatePlayerColumnRight(int column);
    void enableTeleport(int row, int column);
    void disableTeleport();
    void enableInteractive(int row, int column);
    void disableInteractive();
    void openTest();
    void openDialog();
    void updateDialogMessage(String message);
}
