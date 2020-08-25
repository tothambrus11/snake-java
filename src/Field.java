public class Field {

    private final Drawer drawer;
    public Position coinPos;

    public Field(Drawer drawer) {
        this.drawer = drawer;
        randomCoinPos();
    }

    void draw() {
        drawer.background(0);

        drawer.strokeWeight(1);
        drawer.stroke(40);
        for (int i = 1; i < Drawer.w; i++) {
            drawer.line(Drawer.unit * i, 0, Drawer.unit * i, Drawer.height);
        }
        for (int i = 1; i < Drawer.h; i++) {
            drawer.line(0, Drawer.unit * i, Drawer.height, Drawer.unit * i);
        }

        drawer.fill(255, 255, 0);
        drawer.noStroke();
        drawer.ellipse((coinPos.x + 0.5f) * Drawer.unit, (coinPos.y + 0.5f) * Drawer.unit, Drawer.unit*0.5f, Drawer.unit*0.5f);

    }

    public void randomCoinPos() {
        Position p = new Position(0, 0);
        do {
            p.x = (int) Math.round(Math.random() * (Drawer.w - 1));
            p.y = (int) Math.round(Math.random() * (Drawer.h - 1));
        } while (drawer.snake.tail.contains(p));
        coinPos = p;
    }
}
