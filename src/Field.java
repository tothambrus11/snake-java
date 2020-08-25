public class Field {

    private final Drawer drawer;

    public Field(Drawer drawer) {
        this.drawer = drawer;
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

    }
}
