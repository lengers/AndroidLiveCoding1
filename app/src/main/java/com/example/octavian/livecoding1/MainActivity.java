package com.example.octavian.livecoding1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int trees, salestotal, treesSold;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("tree", 0);
        trees =  prefs.getInt("trees", 0);
        salestotal = prefs.getInt("salestotal", 0);
        treesSold = prefs.getInt("treesSold", 0);
        Log.e("DEBUG", String.valueOf(trees));
        ((TextView) findViewById(R.id.baumCounterTextView)).setText(String.valueOf(trees));

        ((Button) findViewById(R.id.minusButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trees > 0) {
                    trees -= 1;
                    treesSold += 1;
                    SharedPreferences.Editor e = prefs.edit();
                    e.putInt("trees", trees);
                    e.putInt("treesSold", treesSold);
                    e.commit();
                    ((TextView) findViewById(R.id.baumCounterTextView)).setText(String.valueOf(trees));
                }
            }
        });

        ((Button) findViewById(R.id.managementButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( MainActivity.this, ManagementActivity.class));
            }
        });
    }

    @Override
    public void onRestart() {
        super.onRestart();
        //When BACK BUTTON is pressed, the activity on the stack is restarted
        //Do what you want on the refresh procedure here

        prefs = getSharedPreferences("tree", 0);
        trees =  prefs.getInt("trees", 0);
        salestotal = prefs.getInt("salestotal", 0);
        treesSold = prefs.getInt("treesSold", 0);
        Log.e("DEBUG", String.valueOf(trees));
        ((TextView) findViewById(R.id.baumCounterTextView)).setText(String.valueOf(trees));
    }


}
