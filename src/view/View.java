package view;

import controller.Controller;
import controller.EventListener;
import model.GameObjects;

import javax.swing.*;

public class View extends JFrame
{
    private Controller controller;
    private Field field;

    public View(Controller controller)
    {
        this.controller = controller;
    }
    private JTextArea textField = new JTextArea(4,500);
    public void init()
    {
        field = new Field(this);
        textField.setEditable(false);
        textField.setText("R - reset level \nN - next level\nP - previous level");
        this.getContentPane().add(textField,"South");
        add(field);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle(String.format("Socoban. Level %s",controller.getCurrentLevel()));
        setVisible(true);
    }


    public void setEventListener(EventListener eventListener)
    {
        field.setEventListener(eventListener);
    }

    public void update()
    {
        field.repaint();
        setTitle(String.format("Socoban. Level %s",controller.getCurrentLevel()));
    }

    public GameObjects getGameObjects()
    {
        return controller.getGameObjects();
    }

    public void completed(int level)
    {
        update();
        JOptionPane.showMessageDialog
                (field,String.format("Level %s completed!",level)
                        ,"Socoban",JOptionPane.INFORMATION_MESSAGE);
        controller.startNextLevel();
    }
}
