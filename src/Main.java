import org.newdawn.slick.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends BasicGame {
    HashMap<String, Program> programs;
    static String l = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    static char[] c = l.toCharArray();
    ArrayList<String> prs = new ArrayList<>();
    int curP = 0;
    String currentTheme = "Центровка и контроль разгрузки ВС";
    static java.awt.Font font1;
    static TrueTypeFont font;
    static int cameraY = 0;
    static int dayW;

    public Main(String title) {
        super(title);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer agc = new AppGameContainer(new Main("Schedule"));
        agc.setDisplayMode(1280, 720, false);
        agc.setShowFPS(false);
        agc.setTargetFrameRate(64);
        agc.start();
    }

    private Day getNullDay(String date) {
        Pair p1 = new Pair("", "", "", 09.00f, 10.30f);
        Pair p2 = new Pair("", "", "", 10.40f, 12.10f);
        Pair p3 = new Pair("", "", "", 12.50f, 14.20f);
        Pair p4 = new Pair("", "", "", 14.30f, 16.00f);
        ArrayList<Pair> pairs = new ArrayList<>();
        pairs.add(p1);
        pairs.add(p2);
        pairs.add(p3);
        pairs.add(p4);
        return new Day(pairs, date);
    }

    private String[] times = {"9:00-10:30", "10:40-12:10", "12:50-14:20", "14:30-16:00"};

    private void loadPlan() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("res\\plan.txt"), StandardCharsets.UTF_8));
        char c;
        StringBuilder line = new StringBuilder();
        ArrayList<Pair> pairs = new ArrayList<>();
        ArrayList<Day> days = new ArrayList<>();
        int count = 0;
        int day = 1;
        int month = 9;
        while (reader.ready()) {
            c = (char) reader.read();
            line.append(c);
            if (c == '}') {
                String[] l = line.toString().split("'");
                pairs.add(new Pair(l[3], l[11], l[15], times[count] + " " + l[7]));
                line.setLength(0);
                count++;
            }
            if (count == 4) {
                String add1 = "";
                String add2 = "";
                if (day < 10) {
                    add1 = "0";
                }
                if (month < 10) {
                    add2 = "0";
                }
                days.add(new Day(pairs, "Дата: " + add1 + day + "." + add2 + month + ".20"));
                pairs = new ArrayList<>();
                count = 0;
                day++;
                if (day == 31) {
                    day = 1;
                    month = (month + 1) % 13;
                }
            }
        }
        ArrayList<Week> weeks = new ArrayList<>();
        weeks.add(new Week(days));
        Program p = new Program(weeks);
        programs.put("План", p);
        reader.close();
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        programs = new HashMap<>();
        Pair p1 = new Pair("Тема 11.2 Описание БГО ВС", "414Б", "Красненко А.Г.", 09.00f, 10.30f);
        Pair p2 = new Pair("Тема 11.2 Описание БГО ВС", "414Б", "Красненко А.Г.", 10.40f, 12.10f);
        Pair p3 = new Pair("Тема 11.2 Описание БГО ВС", "414Б", "Красненко А.Г.", 12.50f, 14.20f);
        Pair p4 = new Pair("Тема 11.2 Описание БГО ВС", "414Б", "Красненко А.Г.", 14.30f, 16.00f);
        ArrayList<Pair> pairs = new ArrayList<>();
        pairs.add(p1);
        pairs.add(p2);
        pairs.add(p3);
        pairs.add(p4);
        Day d1 = new Day(pairs, " 23 Марта, Понедельник");
        ArrayList<Day> days = new ArrayList<>();
        days.add(d1);
        Day d2 = new Day(pairs, " 24 Марта, Вторник");
        days.add(d2);
        days.add(getNullDay(" 25 Марта, Среда"));
        days.add(getNullDay(" 26 Марта, Четверг"));
        days.add(getNullDay(" 27 Марта, Пятница"));
        days.add(getNullDay(" 28 Марта, Суббота"));
        days.add(getNullDay(" 29 Марта, Воскресенье"));
        Week w1 = new Week(days);
        ArrayList<Week> weeks = new ArrayList<>();
        weeks.add(w1);
        Program program = new Program(weeks);
        prs.add("Центровка и контроль разгрузки ВС");
        prs.add("Перевозка опасных грузов воздушным транспортом. 9 категория ИКАО/ИАТА. Базовый курс");
        prs.add("План");
        programs.put("Центровка и контроль разгрузки ВС", program);
        ArrayList<Day> days2 = new ArrayList<>();
        ArrayList<Pair> pairs1 = new ArrayList<>();
        pairs1.add(new Pair("Тема 1.1. Правовые основы перевозки опасных грузов воздушным транспортом", "422",
                "Попова И. О.", 9.0f, 10.30f));
        pairs1.add(new Pair("Тема 2.1. Общие положения и определения", "422",
                "Попова И. О.", 10.40f, 12.10f));
        pairs1.add(new Pair("Тема 2.2. Классы опасных грузов", "422",
                "Попова И. О.", 12.50f, 14.20f));
        pairs1.add(new Pair("Тема 3.1. Классификация опасных грузов по степени опасности. Освобождения для опасных грузов эксплуатанта. Опасные грузы в авиапочте", "422",
                "Попова И. О.", 14.30f, 16.00f));
        Day sec1 = new Day(pairs1, " 23 Марта, Понедельник");
        days2.add(sec1);
        days2.add(getNullDay(" 24 Марта, Вторник"));
        days2.add(getNullDay(" 25 Марта, Среда"));
        days2.add(getNullDay(" 26 Марта, Четверг"));
        days2.add(getNullDay(" 27 Марта, Пятница"));
        days2.add(getNullDay(" 28 Марта, Суббота"));
        days2.add(getNullDay(" 29 Марта, Воскресенье"));
        Week w2 = new Week(days2);
        ArrayList<Week> weeks2 = new ArrayList<>();
        weeks2.add(w2);
        Program pd = new Program(weeks2);
        programs.put("Перевозка опасных грузов воздушным транспортом. 9 категория ИКАО/ИАТА. Базовый курс", pd);
        font1 = new java.awt.Font("Verdana", java.awt.Font.PLAIN, 22);
        font = new TrueTypeFont(font1, false, c);
        dayW = 600;
        try {
            loadPlan();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        currentTheme = prs.get(curP);
        graphics.setBackground(Color.white);
        Image img = new Image("res\\logo.png");
        img.draw(0, 0);
        font.drawString(10, 10 - cameraY, currentTheme, Color.darkGray);
        graphics.setColor(Color.blue);
        programs.get(currentTheme).draw(graphics, 10, font.getHeight() + 10 - cameraY);
        Input in = gameContainer.getInput();
        if (in.isKeyDown(Input.KEY_DOWN)) {
            cameraY += 4;
        }
        if (in.isKeyDown(Input.KEY_UP) && cameraY > 10) {
            cameraY -= 4;
        }
        if (in.isKeyPressed(Input.KEY_RIGHT)) {
            curP = (curP + 1) % prs.size();
        }

        if (in.isKeyPressed(Input.KEY_LEFT)) {
            curP = ((curP - 1) % prs.size() + prs.size()) % prs.size();
        }
    }
}
