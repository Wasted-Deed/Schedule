import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class Day {
    ArrayList<Pair> pairs;
    String day;
    public Day(ArrayList<Pair> pairs, String day) {
        this.pairs = pairs;
        this.day = day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void draw(Graphics graphics, int x, int y) {
        if (pairs.size() > 0) {
            graphics.setColor(Color.decode("#E5E5E5"));
            graphics.fillRect(x, y, Main.dayW, Main.font.getHeight());
            graphics.setColor(Color.black);
            graphics.drawRect(x, y, Main.dayW, Main.font.getHeight());
            Main.font.drawString(x, y, day, Color.black);
        }
        for (int i = 0; i < pairs.size(); i++) {
            pairs.get(i).draw(graphics, x, y + (i + 1) * Main.font.getHeight());
        }
    }

    public int getY() {
        return (pairs.size() + 1) * Main.font.getHeight();
    }
}
