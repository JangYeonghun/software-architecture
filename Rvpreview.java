import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Set;

public class Rvpreview extends JFrame {
    private int totalNumberOfPeople;
    private int numberOfAdults;
    private int numberOfYouths;
    private int numberOfStudents;
    private int numberOfChildren;
    private int numberOfInfants;
    private Set<String> selectedSeats;

    public Rvpreview(int totalNumberOfPeople, int numberOfAdults, int numberOfYouths, int numberOfStudents,
                     int numberOfChildren, int numberOfInfants, Set<String> selectedSeats) {
        this.totalNumberOfPeople = totalNumberOfPeople;
        this.numberOfAdults = numberOfAdults;
        this.numberOfYouths = numberOfYouths;
        this.numberOfStudents = numberOfStudents;
        this.numberOfChildren = numberOfChildren;
        this.numberOfInfants = numberOfInfants;
        this.selectedSeats = selectedSeats;

        setTitle("예약 내역 요약");
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2));

        JLabel totalNumberOfPeopleLabel = new JLabel("총 인원수: " + totalNumberOfPeople);
        JLabel numberOfAdultsLabel = new JLabel("성인: " + numberOfAdults);
        JLabel numberOfYouthsLabel = new JLabel("청소년: " + numberOfYouths);
        JLabel numberOfStudentsLabel = new JLabel("초등학생: " + numberOfStudents);
        JLabel numberOfChildrenLabel = new JLabel("미취학 아동: " + numberOfChildren);
        JLabel numberOfInfantsLabel = new JLabel("영유아: " + numberOfInfants);

        double totalPrice = calculateTotalPrice();
        JLabel totalPriceLabel = new JLabel("총 가격: " + new DecimalFormat("#,###").format(totalPrice) + "원");

        JLabel selectedSeatsLabel = new JLabel("선택한 좌석: " + selectedSeats.toString());

        add(totalNumberOfPeopleLabel);
        add(new JLabel());
        add(numberOfAdultsLabel);
        add(new JLabel());
        add(numberOfYouthsLabel);
        add(new JLabel());
        add(numberOfStudentsLabel);
        add(new JLabel());
        add(numberOfChildrenLabel);
        add(new JLabel());
        add(numberOfInfantsLabel);
        add(new JLabel());
        add(totalPriceLabel);
        add(selectedSeatsLabel);

        JButton closeButton = new JButton("닫기");
        closeButton.addActionListener(e -> System.exit(0));
        add(closeButton);
    }

    private double calculateTotalPrice() {
        double adultPrice = 10000.0;
        double youthPrice = 6000.0;
        double studentPrice = 4000.0;
        double childPrice = 2000.0;
        double infantPrice = 0.0;

        double totalPrice = numberOfAdults * adultPrice +
                numberOfYouths * youthPrice +
                numberOfStudents * studentPrice +
                numberOfChildren * childPrice +
                numberOfInfants * infantPrice;

        return totalPrice;
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Peoples frame = new Peoples();
            frame.setVisible(true);
        });
    }
}