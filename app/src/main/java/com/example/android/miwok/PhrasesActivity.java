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

public class PhrasesActivity extends AppCompatActivity {

    private AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                                // pause playback
                                mMediaPLayer.pause();
                                // start from the beginning
                                mMediaPLayer.seekTo(0);
                    }else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                                // resume playback
                                mMediaPLayer.start();
                    }else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                                // stop playback and cleanup resources
                                releaseMediaPlayer();
                    }
                }
            };

    private AudioManager mAudioManager;

    private MediaPlayer mMediaPLayer;

    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {

        }
    };

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
                mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                int result  = mAudioManager.requestAudioFocus(mAudioFocusChangeListener,
                                // use the miusic stream
                                AudioManager.STREAM_MUSIC,
                                // request for permanent focus
                                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
                        );
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    // released the audio file if it is exist because
                    // we are about to play another audio file
                    releaseMediaPlayer();

                    // created a media player and setup the resource id
                    mMediaPLayer = MediaPlayer.create(PhrasesActivity.this, word.getmMediaResouceId());
                    // start the audio file
                    mMediaPLayer.start();

                    // setup a on completion listener for the media player, so that we
                    // can stop and released it onece it finished playing .
                    mMediaPLayer.setOnCompletionListener(mOnCompletionListener);

                }
            }
        });
    }

    /**
     *  release the media player resource onStop()
     */
    @Override
    protected void onStop(){
        super.onStop();
        // When the activity is stopped, release the media player resource
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
            //abandon audio focus when playback is completed
            mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
        }
    }
}
