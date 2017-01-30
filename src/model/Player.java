package model;

import java.awt.*;

/**
 * Created by butkoav on 19.01.2017.
 */
public class Player extends CollisionObject implements Movable
{
    public Player(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void move(int x, int y)
    {
        setX(getX() + x);
        setY(getY() + y);
    }

    @Override
    public void draw(Graphics g)
    {
        int tx = getX() - Model.FIELD_SELL_SIZE / 2;
        int ty = getY() - Model.FIELD_SELL_SIZE / 2;
        g.setColor(new Color(130,0,180));
        g.fillOval(tx, ty, Model.FIELD_SELL_SIZE, Model.FIELD_SELL_SIZE);
    }
}
