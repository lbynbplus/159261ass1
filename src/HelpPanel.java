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

        // 添加帮助文本
        JTextArea helpText = new JTextArea("This is a help panel.");
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
