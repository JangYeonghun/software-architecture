import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class Seats extends JFrame {
    private int totalNumberOfPeople;
    private int numberOfAdults;
    private int numberOfYouths;
    private int numberOfStudents;
    private int numberOfChildren;
    private int numberOfInfants;

    private Set<String> selectedSeats;
    private JLabel selectedSeatsLabel;
    private JLabel totalSelectedSeatsLabel;
    private JLabel totalPriceLabel;
    private TypePeople typepeople;
    TypePeople typePeople = TypePeople.getInstance(totalNumberOfPeople);
    int sum = typePeople.getTotalNumberOfPeople();

    public Seats(int totalNumberOfPeople, int numberOfAdults, int numberOfYouths,
                 int numberOfStudents, int numberOfChildren, int numberOfInfants, TypePeople typePeople) {

        this.totalNumberOfPeople = totalNumberOfPeople;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
        this.numberOfYouths = numberOfYouths;
        this.numberOfStudents = numberOfStudents;
        this.numberOfInfants = numberOfInfants;
        this.typepeople = typePeople;

        setTitle("좌석 선택");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        selectedSeats = new HashSet<>();

        JPanel seatsPanel = createSeatsPanel();
        add(seatsPanel, BorderLayout.CENTER);

        JPanel infoPanel = createInfoPanel();
        add(infoPanel, BorderLayout.NORTH);

        JButton nextButton = createNextButton();
        add(nextButton, BorderLayout.SOUTH);
    }


    private JPanel createSeatsPanel() {
        JPanel seatsPanel = new JPanel(new GridLayout(5, 5));
        JButton[][] seatButtons = new JButton[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                seatButtons[i][j] = new JButton((i + 1) + "" + (char) ('A' + j));
                seatButtons[i][j].addActionListener(new SeatButtonListener((i + 1) + "" + (char) ('A' + j)));
                seatsPanel.add(seatButtons[i][j]);
            }
        }
        return seatsPanel;
    }

    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel(new GridLayout(3, 1));


        selectedSeatsLabel = new JLabel();
        totalSelectedSeatsLabel = new JLabel();
        JLabel selectedTotalPeopleValueLabel = new JLabel(Integer.toString(totalNumberOfPeople)+"개의 좌석 까지 선택 가능합니다.");
        infoPanel.add(selectedSeatsLabel);

        infoPanel.add(totalSelectedSeatsLabel);

        infoPanel.add(selectedTotalPeopleValueLabel);


        return infoPanel;
    }


    private JButton createNextButton() {
        JButton nextButton = new JButton("예매내역 확인하기");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedSeats.size() != totalNumberOfPeople) {
                    JOptionPane.showMessageDialog(Seats.this, "총 인원수와 선택된 좌석 수가 일치하지 않습니다 \n" +
                            "총 인원수 : "+ sum);
                    return;
                }
                dispose();
                Rvpreview rvpreview = Rvpreview.createInstance(totalNumberOfPeople, numberOfAdults, numberOfYouths,
                        numberOfStudents, numberOfChildren, numberOfInfants,
                        selectedSeats, true);
                rvpreview.setVisible(true);
            }
        });
        return nextButton;
    }

    private class SeatButtonListener implements ActionListener {
        private String seatNumber;

        public SeatButtonListener(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedSeats.contains(seatNumber)) {
                selectedSeats.remove(seatNumber);
                JButton seatButton = (JButton) e.getSource();
                seatButton.setBackground(null);
            } else {
                if (selectedSeats.size() >= totalNumberOfPeople) {
                    JOptionPane.showMessageDialog(
                            Seats.this, "더 이상 좌석을 선택할 수 없습니다.\n" + "선택 가능한 최대 좌석수 : "+sum);
                    return;
                }

                selectedSeats.add(seatNumber);
                JButton seatButton = (JButton) e.getSource();
                seatButton.setBackground(Color.BLUE);
            }

            updateSelectedSeatsLabel();
        }


        private void updateSelectedSeatsLabel() {
            StringBuilder selectedSeatsStr = new StringBuilder();
            for (String seat : selectedSeats) {
                selectedSeatsStr.append(seat).append(", ");
            }
            if (selectedSeatsStr.length() > 0) {
                selectedSeatsStr.delete(selectedSeatsStr.length() - 2, selectedSeatsStr.length());
            }
            selectedSeatsLabel.setText(selectedSeatsStr.toString());
            totalSelectedSeatsLabel.setText("현재 선택된 좌석의 수: " + selectedSeats.size());
        }
    }
}
