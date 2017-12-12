package com.example.octavian.livecoding1;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class ManagementActivity extends AppCompatActivity {

    int trees, salestotal, treesSold;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);

        prefs = getSharedPreferences("tree", 0);
        trees = prefs.getInt("trees", 0);
        salestotal = prefs.getInt("salestotal", 0);
        treesSold = prefs.getInt("treesSold", 0);

        ((TextView) findViewById(R.id.gesamtTextView)).setText(String.valueOf(treesSold * 30));

        final NumberPicker numberPicker = findViewById(R.id.numberPicker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(2000);
        numberPicker.setValue(1);

        ((Button) findViewById(R.id.addButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor e = prefs.edit();
                e.putInt("trees", trees + numberPicker.getValue());
                e.commit();
                Toast.makeText(ManagementActivity.this, "Es wurden " + String.valueOf(numberPicker.getValue()) + " neue Bäume hinzugefügt.", Toast.LENGTH_LONG).show();

                numberPicker.setValue(1);
            }
        });

        ((Button) findViewById(R.id.resetButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor e = prefs.edit();
                e.putInt("trees", 0);
                e.commit();
                Toast.makeText(ManagementActivity.this, "Alle Bäume wurden entfernt.", Toast.LENGTH_LONG).show();
            }
        });
    }


}
