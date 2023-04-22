import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameCore extends GameEngine {
    private JPanel gamePanel;
    private JButton backButton;
    private SnakeGameGUI snakeGameGUI;

    public GameCore(SnakeGameGUI snakeGameGUI) {
        this.snakeGameGUI = snakeGameGUI;
        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                draw(g);
            }
        };
        gamePanel.setLayout(new BorderLayout());
        init();

        backButton = new JButton("Return");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                snakeGameGUI.openStartPanel();
            }
        });
        gamePanel.add(backButton, BorderLayout.NORTH);
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    private void draw(Graphics g) {
        // 在此处绘制游戏元素
    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void paintComponent() {

    }
}
