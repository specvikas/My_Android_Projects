package com.example.android.miwok;

public class Word
{
    private String mDefaultTranslation ;

    private String mMiwokTranslation;

    private Integer mImageId;

    private Integer mAudioId;

    public Word(String defaultTranslation, String miwokTranslation, Integer imageId, Integer audioId )
    {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageId = imageId;
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

    public int getImage(){ return mImageId;}

    public int getAudio(){ return mAudioId;}
}
