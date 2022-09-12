//Daniel Painter
//6-13-2022
//The class that manages the entire game.
/*
 FUNCTIONS
 * guess_letter - Function that controls the action of actually guessing a letter. Calls many other functions
 * check_game_status - check to see if the game is over or not. If not returns an 'N'. If it is then it returns a 'F' or a 'W' depending on whether or not there was a winner.
 * check_letter - checks to see that the given letter is a valid letter. Called by guess_letter.
 * in_letters - checks to see if the given letter is in the valid letters list. Needed to be seperated from check_letter. Called by construct guess.
 * construct_guess - constructs the currentGuess variable when the object is constructed.
 * construct_puzzle - constructs the current Puzzle variable. also called whenever guess_letter is called.
 * toString - outputs the object into a neat looking game board.
 VARIABLES
 * DIFF - enum of difficulties
 * difficultyExplanations - Array of strings displayed at start of program to explain the difference between the difficulties.
 * guesses - the # of guesses made. Invalid guesses also count.
 * currentPhase - the current game phase. or rather the number of body parts on the man.
 * incorrectGuesses - incorrect guesses. Resets after a body part is added to the man. Used for difficulty purposes.
 * difficulty - keeps track of the games difficulty. Is a DIFF enum object
 * currentPuzzle - the current 'puzzle' string. Will often look like this "_ _ _ _ _ _ ' _  _ _ _ _"
 * currentAnswer - the answer to the puzzle. will look like this "Daniel's Code"
 * currentGuess - keeps track of how much of the puzzle the player has guessed. will look something like this "***iel's ***e"
 * validLetters - list of valid letters. This wasn't actually nessecary, but I ended up want to add special characters to the game like ' , . ? so I ended up
    just using this as a way to differeniate them from letters
 * HANGMANPICS - ASCII art of the hangman. Got this off of google. Its the first thing that pops up when you search "ascii art hangman".
    Credit to someone named christ horton for it. Hangman Ascii art taken from here: https://gist.github.com/chrishorton/8510732aa9a80a03c829b09f12e20d9c
 */


import java.util.ArrayList;

public class HangmanAscii{

    enum DIFF{EASY, NORMAL, HARD, IMPOSSIBLE};
    final static String[] difficultyExplanations = {"EASY - 3 incorrect guesses per body part.",
                                    "NORMAL - 2 incorrect guesses per body part.",
                                    "HARD - 1 incorrect guess per body part. *Recommended*",
                                    "IMPOSSIBLE - 1 incorrect guess."};

    int guesses = 0;
    int currentPhase = 0;
    int incorrectGuesses = 0;
    DIFF difficulty = DIFF.EASY;
    String currentPuzzle;
    String currentAnswer;
    String currentGuess;
    ArrayList<Character> guessedLetters = new ArrayList<Character>();
    char[] validLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    HangmanAscii(String _puzzle, DIFF _diff){
        currentAnswer = _puzzle;
        difficulty = _diff;
        construct_guess();
        construct_puzzle();
    }


    public char check_game_status(){

        if (currentPhase >= 6){
            return 'F';
        }
        boolean win = true;
        for (int i = 0; i < currentGuess.length(); i++) {
            if (currentGuess.charAt(i) == '*'){
                win = false;
            }
        }
        if(win)
            return 'W';

        return 'N';
    }

    public String check_letter(char _letter){
        for (int letter : guessedLetters) {
            if (letter == _letter) {
                return "Guessed";
            }
        }
        if(in_letters(_letter)){
            return "Good";
        }
        return "Invalid";
    }

    public void construct_guess(){
        StringBuilder guess = new StringBuilder();
        String answer = currentAnswer.toUpperCase();
        for (int i = 0; i < currentAnswer.length(); i++) {
            if(in_letters(answer.charAt(i))){
                guess.append("*");
            }else{
                guess.append(currentAnswer.charAt(i));
            }
        }
        System.out.print(guess.toString());
        currentGuess = guess.toString();
    }

    public boolean in_letters(char _letter){
        for (int letter : validLetters) {
            if (letter == _letter) {
                return true;
            }
        }
        return false;
    }
    public void construct_puzzle(){
        StringBuilder puzzle = new StringBuilder();
        for (int i = 0; i < currentGuess.length(); i++) {
            if(currentGuess.charAt(i) == '*'){
                puzzle.append("_");
            }else{
                puzzle.append(currentGuess.charAt(i));
            }
            puzzle.append(" ");
        }
        currentPuzzle = puzzle.toString();
    }

    
    public void guess_letter(char letter){
        String answer = currentAnswer.toUpperCase();
        StringBuilder newGuess = new StringBuilder();
        boolean letterFound = false;
        for (int i = 0; i < answer.length(); i++) {
            if (currentGuess.charAt(i) == '*'){
                if (answer.charAt(i) == letter){
                    newGuess.append(currentAnswer.charAt(i));
                    letterFound = true;
                }
                else{
                    newGuess.append('*');
                }
            }
            else{
                newGuess.append(currentGuess.charAt(i));
            }
        }
        if(!letterFound)
            incorrectGuesses++;
            switch(difficulty){
                case EASY:
                    if(incorrectGuesses == 3){
                        currentPhase++;
                        incorrectGuesses = 0;
                    }
                    break;
                case NORMAL:
                    if(incorrectGuesses == 2){
                        currentPhase++;
                        incorrectGuesses = 0;
                    }
                    break;
                case HARD:
                    if(incorrectGuesses == 1){
                        currentPhase++;
                        incorrectGuesses = 0;
                    }
                case IMPOSSIBLE:
                    if(incorrectGuesses == 1){
                        currentPhase = 6;
                        incorrectGuesses = 0;
                    }
                    break;
                default:
                    break;
            }

            
        guessedLetters.add(letter);
        currentGuess = newGuess.toString();
        construct_puzzle();
        guesses ++;
        
    }

    @Override
    public java.lang.String toString() {
        String[] strings = HANGMANPICS[currentPhase].split("\n");
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            output.append(strings[i]);
            if(i==0){
                output.append("   Guessed Letters: ");
            }else if(i < 3){
                output.append("                    ");
            }

            if(i * 10 < guessedLetters.size()){
                for (int j = i * 10 ; j < i * 10 + 10; j++) {
                    if(j >= guessedLetters.size()){
                        break;
                    }
                    output.append(guessedLetters.get(j) + ", ");
                }

            }

            
            if(i == strings.length - 1)
                output.append(" Current Puzzle: " + currentPuzzle);
            else
                output.append("\n");
        }
        return output.toString();

    }

    //It's usually against practice to have variables at the bottom of a class, but considering how big and clunky this one is, that's where I'm putting it.
    //Hangman Ascii art taken from here: https://gist.github.com/chrishorton/8510732aa9a80a03c829b09f12e20d9c
    String[] HANGMANPICS = {"""
  +---+
  |   |
      |
      |
      |
      |
=========""", """
  +---+
  |   |
  O   |
      |
      |
      |
=========""", """
  +---+
  |   |
  O   |
  |   |
      |
      |
=========""", """
  +---+
  |   |
  O   |
 /|   |
      |
      |
=========""", """
  +---+
  |   |
  O   |
 /|\\  |
      |
      |
=========""", """
  +---+
  |   |
  O   |
 /|\\  |
 /    |
      |
=========""", """
  +---+
  |   |
  O   |
 /|\\  |
 / \\  |
      |
========="""};
}
