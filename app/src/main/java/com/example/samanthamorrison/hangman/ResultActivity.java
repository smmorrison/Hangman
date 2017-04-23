package com.example.samanthamorrison.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity {

    private Game game;

    protected TextView result;
    protected TextView theWordWas;
    protected TextView triesLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        game = Game.getInstance();

        result = (TextView) findViewById(R.id.result);
        theWordWas = (TextView) findViewById(R.id.the_word_was);
        triesLeft = (TextView) findViewById(R.id.tries_left);

        if(game.hasLost()){
            result.setText("You lost!");
        }

        if(game.hasWon()){
            result.setText("You won!");
        }

        theWordWas.setText("The correct word was: "+game.getWord());

        triesLeft.setText("Tries left: "+game.getTriesLeft());

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

    /**
     * Takes user to main menu
     * @param view - view which is clicked
     */
    public void goToMenu(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}