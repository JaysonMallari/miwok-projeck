package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class NumbersActivity extends AppCompatActivity {
    ArrayList<String> words  = new ArrayList<String>(
            Arrays.asList("one", "two" , "three" , "four" , "five" , "six" , "seven" , "eight" , "nine" ,"ten"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        display();
    }

    public void display(){
        LinearLayout rootView = (LinearLayout) findViewById(R.id.root_view);


        for (int x=0; x<words.size(); x++){
            TextView wordView = new TextView(this);
            wordView.setText(words.get(x));
            rootView.addView(wordView);
        }
    }
}
