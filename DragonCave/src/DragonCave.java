import java.util.Scanner;

public class DragonCave {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "You are in a land full of dragons. In front of you,"
                +"\nyou see two caves. In one cave, the dragon is friendly"
                +"\nand will share his treasure with you. The other dragon"
                +"\nis greedy and hungry and will eat you on sight."
                +"\nwhich cave will you go into? (1 or 2)");
        String choice = sc.nextLine();

        if (choice.equals("1")) {
            System.out.println(
                    "You approach the cave..." +
                    "\nIt is dark and spooky..." +
                    "\nA large dragon jumps out in front of you! He opens his jaws and..." +
                    "\nGobbles you down in one bite!");
        }
            if (choice.equals("2")){
                System.out.println(
                    "You approach the cave..." +
                    "\nIt is dark and spooky..." +
                    "\nA large dragon jumps out in front of you!" +
                    "\nHe gives you an bunch of gold.");
        }
            else{
                System.out.println("That wasn't an option." +
                        "\nA dragon drops from the sky and eats you");
            }





    }
}



