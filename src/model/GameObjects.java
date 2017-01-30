package model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by butkoav on 19.01.2017.
 */
public class GameObjects
{
    private Set<Wall> walls;
    private Set<Box> boxes;
    private Set<Home> homes;
    private Player player;

    public GameObjects(Set<Wall> walls, Set<Box> boxes, Set<Home> homes, Player player)
    {
        this.walls = walls;
        this.boxes = boxes;
        this.homes = homes;
        this.player = player;
    }

    public Set<GameObject> getAll()
    {
        Set<GameObject> allGO = new HashSet<>();
        allGO.addAll(walls);
        allGO.addAll(boxes);
        allGO.addAll(homes);
        allGO.add(player);

        return allGO;
    }

    public Set<Wall> getWalls()
    {
        return walls;
    }

    public Set<Box> getBoxes()
    {
        return boxes;
    }

    public Set<Home> getHomes()
    {
        return homes;
    }

    public Player getPlayer()
    {
        return player;
    }
}
