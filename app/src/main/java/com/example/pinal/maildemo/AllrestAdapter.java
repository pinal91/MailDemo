package com.example.pinal.maildemo;

/**
 * Created by pinal on 10/5/16.
 */
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Pinal on 3/20/2016.
 */
public class AllrestAdapter  extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;

    HashMap<String, String> result = new HashMap<String, String>();



    public AllrestAdapter(Context context,
                          ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        this.data = arraylist;


    }

    public int getCount() {
        // TODO A// UNIVERSAL IMAGE LOADER SETUP

        return data.size();
    }

    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // TODO Auto-generated method stub

        TextView tname,tdate,tnumber;
        ImageView img ;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.singlerest, parent, false);
        result = data.get(position);

        tname = (TextView)itemView.findViewById(R.id.txtrnmae);
        tdate = (TextView)itemView.findViewById(R.id.txtrphone);
        tnumber = (TextView)itemView.findViewById(R.id.number);

        img = (ImageView)itemView.findViewById(R.id.imgres);

        tname.setText(result.get("rname"));
        tdate.setText(result.get("remail"));
        tnumber.setText(result.get("rlati"));


       /* String surl = "http://192.168.43.72/restaurant/AndroidFileUpload/uploads/"+result.get("rurl");
        imageLoader.displayImage(surl, img, options);

*/

        itemView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                HashMap<String, String> detailmap = new HashMap<String, String>();

                detailmap = data.get(position);
                Intent idetl = new Intent(context, MainActivity.class);
                idetl.putExtra("rid", detailmap.get("rid"));

                idetl.putExtra("rname", detailmap.get("rname"));
                idetl.putExtra("remail", detailmap.get("remail"));
                idetl.putExtra("rlati", detailmap.get("rlati"));

                context.startActivity(idetl);

            }
        });

        return itemView;
    }

}
