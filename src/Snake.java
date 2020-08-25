import processing.core.PApplet;
import processing.event.KeyEvent;

import java.util.ArrayList;

public class Snake {
    Drawer drawer;
    ArrayList<Position> tail;
    int dir;
    boolean isDead = false;
    boolean shouldRemove = true;

    Snake(Drawer drawer, Position startPos, int startDir, int startSize) {
        this.drawer = drawer;
        tail = new ArrayList<>();
        tail.add(startPos);
        for (int i = 0; i < startSize - 1; i++) {
            startPos = startPos.copy();
            startPos.moveDir(startDir);
            tail.add(startPos);
        }
    }


    void draw() {
        drawer.strokeWeight(Drawer.unit * 0.5f);
        drawer.stroke(200);
        for (int i = 1; i < tail.size(); i++) {
            Position a = tail.get(i - 1);
            Position b = tail.get(i);

            if (b.distance(tail.get(i - 1)) == 1) {
                drawer.line((b.x + 0.5f) * Drawer.unit, (b.y + 0.5f) * Drawer.unit, (a.x + 0.5f) * Drawer.unit, (a.y + 0.5f) * Drawer.unit);
            } else {
                var delta = new Position(a.x - b.x, a.y - b.y);
                delta.normalize();
                var copiedB = b.copy();
                copiedB.sub(delta);

                var copiedA = a.copy();
                copiedA.add(delta);

                drawer.line((b.x + 0.5f) * Drawer.unit, (b.y + 0.5f) * Drawer.unit, (copiedB.x + 0.5f) * Drawer.unit, (copiedB.y + 0.5f) * Drawer.unit);
                drawer.line((copiedA.x + 0.5f) * Drawer.unit, (copiedA.y + 0.5f) * Drawer.unit, (a.x + 0.5f) * Drawer.unit, (a.y + 0.5f) * Drawer.unit);
            }
        }

        drawer.fill(255);
        drawer.noStroke();

        drawer.text("Score: " + (tail.size() - 5), 0, 0, 200,200);
    }

    void move() {
        if(shouldRemove) {
            tail.remove(0);
        } else {
            shouldRemove = true;
        }
        var temp = tail.get(tail.size() - 1).copy();
        temp.moveDir(dir);
        if(temp.equals(drawer.field.coinPos)){
            shouldRemove = false;
            drawer.field.randomCoinPos();
        }

        tail.add(temp);
    }

    public void keyPressed(KeyEvent event) {
        int tempDir;

        switch (event.getKeyCode()) {
            case PApplet.UP:
                tempDir = 0;
                break;
            case PApplet.RIGHT:
                tempDir = 1;
                break;
            case PApplet.DOWN:
                tempDir = 2;
                break;
            case PApplet.LEFT:
                tempDir = 3;
                break;
            default:
                return;
        }

        Position head = getHead().copy();
        head.moveDir(tempDir);
        if (!head.equals(tail.get(tail.size() - 2))) {
            dir = tempDir;
        }


    }

    public Position getHead() {
        return tail.get(tail.size() - 1);
    }

    public void killIfAlreadyDead() {
        Position head = getHead();
        for (int i = 0; i < tail.size() - 1; i++) {
            if (tail.get(i).equals(head)) {
                isDead = true;
            }
        }
    }
}
