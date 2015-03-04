package edu.washington.akpuri.quizdroid;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Akash on 2/17/2015.
 */
public class TopicArrayAdapter extends ArrayAdapter<Topic> {
    private final Context context;

    public TopicArrayAdapter(Context context, int resource, ArrayList<Topic> topics) {
        super(context, resource, topics);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.topiclayout, parent, false);
        }

        Topic topic = getItem(position);

        ImageView icon = (ImageView) v.findViewById(R.id.icon);
        TextView titleText = (TextView) v.findViewById(R.id.titleTopic1);
        TextView shortO = (TextView) v.findViewById(R.id.shortO);
        //Log.i("TopicListView", "Position = " + position);
        //Log.i("TopicListView", "Title = " + topic.getTitle());
        titleText.setText(topic.getTitle());
        shortO.setText(topic.getShortOverview());

        //LayoutInflater inflater = (LayoutInflater) context
        //        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        return v;
    }

}
