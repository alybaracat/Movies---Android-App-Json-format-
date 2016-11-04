package aucegypt.alybaracat.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by AlyBaracat on 6/16/16.
 */
public class EpisodeAdapter extends ArrayAdapter<Episodes> {
    public EpisodeAdapter(Context context, ArrayList<Episodes> episodes){
        super(context,0, episodes);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Episodes episode=getItem(position);

        if(convertView==null){
            if (position == 0)
                convertView= LayoutInflater.from(getContext()).inflate(R.layout.item2,parent,false);
            else  convertView= LayoutInflater.from(getContext()).inflate(R.layout.item2,parent,false);

        }

        TextView el_name=(TextView)convertView.findViewById(R.id.name);


        el_name.setText(episode.video);





        return convertView;
    }
}

