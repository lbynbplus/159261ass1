import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpPanel extends JPanel {
    private JButton backButton;

    public HelpPanel(SnakeGameGUI snakeGameGUI) {
        // 添加返回按钮
        backButton = new JButton("Return");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                snakeGameGUI.openStartPanel();
            }
        });
        this.add(backButton);

        // 在此处添加帮助信息的内容
        // 例如，添加一个JLabel来显示帮助文本
        JLabel helpLabel = new JLabel("<html><body><p>这里是帮助信息。请根据您的需求自定义帮助文本。</p></body></html>");
        this.add(helpLabel);
    }
}
