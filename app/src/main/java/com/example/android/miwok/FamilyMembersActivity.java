package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class FamilyMembersActivity extends AppCompatActivity {

    MediaPlayer mMediaPlayer;

    final ArrayList<Word> words = new ArrayList<Word>(
            Arrays.asList(
                    new Word("father", "epe",R.drawable.family_father, R.raw.family_father),
                    new Word("mother", "eta",R.drawable.family_mother, R.raw.family_mother),
                    new Word("son", "angsi",R.drawable.family_son, R.raw.family_son),
                    new Word("daughter", "tune",R.drawable.family_daughter, R.raw.family_daughter),
                    new Word("older brother", "taachi",R.drawable.family_older_brother, R.raw.family_older_brother),
                    new Word("younger brother", "chalitti",R.drawable.family_younger_brother, R.raw.family_younger_brother),
                    new Word("older sister", "tete",R.drawable.family_older_sister, R.raw.family_older_sister),
                    new Word("younger sister", "kolliti",R.drawable.family_younger_sister, R.raw.family_younger_sister),
                    new Word("grandmother", "ama",R.drawable.family_grandmother, R.raw.family_grandmother),
                    new Word("grandfather", "paapa",R.drawable.family_grandfather, R.raw.family_grandfather)
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
        WordAdapter adapter  =  new WordAdapter(this, words, R.color.category_family);

        // cast  the ListView
        ListView listView = (ListView) findViewById(R.id.list);

        // set the adapter to our ListView
        listView.setAdapter(adapter);

        // set audio for each item position on our listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word  = words.get(position);

                mMediaPlayer = MediaPlayer.create(FamilyMembersActivity.this, word.getmMediaResouceId());
                mMediaPlayer.start();
            }
        });
    }
}
