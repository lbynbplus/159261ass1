import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpPanel extends JPanel {
    private Homepage mHomepage;
    private JButton mBackButton;

    public HelpPanel(Homepage homepage) {
        mHomepage = homepage;
        setLayout(new BorderLayout());

        JTextArea helpText = new JTextArea("This is a help panel.\n\n"
                + "This is a Snake game.\n\n you can control the snake's movement using the up, down, left, and right arrow keys on the \n keyboard. Please note that the snake cannot turn around.\n"
                + "You need to eat apples to increase the length of the snake and earn 10 points each time. \n The game ends when the snake hits the border or its own body.\n"
                + "You can press the space key to return to the main page but it won't close the current \n window.");
        add(helpText, BorderLayout.CENTER);

        // 添加返回按钮
        mBackButton = new JButton("Back");
        mBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mHomepage.openStartPanel();
            }
        });
        add(mBackButton, BorderLayout.SOUTH);
    }
}
