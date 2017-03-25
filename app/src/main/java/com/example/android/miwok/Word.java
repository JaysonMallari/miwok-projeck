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

    public Word(String defaultTranslation, String miwokTranslation){
         mDefaultTranslation = defaultTranslation;
         mMiwokTranslation = miwokTranslation;
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
}
