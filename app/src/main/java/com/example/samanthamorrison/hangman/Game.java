package com.example.samanthamorrison.hangman;



import android.util.Log;

import java.util.ArrayList;
import java.util.Random;


public class Game {

    private static Game instance = null;

    private final int NUMBER_OF_WORDS = 5;
    private String[] directory;
    private String word;
    private Boolean[] wordMask;
    private ArrayList<Character> correctLetters = new ArrayList<>();
    private ArrayList<Character> incorrectLetters = new ArrayList<>();
    private int triesLeft;

    private Random random = new Random();

    private Game(){}

    /**
     * Gets and returns instance of Game.
     * @return instance.
     */
    public static Game getInstance(){

        if (instance == null){
            instance = new Game();
        }
        return instance;

    }

    /**
     * Starts and resets game
     */
    public void start(){

        this.directory = new String[NUMBER_OF_WORDS];

        this.directory[0] = "apple";
        this.directory[1] = "banana";
        this.directory[2] = "orange";
        this.directory[3] = "pear";
        this.directory[4] = "grape";

        this.setRandomWord();

        this.wordMask = new Boolean[word.length()];

        for(int i = 0; i<this.wordMask.length; i++){
            wordMask[i] = false;
        }

        incorrectLetters.clear();
        correctLetters.clear();

        this.triesLeft = 10;

    }

    private void setRandomWord(){
        this.word = this.directory[random.nextInt(NUMBER_OF_WORDS)];
    }


    public String getWord(){
        return this.word;
    }

    public String getWordMask(){

        String mask = "";

        for (int i=0; i<this.word.length(); i++){

            char character = this.word.charAt(i);

            if(this.wordMask[i]){

                mask += ""+character;

            } else {

                mask += "-";

            }

        }

        return mask;

    }

    public int getTriesLeft(){

        return this.triesLeft;

    }

    public String getWrongLettersAsString(){

        String wrongLettersString = "";
        for (int i = 0; i< incorrectLetters.size(); i++){

            wrongLettersString += incorrectLetters.get(i);

            if(i!=(incorrectLetters.size()-1)){

                wrongLettersString += ", ";

            }

        }
        return wrongLettersString;

    }

    public ArrayList<Character> getIncorrectLetters(){
        return incorrectLetters;
    }

    public ArrayList<Character> getCorrectLetters(){
        return correctLetters;
    }

    private void decreaseTriesByOne(){

        if(this.triesLeft>0){
            this.triesLeft -= 1;
        }

    }

    /**
     * Guess of letter.
     * @param letter - the letter that player has entered.
     */
    public void guess(char letter){

        boolean isCorrect = false;
        char wrongLetter = letter;
        char correctLetter = letter;
        for (int i=0; i<this.word.length(); i++){

            char character = this.word.charAt(i);

            if(letter == character){

                this.wordMask[i] = true;
                correctLetter = letter;
                isCorrect = true;

            } else {

                wrongLetter = letter;
            }
        }

        if(!isCorrect){

            if(!this.searchForLetter(letter, incorrectLetters)){
                this.decreaseTriesByOne();
                incorrectLetters.add(wrongLetter);
            } else {
            }

        } else {

            if(!this.searchForLetter(letter,correctLetters)){

                correctLetters.add(correctLetter);

            } else {
            }
        }
    }

    /**
     * Search for a letter in list of characters
     * @param letter the letter which is guessed
     * @param list the list of characters
     * @return isFound - if guessed letter is found in list return true
     */
    public boolean searchForLetter(char letter, ArrayList<Character> list){

        boolean isFound = false;
        for(int i=0; i<list.size(); i++){

            if(letter == list.get(i)){
                isFound = true;
            }
        }
        return isFound;
    }

    /**
     * Checks if game is lost
     * @return true if player has lost
     */
    public boolean hasLost(){

        if(this.getTriesLeft()==0){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if game is won
     * @return true if player has won
     */
    public boolean hasWon(){

        boolean hasLettersLeft = false;
        for(int i = 0; i<this.wordMask.length; i++){
            if(!wordMask[i]){
                hasLettersLeft = true;
            }
        }
        if(!hasLettersLeft){
            return true;
        } else {
            return false;
        }
    }
}