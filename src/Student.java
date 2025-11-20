public class Student {
    public int studentId;
    public String name;
    public String department;
    public int year;

    public Student(int studentId, String name, String department, int year) {
        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.year = year;
    }

    @Override
    public String toString() {
        return studentId + " | " + name + " | " + department + " | Year " + year;
    }
}
