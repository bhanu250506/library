import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

// Class to store student information
class Student {
    int id;
    String name;
    String phoneNumber;
    String joinDate;
    int fees;

    // Constructor for Student
    public Student(int id, String name, String phoneNumber, String joinDate, int fees) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.joinDate = joinDate;
        this.fees = fees;
    }

    // Method to display student details
    public void displayInfo() {
        System.out.printf("ID: %d\nName: %s\nPhone Number: %s\nJoin Date: %s\nFees Paid: ₹%d\n",
                id, name, phoneNumber, joinDate, fees);
        System.out.println("----------------------------");
    }
}

// Main class for the library management system
public class BhanuLibrary {
    private static final int FEES = 600; // Constant fee amount
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static int studentIdCounter = 1;
    private static int totalFeesCollected = 0;

    public static void main(String[] args) {
        System.out.println("Welcome to Bhanu Library!");
        System.out.println("Standard Fee: ₹" + FEES);
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add New Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search for a Student");
            System.out.println("4. Remove a Student");
            System.out.println("5. Update Student Information");
            System.out.println("6. Display Total Fees Collected");
            System.out.println("7. Show Students by Join Date");
            System.out.println("8. Exit");

            int choice = sc.nextInt();
            sc.nextLine(); // To consume the newline character

            switch (choice) {
                case 1:
                    addNewStudent();
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    removeStudent();
                    break;
                case 5:
                    updateStudentInfo();
                    break;
                case 6:
                    displayTotalFeesCollected();
                    break;
                case 7:
                    showStudentsByDate();
                    break;
                case 8:
                    System.out.println("Exiting... Have a good day!");
                    return;
                default:
                    System.out.println("Invalid choice! Please select again.");
            }
        }
    }

    // Method to add new student information
    private static void addNewStudent() {
        System.out.println("\nEnter Student's Name:");
        String name = sc.nextLine();

        System.out.println("Enter Student's Phone Number:");
        String phoneNumber = sc.nextLine();

        // Get the current date for when the student joined
        String joinDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        // Create a new student and add to the list
        Student student = new Student(studentIdCounter++, name, phoneNumber, joinDate, FEES);
        students.add(student);

        totalFeesCollected += FEES; // Update total fees collected

        System.out.println("Student added successfully!\n");
        System.out.printf("Student Details:\n");
        student.displayInfo();
    }

    // Method to display all students
    private static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("\nNo students have joined yet.");
        } else {
            System.out.println("\nList of students:");
            for (Student student : students) {
                student.displayInfo();
            }
        }
    }

    // Method to search for a student by name or phone number
    private static void searchStudent() {
        System.out.println("\nEnter student's name or phone number to search:");
        String query = sc.nextLine();

        boolean found = false;
        for (Student student : students) {
            if (student.name.equalsIgnoreCase(query) || student.phoneNumber.equals(query)) {
                System.out.println("\nStudent found:");
                student.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No student found with the given details.");
        }
    }

    // Method to remove a student
    private static void removeStudent() {
        System.out.println("\nEnter student ID to remove:");
        int id = sc.nextInt();
        sc.nextLine(); // To consume the newline

        Student toRemove = null;
        for (Student student : students) {
            if (student.id == id) {
                toRemove = student;
                break;
            }
        }

        if (toRemove != null) {
            students.remove(toRemove);
            totalFeesCollected -= FEES; // Deduct fees if the student is removed
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student ID not found.");
        }
    }

    // Method to update student information (phone number)  
    private static void updateStudentInfo() {
        System.out.println("\nEnter student ID to update:");
        int id = sc.nextInt();
        sc.nextLine(); // To consume the newline

        for (Student student : students) {
            if (student.id == id) {
                System.out.println("Enter new phone number:");
                String newPhoneNumber = sc.nextLine();
                student.phoneNumber = newPhoneNumber;
                System.out.println("Student information updated successfully.");
                student.displayInfo(); // Display updated info
                return;
            }
        }

        System.out.println("Student ID not found.");
    }

    // Method to display the total fees collected
    private static void displayTotalFeesCollected() {
        System.out.println("\nTotal Fees Collected: ₹" + totalFeesCollected);
    }

    // Method to show students by join date
    private static void showStudentsByDate() {
        System.out.println("\nEnter the join date (dd-MM-yyyy) to search:");
        String date = sc.nextLine();

        boolean found = false;
        for (Student student : students) {
            if (student.joinDate.equals(date)) {
                student.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No students found who joined on this date.");
        }
    }
}
