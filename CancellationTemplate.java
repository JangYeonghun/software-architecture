import javax.swing.*;
import java.awt.*;

public abstract class CancellationTemplate extends JFrame {
    protected int totalNumberOfPeople;
    protected int numberOfAdults;
    protected int numberOfChildren;
    protected int numberOfYouths;
    protected int numberOfStudents;
    protected int numberOfInfants;

    public CancellationTemplate(int totalNumberOfPeople, int numberOfAdults,int numberOfYouths ,int numberOfStudents,int numberOfChildren
                                ,int numberOfInfants) {
        this.totalNumberOfPeople = totalNumberOfPeople;
        this.numberOfAdults = numberOfAdults;
        this.numberOfYouths = numberOfYouths;
        this.numberOfStudents = numberOfStudents;
        this.numberOfChildren = numberOfChildren;
        this.numberOfInfants = numberOfInfants;


        setTitle("예매 취소");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        addButton();

        JButton exitButton = new JButton("예매 종료");
        exitButton.addActionListener(e -> System.exit(0));
        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "영화 예매가 종료되었습니다.");
            System.exit(0);
        });
        add(exitButton);
    }

    protected abstract void addButton();
}
