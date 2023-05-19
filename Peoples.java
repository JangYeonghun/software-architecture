import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Peoples extends JFrame {
    private int totalNumberOfPeople;

    public Peoples() {
        setTitle("예약 메인 화면");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new FlowLayout());

        JLabel label = new JLabel("총 인원 수 입력:");
        JTextField textField = new JTextField(10);
        JButton nextButton = new JButton("인원 선택하기");

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalNumberOfPeople = Integer.parseInt(textField.getText());
                dispose();
                TypePeople seatTypeFrame = new TypePeople(totalNumberOfPeople);
                seatTypeFrame.setVisible(true);
            }
        });

        panel.add(label);
        panel.add(textField);
        panel.add(nextButton);

        add(panel, BorderLayout.CENTER);
    }


    public static void main(String[] args) {
        Peoples frame = new Peoples();
        frame.setVisible(true);

    }
}
