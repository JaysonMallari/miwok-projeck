package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class NumbersActivity extends AppCompatActivity {

    // handles the playback of a sound
    private MediaPlayer mMediaPLayer;

    private AudioManager.OnAudioFocusChangeListener mAudiofocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener(){

                @Override
                public void onAudioFocusChange(int focusChange) {
                    if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                                // pause playback
                                mMediaPLayer.pause();
                                mMediaPLayer.seekTo(0);
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                                // resume playback
                                mMediaPLayer.start();
                    }
                    else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                                // stop playback and clean-up resources
                                releaseMediaPlayer();
                    }
                }
            };

    // handles audio focus when playing a sounds file
    private AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener afChangeListener;


    private MediaPlayer.OnCompletionListener mOnCOmpletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {

        }
    };

    final ArrayList<Word> words  = new ArrayList<Word>(
            Arrays.asList(
                    new Word("one","lutti",R.drawable.number_one,R.raw.number_one),
                    new Word("two","otiiko",R.drawable.number_two,R.raw.number_two),
                    new Word("three","tolooksu",R.drawable.number_three,R.raw.number_three),
                    new Word("four","oyyisa",R.drawable.number_four,R.raw.number_four),
                    new Word("five","massokka",R.drawable.number_five,R.raw.number_five),
                    new Word("six","temmokka",R.drawable.number_six,R.raw.number_six),
                    new Word("seven","kenekaku",R.drawable.number_seven,R.raw.number_seven),
                    new Word("eight","kawinta",R.drawable.number_eight,R.raw.number_eight),
                    new Word("nine","wo'e",R.drawable.number_nine,R.raw.number_nine),
                    new Word("ten","na'aacha",R.drawable.number_ten,R.raw.number_ten)
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
        WordAdapter itemsAdapter =  new WordAdapter(this, words, R.color.category_numbers);

        // Cast the number Listview
        ListView listView = (ListView) findViewById(R.id.list);

        //set the adapter to our ListView
        listView.setAdapter(itemsAdapter);

        //
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word  = words.get(position);

                mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                int result = mAudioManager.requestAudioFocus(mAudiofocusChangeListener,
                            //Use the music stream
                            AudioManager.STREAM_MUSIC,
                            //Request Permanent focus
                            AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
                        );

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                        // Release the media player if it is exist because were about
                        // to play another audio file
                        releaseMediaPlayer();

                        // create a mediaplayer and setup the resource id
                        mMediaPLayer = MediaPlayer.create(NumbersActivity.this, word.getmMediaResouceId());

                        // start the audio file
                        mMediaPLayer.start();

                        // setup the listener on the media player, so that we can stop and release the
                        // media player once the sounds has finished playing
                        mMediaPLayer.setOnCompletionListener(mOnCOmpletionListener);

                }

            }
        });
    }

    /**
     *  release media player onStop()
     */
    @Override
    protected void onStop(){
        super.onStop();
        // when the activity is stopped, release the media player resource
        // because we wont be playing any more sounds.
        releaseMediaPlayer();
    }


    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPLayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPLayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPLayer = null;
            // abandon audio focus when playback is completed
            mAudioManager.abandonAudioFocus(mAudiofocusChangeListener);
        }
    }
}
