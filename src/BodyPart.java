import java.util.Objects;

public class BodyPart {
    int x, y;

    public BodyPart(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BodyPart bodyPart = (BodyPart) o;
        return x == bodyPart.x &&
                y == bodyPart.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "BodyPart{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    void moveDir(int dir) {
        switch (dir) {
            case 0:
                y--;
                break;
            case 1:
                x++;
                break;
            case 2:
                y++;
                break;
            case 3:
                x--;
                break;
        }

        if (x == -1) {
            x = Drawer.w - 1;
        } else if (y == -1) {
            y = Drawer.h - 1;
        } else if (x == Drawer.w) {
            x = 0;
        } else if (y == Drawer.h) {
            y = 0;
        }
    }

    BodyPart copy() {
        return new BodyPart(x, y);
    }

    int distance(BodyPart b) {
        return Math.abs(x - b.x) + Math.abs(y - b.y);
    }

    public void add(BodyPart b) {
        x += b.x;
        y += b.y;
    }

    public void normalize() {
        x /= x == 0 ? 1 : Math.abs(x);
        y /= y == 0 ? 1 : Math.abs(y);
    }

    public void sub(BodyPart b) {
        x -= b.x;
        y -= b.y;
    }
}
