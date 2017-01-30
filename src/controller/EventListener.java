package controller;

import model.Direction;

/**
 * Created by butkoav on 19.01.2017.
 */
public interface EventListener
{
    void move(Direction direction);

    void restart();

    void startNextLevel();

    void levelCompleted(int level);


    void startPrevLevel();
}
