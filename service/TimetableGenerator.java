package service;

import model.Subject;

public class TimetableGenerator {

    private Subject[] subjects;
    private String[] slots;
    private Subject[] timetable;

    public TimetableGenerator(Subject[] subjects, String[] slots) {
        this.subjects = subjects;
        this.slots = slots;
        this.timetable = new Subject[slots.length];
    }

    // Check constraints
    private boolean isSafe(int index, Subject subject) {
        for (int i = 0; i < index; i++) {
            Subject assigned = timetable[i];

            if (assigned != null) {
                // Teacher conflict
                if (assigned.teacher.equals(subject.teacher)) {
                    return false;
                }
                // Room conflict
                if (assigned.room.equals(subject.room)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Backtracking
    public boolean generateTimetable(int index) {
        if (index == subjects.length) {
            return true;
        }

        for (int i = 0; i < slots.length; i++) {
            if (timetable[i] == null && isSafe(i, subjects[index])) {
                timetable[i] = subjects[index];

                if (generateTimetable(index + 1)) {
                    return true;
                }

                // backtrack
                timetable[i] = null;
            }
        }
        return false;
    }

    // Display
    public void printTimetable() {
        System.out.println("\n=== Generated Timetable ===");
        for (int i = 0; i < slots.length; i++) {
            if (timetable[i] != null) {
                System.out.println(slots[i] + " -> " + timetable[i].name +
                        " (" + timetable[i].teacher + ", " + timetable[i].room + ")");
            } else {
                System.out.println(slots[i] + " -> Free");
            }
        }
    }
}