import model.Subject;
import service.TimetableGenerator;

public class Main {
    public static void main(String[] args) {

        Subject[] subjects = {
                new Subject("Math", "Mr. A", "R1"),
                new Subject("Physics", "Mr. B", "R2"),
                new Subject("Chemistry", "Mr. A", "R3")
        };

        String[] slots = {
                "9AM", "10AM", "11AM"
        };

        TimetableGenerator generator = new TimetableGenerator(subjects, slots);

        if (generator.generateTimetable(0)) {
            generator.printTimetable();
        } else {
            System.out.println("No valid timetable found.");
        }
    }
}