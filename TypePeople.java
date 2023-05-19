import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TypePeople extends JFrame {
    private static volatile TypePeople instance;

    private int totalNumberOfPeople;

    TypePeople(int totalNumberOfPeople) {
        this.totalNumberOfPeople = totalNumberOfPeople;

        setTitle("좌석 유형 선택");
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(6, 2));

        JLabel adultsLabel = new JLabel("성인의 수:");
        JTextField adultsTextField = new JTextField(10);
        JLabel youthsLabel = new JLabel("청소년의 수:");
        JTextField youthsTextField = new JTextField(10);
        JLabel studentsLabel = new JLabel("초등학생의 수:");
        JTextField studentsTextField = new JTextField(10);
        JLabel childrenLabel = new JLabel("미취학 아동의 수:");
        JTextField childrenTextField = new JTextField(10);
        JLabel infantsLabel = new JLabel("영유아의 수:");
        JTextField infantsTextField = new JTextField(10);
        JButton nextButton = new JButton("좌석 선택하기");

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberOfAdults = Integer.parseInt(adultsTextField.getText());
                int numberOfYouths = Integer.parseInt(youthsTextField.getText());
                int numberOfStudents = Integer.parseInt(studentsTextField.getText());
                int numberOfChildren = Integer.parseInt(childrenTextField.getText());
                int numberOfInfants = Integer.parseInt(infantsTextField.getText());

                int total = numberOfAdults + numberOfYouths + numberOfStudents + numberOfChildren + numberOfInfants;
                if (total != totalNumberOfPeople) {
                    JOptionPane.showMessageDialog(TypePeople.this, "입력된 인원 수의 합이 일치하지 않습니다.");
                    return;
                }

                dispose();
                Seats seats = SeatFactory.createSeats(totalNumberOfPeople, numberOfAdults, numberOfYouths, numberOfStudents, numberOfChildren, numberOfInfants);
                seats.setVisible(true);
            }
        });

        panel.add(adultsLabel);
        panel.add(adultsTextField);
        panel.add(youthsLabel);
        panel.add(youthsTextField);
        panel.add(studentsLabel);
        panel.add(studentsTextField);
        panel.add(childrenLabel);
        panel.add(childrenTextField);
        panel.add(infantsLabel);
        panel.add(infantsTextField);
        add(panel, BorderLayout.CENTER);
        add(nextButton,BorderLayout.SOUTH);

    }

    public static synchronized TypePeople getInstance(int totalNumberOfPeople) {
        if (instance == null) {
            instance = new TypePeople(totalNumberOfPeople);
        }
        return instance;
    }
}

class SeatFactory {
    public static Seats createSeats(int totalNumberOfPeople, int numberOfAdults, int numberOfYouths,
                                    int numberOfStudents, int numberOfChildren, int numberOfInfants) {
        return new Seats(totalNumberOfPeople, numberOfAdults, numberOfYouths, numberOfStudents, numberOfChildren, numberOfInfants);
    }
}
