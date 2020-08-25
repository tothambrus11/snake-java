
import processing.core.PApplet;
import processing.event.KeyEvent;

public class Drawer extends PApplet {

    static final String title = "Processing...";
    static Colors colors;
    static final int width = 600;
    static final int height = 600;

    static int w;
    static int h;

    Field field;
    Snake snake;

    static float unit;

    public void setup() {

        surface.setTitle(title);
        if (width < height) {
            w = 10;
            h = (int) Math.floor(height / width * w);
        } else {
            h = 10;
            w = (int) Math.floor(width / height * h);
        }
        unit = height > width ? width * 0.1f : height * 0.1f;

        snake = new Snake(this, new Position(0,w-1), 0, 5);
        field = new Field(this);

        frameRate(20);
    }

    public void settings() {
        App.drawer = this;

        size(width, height);

        colors = new Colors("dark");
    }

    public void draw() {
        field.draw();
        snake.draw();

        if(!snake.isDead){
            if(frameCount % 10 == 0){
                snake.move();
                snake.killIfAlreadyDead();
            }
        } else{
            noStroke();
            textAlign(CENTER, CENTER);
            textSize(30);
            text("GAME OVER!", 0, 0, width, height);
        }
    }

    public void keyPressed(KeyEvent event) {
        snake.keyPressed(event);
        if(key == 'r'){
            setup();
        }
    }
}
