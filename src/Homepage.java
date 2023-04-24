import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Homepage extends GameEngine {

    private HelpPanel mHelpPanel;
    private Homepage mHomepage;
    private GameCore gameCore;
    private GameCore.Snake Snake;
    Image apple;
    Image head;
    Image body;

    public static void main(String[] args) {

        Homepage game = new Homepage();
        createGame(game);

    }

    public Homepage() {
        // Create the window
        setupWindow(500,500);

        // Create buttons
        createButtons();
    }

    public void init() {
        apple = loadImage("src/resources/apple.png");
        head = loadImage("src/resources/head.png");
        body = loadImage("src/resources/dot.png");
        //createButtons();
        mHomepage = this;
    }

    public void update(double dt) {

    }

    private void createButtons() {
        // 创建三个按钮
        JButton playButton = new JButton("Play");
        JButton helpButton = new JButton("Help");
        JButton quitButton = new JButton("Quit");

        // 设置按钮样式
        playButton.setPreferredSize(new Dimension(200, 100));
        helpButton.setPreferredSize(new Dimension(200, 100));
        quitButton.setPreferredSize(new Dimension(200, 100));

        // 设置按钮颜色
        playButton.setBackground(Color.WHITE);
        playButton.setForeground(Color.BLACK);
        helpButton.setBackground(Color.WHITE);
        helpButton.setForeground(Color.BLACK);
        quitButton.setBackground(Color.WHITE);
        quitButton.setForeground(Color.BLACK);

        // 设置按钮动作
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameStart();
            }
        });

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 处理 Help 按钮
                mHomepage.openHelpPanel();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 处理 Quit 按钮
                System.exit(0);
            }
        });

        // 添加按钮到窗口
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 0, 0));
        buttonPanel.add(playButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(quitButton);

        mPanel.setLayout(new BorderLayout());
        mPanel.add(buttonPanel, BorderLayout.NORTH);
    }


    public void paintComponent() {

    }

    public void draw(Graphics2D g) {
        // 绘制游戏界面
    }

    public void GameStart(){
        //mPanel.removeAll();
        gameCore = new GameCore();
        //mPanel.add(gameCore.getGamePanel());
        gameCore.gameLoop(30);
        gameCore.start();
        mPanel.revalidate();
        mPanel.repaint();

    }

    public void openHelpPanel() {
        mFrame.remove(mPanel);
        mHelpPanel = new HelpPanel(this);
        mFrame.add(mHelpPanel);
        mFrame.revalidate();
        mFrame.repaint();
    }

    public void openStartPanel() {
        mFrame.remove(mHelpPanel);
        mFrame.add(mPanel);
        mFrame.revalidate();
        mFrame.repaint();
    }

}
