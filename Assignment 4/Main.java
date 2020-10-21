// Puri Rachele 547938

public class Main {
    public static void main(String[] args) {

        if (args.length < 3)
            System.out.println("Usage: java Main NumberOfStudents NumberOfUndergraduates NumberOfTeahcers");

        // Number of students.
        String s = args[0];
        // Number of undergraduates.
        String u = args[1];
        // Number of teachers.
        String t = args[2];
        // Number of accesses.
        int kStudents;
        int kUndergraduates;
        int kTeachers;
        int numOfStudents = Integer.parseInt(s);
        int numOfUndergraduates = Integer.parseInt(u);
        int numOfTeachers = Integer.parseInt(t);
        int UndergraduatesComputerNumber = (int) (Math.random() * 20);

        Student[] students = new Student[numOfStudents];
        Undergraduate[] undergraduates = new Undergraduate[numOfUndergraduates];
        Teacher[] teachers = new Teacher[numOfTeachers];

        TutorMonitor TM = new TutorMonitor(UndergraduatesComputerNumber);

        // TEACHERS
        kTeachers = (int) (Math.random() * 4);
        while (kTeachers == 0) {
            kTeachers = (int) (Math.random() * 4);
        }
        for (int i = 0; i < numOfTeachers; i++) {
            teachers[i] = new Teacher("Teacher-" + i, 2, TM, kTeachers);
        }

        // UNDERGRADUATES
        kUndergraduates = (int) (Math.random() * 4);
        while (kUndergraduates == 0) {
            kUndergraduates = (int) (Math.random() * 4);
        }
        for (int i = 0; i < numOfUndergraduates; i++) {
            undergraduates[i] = new Undergraduate("Undergraduate-" + i, 1, TM, UndergraduatesComputerNumber,
                    kUndergraduates);
        }

        // STUDENTS
        kStudents = (int) (Math.random() * 4);
        while (kStudents == 0) {
            kStudents = (int) (Math.random() * 4);
        }
        for (int i = 0; i < numOfStudents; i++) {
            students[i] = new Student("Student-" + i, 0, TM, kStudents);
        }

        System.out.println("Number of students accesses: " + kStudents);
        System.out.println("Number of undergraduates accesses: " + kUndergraduates);
        System.out.println("Number of teachers accesses: " + kTeachers);

        // Threads starting.
        for (int i = 0; i < (numOfStudents + numOfTeachers + numOfUndergraduates); i++) {
            if (i < numOfStudents) {
                students[i].start();
            }
            if (i < numOfUndergraduates) {
                undergraduates[i].start();
            }
            if (i < numOfTeachers) {
                teachers[i].start();
            }
        }
    }
}