package model;

/**
 * Created by butkoav on 19.01.2017.
 */
public abstract class CollisionObject extends GameObject
{
    public CollisionObject(int x, int y)
    {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction)
    {
        int x1 = 0, y1 = 0;
        switch (direction)
        {
            case DOWN:
                x1 = getX();
                y1 = getY() + Model.FIELD_SELL_SIZE;
                break;
            case UP:
                x1 = getX();
                y1 = getY() - Model.FIELD_SELL_SIZE;
                break;
            case LEFT:
                x1 = getX() - Model.FIELD_SELL_SIZE;
                y1 = getY();
                break;
            case RIGHT:
                x1 = getX() + Model.FIELD_SELL_SIZE;
                y1 = getY();
                break;
        }

        if (x1 == gameObject.getX() && y1 == gameObject.getY()) return true;
        return false;
    }
}
