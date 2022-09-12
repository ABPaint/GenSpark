import java.util.Random;
import java.util.Scanner;

public class GuesstheNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! What is you name?");
        String name = sc.nextLine();
        System.out.println(
                "Well,"+name+",I am thinking of a number between 0 and 20." +
                "\nTake a guess");
        Random rand = new Random();
        int randomNumber = rand.nextInt(21);
        int guess = 21;
        while (guess != randomNumber){
        guess = sc.nextInt();
        }
    }
}
