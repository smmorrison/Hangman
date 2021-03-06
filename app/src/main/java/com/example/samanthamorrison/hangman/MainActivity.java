package com.example.samanthamorrison.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
     * Starts game
     * @param view - view which is clicked
     */
    public void goToGame(View view) {

        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);

    }

    /**
     * Takes user to view with information
     * @param view - view which is clicked
     */
    public void goToAbout(View view) {

        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);

    }
}