package com.example.pinal.maildemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Pinal on 3/20/2016.
 */
public class VehicleDetail extends Activity {



    ProgressDialog pd;
int p=4484;
    ListView l1;
    ArrayList<HashMap<String, String>> data;
   // PreferenceHelper pf;
    Button badd;
    Json js;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_detail);


        l1 = (ListView)findViewById(R.id.listallrest);
        new getmeet().execute();

    }




    class getmeet extends AsyncTask<Void, Void, Void> {
       // PreferenceHelper pf;
        String rid,rfname,rname,remail,radd,rphone,rlati,rlangi,rurl;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pd = new ProgressDialog(VehicleDetail.this);
            pd.setMessage("fetching data...");
            pd.setIndeterminate(false);
            pd.setCancelable(true);
            pd.show();
        }

        @SuppressWarnings("static-access")
        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub


            js = new Json();
            data = new ArrayList<HashMap<String,String>>();


            String strresponce = js.getdata("http://192.168.1.222/RTO/allproduct.php?VehicleNo="+p);
            Log.d("database", "product comes"+ strresponce);
            JSONObject jobjResponse;
            try {
                jobjResponse = new JSONObject(strresponce);
                JSONArray jArrayProdList = new JSONArray();
                jArrayProdList = jobjResponse.getJSONArray("catagory");
                for (int i = 0; i < jArrayProdList.length(); i++) {
                    HashMap<String,String> map = new HashMap<String, String>();
                    JSONObject jobj = jArrayProdList.getJSONObject(i);
                    rid = jobj.getString("id");
                    rfname = jobj.getString("fname");
                    rname = jobj.getString("lname");
                    remail = jobj.getString("Email");
                    radd = jobj.getString("Mobile");
                    rlati = jobj.getString("VehicleNo");





                    map.put( "rid", rid );
                    map.put( "rfname", rfname );
                    map.put( "rname", rname );
                    map.put( "remail", remail );
                    map.put( "radd", radd );
                    map.put( "rlati", rlati );




                    data.add(map);
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if(pd.isShowing()){
                pd.dismiss();

            }
            AllrestAdapter adapter = new AllrestAdapter(VehicleDetail.this,data );
            l1.setAdapter(adapter);
        }

    }





}
