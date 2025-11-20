import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n=========================================");
        System.out.println(" Student Exam Seating Arrangement System ");
        System.out.println("=========================================\n");

        ArrayList<Student> students = SeatingManager.readCSV("input/university_students_2000.csv");
        if (students.isEmpty()) {
            System.out.println("CSV not loaded.");
            return;
        }
        System.out.println("Total Students Loaded: " + students.size());

        // Department-wise sorting
        SeatingManager.sortByDepartment(students);

        // Define rooms
        LinkedHashMap<String, Integer> rooms = new LinkedHashMap<>();
        rooms.put("A101", 50);
        rooms.put("A102", 50);
        rooms.put("B201", 45);
        rooms.put("B202", 30);
        rooms.put("C301", 40);

        // Allocate seats
        HashMap<String, ArrayList<Student>> allocation = SeatingManager.generateSeating(students, rooms);

        //  Only show preview in console (first 5 per room)
        System.out.println("\n--- Preview (First 5 students per room) ---");
        for (String room : allocation.keySet()) {
            ArrayList<Student> list = allocation.get(room);
            System.out.println("Room: " + room);
            for (int i = 0; i < Math.min(5, list.size()); i++) {
                Student s = list.get(i);
                System.out.println((i + 1) + " | " + s.studentId + " | " + s.name + " | " + s.department + " | Year " + s.year);
            }
            if (list.size() > 5) System.out.println("  ...");
            System.out.println();
        }

        //  Save all data in table format CSV
        SeatingManager.saveCSVTable(allocation);

        // Search by ID
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\nDo you want to search a student by ID? (Y/N): ");
            String choice = sc.nextLine().trim().toUpperCase();
            if (!choice.equals("Y")) break;
            System.out.print("Enter Student ID: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            SeatingManager.searchStudent(allocation, id);
        }
        sc.close();
        System.out.println("Program Ended.");
    }
}
