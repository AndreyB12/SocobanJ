package model;

import java.awt.*;

/**
 * Created by butkoav on 19.01.2017.
 */
public class Home extends GameObject
{
    public Home(int x, int y)
    {
        super(x, y, 4, 4);
    }

    @Override
    public void draw(Graphics g)
    {
        int tx = getX() - getWidth() / 2;
        int ty = getY() - getHeight() / 2;
        g.setColor(Color.RED);
        g.fillOval(tx, ty,getWidth(), getHeight());
    }
}
