import javax.swing.*;
import java.awt.*;

public abstract class CancellationTemplate extends JFrame {
    protected int totalNumberOfPeople;

    public CancellationTemplate(int totalNumberOfPeople) {
        this.totalNumberOfPeople = totalNumberOfPeople;

        setTitle("예매 취소");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        addButton();

        JButton exitButton = new JButton("예매 종료");
        exitButton.addActionListener(e -> System.exit(0));
        add(exitButton);
    }

    protected abstract void addButton();
}
