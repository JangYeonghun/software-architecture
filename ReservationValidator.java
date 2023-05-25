// 다른 인원들의 값이 0 이고 영유아의 값만 들어왔을 경우

public class ReservationValidator {
    public static boolean isInfantOnlyReservation(int numberOfAdults, int numberOfYouths,
                                                  int numberOfStudents, int numberOfChildren, int numberOfInfants) {
        return numberOfAdults == 0 && numberOfYouths == 0 && numberOfStudents == 0 && numberOfChildren == 0 && numberOfInfants > 0;
    }
}
