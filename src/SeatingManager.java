import java.io.*;
import java.util.*;

public class SeatingManager {

    // Read CSV
    public static ArrayList<Student> readCSV(String csvPath) {
        ArrayList<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length < 4) continue;
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String dept = parts[2].trim();
                int year = Integer.parseInt(parts[3].trim());
                students.add(new Student(id, name, dept, year));
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }
        return students;
    }

    // Simple Department-wise Sort
    public static void sortByDepartment(ArrayList<Student> students) {
        for (int i = 0; i < students.size(); i++) {
            for (int j = i + 1; j < students.size(); j++) {
                Student s1 = students.get(i);
                Student s2 = students.get(j);
                if (s1.department.compareTo(s2.department) > 0 ||
                        (s1.department.equals(s2.department) && s1.studentId > s2.studentId)) {
                    Student temp = students.get(i);
                    students.set(i, students.get(j));
                    students.set(j, temp);
                }
            }
        }
    }

    // Seating assignment using simple loops
    public static HashMap<String, ArrayList<Student>> generateSeating(ArrayList<Student> students,
            LinkedHashMap<String, Integer> rooms) {

        HashMap<String, ArrayList<Student>> allocation = new LinkedHashMap<>();
        for (String room : rooms.keySet()) {
            allocation.put(room, new ArrayList<Student>());
        }

        int totalSeats = 0;
        for (String room : rooms.keySet()) totalSeats += rooms.get(room);

        int assignedCount = 0;
        int index = 0;

        for (String room : rooms.keySet()) {
            int capacity = rooms.get(room);
            ArrayList<Student> list = allocation.get(room);

            for (int i = 0; i < capacity; i++) {
                if (index >= students.size() || assignedCount >= totalSeats) break;
                list.add(students.get(index));
                index++;
                assignedCount++;
            }
        }

        return allocation;
    }

    // Print table in console
    public static void printTable(HashMap<String, ArrayList<Student>> allocation) {
        for (String room : allocation.keySet()) {
            System.out.println("Room: " + room);
            System.out.printf("%-6s %-8s %-20s %-10s %-5s\n", "Seat", "ID", "Name", "Department", "Year");
            ArrayList<Student> list = allocation.get(room);
            for (int i = 0; i < list.size(); i++) {
                Student s = list.get(i);
                System.out.printf("%-6d %-8d %-20s %-10s %-5d\n", i + 1, s.studentId, s.name, s.department, s.year);
            }
            System.out.println();
        }
    }

// Save all seating in table format CSV
public static void saveCSVTable(HashMap<String, ArrayList<Student>> allocation) {
    File dir = new File("output");
    if (!dir.exists()) dir.mkdir();

    try (BufferedWriter bw = new BufferedWriter(new FileWriter("output/seating_plan.csv"))) {
        // Header
        String header = String.format("%-6s %-8s %-20s %-12s %-5s %-8s\n", "Seat", "ID", "Name", "Department", "Year", "Room");
        bw.write(header);
        bw.write("-------------------------------------------------------------------------------\n");

        for (String room : allocation.keySet()) {
            ArrayList<Student> list = allocation.get(room);
            for (int i = 0; i < list.size(); i++) {
                Student s = list.get(i);
                String line = String.format("%-6d %-8d %-20s %-12s %-5d %-8s\n", i + 1, s.studentId, s.name, s.department, s.year, room);
                bw.write(line);
            }
            bw.write("-------------------------------------------------------------------------------\n");
        }
        System.out.println("All seating saved in table format CSV: output/seating_plan.csv\n");
    } catch (IOException e) {
        System.out.println("Error saving CSV table: " + e.getMessage());
    }
}

    // Search by ID
    public static void searchStudent(HashMap<String, ArrayList<Student>> allocation, int searchId) {
        boolean found = false;
        for (String room : allocation.keySet()) {
            ArrayList<Student> list = allocation.get(room);
            for (int i = 0; i < list.size(); i++) {
                Student s = list.get(i);
                if (s.studentId == searchId) {
                    System.out.println("Student Found!");
                    System.out.println("ID: " + s.studentId + " | Name: " + s.name + " | Dept: " + s.department + " | Year: " + s.year);
                    System.out.println("Room: " + room + " | Seat: " + (i + 1));
                    found = true;
                    return;
                }
            }
        }
        if (!found) System.out.println("Student ID " + searchId + " not assigned (Seats Full)");
    }
}
