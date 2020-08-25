public class Colors {
    int backgroundColor;

    Colors(String mode) {
        switch (mode) {
            case "light":
                backgroundColor = getColor(255, 255, 255);
                break;
            case "dark":
                backgroundColor = App.drawer.color(0, 0, 0);
                break;
        }
    }

    private int getColor(int r, int g, int b) {
        return getColor(r, g, b, 255);
    }

    public static int getColor(int r, int g, int b, int a) {
        return App.drawer.color(r, g, b, a);
    }
}
