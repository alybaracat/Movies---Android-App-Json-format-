package aucegypt.alybaracat.movies;

/**
 * Created by AlyBaracat on 6/15/16.
 */
public class Video {

        String list;
        String image;
        int no_of_shows;
        Show[] shows;

    }

    class Show{
        String show;
        String image;
        int no_of_episodes;
        Episodes[] episodes;
        String description;


    }

class Episodes{
    String video;
    String url;

}


