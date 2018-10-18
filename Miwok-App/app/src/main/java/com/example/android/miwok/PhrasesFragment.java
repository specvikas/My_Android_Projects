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

public class PhrasesFragment extends Fragment
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

        View RootView = inflater.inflate(R.layout.activity_phrases, container, false);


        am = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word1> colors = new ArrayList<>();

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Objects.requireNonNull(getActionBar()).setDisplayHomeAsUpEnabled(true);
//        }


        colors.add( new Word1("Where are you going?", "minto wuksus",R.raw.phrase_where_are_you_going));
        colors.add( new Word1("What is your name?", "tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        colors.add( new Word1("My name is...", "oyaaset...",R.raw.phrase_my_name_is));
        colors.add( new Word1("How are you feeling?", "michәksәs?",R.raw.phrase_how_are_you_feeling));
        colors.add( new Word1("I’m feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
        colors.add( new Word1("Are you coming?", "әәnәs'aa?",R.raw.phrase_are_you_coming));
        colors.add( new Word1("Yes, I’m coming.", "hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        colors.add( new Word1("I’m coming.", "әәnәm",R.raw.phrase_im_coming));
        colors.add( new Word1("Let’s go.", "yoowutis",R.raw.phrase_lets_go));
        colors.add( new Word1("Come here.", "әnni'nem",R.raw.phrase_come_here));



        ListView colorsList = (ListView)RootView.findViewById(R.id.english);

        WordAdapter1 miwokColors =  new WordAdapter1(getActivity(), colors, R.color.category_phrases);

        colorsList.setAdapter(miwokColors);

        colorsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int afRequest = am.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                releaseMediaPlayer();
                Word1 item = colors.get(position);
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
