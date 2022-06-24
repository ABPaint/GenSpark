import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class GuesstheNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! What is your name?");
        String name = sc.nextLine();

        Random rand = new Random();
        int randomNumber = rand.nextInt(21);
        int guess = 21;
        int numberOfGuesses = 0;
        String again = "y";

        while (again.equals("y")) {
            System.out.println(
                    "Well, "+name+",I am thinking of a number between 0 and 20." +
                    "\nTake a guess");
            while (guess != randomNumber) {
                guess = sc.nextInt();
                numberOfGuesses++;
                if (guess > randomNumber)
                    System.out.println("Your number is too high.\nGuess again.");
                if (guess < randomNumber)
                    System.out.println("Your number is too low.\nGuess again.");

            }
            System.out.println(
                    "Good job, "+name+"! You guessed my number in "+ numberOfGuesses+" guesses!" +
                            "\nWould you line to play again? (y or n)");
            again = sc.next();
            guess = 21;
            randomNumber = rand.nextInt(21);
            numberOfGuesses = 0;


            }

        }
    }

