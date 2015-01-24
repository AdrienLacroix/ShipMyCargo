package com.example.aakashmaroti.fillup;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.makemyandroidapp.googleformuploader.GoogleFormUploader;

import java.sql.SQLException;


public class DisplayDetails extends ActionBarActivity implements View.OnClickListener

{

    ImageButton ibSync;
    ImageButton ibDelete;
    TextView DispDetails;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();

        String value="1";
        if (extras != null) {
           value = extras.getString("position");
        }
        pos=Integer.parseInt(value);
        setContentView(R.layout.activity_display_details);
        ibSync=(ImageButton)findViewById(R.id.imageButtonSync);
        ibDelete=(ImageButton)findViewById(R.id.imageButtonDelete);
        DispDetails = (TextView)findViewById(R.id.textViewDisplayDetails);
        String s="";

        ibSync.setOnClickListener(this);
        ibDelete.setOnClickListener(this);

        Find info1=new Find(this);
        try
        {
            info1.open();
            s=info1.getDetails(pos);
            info1.close();

        } catch (SQLException e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Oops there has been an error "+e.toString()+"", Toast.LENGTH_LONG).show();
        }
        DispDetails.setText(s);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {
        String isSynced="";
        Find obj = new Find(this);
        try
        {
            obj.open();
            isSynced=obj.getSyncStatus(pos);
            obj.close();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }



        if(v.getId()==R.id.imageButtonSync)
        {

            if (isSynced.equalsIgnoreCase("not Synced")||isSynced.equalsIgnoreCase(" not Synced"))
            {
                if (isOnline())
                {
                    Toast.makeText(getApplicationContext(), "Syncing your record", Toast.LENGTH_LONG).show();
                    //Find objkht = new Find(this);
                    ToSync abc = new ToSync(this);
                    int SyncedOrNot=abc.SyncThis(pos);


                    if(SyncedOrNot==1)
                    {
                        Toast.makeText(getApplicationContext(), "Your Record has been Synced", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Unable to Sync", Toast.LENGTH_LONG).show();

                } else
                    Toast.makeText(getApplicationContext(), "You are not connected to the internet", Toast.LENGTH_LONG).show();
                cancel();

            }
            else
                Toast.makeText(getApplicationContext(), "This is already Synced", Toast.LENGTH_LONG).show();

        }
        if(v.getId()==R.id.imageButtonDelete)
        {   Find info1=new Find(this);
            try
            {
                info1.open();
                info1.DeleteRow(pos);
                info1.close();

            } catch (SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Oops there has been an error "+e.toString()+"", Toast.LENGTH_LONG).show();
            }


            Toast.makeText(getApplicationContext(), "This record has been successfully deleted", Toast.LENGTH_LONG).show();
            cancel();
        }
    }

    public void cancel()
    {
        this.finish();
        return;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }


}
