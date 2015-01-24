package com.example.aakashmaroti.fillup;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi.DriveContentsResult;
import com.google.android.gms.drive.DriveFolder.DriveFileResult;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;


public class Form extends BaseDemoActivity implements View.OnClickListener, LocationListener,
        GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener
{
	
	private static final String TAG = "ShipMyCargo -> Form Activity : ";
	boolean isConnected = false;

    //static final int CAMERA_REQUEST_FIRST_CAMERA = 4710;
    Bitmap Image1=null;
    Bitmap Image2=null;
    Bitmap Image3=null;
    Bitmap Image4=null;
    Bitmap Image5=null;

    String isSynced=" not Synced";
    String TimeStamp="";
    String NameOfCompany="";
    String CompanyAddress="";
    String Establish="";
    String Website="";
    String PromoterCEOHOS="";
    String ContactNumber1="";
    String ContactPersonName="";
    String ContactPersonNumber="";
    String ContactPersonDesignation="";
    String Email="";
    String BaseLoc="";
    String MinWeight="";
    String GodownSpace="";
    String SpecificProductCategoriesHandled="";
    String TypeOfVehicle="";
    String Product="";
    String ProductNotHandled="";
    String Payment="";
    String TypeOfPlayer="";
    String CategoryOfPlayer="";
    String TransitInsurance="";
    String DoorPickUp="";
    String DoorDelivery="";
    String DetentionCharges="";
    String PackagingService="";
    String Other="";



    EditText etNameOfCompany;
    EditText etPromoterCEOHOS;
    EditText etContactNumber1;
    EditText etContactPersonName;
    EditText etContactPersonNumber;
    EditText etMinWeight;
    Switch sDoorDelivery;
    EditText etSpecificProductCategoriesHandled;
    EditText sSpecificProductCategoriesHandled;
    Switch sTransitInsurance;

    EditText etDesignation;
    EditText etEmail;
    CheckBox ProductNotHandledGlass;
    CheckBox ProductNotHandledOil;
    CheckBox ProductNotHandledHazardousGoods;
    Spinner sTypeOfPlayer;
    Switch sDoorPickUp;
    Switch sDetentionCharges;
    EditText etOthers;
    EditText etAddress;
    EditText etEstablish;
    Spinner sCategoryOfPlayer;
    EditText etGodownSpace;
    Switch sPackagingService;
    EditText etWebsite;

    CheckBox PaymentBooking;
    CheckBox PaymentDelivery;
    CheckBox PaymentCredit;
    Button b;

    Bitmap initIcon;

    boolean ib1isActive=true;
    boolean ib2isActive=false;
    boolean ib3isActive=false;
    boolean ib4isActive=false;
    boolean ib5isActive=false;


    static final int REQUEST_IMAGE_CAPTURE1 = 1;
    static final int REQUEST_IMAGE_CAPTURE2 = 2;
    static final int REQUEST_IMAGE_CAPTURE3 = 3;
    static final int REQUEST_IMAGE_CAPTURE4 = 4;
    static final int REQUEST_IMAGE_CAPTURE5 = 5;


    ImageButton ib;
    ImageButton ib2;
    ImageButton ib3;
    ImageButton ib4;
    ImageButton ib5;

    CheckBox cbProduct[] = new CheckBox[11];


    MultiAutoCompleteTextView BaseLocation;
    MultiAutoCompleteTextView ContactDesignation;
    MultiAutoCompleteTextView VehicleType;
    String ProductArray[];

    Bundle bundle;

    private double lat, lng;
    private LocationRequest mLocationRequest;
    private LocationClient mLocationClient;

    private void onEnabledLocation() {
        LocationManager lm = null;
        boolean gps_enabled = false,network_enabled = false;
        if(lm==null)
            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try{
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }catch(Exception ex){}
        try{
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }catch(Exception ex){}

        if(!gps_enabled && !network_enabled){
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Do you want to enable location service?");
            dialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS );
                    startActivity(myIntent);
                    //get gps
                }
            });
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                }
            });
            dialog.show();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        lat = 0;
        lng = 0;

        // Create a new global location parameters object
        mLocationRequest = LocationRequest.create();
        /*
         * Set the update interval
         */
        mLocationRequest.setInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setFastestInterval(1000);
        mLocationClient = new LocationClient(this, this, this);

        onEnabledLocation();

         bundle=new Bundle();

        b=(Button) findViewById(R.id.Submit);
        b.setOnClickListener(this);

        ib = (ImageButton)findViewById(R.id.imageButton1);
        ib.setOnClickListener(this);

        ib2 = (ImageButton)findViewById(R.id.imageButton2);
        ib2.setOnClickListener(this);

        ib3 = (ImageButton)findViewById(R.id.imageButton3);
        ib3.setOnClickListener(this);

        ib4 = (ImageButton)findViewById(R.id.imageButton4);
        ib4.setOnClickListener(this);

        ib5 = (ImageButton)findViewById(R.id.imageButton5);
        ib5.setOnClickListener(this);

        initIcon = BitmapFactory.decodeResource(getResources(), R.drawable.captureimage);
        ib.setImageBitmap(initIcon);

        BaseLocation=(MultiAutoCompleteTextView) findViewById(R.id.BaseLocation);
        BaseLocation.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                BaseLocation.setHint("");
            }
        });
        ContactDesignation=(MultiAutoCompleteTextView)findViewById(R.id.ContactDesignation);
        
        VehicleType = (MultiAutoCompleteTextView)findViewById(R.id.MACTVTypeOfVehicle);
        String[] vehicles=getResources().getStringArray(R.array.VehicleBodyArray);
        ArrayAdapter<String> adapterVehicles =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,vehicles);
        
        
        String[] location = getResources().getStringArray(R.array.LocationS);
        ArrayAdapter<String> adapterLocation =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,location);

        String[] desgnation = getResources().getStringArray(R.array.DesignationContactPerson);
        ArrayAdapter<String> adapterDesignation =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,desgnation);

        ProductArray = getResources().getStringArray(R.array.SpecificProductCategoriesArray);

        BaseLocation.setThreshold(1);
        BaseLocation.setAdapter(adapterLocation);
        BaseLocation.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        ContactDesignation.setThreshold(1);
        ContactDesignation.setAdapter(adapterDesignation);
        ContactDesignation.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        ContactDesignation.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                ContactDesignation.showDropDown();
            }
        });
        ContactDesignation.setOnTouchListener(new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            ContactDesignation.showDropDown();
            return false;
        }
    });

        VehicleType.setThreshold(1);
        VehicleType.setAdapter(adapterVehicles);
        VehicleType.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        VehicleType.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                VehicleType.showDropDown();
            }
        });
        VehicleType.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                VehicleType.showDropDown();
                return false;
            }
        });

        etNameOfCompany = (EditText)findViewById(R.id.NameOfCompany);
        etPromoterCEOHOS = (EditText)findViewById(R.id.Promoter);
        etContactNumber1= (EditText)findViewById(R.id.ContactNo1);
        etContactNumber1.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                etContactNumber1.setHint("");
            }
        });
        
        etContactPersonName = (EditText)findViewById(R.id.ContactPerson) ;
        etContactPersonNumber = (EditText)findViewById(R.id.ContactNo2);
        etContactPersonNumber.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                etContactPersonNumber.setHint("");
            }
        });
        etOthers = (EditText) findViewById(R.id.Others);

        etMinWeight = (EditText)findViewById(R.id.MinWeight);
        sDoorDelivery=(Switch)findViewById(R.id.DoorDelivery);

        sTransitInsurance = (Switch)findViewById(R.id.switchTransitInsurance);

        sSpecificProductCategoriesHandled=(EditText)findViewById(R.id.sProductCategoriesHandles);

        etEmail = (EditText)findViewById(R.id.EmailDesignation);

        ProductNotHandledGlass = (CheckBox)findViewById(R.id.checkBoxGlass);
        ProductNotHandledOil = (CheckBox)findViewById(R.id.checkBoxOil);
        ProductNotHandledHazardousGoods = (CheckBox)findViewById(R.id.checkBoxHazard);


        sTypeOfPlayer = (Spinner)findViewById(R.id.spinnerTypeOfPlayer);
        sDoorPickUp = (Switch)findViewById(R.id.DoorPickUp);
        sDetentionCharges = (Switch)findViewById(R.id.DetentionCharges);

        etAddress=(EditText)findViewById(R.id.Address);
        etEstablish=(EditText)findViewById(R.id.Established);

        sCategoryOfPlayer=(Spinner)findViewById(R.id.spinnerCategoryOfPlayer);
        sPackagingService=(Switch)findViewById(R.id.PackagingService);
        etGodownSpace=(EditText)findViewById(R.id.GodownSpace);
        etWebsite=(EditText)findViewById(R.id.Website);
        etWebsite.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                etWebsite.setHint("");
            }
        });
        
        PaymentBooking=(CheckBox)findViewById(R.id.PaymentBooking);
        PaymentDelivery=(CheckBox)findViewById(R.id.PaymentDelivery);
        PaymentCredit=(CheckBox)findViewById(R.id.PaymentCredit);

        cbProduct[0]=(CheckBox)findViewById(R.id.pPTL);
        cbProduct[1]=(CheckBox)findViewById(R.id.pFTL);
        cbProduct[2]=(CheckBox)findViewById(R.id.p3PL);
        cbProduct[3]=(CheckBox)findViewById(R.id.pLTL);
        cbProduct[4]=(CheckBox)findViewById(R.id.pAir);
        cbProduct[5]=(CheckBox)findViewById(R.id.pRail);
        cbProduct[6]=(CheckBox)findViewById(R.id.pColdChain);
        cbProduct[7]=(CheckBox)findViewById(R.id.pWarehousing);
        cbProduct[8]=(CheckBox)findViewById(R.id.pFreightForwarding);
        cbProduct[9]=(CheckBox)findViewById(R.id.pMoversPackers);
        cbProduct[10]=(CheckBox)findViewById(R.id.pContractLogistics);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_form, menu);
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

   // */
    @Override
    public void onClick(View v)
    {
       if(v.getId()==R.id.imageButton1&&ib1isActive)
        {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE1);

        }


        }

        if(v.getId()==R.id.imageButton2&&ib2isActive)
        {

            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE2);
            }


        }
        if(v.getId()==R.id.imageButton3&&ib3isActive)
        {

            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE3);
            }


        }

        if(v.getId()==R.id.imageButton4&&ib4isActive)
        {

            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE4);
            }


        }

        if(v.getId()==R.id.imageButton5&&ib5isActive)
        {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE5);
            }

        }

        if(v.getId()==R.id.Submit)
        {
            boolean isIncomplete=false;
            ProductNotHandled = "";Product=""; Payment="";


            if (sDoorDelivery.isChecked())
                DoorDelivery = "Yes";
            else
                DoorDelivery = "No";

            TypeOfPlayer = sTypeOfPlayer.getSelectedItem().toString();
            CategoryOfPlayer=sCategoryOfPlayer.getSelectedItem().toString();
            NameOfCompany = etNameOfCompany.getText().toString();
            PromoterCEOHOS = etPromoterCEOHOS.getText().toString();
            ContactNumber1 = etContactNumber1.getText().toString();
            Other = etOthers.getText().toString();
            ContactPersonName = etContactPersonName.getText().toString();
            ContactPersonNumber = etContactPersonNumber.getText().toString();
            BaseLoc = BaseLocation.getText().toString();
            MinWeight = etMinWeight.getText().toString();
            CompanyAddress= etAddress.getText().toString();
            Establish = etEstablish.getText().toString();
            GodownSpace=etGodownSpace.getText().toString();
            Website=etWebsite.getText().toString();

            for(int i=0;i<11;i++)
            {
                if(cbProduct[i].isChecked())
                {
                    Product+=ProductArray[i];
                }
            }
            SpecificProductCategoriesHandled = sSpecificProductCategoriesHandled.getText().toString();
            if (sTransitInsurance.isChecked())
                TransitInsurance = "Yes";
            else
                TransitInsurance = "No";

            ContactPersonDesignation= ContactDesignation.getText().toString();
            Email = etEmail.getText().toString();
            CategoryOfPlayer=sCategoryOfPlayer.getSelectedItem().toString();

            TypeOfVehicle=VehicleType.getText().toString();

            if (ProductNotHandledGlass.isChecked())
                ProductNotHandled += "Glass, ";
            if (ProductNotHandledOil.isChecked())
                ProductNotHandled += "Oil, ";
            if (ProductNotHandledHazardousGoods.isChecked())
                ProductNotHandled += "Hazardous Goods";


            if (PaymentBooking.isChecked())
                Payment += "Booking, ";
            if (PaymentDelivery.isChecked())
                Payment += "Delivery, ";
            if (PaymentCredit.isChecked())
                Payment += "Credit, ";


            if (sPackagingService.isChecked())
                PackagingService = "Yes";
            else
                PackagingService = "No";
            if (sDoorPickUp.isChecked())
                DoorPickUp = "Yes";
            else
                DoorPickUp = "No";
            if (sDetentionCharges.isChecked())
                DetentionCharges = "Yes";
            else
                DetentionCharges = "No";

            if (ProductNotHandled.equalsIgnoreCase(""))
                ProductNotHandled = "null";

            if(Product.equalsIgnoreCase(""))
            {
                Toast.makeText(getApplicationContext(), "Please Select Type of Product", Toast.LENGTH_LONG).show();
                isIncomplete=true;
                ProductNotHandled = "";
                Product="";
                return;
            }

            if(Payment.equalsIgnoreCase(""))
            {
                Toast.makeText(getApplicationContext(), "Please Select Type of Payment", Toast.LENGTH_LONG).show();
                isIncomplete=true;
                ProductNotHandled = "";
                Product="";
                Payment="";
                return;
            }


            if (TypeOfPlayer.equalsIgnoreCase("Select Option"))
            {
                ProductNotHandled = "";
                Product=""; Payment="";
                isIncomplete=true;
                Toast.makeText(getApplicationContext(), "Please Select Type of Player", Toast.LENGTH_LONG).show();
                return;
            }

            if (TypeOfVehicle.equalsIgnoreCase("Select Option"))
            {
                ProductNotHandled = "";
                Product=""; Payment="";
                isIncomplete=true;
                Toast.makeText(getApplicationContext(), "Please Select Type of Vehicle", Toast.LENGTH_LONG).show();
                return;
            }


            if (CategoryOfPlayer.equalsIgnoreCase("Select Option"))
            {
                ProductNotHandled = "";
                Product=""; Payment="";
                isIncomplete=true;
                Toast.makeText(getApplicationContext(), "Please Select Category of Player", Toast.LENGTH_LONG).show();
                return;
            }


            if (NameOfCompany.equalsIgnoreCase(""))
            {
                ProductNotHandled = "";Product=""; Payment="";
                isIncomplete=true;
                Toast.makeText(getApplicationContext(), "Please fill Name of Company", Toast.LENGTH_LONG).show();
                return;
            }

            if (PromoterCEOHOS.equalsIgnoreCase(""))
            {
                ProductNotHandled = "";Product=""; Payment="";
                isIncomplete=true;
                Toast.makeText(getApplicationContext(), "Please fill Promoter / CEO / HOS", Toast.LENGTH_LONG).show();
                return;
            }
            if (ContactNumber1.equalsIgnoreCase(""))
            {
                ProductNotHandled = "";Product=""; Payment="";
                isIncomplete=true;
                Toast.makeText(getApplicationContext(), "Please fill Promoter/CEO/HOS 's Contact Number", Toast.LENGTH_LONG).show();
                return;
            }
            if (ContactPersonName.equalsIgnoreCase(""))
            {
                ProductNotHandled = "";Product=""; Payment="";
                isIncomplete=true;
                Toast.makeText(getApplicationContext(), "Please fill Contact Person's Name", Toast.LENGTH_LONG).show();
                return;
            }
            if (ContactPersonNumber.equalsIgnoreCase(""))
            {
                ProductNotHandled = "";Product=""; Payment="";
                isIncomplete=true;
                Toast.makeText(getApplicationContext(), "Please fill Contact Person's Number", Toast.LENGTH_LONG).show();
                return;
            }
            if (BaseLoc.equalsIgnoreCase(""))
            {
                ProductNotHandled = "";Product=""; Payment="";
                isIncomplete=true;
                Toast.makeText(getApplicationContext(), "Please fill Base Location", Toast.LENGTH_LONG).show();
                return;
            }
            if (MinWeight.equalsIgnoreCase(""))
            {
                ProductNotHandled = "";Product=""; Payment="";
                isIncomplete=true;
                Toast.makeText(getApplicationContext(), "Please fill Minimum Weight", Toast.LENGTH_LONG).show();
                return;
            }
            if (SpecificProductCategoriesHandled.equalsIgnoreCase("Select Option") || SpecificProductCategoriesHandled.equalsIgnoreCase(""))
            {
                ProductNotHandled = "";Product=""; Payment="";
                isIncomplete=true;
                Toast.makeText(getApplicationContext(), "Please fill Product Categories Handled", Toast.LENGTH_LONG).show();
                return;
            }

            if (Email.equalsIgnoreCase(""))
            {
                ProductNotHandled = "";Product=""; Payment="";
                isIncomplete=true;
                Toast.makeText(getApplicationContext(), "Please fill Email", Toast.LENGTH_LONG).show();
                return;
            }


            if (Website.equalsIgnoreCase(""))
            {
                ProductNotHandled = "";Product=""; Payment="";
                isIncomplete=true;
                Toast.makeText(getApplicationContext(), "Please fill Website", Toast.LENGTH_LONG).show();
                return;
            }
            if (GodownSpace.equalsIgnoreCase(""))
            {
                ProductNotHandled = "";Product=""; Payment="";
                isIncomplete=true;
                Toast.makeText(getApplicationContext(), "Please fill Godown Space", Toast.LENGTH_LONG).show();
                return;
            }
            if (CompanyAddress.equalsIgnoreCase(""))
            {
                ProductNotHandled = "";Product=""; Payment="";
                isIncomplete=true;
                Toast.makeText(getApplicationContext(), "Please fill Company Address", Toast.LENGTH_LONG).show();
                return;
            }

            if (Establish.equalsIgnoreCase(""))
            {
                ProductNotHandled = "";Product=""; Payment="";
                isIncomplete=true;
                Toast.makeText(getApplicationContext(), "Please fill Established Year", Toast.LENGTH_LONG).show();
                return;
            }
            if (PackagingService.equalsIgnoreCase(""))
            {
                ProductNotHandled = "";Product=""; Payment="";
                isIncomplete=true;
                Toast.makeText(getApplicationContext(), "Please fill Packaging Service", Toast.LENGTH_LONG).show();
                return;
            }
            if (CategoryOfPlayer.equalsIgnoreCase(""))
            {
                ProductNotHandled = "";Product=""; Payment="";
                isIncomplete=true;
                Toast.makeText(getApplicationContext(), "Please fill Category of player", Toast.LENGTH_LONG).show();
                return;
            }
            if (Image1==null)
            {
                ProductNotHandled = "";Product=""; Payment="";
                isIncomplete=true;
                Toast.makeText(getApplicationContext(), "Please capture an authentication image", Toast.LENGTH_LONG).show();
                return;
            }

            if(false)//checkContact()) /////// call to user defined function to validate any field
            {
                ProductNotHandled = "";Product=""; Payment="";
                isIncomplete=true;
                Toast.makeText(getApplicationContext(), "Please fill Up contact Person's Details properly", Toast.LENGTH_LONG).show();
                return;
            }

            /*if(checkWebsite()) ////////////// call to user defined function to validate any field
            {
                ProductNotHandled = "";Product=""; Payment="";
                isIncomplete=true;
                Toast.makeText(getApplicationContext(), "Please fill Up Website properly", Toast.LENGTH_LONG).show();
                return;
            }*/

            if(!isIncomplete)
            {
                if(Product.equalsIgnoreCase(""))
                    Product = "null";
                if(ProductNotHandled.equalsIgnoreCase(""))
                    ProductNotHandled = "null";

                TimeStamp = (DateFormat.format("dd-MM-yyyy hh:mm:ss", new java.util.Date()).toString());

                bundle.putString("TimeStamp", TimeStamp);
                bundle.putString("NameOfCompany", NameOfCompany);
                bundle.putString("CompanyAddress", CompanyAddress);
                bundle.putString("Establish", Establish);
                bundle.putString("Website",Website);
                bundle.putString("PromoterCEOHOS",PromoterCEOHOS);
                bundle.putString("ContactNumber1",ContactNumber1);
                bundle.putString("ContactPersonName",ContactPersonName);
                bundle.putString("ContactPersonNumber",ContactPersonNumber);
                bundle.putString("ContactPersonDesignation",ContactPersonDesignation);
                bundle.putString("Email",Email);
                bundle.putString("BaseLoc",BaseLoc);
                bundle.putString("MinWeight",MinWeight);
                bundle.putString("GodownSpace",GodownSpace);
                bundle.putString("SpecificProductCategoriesHandled",SpecificProductCategoriesHandled);
                bundle.putString("TypeOfVehicle",TypeOfVehicle);
                bundle.putString("Product",Product);
                bundle.putString("ProductNotHandled",ProductNotHandled);
                bundle.putString("Payment",Payment);
                bundle.putString("TypeOfPlayer",TypeOfPlayer);
                bundle.putString("CategoryOfPlayer",CategoryOfPlayer);
                bundle.putString("TransitInsurance",TransitInsurance);
                bundle.putString("DoorPickUp",DoorPickUp);
                bundle.putString("DoorDelivery",DoorDelivery);
                bundle.putString("DetentionCharges",DetentionCharges);
                bundle.putString("PackagingService",PackagingService);
                bundle.putString("Other",Other);
                bundle.putString("Latitude", lat + "");
                bundle.putString("Longitude", lng + "");

                Intent i=new Intent(this,Destination.class);
                i.putExtras(bundle);
                startActivity(i);


                try
                {

                    Find entry = new Find(Form.this);
                    entry.open();
                    entry.close();
                }
                catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Oops! There was an error"+e.toString()+"", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                finally
                {
                    cancel();
                }




            }

        }

    }

    private boolean checkWebsite()
    {
        int ctr=0;
        String d=Website;
        ctr=d.indexOf('.');
        if(ctr<=0)
            return true;
        ctr=0;
        ctr=d.indexOf("www");
        if(ctr<0)
            return true;
        return false;
    }

    private boolean checkContact() // user defined function to check for contacts
    {
        String a=ContactPersonName;
        String b=ContactPersonNumber;
        String c=ContactPersonDesignation;
        String d=Email;
        int a1=0;
        int b1=0;
        int c1=0;
        int d1=0;
        boolean value=true;
        for(int i=0;i<a.length();i++)
        {
            if(a.charAt(i)==',')
                a1++;
        }
        for(int i=0;i<b.length();i++)
        {
            if(b.charAt(i)==',')
                b1++;
        }
        for(int i=0;i<c.length();i++)
        {
            if(c.charAt(i)==',')
                c1++;
        }
        for(int i=0;i<d.length();i++)
        {
            if(d.charAt(i)==',')
                d1++;
        }
        if((c1-a1)>1||(c1-b1)>1||(c1-d1)>1)
            return true;
        b1=0;
        b="."+b+".";
        int ctr=0;
        for(int i=0;i<b.length();i++)
        {
            if(b.charAt(i)!='.'||b.charAt(i)!=',')
                ctr++;
            else
            {
                if(ctr!=0&&ctr<10)
                    return true;
                ctr=0;
            }
        }
        ctr=0;
        if(d.length()<5)
            return true;
        ctr=d.indexOf('@');
        if(ctr<=0)
            return true;
        ctr=0;
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Image1= imageBitmap;
            ib.setImageBitmap(imageBitmap);
            ib2.setImageBitmap(initIcon);
            ib2isActive = true;
            
            if (isConnected)
            	saveFileToDrive(Image1);
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE2 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Image2= imageBitmap;
            ib2.setImageBitmap(imageBitmap);
            ib3.setImageBitmap(initIcon);
            ib3isActive = true;
            if (isConnected)
            	saveFileToDrive(Image2);
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE3 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Image3= imageBitmap;
            ib3.setImageBitmap(imageBitmap);
            ib4.setImageBitmap(initIcon);
            ib4isActive = true;
            if (isConnected)
            	saveFileToDrive(Image3);
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE4 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Image4= imageBitmap;
            ib4.setImageBitmap(imageBitmap);
            ib5.setImageBitmap(initIcon);
            ib5isActive = true;
            if (isConnected)
            	saveFileToDrive(Image4);
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE5 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Image5= imageBitmap;
            ib5.setImageBitmap(imageBitmap);
            if (isConnected)
            	saveFileToDrive(Image5);
        }
    }

    public void cancel()
    {
        this.finish();
        return;
    }

    @Override
    public void onStop() {
        if (mLocationClient.isConnected()) {
            mLocationClient.removeLocationUpdates(this);
        }
        mLocationClient.disconnect();

        super.onStop();
    }

    @Override
    public void onStart() {

        super.onStart();
        mLocationClient.connect();

    }

    @Override
    public void onConnected(Bundle connectionHint) {
        super.onConnected(connectionHint);
        // create new contents resource
        isConnected = true;
        mLocationClient.requestLocationUpdates(mLocationRequest, this);
    }

    @Override
    public void onDisconnected() {

    }

    final private ResultCallback<DriveFileResult> fileCallback = new
            ResultCallback<DriveFileResult>() {
        @Override
        public void onResult(DriveFileResult result) {
            if (!result.getStatus().isSuccess()) {
                showMessage("Error while trying to create the file");
                return;
            }
            showMessage("Created a file with content: " + result.getDriveFile().getDriveId());
        }
    };
    
    /**
     * Create a new file and save it to Drive.
     */
    private void saveFileToDrive(Bitmap bitmap) {
        // Start by creating a new contents, and setting a callback.
        Log.i(TAG, "Creating new contents.");
        final Bitmap image = bitmap;
        Drive.DriveApi.newDriveContents(getGoogleApiClient())
                .setResultCallback(new ResultCallback<DriveContentsResult>() {

            @Override
            public void onResult(DriveContentsResult result) {
                // If the operation was not successful, we cannot do anything
                // and must
                // fail.
                if (!result.getStatus().isSuccess()) {
                    Log.i(TAG, "Failed to create new contents.");
                    return;
                }
                // Otherwise, we can write our data to the new contents.
                Log.i(TAG, "New contents created.");
                // Get an output stream for the contents.
                OutputStream outputStream = result.getDriveContents().getOutputStream();
                // Write the bitmap data from it.
                ByteArrayOutputStream bitmapStream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.PNG, 100, bitmapStream);
                try {
                    outputStream.write(bitmapStream.toByteArray());
                } catch (IOException e1) {
                    Log.i(TAG, "Unable to write file contents.");
                }
                // Create the initial metadata - MIME type and title.
                // Note that the user will be able to change the title later.
                MetadataChangeSet metadataChangeSet = new MetadataChangeSet.Builder()
                        .setMimeType("image/jpeg").setTitle(Calendar.getInstance().getTime().toString() + ".png").build();
                
                Drive.DriveApi.getRootFolder(getGoogleApiClient())
                .createFile(getGoogleApiClient(), metadataChangeSet, result.getDriveContents())
                .setResultCallback(fileCallback);
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        lng = location.getLongitude();
    }
}
