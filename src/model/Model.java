package model;

import controller.EventListener;

import java.nio.file.Paths;

/**
 * Created by butkoav on 19.01.2017.
 */
public class Model
{
    public static int FIELD_SELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader =
            new LevelLoader(Paths.get("./src/res/levels.txt"));


    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects()
    {
        return gameObjects;
    }

    public void restartLevel(int level)
    {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart()
    {
        restartLevel(currentLevel);
    }

    public void startNextLevel()
    {
        restartLevel(++currentLevel);
    }

    public void move(Direction direction)
    {

        if (checkWallCollision(getGameObjects().getPlayer(), direction)) return;
        if(checkBoxCollision(direction)) return;
        move(getGameObjects().getPlayer(), direction);
        checkCompletion();
    }

    private void move(Movable object, Direction direction)
    {
        switch (direction)
        {
            case DOWN:
                object.move(0, FIELD_SELL_SIZE);
                break;
            case UP:
                object.move(0, -FIELD_SELL_SIZE);
                break;
            case LEFT:
                object.move(-FIELD_SELL_SIZE, 0);
                break;
            case RIGHT:
                object.move(FIELD_SELL_SIZE, 0);
                break;
        }
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction)
    {
        for (CollisionObject co : gameObjects.getWalls())
        {
            if (gameObject.isCollision(co, direction)) return true;
        }
        return false;
    }

    public boolean checkBoxCollision(Direction direction)
    {
        for (Box box : gameObjects.getBoxes())
        {
            if (gameObjects.getPlayer().isCollision(box, direction))
            {
                if (checkWallCollision(box, direction) || checkBoxCollision(box, direction))
                    return true;
                move(box, direction);
            }
        }
        return false;
    }

    private boolean checkBoxCollision(CollisionObject gameObject, Direction direction)
    {
        for (CollisionObject co : gameObjects.getBoxes())
        {
            if (gameObject.isCollision(co, direction)) return true;
        }
        return false;
    }

    public void checkCompletion()
    {
        boolean atHome;
        for (GameObject home : gameObjects.getHomes())
        {
            atHome = false;
            for (GameObject box : gameObjects.getBoxes())
            {
                if (home.getX() == box.getX() && home.getY() == box.getY())
                    atHome = true;
            }
            if (!atHome) return;
        }

        eventListener.levelCompleted(currentLevel);
    }

    public int getCurrentLevel()
    {
        return currentLevel;
    }
}
