package com.example.android.miwok;

public class Word1 {

        private String mDefaultTranslation ;

        private String mMiwokTranslation;

    private Integer mAudioId;

        public Word1(String defaultTranslation, String miwokTranslation, Integer audioId )
        {
            mDefaultTranslation = defaultTranslation;
            mMiwokTranslation = miwokTranslation;
            mAudioId = audioId;
        }

        public String getDefaultTranslation()
        {
            return mDefaultTranslation;
        }

        public String getMiwokTranslation()
        {
            return mMiwokTranslation;
        }

        public int getAudio(){ return mAudioId;}

    }


