package model;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by butkoav on 19.01.2017.
 */
public class LevelLoader
{
    private Path levels;

    public LevelLoader(Path levels)
    {
        this.levels = levels;
    }

    public GameObjects getLevel(int level)
    {
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Player player = null;
        Set<Home> homes = new HashSet<>();
        int lvlOffset = 0;
        try (RandomAccessFile ras = new RandomAccessFile(levels.toFile(), "r"))
        {
            String line;
            int lvl = 0;
            while (true)
            {
                line = ras.readLine();
                Pattern pattern = Pattern.compile("Maze:\\D+(\\d+?)");
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches())
                {
                    lvl = Integer.parseInt(matcher.group(1));
                    //  line = ras.readLine();
                    if (lvl + lvlOffset == level)
                        break;
                }
                if (ras.getFilePointer() == ras.length())
                {
                    if (lvl == 0) break;
                    lvlOffset = lvl;
                    ras.seek(0);
                }
            }

            ras.readLine();
            ras.readLine();
            ras.readLine();
            ras.readLine();
            ras.readLine();
            ras.readLine();

            int x;
            int y = Model.FIELD_SELL_SIZE / 2;
            while (!(line = ras.readLine()).equals(""))
            {
                x = Model.FIELD_SELL_SIZE / 2;
                for (Character chr : line.toCharArray())
                {
                    switch (chr)
                    {
                        case 'X':
                            walls.add(new Wall(x, y));
                            break;
                        case '*':
                            boxes.add(new Box(x, y));
                            break;
                        case '&':
                            boxes.add(new Box(x, y));
                            homes.add(new Home(x, y));
                            break;
                        case '.':
                            homes.add(new Home(x, y));
                            break;
                        case '@':
                            player = new Player(x, y);
                            break;
                    }

                    x += Model.FIELD_SELL_SIZE;
                }
                y += Model.FIELD_SELL_SIZE;
            }

        }
        catch (IOException e)
        {
          //  ExceptionHandler.log(e);
        }
        GameObjects gos = new GameObjects(walls, boxes, homes, player);
        return gos;
    }


}
