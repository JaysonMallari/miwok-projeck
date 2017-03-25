package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class NumbersActivity extends AppCompatActivity {
    ArrayList<Word> words  = new ArrayList<Word>(
            Arrays.asList(
                    new Word("one","lutti"),
                    new Word("two","otiiko"),
                    new Word("three","tolooksu"),
                    new Word("four","oyyisa"),
                    new Word("five","massokka"),
                    new Word("six","temmokka"),
                    new Word("seven","kenekaku"),
                    new Word("eight","kawinta"),
                    new Word("nine","wo'e"),
                    new Word("ten","na'aacha")
            ));

    //"two" , "three" , "four" , "five" , "six" , "seven" , "eight" , "nine" ,"ten"));

    /**
     *      Word w  = new Word("one","lutti");
     *      words.add(w);
     *      OR
     *      words.add(new Word("one","lutti"));
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);

        // call the setNumberAdapter onCreate method
        setNumberAdapter();
    }

    //set the number adapter
    public void setNumberAdapter(){
        // Construct a new WordAdapter
        WordAdapter itemsAdapter =  new WordAdapter(this, words);

        // Cast the number Listview
        ListView listView = (ListView) findViewById(R.id.list);

        //set the adapter to our ListView
        listView.setAdapter(itemsAdapter);
    }
}
