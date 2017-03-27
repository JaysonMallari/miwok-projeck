package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class PhrasesActivity extends AppCompatActivity {

    MediaPlayer mMediaPLayer;

   final ArrayList<Word> words = new ArrayList<Word>(
            Arrays.asList(
                    new Word("Where are you going?","minto wuksus",R.raw.phrase_where_are_you_going),
                    new Word("What is your name?","tinnә oyaase'nә",R.raw.phrase_what_is_your_name),
                    new Word("My name is...","oyaaset...",R.raw.phrase_my_name_is),
                    new Word("How are you feeling?","michәksәs?",R.raw.phrase_how_are_you_feeling),
                    new Word("I’m feeling good.","kuchi achit",R.raw.phrase_im_feeling_good),
                    new Word("Are you coming?","әәnәs'aa?",R.raw.phrase_are_you_coming),
                    new Word("Yes, I’m coming.","hәә’ әәnәm",R.raw.phrase_yes_im_coming),
                    new Word("I’m coming.","әәnәm",R.raw.phrase_im_coming),
                    new Word("Let’s go.","yoowutis",R.raw.phrase_lets_go),
                    new Word("Come here.","әnni'nem",R.raw.phrase_come_here)
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word  = words.get(position);

                mMediaPLayer = MediaPlayer.create(PhrasesActivity.this, word.getmMediaResouceId());
                mMediaPLayer.start();
            }
        });

    }
}
