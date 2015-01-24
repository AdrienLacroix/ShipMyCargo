package com.example.aakashmaroti.fillup;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.MultiAutoCompleteTextView;


public class Destination extends ActionBarActivity implements View.OnClickListener
{

    MultiAutoCompleteTextView DeliveryLocation;
    String DeliveryPoints="";
    Button ContinueD;
    CheckBox AllIndia;
    CheckBox AllIndiaFTL;
    CheckBox TopLocations[]=new CheckBox[30];
    boolean isCheckedCB[]=new boolean[30];
    Bundle bundle;
    //CheckBox ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        ContinueD=(Button)findViewById(R.id.continueDelivery);
        ContinueD.setOnClickListener(this);
        bundle = getIntent().getExtras();

        DeliveryLocation= (MultiAutoCompleteTextView) findViewById(R.id.DeliveryLocation);

                String[] location = getResources().getStringArray(R.array.LocationS);
                ArrayAdapter<String> adapterLocation =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,location);
                DeliveryLocation.setThreshold(1);
                DeliveryLocation.setAdapter(adapterLocation);
                DeliveryLocation.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        AllIndia=(CheckBox)findViewById(R.id.checkBoxAllIndia);
        AllIndiaFTL=(CheckBox)findViewById(R.id.checkBoxAllIndiaFTL);
        TopLocations[0]=(CheckBox)  findViewById(R.id.Bangalore);
        TopLocations[1]=(CheckBox)  findViewById(R.id.Chennai);
        TopLocations[2]=(CheckBox)  findViewById(R.id.GreaterMumbai);
        TopLocations[3]=(CheckBox)  findViewById(R.id.Bhiwandi);
        TopLocations[4]=(CheckBox)  findViewById(R.id.Delhi);
        TopLocations[5]=(CheckBox)  findViewById(R.id.Pune);
        TopLocations[6]=(CheckBox)  findViewById(R.id.Baroda);
        TopLocations[7]=(CheckBox)  findViewById(R.id.Ahmedabad);
        TopLocations[8]=(CheckBox)  findViewById(R.id.Noida);
        TopLocations[9]=(CheckBox)  findViewById(R.id.Calcutta);
        TopLocations[10]=(CheckBox)  findViewById(R.id.Indore);
        TopLocations[11]=(CheckBox)  findViewById(R.id.Baddi);
        TopLocations[12]=(CheckBox)  findViewById(R.id.Faridabad);
        TopLocations[13]=(CheckBox)  findViewById(R.id.Nagpur);
        TopLocations[14]=(CheckBox)  findViewById(R.id.Hyderabad);
        TopLocations[15]=(CheckBox)  findViewById(R.id.Rudrapur);
        TopLocations[16]=(CheckBox)  findViewById(R.id.Aurangabad);
        TopLocations[17]=(CheckBox)  findViewById(R.id.Ghaziabad);
        TopLocations[18]=(CheckBox)  findViewById(R.id.Hosur);
        TopLocations[19]=(CheckBox)  findViewById(R.id.Vapi);
        TopLocations[20]=(CheckBox)  findViewById(R.id.Ludhiana);
        TopLocations[21]=(CheckBox)  findViewById(R.id.Gurgaon);
        TopLocations[22]=(CheckBox)  findViewById(R.id.Bhiwadi);
        TopLocations[23]=(CheckBox)  findViewById(R.id.Panjim);
        TopLocations[24]=(CheckBox)  findViewById(R.id.NaviMumbai);
        TopLocations[25]=(CheckBox)  findViewById(R.id.Zirakpur);
        TopLocations[26]=(CheckBox)  findViewById(R.id.Pithampur);
        TopLocations[27]=(CheckBox)  findViewById(R.id.Nasik);
        TopLocations[28]=(CheckBox)  findViewById(R.id.MumbaiCity);
        TopLocations[29]=(CheckBox)  findViewById(R.id.Vasai);


        isCheckedCB[0]=false;;
        isCheckedCB[1]=false;
        isCheckedCB[2]=false;
        isCheckedCB[3]=false;
        isCheckedCB[4]=false;
        isCheckedCB[5]=false;
        isCheckedCB[6]=false;
        isCheckedCB[7]=false;
        isCheckedCB[8]=false;
        isCheckedCB[9]=false;
        isCheckedCB[10]=false;
        isCheckedCB[11]=false;
        isCheckedCB[12]=false;
        isCheckedCB[13]=false;
        isCheckedCB[14]=false;
        isCheckedCB[15]=false;
        isCheckedCB[16]=false;
        isCheckedCB[17]=false;
        isCheckedCB[18]=false;
        isCheckedCB[19]=false;
        isCheckedCB[20]=false;
        isCheckedCB[21]=false;
        isCheckedCB[22]=false;
        isCheckedCB[23]=false;
        isCheckedCB[24]=false;
        isCheckedCB[25]=false;
        isCheckedCB[26]=false;
        isCheckedCB[27]=false;
        isCheckedCB[28]=false;
        isCheckedCB[29]=false;
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_destination, menu);
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
        String[] location = getResources().getStringArray(R.array.LocationS);
        if(v.getId()==R.id.continueDelivery)
        {
            if(AllIndia.isChecked())
                DeliveryPoints="All India |";
            if(AllIndiaFTL.isChecked())
                DeliveryPoints+="| All India FTL |";
            for(int i=0;i<30;i++)
            {
                if(TopLocations[i].isChecked())
                {
                    DeliveryPoints += location[i] + ", ";
                    isCheckedCB[i]=true;
                }
            }
            DeliveryPoints+=DeliveryLocation.getText().toString();
            String sopu;
            sopu=DeliveryLocation.getText().toString();
            Intent i=new Intent(this,Booking.class);
            Bundle b= new Bundle();
            bundle.putString("DeliveryPoints",DeliveryPoints);
            bundle.putBooleanArray("CheckedLocation",isCheckedCB);
            i.putExtras(bundle);
            i.putExtra("Mactv", sopu);

            i.putExtra("AllIndia",AllIndia.isChecked());
            startActivity(i);
        }
    }
}
