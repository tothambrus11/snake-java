import processing.core.PApplet;
import processing.event.KeyEvent;

import java.util.ArrayList;

public class Snake {
    Drawer drawer;
    ArrayList<BodyPart> tail;
    int dir;
    boolean isDead = false;

    Snake(Drawer drawer, BodyPart startPos, int startDir, int startSize) {
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
            BodyPart a = tail.get(i - 1);
            BodyPart b = tail.get(i);

            if (b.distance(tail.get(i - 1)) == 1) {
                drawer.line((b.x + 0.5f) * Drawer.unit, (b.y + 0.5f) * Drawer.unit, (a.x + 0.5f) * Drawer.unit, (a.y + 0.5f) * Drawer.unit);
            } else {
                var delta = new BodyPart(a.x - b.x, a.y - b.y);
                delta.normalize();
                var copiedB = b.copy();
                copiedB.sub(delta);

                var copiedA = a.copy();
                copiedA.add(delta);

                drawer.line((b.x + 0.5f) * Drawer.unit, (b.y + 0.5f) * Drawer.unit, (copiedB.x + 0.5f) * Drawer.unit, (copiedB.y + 0.5f) * Drawer.unit);
                drawer.line((copiedA.x + 0.5f) * Drawer.unit, (copiedA.y + 0.5f) * Drawer.unit, (a.x + 0.5f) * Drawer.unit, (a.y + 0.5f) * Drawer.unit);
            }
        }
    }

    void move() {
        tail.remove(0);
        var temp = tail.get(tail.size() - 1).copy();
        temp.moveDir(dir);

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

        BodyPart head = getHead().copy();
        head.moveDir(tempDir);
        if (!head.equals(tail.get(tail.size() - 2))) {
            dir = tempDir;
        }


    }

    public BodyPart getHead() {
        return tail.get(tail.size() - 1);
    }

    public void killIfAlreadyDead() {
        BodyPart head = getHead();
        for (int i = 0; i < tail.size() - 1; i++) {
            if (tail.get(i).equals(head)) {
                isDead = true;
            }
        }
    }
}
