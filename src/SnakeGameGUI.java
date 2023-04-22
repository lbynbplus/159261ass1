import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeGameGUI {
    private JFrame frame;
    private JPanel startPanel;
    private JButton playButton;
    private JButton helpButton;
    private JButton quitButton;

    private GameCore game;
    private HelpPanel helpPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SnakeGameGUI();
            }
        });
    }

    public SnakeGameGUI() {
        frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        initComponents();
        frame.add(startPanel);
        frame.setVisible(true);
    }

    private void initComponents() {
        startPanel = new JPanel();
        startPanel.setLayout(new GridLayout(3, 1));

        playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openGamePanel();
            }
        });

        helpButton = new JButton("Help");
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openHelpPanel();
            }
        });

        quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        startPanel.add(playButton);
        startPanel.add(helpButton);
        startPanel.add(quitButton);

        helpPanel = new HelpPanel(this);
        game = new GameCore(this);
    }

    private void openGamePanel() {
        frame.remove(startPanel);
        frame.add(game.getGamePanel());
        frame.revalidate();
        frame.repaint();
    }

    private void openHelpPanel() {
        frame.remove(startPanel);
        frame.add(helpPanel);
        frame.revalidate();
        frame.repaint();
    }

    public void openStartPanel() {
        frame.remove(helpPanel);
        frame.remove(game.getGamePanel());
        frame.add(startPanel);
        frame.revalidate();
        frame.repaint();
    }
}