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

public class ColorsActivity extends AppCompatActivity {

    private AudioManager mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

    private AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                                // pause playback
                                mMediaPlayer.pause();
                                mMediaPlayer.seekTo(0);
                    }else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                                // resume playback
                                mMediaPlayer.start();
                    }else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                                // stop playback and cleanup resources
                                releaseMediaPlayer();
                    }
                }
            };

    private MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mOnCOmpletionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    final ArrayList<Word> words  = new ArrayList<Word>(
            Arrays.asList(
                    new Word("red","wetetti",R.drawable.color_red ,R.raw.color_red),
                    new Word("green","chokokki",R.drawable.color_green ,R.raw.color_green),
                    new Word("brown","takaakki",R.drawable.color_brown ,R.raw.color_brown),
                    new Word("gray","topoppi",R.drawable.color_gray ,R.raw.color_gray),
                    new Word("black","kululli",R.drawable.color_black ,R.raw.color_black),
                    new Word("white","kelelli",R.drawable.color_white ,R.raw.color_white),
                    new Word("dusty yellow","ṭopiisә",R.drawable.color_dusty_yellow ,R.raw.color_dusty_yellow),
                    new Word("mustard yellow","chiwiiṭә",R.drawable.color_mustard_yellow ,R.raw.color_mustard_yellow)

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

        //set the media audio to esch item in out listview list using position
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                int result = mAudioManager.requestAudioFocus(mAudioFocusChangeListener,
                            // use the music stream
                            AudioManager.STREAM_MUSIC,
                            // request for permanent focus
                            AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
                        );
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // release the media player because we are about to player a different audio file
                    releaseMediaPlayer();

                    // created a media player and setup the resource id
                    mMediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getmMediaResouceId());

                    // start audio file
                    mMediaPlayer.start();

                    // setup On completion listener to the media player so that we can stop and release
                    // the media player once it finished playing.
                    mMediaPlayer.setOnCompletionListener(mOnCOmpletionListener);
                }
            }
        });
    }

    /**
     *  Release the media resource onStop()
     */
    @Override
    protected void onStop(){
        super.onStop();
        //  When the activity is stopped, released the media player resouce
        // because we wont be playing any more sounds
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            // abandon audio focus when playback is completed
            mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
        }
    }
}
