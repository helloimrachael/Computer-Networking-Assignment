import java.util.ArrayList;
import java.util.concurrent.*;

public class Main {
    public static void main (String[] args) {

        if(args.length < 3)
            System.out.println("Usage: java Main NumberOfStudents NumberOfUndergraduates NumberOfTeahcers");
        
        // Number of students.
        String s = args[0];
        // Number of undergraduates.
        String u = args[1];
        // Number of teachers.
        String t = args[2];
        // Number of accesses.
        int k;
        int numOfStudents = Integer.parseInt(s);
        int numOfUndergraduates = Integer.parseInt(u);
        int numOfTeachers = Integer.parseInt(t);
        int UndergraduatesComputerNumber = (int) (Math.random()*20);
        
        ArrayList<User> Users = new ArrayList<>();
        Tutor computers = new Tutor();

        // STUDENTS
        k = (int) (Math.random()*4);
        while (k == 0) {
            k = (int) (Math.random()*4);
        }
        System.out.println("Number of students accesses: " + k);
        for(int i = 0; i < numOfStudents; i++) {
            Users.add(new User("Student-" + i, 0, k, computers, UndergraduatesComputerNumber));
        }

        // UNDERGRADUATES
        k = (int) (Math.random()*4);
        while (k == 0) {
            k = (int) (Math.random()*4);
        }
        System.out.println("Number of undergraduates accesses: " + k);
        for(int j = 0; j < numOfUndergraduates; j++) {
            Users.add(new User("Undergraduate-" + j, 1, k, computers, UndergraduatesComputerNumber));
        }
        
        // TEACHERS
        k = (int) (Math.random()*4);
        while (k == 0) {
            k = (int) (Math.random()*4);
        }
        System.out.println("Number of teachers accesses: " + k);
        for(int h = 0; h < numOfTeachers; h++) {
            Users.add(new User("Teacher-" + h, 2, k, computers, UndergraduatesComputerNumber));
        }

        // Thread starting.
        for(int z = 0; z < Users.size(); z++) {
            Users.get(z).start();
            System.out.println(Users.get(z).getName() + " started.");
        }
    }
}