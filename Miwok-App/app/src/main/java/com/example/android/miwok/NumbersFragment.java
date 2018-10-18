package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersFragment extends Fragment
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View RootView =  inflater.inflate(R.layout.activity_numbers,container,false);


            am = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

            final ArrayList<Word> translation = new ArrayList<>();

            translation.add( new Word("One", "Lutti",R.drawable.number_one, R.raw.number_one));
            translation.add( new Word("Two", "otiiko",R.drawable.number_two,R.raw.number_two));
            translation.add( new Word("Three", "tolookosu",R.drawable.number_three,R.raw.number_three));
            translation.add( new Word("Four", "oyyisa",R.drawable.number_four,R.raw.number_four));
            translation.add( new Word("Five", "massokka",R.drawable.number_five,R.raw.number_five));
            translation.add( new Word("Six", "temmokka",R.drawable.number_six,R.raw.number_six));
            translation.add( new Word("Seven", "kenekaku",R.drawable.number_seven,R.raw.number_seven));
            translation.add( new Word("Eight", "kawinta",R.drawable.number_eight,R.raw.number_eight));
            translation.add( new Word("Nine", "wo’e",R.drawable.number_nine,R.raw.number_nine));
            translation.add( new Word("Ten", "na’aacha",R.drawable.number_ten,R.raw.number_ten));

            ListView englishAlpha = (ListView)RootView.findViewById(R.id.english);

            WordAdapter word =  new WordAdapter(getActivity(), translation, R.color.category_numbers);

            englishAlpha.setAdapter(word);

        englishAlpha.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int afRequest = am.requestAudioFocus(mOnAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);


                releaseMediaPlayer();

                Word item = translation.get(position);

                if(afRequest == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
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
