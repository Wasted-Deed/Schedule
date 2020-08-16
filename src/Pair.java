import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Pair {
    String name;
    String aud;
    String prep;
    float start = 0;
    float end;
    String diap;
    int w;

    public Pair(String name, String aud, String prep, float start, float end) {
        this.name = name;
        this.aud = aud;
        this.prep = prep;
        this.start = start;
        this.end = end;
    }

    public Pair(String name, String aud, String prep, String diap) {
        this.name = name;
        this.aud = aud;
        this.prep = prep;
        this.diap = diap;
    }

    public void draw(Graphics graphics, int x, int y) {
        int height = Main.font.getHeight();
        String add = "";
        if(y < -height || y > 720) {
            return;
        }
        if (start < 10 && start != 0) {
            add = "0";
        }
        int l1;
        if(start != 0) {
            l1 = Main.font.getWidth(" " + add + start + "0-" + end + "0");
        } else {
            l1 = Main.font.getWidth(diap + " ");
        }
        int l2 = Main.font.getWidth(name + " ");
        int l3 = Main.font.getWidth(" Ауд. " + aud);
        int l4 = Main.font.getWidth(prep + " ");
        graphics.setColor(Color.decode("#E5E5E5"));
        graphics.fillRect(x, y, l1 + l2 + l3 + l4, height);
        graphics.setColor(Color.black);
        graphics.drawRect(x, y, l1, height);
        graphics.drawRect(x + l1, y, l2, height);
        graphics.drawRect(x + l1 + l2, y, l3, height);
        graphics.drawRect(x + l1 + l2 + l3, y, l4, height);
        if (start != 0) {
            Main.font.drawString(x, y, " " + add + start + "0-" + end + "0 " + name + " Ауд." + aud + "  " + prep, Color.black);
        } else {
            Main.font.drawString(x, y, " " + add + diap + " " + name + " Ауд." + aud + "  " + prep, Color.black);
        }
        w = l1 + l2 + l3 + l4;
    }

    public int getW() {
        return w;
    }
}
