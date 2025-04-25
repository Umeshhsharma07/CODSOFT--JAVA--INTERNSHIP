package com.umesh;

import java.util.Scanner;
import java.util.Random;

public class NumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int totalRounds = 0;
        int totalScore = 0;

        System.out.println("🎯 Welcome to the Number Guessing Game!");

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1; // Generates number between 1 to 100
            int maxAttempts = 5;
            int attempts = 0;
            boolean hasWon = false;

            System.out.println("\nRound " + (totalRounds + 1) + ": Guess the number between 1 and 100");
            System.out.println("You have " + maxAttempts + " attempts.");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int guess;

                // Check if user entered an integer
                if (scanner.hasNextInt()) {
                    guess = scanner.nextInt();
                } else {
                    System.out.println("⚠️ Please enter a valid number!");
                    scanner.next(); // clear invalid input
                    continue;
                }

                attempts++;

                if (guess == numberToGuess) {
                    System.out.println("🎉 Correct! You guessed the number in " + attempts + " attempts.");
                    totalScore += (maxAttempts - attempts + 1); // More score for fewer attempts
                    hasWon = true;
                    break;
                } else if (guess < numberToGuess) {
                    System.out.println("📉 Too low!");
                } else {
                    System.out.println("📈 Too high!");
                }
            }

            if (!hasWon) {
                System.out.println("❌ You've used all attempts. The number was: " + numberToGuess);
            }

            totalRounds++;

            System.out.print("Do you want to play another round? (yes/no): ");
            scanner.nextLine(); // Clear newline character
            String response = scanner.nextLine();

            if (!response.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
        }

        System.out.println("\n🎮 Game Over!");
        System.out.println("Total rounds played: " + totalRounds);
        System.out.println("Your total score: " + totalScore);
        scanner.close();
    }
}
