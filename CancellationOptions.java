import javax.swing.*;

public class CancellationOptions extends CancellationTemplate {
    private int[] individualCounts; // 각각의 인원 수를 저장할 배열

    public CancellationOptions(int totalNumberOfPeople) {
        super(totalNumberOfPeople);
        this.individualCounts = individualCounts;
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
            Seats seats = new Seats(totalNumberOfPeople, individualCounts[0], individualCounts[1], individualCounts[2], individualCounts[3], individualCounts[4]);
            seats.setVisible(true);
            dispose();
        });
        add(seatSelectButton);
    }
}
