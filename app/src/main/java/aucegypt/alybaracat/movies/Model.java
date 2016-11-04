package aucegypt.alybaracat.movies;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by AlyBaracat on 6/16/16.
 */
public class Model {
    private static Model ourInstance = new Model();
    private String response;
    public static Model getInstance() {
        return ourInstance;
    }

    private static Video[] videos;

    private Model() {
    }

    public Video[] Get_Array(){
        return videos;

    }

    public void Set_Array(Video[] video){
        this.videos=video;

    }
    public Boolean isEmpty(){
        try {
           int i=  this.videos.length;
            if(i>=0)return false;

        }
        catch (NullPointerException e) {
            return true;
        }
        return true;
    }


    }

