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
import android.widget.CompoundButton;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;
import java.sql.SQLException;


public class Booking extends ActionBarActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener
{

    MultiAutoCompleteTextView BookingLocation;
    String BookingPoints="";
    Button ContinueB;
    CheckBox AllIndia;
    CheckBox SameAsDelivery;
    CheckBox TopLocations[]=new CheckBox[30];
    boolean isCheck[]=new boolean[30];
    String DeliveryMACTV="";
    Bundle bundle;
    Boolean AllIndiaD=false;
    Button SubmitAtBook;
    boolean isCheckedCB[]=new boolean[30];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        SubmitAtBook=(Button)findViewById(R.id.DoneBookingAtBook);
        SubmitAtBook.setOnClickListener(this);


        //=new boolean[30];
        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            bundle=this.getIntent().getExtras();
            boolean brec[]=bundle.getBooleanArray("CheckedLocation");
            DeliveryMACTV=extras.getString("Mactv");

            AllIndiaD=extras.getBoolean("AllIndia");
            for(int i=0;i<30;i++)
            {
                isCheck[i]=brec[i];
            }
            //String abcd=""+isCheck.length;

           //Toast.makeText(getApplicationContext(),b.getString("ContactPersonDesignation"), Toast.LENGTH_LONG).show();
        }



        ContinueB=(Button)findViewById(R.id.continueBooking);
        ContinueB.setOnClickListener(this);

        BookingLocation= (MultiAutoCompleteTextView) findViewById(R.id.BookingLocation);

        String[] location = getResources().getStringArray(R.array.LocationS);
        ArrayAdapter<String> adapterLocation =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,location);
        BookingLocation.setThreshold(1);
        BookingLocation.setAdapter(adapterLocation);
        BookingLocation.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        AllIndia=(CheckBox)findViewById(R.id.checkBoxAllIndiaB);
        SameAsDelivery=(CheckBox)findViewById(R.id.checkBoxSameAsDelivery);
        SameAsDelivery.setOnCheckedChangeListener(this);


        TopLocations[0]=(CheckBox)  findViewById(R.id.bBangalore);
        TopLocations[1]=(CheckBox)  findViewById(R.id.bChennai);
        TopLocations[2]=(CheckBox)  findViewById(R.id.bGreaterMumbai);
        TopLocations[3]=(CheckBox)  findViewById(R.id.bBhiwandi);
        TopLocations[4]=(CheckBox)  findViewById(R.id.bDelhi);
        TopLocations[5]=(CheckBox)  findViewById(R.id.bPune);
        TopLocations[6]=(CheckBox)  findViewById(R.id.bBaroda);
        TopLocations[7]=(CheckBox)  findViewById(R.id.bAhmedabad);
        TopLocations[8]=(CheckBox)  findViewById(R.id.bNoida);
        TopLocations[9]=(CheckBox)  findViewById(R.id.bCalcutta);
        TopLocations[10]=(CheckBox)  findViewById(R.id.bIndore);
        TopLocations[11]=(CheckBox)  findViewById(R.id.bBaddi);
        TopLocations[12]=(CheckBox)  findViewById(R.id.bFaridabad);
        TopLocations[13]=(CheckBox)  findViewById(R.id.bNagpur);
        TopLocations[14]=(CheckBox)  findViewById(R.id.bHyderabad);
        TopLocations[15]=(CheckBox)  findViewById(R.id.bRudrapur);
        TopLocations[16]=(CheckBox)  findViewById(R.id.bAurangabad);
        TopLocations[17]=(CheckBox)  findViewById(R.id.bGhaziabad);
        TopLocations[18]=(CheckBox)  findViewById(R.id.bHosur);
        TopLocations[19]=(CheckBox)  findViewById(R.id.bVapi);
        TopLocations[20]=(CheckBox)  findViewById(R.id.bLudhiana);
        TopLocations[21]=(CheckBox)  findViewById(R.id.bGurgaon);
        TopLocations[22]=(CheckBox)  findViewById(R.id.bBhiwadi);
        TopLocations[23]=(CheckBox)  findViewById(R.id.bPanjim);
        TopLocations[24]=(CheckBox)  findViewById(R.id.bNaviMumbai);
        TopLocations[25]=(CheckBox)  findViewById(R.id.bZirakpur);
        TopLocations[26]=(CheckBox)  findViewById(R.id.bPithampur);
        TopLocations[27]=(CheckBox)  findViewById(R.id.bNasik);
        TopLocations[28]=(CheckBox)  findViewById(R.id.bMumbaiCity);
        TopLocations[29]=(CheckBox)  findViewById(R.id.bVasai);

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
        getMenuInflater().inflate(R.menu.menu_booking, menu);
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
        if(v.getId()==R.id.continueBooking)
        {
            String[] location = getResources().getStringArray(R.array.LocationS);
            if(v.getId()==R.id.continueDelivery)
            {
                if(AllIndia.isChecked())
                    BookingPoints="All India |";
                for(int i=0;i<30;i++)
                {
                    if(TopLocations[i].isChecked())
                    {
                        BookingPoints += location[i] + ", ";
                        isCheckedCB[i]=true;
                    }
                }
                BookingPoints+=BookingLocation.getText().toString();
               
            }
            
            //////////////////////
            Intent i=new Intent(this,Route.class);
            bundle.putString("BookingPoints",BookingPoints);
            i.putExtras(bundle);
            startActivity(i);
        }
        if(v.getId()==R.id.DoneBookingAtBook)
        {
            //////////////////////////Extract from Bundle and save here
            String[] location = getResources().getStringArray(R.array.LocationS);
            if(v.getId()==R.id.continueDelivery)
            {
                if(AllIndia.isChecked())
                    BookingPoints="All India |";
                for(int i=0;i<30;i++)
                {
                    if(TopLocations[i].isChecked())
                    {
                        BookingPoints += location[i] + ", ";
                        isCheckedCB[i]=true;
                    }
                }
                BookingPoints+=BookingLocation.getText().toString();


            }

            Find ob = new Find (this);
            try
            {
                ob.open();

                ob.createEntry("not Synced",bundle.getString("TimeStamp"),bundle.getString("NameOfCompany"),bundle.getString("CompanyAddress"), bundle.getString("Establish"), bundle.getString("Website"), bundle.getString("PromoterCEOHOS"), bundle.getString("ContactNumber1"), bundle.getString("ContactPersonName"),bundle.getString("ContactPersonNumber"),bundle.getString("ContactPersonDesignation"),bundle.getString("Email"),bundle.getString("BaseLoc"),bundle.getString("MinWeight"),bundle.getString("GodownSpace"),bundle.getString("SpecificProductCategoriesHandled"),bundle.getString("TypeOfVehicle"),bundle.getString("Product"),bundle.getString("ProductNotHandled"),bundle.getString("Payment"),bundle.getString("TypeOfPlayer"),bundle.getString("CategoryOfPlayer"),bundle.getString("TransitInsurance"),bundle.getString("DoorPickUp"),bundle.getString("DoorDelivery"),bundle.getString("DetentionCharges"),bundle.getString("PackagingService"),bundle.getString("Other"), bundle.getString("DeliveryPoints"),BookingPoints,"",bundle.getString("Latitude"),bundle.getString("Longitude"),"",null,null,null,null,null);

                ob.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
            Intent intent=new Intent(getApplicationContext(),StartScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        if(buttonView.getId()==R.id.checkBoxSameAsDelivery)
        {
            if(SameAsDelivery.isChecked())
            {
                for(int i=0;i<30;i++)
                {
                    TopLocations[i].setChecked(isCheck[i]);
                }
                AllIndia.setChecked(AllIndiaD);
            }
            BookingLocation.setText(DeliveryMACTV);
        }
    }
}
