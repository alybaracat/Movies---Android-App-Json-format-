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

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by AlyBaracat on 6/15/16.
 */
public class ElementAdapter extends ArrayAdapter<Element> {
    public ElementAdapter(Context context, ArrayList<Element> elements){
        super(context,0, elements);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Element element=getItem(position);

        if(convertView==null){
            if (position == 0)
                convertView= LayoutInflater.from(getContext()).inflate(R.layout.item,parent,false);
            else  convertView= LayoutInflater.from(getContext()).inflate(R.layout.item,parent,false);

        }

        TextView el_name=(TextView)convertView.findViewById(R.id.name);
        TextView el_category=(TextView)convertView.findViewById(R.id.id);
        CircleImageView el_image=(CircleImageView) convertView.findViewById(R.id.image);
        TextView el_no_show=(TextView) convertView.findViewById(R.id.no_shows);


        el_name.setText(element.name);
        el_category.setText(element.category);

        Picasso.with(getContext()).load(element.image_url).into(el_image);


        el_no_show.setText("no. " + Integer.toString(element.episodes));




        return convertView;
    }
}

