package com.example.aakashmaroti.fillup;

import java.sql.SQLException;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class StartScreen extends ActionBarActivity implements View.OnClickListener {



    ListView lv;
    Context context=this;

    int i=1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        Button b = (Button) findViewById(R.id.AddButton);
        b.setOnClickListener(this);


        lv = (ListView) findViewById(R.id.listview);
        try
        {
            populateListViewFromDatabase();
        } catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Oops there has been an error this is on create "+e.toString()+"", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                TextView pos = (TextView) view.findViewById(R.id.rowno);
                showsDialog(Integer.parseInt(pos.getText().toString()));
            }
        });

        //async task to sync all the details

        SyncAllData s=new SyncAllData();
        s.execute();

        //end async task
    }


    @Override
    public void onResume()
    {
        super.onResume();
        //favoriteList = db.getFavList1(this); //changw here
        //lv.setAdapter(new ViewAdapter());
        try
        {
            populateListViewFromDatabase();
        } catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Oops there has been an error this is on resume"+e.toString()+"", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.AddButton:
                startActivity(new Intent(this,Form.class));
                break;

            default: break;
        }

    }

    public void populateListViewFromDatabase()throws Exception
    {
        Find info=new Find(this);
        try
        {
            info.open();
            SimpleCursorAdapter myCursorAdapter;
            myCursorAdapter = info.listUp();
            lv.setAdapter(myCursorAdapter);
            info.close();

        } catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Oops there has been an error this is populate list view "+e.toString()+"", Toast.LENGTH_LONG).show();
        }
    }
    //class to sync automatically.
    public class SyncAllData extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... urls) {
            String isSynced="";
            int pos=1;
            Find obj = new Find(getApplicationContext());

            if(isOnline())
            {
                try
                {
                    obj.open();
                    obj.SyncAll();
                    obj.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            try
            {
                //populateListViewFromDatabase();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return isSynced;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("Ho paya","yi");
        }
    }


    public void showsDialog(int pos)
    {

        //pos++;
        String s = "";
        s += pos;
        Intent i = new Intent(getApplicationContext(), DisplayDetails.class);
        i.putExtra("position", s);
        startActivity(i);
        try
        {
            populateListViewFromDatabase();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }


}





