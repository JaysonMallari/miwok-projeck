package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class PhrasesActivity extends AppCompatActivity {

    ArrayList<Word> words = new ArrayList<Word>(
            Arrays.asList(
                    new Word("Where are you going?","minto wuksus"),
                    new Word("What is your name?","tinnә oyaase'nә"),
                    new Word("My name is...","oyaaset..."),
                    new Word("How are you feeling?","michәksәs?"),
                    new Word("I’m feeling good.","kuchi achit"),
                    new Word("Are you coming?","әәnәs'aa?"),
                    new Word("Yes, I’m coming.","hәә’ әәnәm"),
                    new Word("I’m coming.","әәnәm"),
                    new Word("Let’s go.","yoowutis"),
                    new Word("Come here.","әnni'nem")
            ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);

        //call the setPhrasesAdapter onCreate method
        setPhrasesAdapter();
    }

    public void setPhrasesAdapter(){
        // Create a new adapter object
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);

        // find the listview using id
        ListView listView = (ListView) findViewById(R.id.list);

        //set the adapter to our listview
        listView.setAdapter(adapter);

    }
}
