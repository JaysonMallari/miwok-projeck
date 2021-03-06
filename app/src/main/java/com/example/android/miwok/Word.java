package com.example.android.miwok;

/**
 * Created by My__Boo on 3/25/2017.
 *  Default and Miwok translation of the word
 */

public class Word {

    /** default translation of the word*/
    private String mDefaultTranslation;

    /** miwok translation of the word*/
    private String mMiwokTranslation;

    /** audio resouce Id */
    private int mMediaResouceId;

    /** initialize the value for no image */
    private static final int NO_IMAGE_PROVIDED = -1;

    /** Image resource ID for the word*/
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    //1st Constructor that takes 2 String parameeter
    public Word(String defaultTranslation, String miwokTranslation, int mediaResourceId){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mMediaResouceId = mediaResourceId;
    }

    //2nd Constructor that take 2 String and one int parameter
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int mediaResourceId){
         mDefaultTranslation = defaultTranslation;
         mMiwokTranslation = miwokTranslation;
         mImageResourceId = imageResourceId;
         mMediaResouceId = mediaResourceId;
    }

    /**
     * get the default translation of the word
     */
    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    /**
     *  get the miwok translation of the word
     */
    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }


    /**
     *  get the Image resource ID
     */
    public int getmImageResourceId(){
        return mImageResourceId;
    }


    /**
     *  return a boolean whether or not the image is provided
     */
    public boolean hasImage(){
        return  mImageResourceId != NO_IMAGE_PROVIDED;
    }

    /**
     *  return the media resource id
     */
    public int getmMediaResouceId(){
        return mMediaResouceId;
    }

}
