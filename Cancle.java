import javax.swing.*;
import java.awt.*;

public class Cancle extends JFrame {
    private int totalNumberOfPeople;

    public Cancle(int totalNumberOfPeople) {
        this.totalNumberOfPeople = totalNumberOfPeople;

        setTitle("예매 취소");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        JButton reselectButton = new JButton("총인원 다시 선택하기");
        reselectButton.addActionListener(e -> {
            Peoples people = new Peoples();
            people.setVisible(true);
            dispose(); // Cancel 프레임 닫기
        });
        add(reselectButton);

        JButton seatSelectButton = new JButton("좌석 다시 선택하기");
        seatSelectButton.addActionListener(e -> {
            Seats seats = new Seats(totalNumberOfPeople, 0, 0, 0, 0, 0);
            seats.setVisible(true);
            dispose(); // Cancel 프레임 닫기
        });
        add(seatSelectButton);

        JButton exitButton = new JButton("예매 종료");
        exitButton.addActionListener(e -> System.exit(0));
        add(exitButton);
    }
}
