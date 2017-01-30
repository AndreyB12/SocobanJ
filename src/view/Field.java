package view;

import controller.EventListener;
import model.Direction;
import model.GameObject;
import model.GameObjects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

;

/**
 * Created by butkoav on 19.01.2017.
 */
public class Field extends JPanel
{
    private View view;
    private EventListener eventListener;

    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }

    public Field(View view)
    {
        this.view = view;
        addKeyListener(new KeyHandler());
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        GameObjects gos = view.getGameObjects();
        if (gos == null) return;
        for (GameObject go : gos.getWalls())
        {
            go.draw(g);
        }
        for (GameObject go : gos.getBoxes())
        {
            go.draw(g);
        }
        for (GameObject go : gos.getHomes())
        {
            go.draw(g);
        }
        gos.getPlayer().draw(g);
    }

    public class KeyHandler extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_LEFT:
                    eventListener.move(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    eventListener.move(Direction.RIGHT);
                    break;
                case KeyEvent.VK_UP:
                    eventListener.move(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    eventListener.move(Direction.DOWN);
                    break;
                case KeyEvent.VK_R:
                    eventListener.restart();
                    break;
                case KeyEvent.VK_N:
                    eventListener.startNextLevel();
                    break;
            }
        }
    }
}
