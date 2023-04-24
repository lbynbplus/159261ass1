import com.sun.javafx.scene.traversal.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameCore extends GameEngine{
    private JPanel gamePanel;
    private Homepage homepage;
    private boolean upKey, downKey, leftKey, rightKey, spaceKey;
    Image apple;
    Image head;
    Image body;
    private Snake snake;
    boolean gameOver;

    public GameCore(Homepage homepage) {
        snake = new Snake(250,250,5);
        apple = loadImage("src/resources/apple.png");
        head = loadImage("src/resources/head.png");
        body = loadImage("src/resources/dot.png");
        gameOver = false;
        gamePanel = new JPanel();
        gamePanel.setBackground(Color.BLACK);
        gamePanel.setDoubleBuffered(true);
        gamePanel.addMouseListener(this);
        gamePanel.addMouseMotionListener(this);
        //snake.drawS();
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    public void update(double dt) {
        if(gameOver == true) {
            // Don't try to update anything.
            return;
        }
        if (upKey) {
            // 处理向上移动
        }
        if (downKey) {
            // 处理向下移动
        }
        if (leftKey) {
            // 处理向左移动
        }
        if (rightKey) {
            // 处理向右移动
        }
        if (spaceKey) {
            // 处理空格键
        }
        snake.move();
    }


    public void paintComponent() {
        changeBackgroundColor(black);
        clearBackground(width(), height());

        // If the game is not over yet
        if(gameOver == false) {
            snake.drawS();
            //drawText(width()/2-165, height()/2, "GAME OVER!", "Arial", 50);
        } else {
            // If the game is over
            // Display GameOver text
            changeColor(white);
            drawText(width()/2-165, height()/2, "GAME OVER!", "Arial", 50);
        }
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                upKey = true;
                break;
            case KeyEvent.VK_DOWN:
                downKey = true;
                break;
            case KeyEvent.VK_LEFT:
                leftKey = true;
                break;
            case KeyEvent.VK_RIGHT:
                rightKey = true;
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
        private int length;            // 蛇的长度

        public Snake(int x, int y, int length) {
            this.gm = gm;
            this.body = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                this.body.add(new Point(x - i, y)); // 初始化蛇身体的位置
            }
            this.direction = Direction.RIGHT;      // 初始化蛇的移动方向
            this.length = length;
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
            Point head = new Point(body.get(0));  // 获取蛇头位置
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
            body.add(0, head);  // 将蛇头加入位置列表的头部
            body.remove(length); // 移除位置列表的尾部
        }

        public void grow() {
            Point tail = new Point(body.get(length - 1));  // 获取蛇尾位置
            switch (direction) {
                case UP:
                    tail.translate(0, 1);
                    break;
                case DOWN:
                    tail.translate(0, -1);
                    break;
                case LEFT:
                    tail.translate(1, 0);
                    break;
                case RIGHT:
                    tail.translate(-1, 0);
                    break;
            }
            body.add(tail);  // 将新的蛇尾加入位置列表的尾部
            length++;        // 增加蛇的长度
        }

        public void drawS(){
            Point head = new Point(body.get(0)); // 蛇头是列表中的第一个元素

            int headX = head.x; // 获取蛇头的x值
            int headY = head.y;
            drawImage(gm.head,headX,headY);
            for (int i = 1; i < length; i++) {
                Point bodyPart = body.get(i);
                int bodyX = bodyPart.x;
                int bodyY = bodyPart.y;
                drawImage(gm.head,bodyX,bodyY);
            }
        }
    }
}
