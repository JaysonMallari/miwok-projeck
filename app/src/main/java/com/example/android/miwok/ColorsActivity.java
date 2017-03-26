package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class ColorsActivity extends AppCompatActivity {

    ArrayList<Word> words  = new ArrayList<Word>(
            Arrays.asList(
                    new Word("red","wetetti",R.drawable.color_red),
                    new Word("green","chokokki",R.drawable.color_green),
                    new Word("brown","takaakki",R.drawable.color_brown),
                    new Word("gray","topoppi",R.drawable.color_gray),
                    new Word("black","kululli",R.drawable.color_black),
                    new Word("white","kelelli",R.drawable.color_white),
                    new Word("dusty yellow","ṭopiisә",R.drawable.color_dusty_yellow),
                    new Word("mustard yellow","chiwiiṭә",R.drawable.color_mustard_yellow)

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
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);

        // fide the listview using id and Cast
        ListView listView = (ListView) findViewById(R.id.list);

        //set the adapter for the color listview
        listView.setAdapter(adapter);
    }
}
