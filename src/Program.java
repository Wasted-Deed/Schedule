import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class Program {
    ArrayList<Week> weeks;

    public Program(ArrayList<Week> weeks) {
        this.weeks = weeks;
    }

    public void draw(Graphics g, int x, int y) {
        int count = 0;
        for (Week w : weeks) {
            w.draw(g, x, y + count);
            count += w.getY();
        }
    }
    public void addWeek(Week w) {
        weeks.add(w);
    }
}
