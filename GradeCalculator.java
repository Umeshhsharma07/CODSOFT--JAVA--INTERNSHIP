package com.umesh;
import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of subjects: ");
        int n = scanner.nextInt();  // Number of subjects

        int totalMarks = 0;
        for (int i = 1; i <= n; i++) {
            System.out.print("Enter marks for subject " + i + " (0–100): ");
            int marks = scanner.nextInt();
            // Validate input range
            if (marks < 0 || marks > 100) {
                System.out.println("Invalid marks! Please enter 0 to 100.");
                i--; // repeat this iteration
                continue;
            }
            totalMarks += marks;
        }

        // Calculate average percentage
        double average = (double) totalMarks / n;

        // Determine grade
        String grade;
        if (average >= 90) {
            grade = "A+";
        } else if (average >= 80) {
            grade = "A";
        } else if (average >= 70) {
            grade = "B";
        } else if (average >= 60) {
            grade = "C";
        } else if (average >= 50) {
            grade = "D";
        } else grade = "F";

        // Display results
        System.out.println("\n=== Result ===");
        System.out.println("Total Marks   : " + totalMarks);
        System.out.printf("Average (%%)   : %.2f%%%n", average);
        System.out.println("Grade         : " + grade);

        scanner.close();
    }
}
