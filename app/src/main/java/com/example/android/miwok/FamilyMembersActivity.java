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

public class FamilyMembersActivity extends AppCompatActivity {

    private AudioManager.OnAudioFocusChangeListener mAudiofocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                                // pause playback
                                mMediaPlayer.pause();
                                mMediaPlayer.seekTo(0);
                    }else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                                // start playback
                                mMediaPlayer.start();
                    }else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                                // stop playbakc and cleaup resources
                                releaseMediaPlayer();
                    }

                }
            };

    private AudioManager mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

    private MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

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

                int result = mAudioManager.requestAudioFocus(mAudiofocusChangeListener,
                            // use the music stream
                            AudioManager.STREAM_MUSIC,
                            // request permanent focus
                            AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
                        );

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    // Release the media file if it is exist because we are about to play
                    // another media file from our list.
                    releaseMediaPlayer();

                    // create a media player and setup the resource id
                    mMediaPlayer = MediaPlayer.create(FamilyMembersActivity.this, word.getmMediaResouceId());

                    // start the audio file
                    mMediaPlayer.start();

                    // setup a listener to our media player, so that we can stop and release
                    // the media once the sounds has finished playing.
                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);

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
        // when the activity is stopped, release the media resource
        // because we wont be playing any more sounds.
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
            mAudioManager.abandonAudioFocus(mAudiofocusChangeListener);
        }
    }
}
