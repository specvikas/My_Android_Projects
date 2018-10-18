package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyFragment extends Fragment
{

    private MediaPlayer mMediaPlayer;

    private AudioManager am ;

    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {

            releaseMediaPlayer();

        }
    };

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener(){

        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT)
            {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }

            else if(focusChange == AudioManager.AUDIOFOCUS_GAIN)
            {
                mMediaPlayer.start();
            }

            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS)
            {
                releaseMediaPlayer();
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View RootView = inflater.inflate(R.layout.activity_family,container,false);


        am = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> colors = new ArrayList<>();

        colors.add( new Word("father", "әpә",R.drawable.family_father,R.raw.family_father));
        colors.add( new Word("mother", "әṭa",R.drawable.family_mother,R.raw.family_mother));
        colors.add( new Word("son", "angsi",R.drawable.family_son,R.raw.family_son));
        colors.add( new Word("daughter", "tune",R.drawable.family_daughter,R.raw.family_daughter));
        colors.add( new Word("older brother", "taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        colors.add( new Word("younger brother", "chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        colors.add( new Word("older sister", "teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        colors.add( new Word("younger sister", "kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        colors.add( new Word("grandmother", "ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        colors.add( new Word("grandfather", "paapa",R.drawable.family_grandfather,R.raw.family_grandfather));



        ListView colorsList = (ListView)RootView.findViewById(R.id.english);

        WordAdapter miwokColors =  new WordAdapter(getActivity(), colors, R.color.category_family);

        colorsList.setAdapter(miwokColors);

        colorsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int afRequest = am.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                releaseMediaPlayer();
                Word item = colors.get(position);
                if (afRequest == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(getActivity(), item.getAudio());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                }
            }
        });

        return RootView;
    }

    @Override
    public void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;

            am.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
