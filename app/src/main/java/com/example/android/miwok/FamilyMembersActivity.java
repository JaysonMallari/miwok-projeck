package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class FamilyMembersActivity extends AppCompatActivity {
    ArrayList<Word> words = new ArrayList<Word>(
            Arrays.asList(
                    new Word("father", "epe"),
                    new Word("mother", "eta"),
                    new Word("son", "angsi"),
                    new Word("daughter", "tune"),
                    new Word("older brother", "taachi"),
                    new Word("younger brother", "chalitti"),
                    new Word("older sister", "tete"),
                    new Word("younger sister", "kolliti"),
                    new Word("grandmother", "ama"),
                    new Word("grandfather", "paapa")
            ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);

       //call the setFamilyAdapter onCreate method
       setFamilyAdapter();
    }

    // method to setup the adapter
    public void setFamilyAdapter(){
        // Construct a new WordAdapter
        WordAdapter adapter  =  new WordAdapter(this, words);

        // cast  the ListView
        ListView listView = (ListView) findViewById(R.id.list);

        // set the adapter to our ListView
        listView.setAdapter(adapter);
    }
}
