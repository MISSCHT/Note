package com.example.administrator.note;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NotesAdapter extends BaseAdapter {
    Context mcontext;
    int layoutid;
    List<Notebean> mylist;

    public NotesAdapter(Context mcontext, int layoutid, List<Notebean> mylist) {
        this.mcontext = mcontext;
        this.layoutid = layoutid;
        this.mylist = mylist;
    }

    @Override
    public int getCount() {
        return mylist.size();
    }

    @Override
    public Object getItem(int position) {
        return mylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=LayoutInflater.from(mcontext).inflate(layoutid,parent,false);
        TextView titleText=(TextView)convertView.findViewById(R.id.title);
        TextView bodyText=(TextView)convertView.findViewById(R.id.body);
        Notebean m=mylist.get(position);
        titleText.setText(""+m.getTitle());
        bodyText.setText(""+m.getBody());
        return convertView;
    }
}
