import com.sun.javafx.scene.traversal.Direction;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class GameCore extends GameEngine{
    private boolean upKey, downKey, leftKey, rightKey, spaceKey;
    Image apple;
    Image head;
    Image body;
    private Snake snake;
    boolean gameOver;
    private Apple appleobj;
    private int playerscore;
    private Homepage homepage;

    public GameCore() {
        snake = new Snake(this,250,250,5);
        appleobj = new Apple(this);
        apple = loadImage("src/resources/apple.png");
        head = loadImage("src/resources/head.png");
        body = loadImage("src/resources/dot.png");
        gameOver = false;
        this.playerscore = 0;
        setupWindow(500,500);
        //mPanel.setBackground(Color.BLACK);
        mPanel.setDoubleBuffered(true);
        mPanel.addMouseListener(this);
        mPanel.addMouseMotionListener(this);
        mPanel.addKeyListener(this);
        mFrame.add(mPanel);
        mPanel.repaint();

        //snake.drawS();
        //gamePanel.init();
    }

    public void init(){
    }

    public void start(){

    }

    public void update(double dt) {
        if (spaceKey) {
            homepage.openStartPanel();
        }
        if(gameOver == true) {
            // Don't try to update anything.
            return;
        }
        if (checkCollision(snake.getBody().get(0), 5, appleobj.getLocation(), 5)) {
            appleobj.generateNewApple();
            snake.grow();
            playerscore += 10;
        }
        snake.move();
        //snake.drawS();
    }

    public boolean checkCollision(Point pos1, int r1, Point pos2, int r2) {
        int dx = pos1.x - pos2.x;
        int dy = pos1.y - pos2.y;
        int radiusSum = r1 + r2;
        return dx * dx + dy * dy <= radiusSum * radiusSum;
    }


    public void paintComponent() {
        changeBackgroundColor(black);
        clearBackground(width(), height());
        // If the game is not over yet
        if(gameOver == false) {
            snake.drawS();
            appleobj.draw();
        } else {
            // If the game is over
            // Display GameOver text
            changeColor(white);
            drawText(width()/2-165, height()/2, "GAME OVER!", "Arial", 50);
            drawText(width()/2-70, height()/2+50, "Score: " + playerscore, "Arial", 30);
        }
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                snake.changeDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                snake.changeDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                snake.changeDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                snake.changeDirection(Direction.RIGHT);
                break;
            case KeyEvent.VK_SPACE:
                spaceKey = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                upKey = false;
                break;
            case KeyEvent.VK_DOWN:
                downKey = false;
                break;
            case KeyEvent.VK_LEFT:
                leftKey = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightKey = false;
                break;
            case KeyEvent.VK_SPACE:
                spaceKey = false;
                break;
        }
    }

    public class Snake {
        private GameCore gm;
        private ArrayList<Point> body;  // 蛇身体的位置列表
        private Direction direction;   // 蛇的移动方向
        private int length;
        private int spacing;


        public Snake(GameCore gm, int x, int y, int length) {
            this.gm = gm;
            this.body = new ArrayList<>();
            this.direction = Direction.RIGHT;
            this.spacing = 10;
            this.length = length * spacing;


            for (int i = 0; i < length * spacing; i++) {
                this.body.add(new Point(x - i, y));
            }
            this.direction = Direction.RIGHT; // 初始化蛇的移动方向
            //this.lengthS = length;
        }


        public void changeDirection(Direction newDirection) {
            if (direction == Direction.UP && newDirection == Direction.DOWN
                    || direction == Direction.DOWN && newDirection == Direction.UP
                    || direction == Direction.LEFT && newDirection == Direction.RIGHT
                    || direction == Direction.RIGHT && newDirection == Direction.LEFT) {
                return;
            }
            direction = newDirection;
        }

        public ArrayList<Point> getBody() {
            return body;
        }

        public int getLength() {
            return length;
        }

        public void setDirection(Direction direction) {
            this.direction = direction;
        }

        public void move() {

            Point head = new Point(body.get(0)); // 获取蛇头位置
            switch (direction) {
                case UP:
                    head.translate(0, -1);
                    break;
                case DOWN:
                    head.translate(0, 1);
                    break;
                case LEFT:
                    head.translate(-1, 0);
                    break;
                case RIGHT:
                    head.translate(1, 0);
                    break;
            }

            if (head.x < 5 || head.x > gm.width() - 10 || head.y < 5 || head.y > gm.height() - 10) {
                gm.gameOver = true;
                return;
            }

            // 判断蛇头是否撞到身体
            for (int i = 1; i < body.size(); i++) {
                if (head.equals(body.get(i))) {
                    gm.gameOver = true;
                    return;
                }
            }

            // 将蛇头加入位置列表的头部
            body.add(0, head);

            for (int i = body.size() - 1; i > 1; i--) {
                body.set(i, body.get(i - 1));
            }

            // 移除位置列表的尾部
            body.remove(length);
        }

        public void grow() {
            Point tail = body.get(length - 1);  // 获取蛇尾位置
            for (int i = 0; i < spacing; i++) {
                Point newTail = new Point(tail.x, tail.y + i);
                body.add(newTail);
            }
            length += spacing;
        }

        public void drawS(){
            Point head = new Point(body.get(0));
            int headX = head.x;
            int headY = head.y;
            drawImage(gm.head,headX,headY);
            for (int i = spacing; i < length; i += spacing) {
                Point bodyPart = body.get(i);
                int bodyX = bodyPart.x;
                int bodyY = bodyPart.y;
                drawImage(gm.body,bodyX,bodyY);
            }
        }
    }

    public class Apple {
        private GameCore gm;
        private Point location;
        private final int gridSize = 5;
        private Random random;

        public Apple(GameCore gm) {
            this.gm = gm;
            random = new Random();
            generateNewApple();
        }

        public void generateNewApple() {
            int x = random.nextInt(495);
            int y = random.nextInt(495);
            if(x < 5){
                x += 5;
            }
            if(y < 5){
                y += 5;
            }
            location = new Point(x, y);
        }

        public void draw() {
            drawImage(apple, location.x, location.y);
        }

        public Point getLocation() {
            return location;
        }
    }
}
