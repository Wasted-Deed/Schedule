import org.newdawn.slick.Graphics;

import java.awt.*;
import java.util.ArrayList;

public class Week {
    ArrayList<Day> days;

    public Week(ArrayList<Day> days) {
        this.days = days;
    }

    public void draw(Graphics g, int x, int y) {
        int count = 0;
        for (Day day : days) {
            day.draw(g, x, y + count);
            count += day.getY();
        }
    }

    public int getY() {
        int count = 0;
        for (Day day : days) {
            count += day.getY();
        }
        return count;
    }
}
