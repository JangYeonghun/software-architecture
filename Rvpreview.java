import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Set;

import static javax.swing.text.html.parser.DTDConstants.SYSTEM;

public class Rvpreview extends JFrame {
    private int totalNumberOfPeople;
    private int numberOfAdults;
    private int numberOfYouths;
    private int numberOfStudents;
    private int numberOfChildren;
    private int numberOfInfants;
    private Set<String> selectedSeats;

    private DiscountStrategy discountStrategy; // 할인 전략 객체

    public Rvpreview(int totalNumberOfPeople, int numberOfAdults, int numberOfYouths, int numberOfStudents,
                     int numberOfChildren, int numberOfInfants, Set<String>selectedSeats,
                     DiscountStrategy discountStrategy) {
        this.totalNumberOfPeople = totalNumberOfPeople;
        this.numberOfAdults = numberOfAdults;
        this.numberOfYouths = numberOfYouths;
        this.numberOfStudents = numberOfStudents;
        this.numberOfChildren = numberOfChildren;
        this.numberOfInfants = numberOfInfants;
        this.selectedSeats = selectedSeats;
        this.discountStrategy = discountStrategy; // 할인 전략 주입

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
        double discountedPrice = discountStrategy.calculateDiscount(totalPrice); // 할인 적용
        JLabel totalPriceLabel = new JLabel("총 가격: " + new DecimalFormat("#,###").format(discountedPrice) + "원");

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
        double totalPrice;

        totalPrice = numberOfAdults * adultPrice + numberOfYouths * youthPrice + numberOfStudents * studentPrice + numberOfChildren * childPrice
                + numberOfInfants * infantPrice;

        return totalPrice;
    }

    public static Rvpreview createInstance(int totalNumberOfPeople, int numberOfAdults, int numberOfYouths,
                                           int numberOfStudents, int numberOfChildren, int numberOfInfants,
                                           Set<String> selectedSeats, boolean isInfantOnlyReservation) {
        DiscountStrategy discountStrategy;

        if (totalNumberOfPeople >= 5) {
            discountStrategy = new GroupDiscountStrategy(); // 총 인원이 5명 이상인 경우 그룹 할인 전략
        } else {
            discountStrategy = new DefaultDiscountStrategy(); // 그 외에는 기본 금액 전략
        }

        if (ReservationValidator.isInfantOnlyReservation(numberOfAdults, numberOfYouths,
                numberOfStudents, numberOfChildren, numberOfInfants)) {
            // 영유아로만 구성된 예약은 불가능하므로 메시지 창을 띄우고 null 반환
            JOptionPane.showMessageDialog(null, "영유아로만 구성된 예약은 불가능합니다.", "예약 불가", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return new Rvpreview(totalNumberOfPeople, numberOfAdults, numberOfYouths, numberOfStudents,
                numberOfChildren, numberOfInfants, selectedSeats, discountStrategy);
    }
}
