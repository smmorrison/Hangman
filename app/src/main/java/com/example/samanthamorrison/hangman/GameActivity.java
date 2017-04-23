package com.example.samanthamorrison.hangman;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private Game game;
    protected TextView currentWord;
    protected EditText guesses;
    protected TextView triesLeft;
    protected ImageView hangingMan;
    protected TextView guessedLetters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        game = Game.getInstance();
        game.start();

        currentWord = (TextView) findViewById(R.id.current_word);
        guesses = (EditText) findViewById(R.id.guesses);
        triesLeft = (TextView) findViewById(R.id.turns_left);
        hangingMan = (ImageView) findViewById(R.id.murder_gear);
        guessedLetters = (TextView) findViewById(R.id.guessed_letters);

        this.currentWord.setText(game.getWordMask());
        this.triesLeft.setText(game.getTriesLeft()+" tries left.");

    }

    /**
     * Guess is made when user enters letter and clicks on "guess"-button
     * @param view - view which is clicked
     */
    public void guess(View view) {

        String guess = this.guesses.getText().toString();
        if(guess.length()==1){
            char character = guess.charAt(0);
            if(Character.isLetter(character)){

                if(game.searchForLetter(character,game.getCorrectLetters())){
                    this.showToast("This letter has already been used.");
                }

                if(!game.searchForLetter(character,game.getIncorrectLetters())){
                    game.guess(character);
                } else {
                    this.showToast("This letter has already been used.");
                }

            } else {
                this.showToast("Error! Enter one letter only.");
            }

        } else {
            this.showToast("Error! Enter one letter only.");
        }

        this.guesses.setText("");
        this.currentWord.setText(game.getWordMask());
        this.triesLeft.setText(game.getTriesLeft()+" tries left.");
        this.guessedLetters.setText(game.getWrongLettersAsString());

        switch(game.getTriesLeft()){
            case 0:
                hangingMan.setImageResource(R.drawable.hang0);
                break;
            case 1:
                hangingMan.setImageResource(R.drawable.hang1);
                break;
            case 2:
                hangingMan.setImageResource(R.drawable.hang2);
                break;
            case 3:
                hangingMan.setImageResource(R.drawable.hang3);
                break;
            case 4:
                hangingMan.setImageResource(R.drawable.hang4);
                break;
            case 5:
                hangingMan.setImageResource(R.drawable.hang5);
                break;
            case 6:
                hangingMan.setImageResource(R.drawable.hang6);
                break;
            case 7:
                hangingMan.setImageResource(R.drawable.hang7);
                break;
            case 8:
                hangingMan.setImageResource(R.drawable.hang8);
                break;
            case 9:
                hangingMan.setImageResource(R.drawable.hang9);
                break;
            case 10:
                hangingMan.setImageResource(R.drawable.hang10);
                break;
        }

        if(game.hasLost()){
            Intent intent = new Intent(this, ResultActivity.class);
            startActivity(intent);
        }

        if(game.hasWon()){
            Intent intent = new Intent(this, ResultActivity.class);
            startActivity(intent);
        }
    }

    private void showToast(CharSequence str){

        Context context = getApplicationContext();
        CharSequence text = str;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tools, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        Intent intent;
        switch (id){
            case R.id.tool_game:
                intent = new Intent(this, GameActivity.class);
                startActivity(intent);
                break;
            case R.id.tool_info:
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}