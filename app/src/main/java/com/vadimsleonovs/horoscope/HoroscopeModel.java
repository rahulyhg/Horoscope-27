package com.vadimsleonovs.horoscope;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vadims.leonovs on 11/20/2015.
 */
public class HoroscopeModel {
    
    @SerializedName("author")
    private  String mAuthor;
        
    @SerializedName("author_url")
    private  String mAuthorUrl;
        
    @SerializedName("base_url")
    private  String mBaseUrl;

    public String getAuthor() {
        return mAuthor;
    }

    public String getAuthorUrl() {
        return mAuthorUrl;
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }

    public String toString() {
        return "Module{" +
                "mAuthor='" + mAuthor + '\'' +
                ",mAuthorUrl='" + mAuthorUrl + '\'' +
                ",mBaseUrl='" + mBaseUrl + '\'' +
                "}";

    }
}
