public class ReservationValidator {
    public static boolean isInfantOnlyReservation(int numberOfAdults, int numberOfYouths,
                                                  int numberOfStudents, int numberOfChildren, int numberOfInfants) {
        return numberOfAdults == 0 && numberOfYouths == 0 && numberOfStudents == 0 && numberOfChildren == 0 && numberOfInfants > 0;
    }
}
