package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class ColorsActivity extends AppCompatActivity {

    ArrayList<Word> words  = new ArrayList<Word>(
            Arrays.asList(
                    new Word("red","wetetti"),
                    new Word("green","chokokki"),
                    new Word("brown","takaakki"),
                    new Word("gray","topoppi"),
                    new Word("black","kululli"),
                    new Word("white","kelelli"),
                    new Word("dusty yellow","ṭopiisә"),
                    new Word("mustard yellow","chiwiiṭә")

            ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);

        //call setColorAdapter onCreate method
        setColorAdapter();
    }

    public void setColorAdapter(){
        //Constructed a new adapter
        WordAdapter adapter = new WordAdapter(this, words);

        // fide the listview using id and Cast
        ListView listView = (ListView) findViewById(R.id.list);

        //set the adapter for the color listview
        listView.setAdapter(adapter);
    }
}
