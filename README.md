# Student Exam Seating Arrangement System

## Project Overview
This project automates exam seating for students using Java. It reads a CSV file containing student data, assigns seats to rooms based on department, and generates a professional table-format seating plan. A search feature allows finding a student by ID. The system handles large datasets and provides CSV output.

---

## Features
- Read student data from CSV automatically.
- Department-wise seating allocation to rooms.
- Stops assigning after all seats are filled.
- Table-format seating plan saved in CSV (output/seating_plan.csv).
- Preview first 5 students per room in console.
- Search students by ID to locate room and seat.

---

## Folder Structure

```
StudentSeatingSystem/
│
├── input/
│ └── university_students_2000.csv # Input CSV file with student data
├── output/
│ └── seating_plan.csv # Table-format seating plan for all students
├── src/
│ ├── Student.java # Student class
│ ├── SeatingManager.java # Seating allocation, CSV export, search
│ └── Main.java # Main program
└── README.md
```

---

## How to Run
1. Make sure Java is installed on your system.
2. Place your CSV file in `input/` folder.
3. Compile all Java files:
```bash
javac src/*.java
```
4.Run the program:
```
java -cp src Main
```
5.Preview of first 5 students per room will display in console.
6.Full seating plan will be saved in output/seating_plan.csv.
7.You can search for a student by ID when prompted.