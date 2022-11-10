package controller.obsever;

public interface GameFrameObserver {
    void navigateToHouseScreen();
    void navigateToInitialScreen();
    void navigateToMarketScreen();
    void navigateToTrailerScreen();
    void navigateToVillageScreen();
    void navigateToVillageScreen(int plawerRow, int playerColumn);
}
