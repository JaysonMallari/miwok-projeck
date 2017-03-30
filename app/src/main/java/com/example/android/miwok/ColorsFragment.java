package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {


    private AudioManager mAudioManager;

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


    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.words_list,container,false);

        //call setColorAdapter onCreate method
        setColorAdapter(rootView);

        return rootView;
    }

    public void setColorAdapter(View rootView){
        //Constructed a new adapter
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_colors);

        // fide the listview using id and Cast
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        //set the adapter for the color listview
        listView.setAdapter(adapter);

        //set the media audio to esch item in out listview list using position
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                mAudioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
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
                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getmMediaResouceId());

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

    @Override
    public void onStop() {
        super.onStop();
        // When the fragment is stopped, release the media player because
        // we will not be playing any more sounds

        releaseMediaPlayer();

    }
}
