package app.controller;

public interface SortingController {
    void start();

    void stop();

    void shuffle();

    void setBarsAmount(int amount);

    void setDelay(int delay);

}
