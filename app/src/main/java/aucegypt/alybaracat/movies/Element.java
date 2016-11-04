package aucegypt.alybaracat.movies;

/**
 * Created by AlyBaracat on 6/15/16.
 */
public class Element {


        public String name;
        public String category;
        public String image_url;
        public int episodes;

        public Element(String name,String id,String image,int episode) {
            this.name = name;
            this.category = id;
            this.image_url=image;
            this.episodes=episode;
        }

}
