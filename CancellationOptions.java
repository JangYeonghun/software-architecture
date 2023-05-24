import javax.swing.*;
import java.awt.*;

public class CancellationOptions extends CancellationTemplate {
    public CancellationOptions(int totalNumberOfPeople, int numberOfAdults, int numberOfYouths,int numberOfStudents,
                               int numberOfChildren ,int numberOfInfants) {
        super(totalNumberOfPeople,numberOfAdults,numberOfYouths,numberOfStudents, numberOfChildren,numberOfInfants);
    }

    @Override
    protected void addButton() {
        JButton reselectMovie = new JButton("영화 다시 선택하기");
        reselectMovie.addActionListener(e -> {
            Info info = new Info();
            info.setVisible(true);
            dispose();
        });
        add(reselectMovie);

        JButton reselectButton = new JButton("총인원 다시 선택하기");
        reselectButton.addActionListener(e -> {
            Peoples people = new Peoples();
            people.setVisible(true);
            dispose();
        });
        add(reselectButton);

        JButton seatSelectButton = new JButton("좌석 다시 선택하기");
        seatSelectButton.addActionListener(e -> {
            Seats seats = new Seats(totalNumberOfPeople,numberOfAdults,
                    numberOfYouths, numberOfStudents,numberOfChildren, numberOfInfants,TypePeople.getInstance(0));
            seats.setVisible(true);
            dispose();
        });
        add(seatSelectButton);
    }
}