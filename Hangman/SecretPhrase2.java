//Daniel Painter
//6-13-2022
//The class that instantiates the HangmanAscii class. Basically the main game loop.
//Also opens the 'Phrases.txt' file to randomly get a phrase. If it is unable to then there is a list of backup phrases. Although that list is much smaller.

import java.util.Scanner;
import java.io.File;
import java.util.Random;
public class SecretPhrase2 {
    
    public static void main(String[] args) {
        //System.out.print(hangman.HANGMANPICS[0].split("\n")[0]);

        System.out.println("Welcome to Hangman!\nPlease choose a difficulty from the list below:");
        Scanner input = new Scanner(System.in);

        for (String diff : HangmanAscii.difficultyExplanations) {
            System.out.println(diff);
        }


        HangmanAscii.DIFF diff;
        while (true){
            try {
                String userEntry = input.nextLine().toUpperCase();
                diff = HangmanAscii.DIFF.valueOf(userEntry);
                break;
            } catch (Exception e) {
                System.out.println("Incorrect Input... Please enter one of the following [Easy, Normal, Hard, Impossible]");
            }
        }
        
        HangmanAscii hangman = new HangmanAscii(pick_phrase(), diff);

        char status = hangman.check_game_status();
        while (status == 'N'){
            System.out.println("=============================================================================");
            System.out.println(hangman.toString());
            System.out.print("Please enter a letter: ");
            char letter = input.nextLine().toUpperCase().charAt(0);
            String check = hangman.check_letter(letter);
            if(!check.equals("Good")){
                if(check.equals("Guessed")){
                    System.out.println("Letter already guessed!");
                    continue;                        
                }
                else{
                    System.out.println("NOT A VALID LETTER!!!");
                    continue;
                }
                
            }
            hangman.guess_letter(letter);
            status = hangman.check_game_status();
        }

        System.out.println("=============================================================================");
        System.out.println(hangman.toString());
        if(status == 'F'){
            System.out.println("You lost....");
        }
        else{
            System.out.println("YOU WON!!!!");
        }

        System.out.println("Answer: " + hangman.currentAnswer);
        System.out.println("# of Guesses: " + hangman.guesses);
        System.out.println("============================== GAME OVER ====================================");
        input.close();
    }


    public static String pick_phrase(){
        Random rand = new Random();
        String phrase = "ERROR";

        //In the off chance that something goes wrong, and the file is unable to open then the phrase will be pulled from here instead.
        String[] backup_phrases = {"Roll With the Punches",
            "Down And Out",
            "Par For the Course",
            "I Smell a Rat",
            "Cut The Mustard",
            "Beating Around the Bush",
           "Long In The Tooth",
            "Flea Market"};

        try {
            File myObj = new File("Phrases.txt");
            Scanner myReader = new Scanner(myObj);
            for (int i = 0; i < rand.nextInt(100); i++) {
                phrase = myReader.nextLine();
            }
            myReader.close();
            } catch (Exception e) {
                phrase = backup_phrases[rand.nextInt(8)];
        }
        return phrase;
    }
    
}
