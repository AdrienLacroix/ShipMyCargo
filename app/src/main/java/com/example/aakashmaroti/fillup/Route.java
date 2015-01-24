package com.example.aakashmaroti.fillup;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;


public class Route extends ActionBarActivity implements View.OnClickListener
{
   //MultiAutoCompleteTextView[][] RouteLoc=new MultiAutoCompleteTextView[15][2];
    Button bAdd;
    Button bSubmit;
    String Routes="";
    LinearLayout container;
    AutoCompleteTextView Bookings;
    Bundle bundle;
    MultiAutoCompleteTextView Destinations;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        bAdd=(Button)findViewById(R.id.buttonAddField);
        bSubmit=(Button)findViewById(R.id.buttonSubmitFromRouteFinal);
        bSubmit.setOnClickListener(this);
        bundle = getIntent().getExtras();
        container = (LinearLayout)findViewById(R.id.container);
        String[] location = getResources().getStringArray(R.array.LocationS);
        final ArrayAdapter<String> adapterLocation =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,location);
        Bookings= (AutoCompleteTextView) findViewById(R.id.BookingMACTV);
        Bookings.setThreshold(1);
        Bookings.setAdapter(adapterLocation);


        Destinations= (MultiAutoCompleteTextView) findViewById(R.id.DestinationMACTV);
        Destinations.setThreshold(1);
        Destinations.setAdapter(adapterLocation);
        Destinations.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        Destinations.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                Destinations.setHint("");
            }
        });
        bAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v123)
            {
                LayoutInflater layoutInflater =(LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.routerow,null);
                TextView TvBooks =(TextView)addView.findViewById(R.id.BookingTV);
                TextView tvDelivers=(TextView)addView.findViewById(R.id.DestinationTV);
                        TvBooks.setText(Bookings.getText().toString());
                        tvDelivers.setText(Destinations.getText().toString());
                        Routes=Routes+"|"+Bookings.getText().toString()+"->"+Destinations.getText().toString();
                        //Toast.makeText(getApplicationContext(),Routes, Toast.LENGTH_LONG).show();
                        String s=Destinations.getText().toString();
                        s+=Bookings.getText().toString();
                        s+=", ";
                        Destinations.setText(s);
                        Bookings.setText("");
                container.addView(addView, 0);
            }
        });

        LayoutTransition transition = new LayoutTransition();
        container.setLayoutTransition(transition);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_route, menu);
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
        if (v.getId()==R.id.buttonSubmitFromRouteFinal)
        {

            Find ob = new Find (this);
            try
            {
                ob.open();

                ob.createEntry("not Synced",bundle.getString("TimeStamp"),bundle.getString("NameOfCompany"),bundle.getString("CompanyAddress"), bundle.getString("Establish"), bundle.getString("Website"), bundle.getString("PromoterCEOHOS"), bundle.getString("ContactNumber1"), bundle.getString("ContactPersonName"),bundle.getString("ContactPersonNumber"),bundle.getString("ContactPersonDesignation"),bundle.getString("Email"),bundle.getString("BaseLoc"),bundle.getString("MinWeight"),bundle.getString("GodownSpace"),bundle.getString("SpecificProductCategoriesHandled"),bundle.getString("TypeOfVehicle"),bundle.getString("Product"),bundle.getString("ProductNotHandled"),bundle.getString("Payment"),bundle.getString("TypeOfPlayer"),bundle.getString("CategoryOfPlayer"),bundle.getString("TransitInsurance"),bundle.getString("DoorPickUp"),bundle.getString("DoorDelivery"),bundle.getString("DetentionCharges"),bundle.getString("PackagingService"),bundle.getString("Other"), bundle.getString("DeliveryPoints"),bundle.getString("BookingPoints"),Routes, bundle.getString("Latitude"), bundle.getString("Longitude"),"",null,null,null,null,null);

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
}
