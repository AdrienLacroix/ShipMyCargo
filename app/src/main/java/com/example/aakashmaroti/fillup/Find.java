package com.example.aakashmaroti.fillup;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Address;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aakash Maroti on 28-Dec-14.
 */
public class Find
{
    public static final String KEY_ROWID = "_id";
    public static final String KEY_isSynced="sync";
    public static final String KEY_TimeStamp="timestamp";
    public static final String KEY_NameOfCompany="name_of_company";
    public static final String KEY_CompanyAddress="company_address";
    public static final String KEY_Establish="established";
    public static final String KEY_Website="website";
    public static final String KEY_PromoterCEOHOS="promoter_ceo_hos";
    public static final String KEY_ContactNumber1="contact_number_promoter";
    public static final String KEY_ContactPersonName="contact_person_name";
    public static final String KEY_ContactPersonNumber="contact_person_number";
    public static final String KEY_ContactPersonDesignation="contact_person_designation";
    public static final String KEY_Email="email";
    public static final String KEY_BaseLoc="base_location";
    public static final String KEY_MinWeight="min_weight";
    public static final String KEY_GodownSpace="godown_space";
    public static final String KEY_SpecificProductCategoriesHandled="specific_product_categories_handled";
    public static final String KEY_TypeOfVehicle="type_of_vehicle";
    public static final String KEY_Product="product";
    public static final String KEY_ProductNotHandled="product_not_handled";
    public static final String KEY_Payment="payment";
    public static final String KEY_TypeOfPlayer="type_of_player";
    public static final String KEY_CategoryOfPlayer="categories_of_player";
    public static final String KEY_TransitInsurance="transit_insurance";
    public static final String KEY_DoorPickUp="door_pickup";
    public static final String KEY_DoorDelivery="door_delivery";
    public static final String KEY_DetentionCharges="destination_charges";
    public static final String KEY_PackagingService="packaging_service";
    public static final String KEY_Other="other";
    public static final String KEY_DeliveryPoint="delivery_point";
    public static final String KEY_BookingPoint="booking_point";
    public static final String KEY_Routes="routes";
    public static final String KEY_imageLink="image";
    public static final String KEY_locationLat="latitude";
    public static final String KEY_locationLng="longitude";

    public static final String KEY_Image1="image1";
    public static final String KEY_Image2="image2";
    public static final String KEY_Image3="image3";
    public static final String KEY_Image4="image4";
    public static final String KEY_Image5="image5";


    private static final String DATABASE_NAME = "FillUpFormsDB";
    private static final String DATABASE_TABLE = "FillUpFormsTable";
    private static final int DATABASE_VERSION = 1;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;


    private static class DbHelper extends SQLiteOpenHelper
    {

        public DbHelper(Context context)
        {
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }



        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
                    KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_isSynced+" TEXT, "+
                    KEY_TimeStamp+" TEXT, "+
                    KEY_NameOfCompany+" TEXT, " +
                    KEY_CompanyAddress+" TEXT, " +
                    KEY_Establish+" TEXT, " +
                    KEY_Website+" TEXT, " +
                    KEY_PromoterCEOHOS+" TEXT, " +
                    KEY_ContactNumber1+" TEXT, " +
                    KEY_ContactPersonName+" TEXT, " +
                    KEY_ContactPersonNumber+" TEXT, " +
                    KEY_ContactPersonDesignation+" TEXT, " +
                    KEY_Email+" TEXT, " +
                    KEY_BaseLoc+" TEXT, " +
                    KEY_MinWeight+" TEXT, " +
                    KEY_GodownSpace+" TEXT, " +
                    KEY_SpecificProductCategoriesHandled+" TEXT, " +
                    KEY_TypeOfVehicle+" TEXT, " +
                    KEY_Product+" TEXT, " +
                    KEY_ProductNotHandled+" TEXT, " +
                    KEY_Payment+" TEXT, " +
                    KEY_TypeOfPlayer+" TEXT, " +
                    KEY_CategoryOfPlayer+" TEXT, " +
                    KEY_TransitInsurance+" TEXT, " +
                    KEY_DoorPickUp+" TEXT, " +
                    KEY_DoorDelivery+" TEXT, " +
                    KEY_DetentionCharges+" TEXT, " +
                    KEY_PackagingService+" TEXT, " +
                    KEY_Other+" TEXT, "+
                    KEY_DeliveryPoint+" TEXT, " +
                    KEY_BookingPoint+" TEXT, " +
                    KEY_Routes+" TEXT, " +
                    KEY_locationLat+" TEXT, " +
                    KEY_locationLng+" TEXT, " +
                    KEY_imageLink+" TEXT);"

            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
            onCreate(db);
        }
    }


    public Find(Context c)
    {
        ourContext = c;
    }


    public Find open()throws SQLException
    {
        ourHelper = new DbHelper(ourContext);
        ourDatabase=ourHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        ourHelper.close();
    }


    public long createEntry(String isSynced,String TimeStamp,String NameOfCompany,String CompanyAddress, String Establish, String Website, String PromoterCEOHOS, String ContactNumber1, String ContactPersonName,String ContactPersonNumber,String ContactPersonDesignation,String Email,String BaseLoc,String MinWeight,String GodownSpace,String SpecificProductCategoriesHandled,String TypeOfVehicle,String Product,String ProductNotHandled,String Payment,String TypeOfPlayer,String CategoryOfPlayer,String TransitInsurance,String DoorPickUp,String DoorDelivery,String DetentionCharges,String PackagingService,String Other, String DeliveryLocaion, String BookingLocation, String Route, String Latitude, String Longitude, String ImageLink, Bitmap image1, Bitmap image2, Bitmap image3, Bitmap image4, Bitmap image5)
    {
        ContentValues cv=new ContentValues();
        cv.put(KEY_isSynced,isSynced);
        cv.put(KEY_TimeStamp,TimeStamp);
        cv.put(KEY_NameOfCompany,NameOfCompany);
        cv.put(KEY_CompanyAddress, CompanyAddress);
        cv.put(KEY_Establish,Establish);
        cv.put(KEY_Website,Website);
        cv.put(KEY_PromoterCEOHOS,PromoterCEOHOS);
        cv.put(KEY_ContactNumber1,ContactNumber1);
        cv.put(KEY_ContactPersonName,ContactPersonName);
        cv.put(KEY_ContactPersonNumber,ContactPersonNumber);
        cv.put(KEY_ContactPersonDesignation,ContactPersonDesignation);
        cv.put(KEY_Email,Email);
        cv.put(KEY_BaseLoc,BaseLoc);
        cv.put(KEY_MinWeight,MinWeight);
        cv.put(KEY_GodownSpace,GodownSpace);
        cv.put(KEY_SpecificProductCategoriesHandled,SpecificProductCategoriesHandled);
        cv.put(KEY_TypeOfVehicle,TypeOfVehicle);
        cv.put(KEY_Product,Product);
        cv.put(KEY_ProductNotHandled,ProductNotHandled);
        cv.put(KEY_Payment,Payment);
        cv.put(KEY_TypeOfPlayer,TypeOfPlayer);
        cv.put(KEY_CategoryOfPlayer,CategoryOfPlayer);
        cv.put(KEY_TransitInsurance,TransitInsurance);
        cv.put(KEY_DoorPickUp,DoorPickUp);
        cv.put(KEY_DoorDelivery,DoorDelivery);
        cv.put(KEY_DetentionCharges,DetentionCharges);
        cv.put(KEY_PackagingService,PackagingService);
        cv.put(KEY_Other,Other);
        cv.put(KEY_DeliveryPoint,DeliveryLocaion);
        cv.put(KEY_BookingPoint,BookingLocation);
        cv.put(KEY_Routes,Route);
        cv.put(KEY_locationLat, Latitude);
        cv.put(KEY_locationLng, Longitude);
        cv.put(KEY_imageLink,ImageLink);

        
        return ourDatabase.insert(DATABASE_TABLE,null,cv);

    }



    public Cursor getCursor()
    {
        	String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_locationLat,KEY_locationLng,KEY_imageLink};
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        return c;
    }




    public void SyncAll()
    {

       	String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_locationLat,KEY_locationLng,KEY_imageLink};
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        int pos=1;
        String isSynced="";
        int SyncedOrNot=0;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {


           // for(int i=0;i<100000000;i++)




            SyncedOrNot=0;
            try
            {
                //obj.open();
                pos=Integer.parseInt(c.getString(0));
                //obj.close();

            } catch (Exception e)
            {
                e.printStackTrace();
            }

            try
            {
                //obj.open();
                isSynced = getSyncStatus(pos);
                //obj.close();

            } catch (Exception e)
            {
                e.printStackTrace();
            }
            if (isSynced.equalsIgnoreCase("not Synced")||isSynced.equalsIgnoreCase(" not Synced"))
            {

                    Log.d("Syncing paya", "yi");
                    //  Toast.makeText(getApplicationContext(), "Syncing your record", Toast.LENGTH_LONG).show();
                    //Find objkht = new Find(ourContext);
                    ToSync abc = new ToSync(ourContext);
                    SyncedOrNot = abc.SyncThis(pos);
                    if (SyncedOrNot == 1)
                    {
                        Log.d("Ho gaya synced", "yi");
                        ;

                        try
                        {
                           UpdateSync(pos);
                           // populateListViewFromDatabase();
                        } catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    //Toast.makeText(getApplicationContext(), "Your Record has been Synced", Toast.LENGTH_LONG).show();
                ;
                //Toast.makeText(getApplicationContext(), "You are not connected to the internet", Toast.LENGTH_LONG).show();


            } else
                Log.d("Synced already paya", "yi");
            ;
            // Toast.makeText(getApplicationContext(), "This is already Synced", Toast.LENGTH_LONG).show();
        }

    }





    public SimpleCursorAdapter listUp()
    {

        String columns[]=new String[]{KEY_ROWID,KEY_NameOfCompany,KEY_isSynced};
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        int toViewIDs[] = new int[]{R.id.rowno,R.id.timestamp,R.id.syncdetails};
        SimpleCursorAdapter CursorAdapter;
        CursorAdapter = new SimpleCursorAdapter(ourContext,R.layout.design_row,c,columns,toViewIDs,0);
        return CursorAdapter;
    }

    public void UpdateSync(int pos)
    {
        ContentValues cvUpdate =new ContentValues();
        cvUpdate.put(KEY_isSynced,"Synced");
        ourDatabase.update(DATABASE_TABLE,cvUpdate,KEY_ROWID+"="+pos,null);
    }



    public String getDetails(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_locationLat,KEY_locationLng,KEY_imageLink};
        String result="";

        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            String r;
            c.moveToFirst();
            r="\nRecord No. : "+c.getString(0)+"\n\nSync Status:"+c.getString(1)+"\n\nTime of Creation:\n"+c.getString(2)+"\n\nName of Company:\n"+c.getString(3)+"\n\nCompany Address \n"+c.getString(4)+"\n\nEstablished in:\n"+c.getString(5)+"\n\nWebsite :\n"+c.getString(6)+"\n\nPromoter / CEO / HOS :\n"+c.getString(7)+"\n\nPromoter /CEO/ HOS 's Number\n"+c.getString(8)+"\n\nContact Person's Name\n"+c.getString(9)+"\n\nContact Person's Number:\n"+c.getString(10)+"\n\nContact Person's Designation:\n"+c.getString(11)+"\n\nEmail\n"+c.getString(12)+"\n\nBAse Location:\n"+c.getString(13)+"\n\nMinimum Weight:\n"+c.getString(14)+"\n\nGodown Space:\n"+c.getString(15)+"\n\nSpecific Product Categories\n"+c.getString(16)+"\n\nType of Vehicle:\n"+c.getString(17)+"\n\nProduct:\n"+c.getString(18)+"\n\nProduct Not Handled:\n"+c.getString(19)+"\n\nPayment\n"+c.getString(20)+"\n\nType Of Player :\n"+c.getString(21)+"\n\nCategory Of Player :\n"+c.getString(22)+"\n\nTransit Insurance :\n"+c.getString(23)+"\n\nDoor Pickup:\n"+c.getString(24)+"\n\nDoor Delivery:\n"+c.getString(25)+"\n\nDetention Charges :\n"+c.getString(26)+"\n\nPackaging Service :\n"+c.getString(27)+"\n\nOther :\n"+c.getString(28)+"\n\nDelivery Location :\n"+c.getString(29)+"\n\nBooking Location:\n"+c.getString(30)+"\n\nRoute :\n"+c.getString(31);
            //r="\nRecord No. : "+c.getString(0)+"\nSync Status:\n"+c.getString(1)+"\nTime of Creation:\n"+c.getString(2)+"\nName of Company:\n"+c.getString(3)+"\nPromoter/CEO/HOS\n"+c.getString(4)+"\nContact :\n"+c.getString(5)+"\nContact Person Name :\n"+c.getString(6)+"\nContact Person's number\n"+c.getString(7)+"\nBase Location\n"+c.getString(8)+"\nMinimum Weight:\n"+c.getString(9)+"\nDoor Delivery:\n"+c.getString(10)+"\nSpecific Product Categories Handled:\n"+c.getString(11)+"\nTransit Insurance:\n"+c.getString(12)+"\nProduct:\n"+c.getString(13)+"\nDesignation:\n"+c.getString(14)+"\nEmail:\n"+c.getString(15)+"\nProduct Not Handled:\n"+c.getString(16)+"\nType Of Player:\n"+c.getString(17)+"\nDoor Pickup:\n"+c.getString(18)+"\nDetention Charges\n"+c.getString(19)+"\nBooking Location :\n"+c.getString(20)+"\nDelivery Location :\n"+c.getString(21)+"\nOthers:\n"+c.getString(22)+"\nCompany Address :\n"+c.getString(23)+"\nYear Established :\n"+c.getString(24)+"\nCategory of Player :\n"+c.getString(25)+"\nPayment :\n"+c.getString(26)+"\nMax Weight\n"+c.getString(27)+"\nVehicle :\n"+c.getString(28)+"\nMax Length :\n"+c.getString(29)+"\nMax Breadth :\n"+c.getString(30)+"\nMax Height :\n"+c.getString(31)+"\nPackaging Service :\n"+c.getString(32)+"\nGodown Space :\n"+c.getString(33)+"\nWebsite :\n"+c.getString(34);
            return r;
        }

        return result;
    }

    public void DeleteRow(int pos)
    {
        ourDatabase.delete(DATABASE_TABLE,KEY_ROWID+"="+pos,null);
    }

    public String getTimeStamp(long row)
    {
        	String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_locationLat,KEY_locationLng,KEY_imageLink};
        String result="";
        //////////////////////Start here
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(2);
        }
        return result;
    }

    public String getSyncStatus(long row)
    {
        	String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(1);
        }
        return result;
    }

    public String getNameofCompany(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(3);
        }
        return result;
    }

    public String getCompanyAddress(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(4);
        }
        return result;
    }
    public String getEstablish(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(5);
        }
        return result;
    }
    public String getWebsite(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(6);
        }
        return result;
    }
    public String getPromoterCEOHOS(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(7);
        }
        return result;
    }
    public String getContactNumber1(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(8);
        }
        return result;
    }

    public String getContactPersonName(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(9);
        }
        return result;
    }
    public String getContactPersonNumber(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(10);
        }
        return result;
    }
    public String getContactPersonDesignation(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(11);
        }
        return result;
    }

    public String getEmail(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(12);
        }
        return result;
    }

    public String getBaseLocation(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(13);
        }
        return result;
    }
    public String getMinWeight(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(14);
        }
        return result;
    }
    public String getGodownSpace(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(15);
        }
        return result;
    }
    public String getSpecificProductCategoriesHandled(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(16);
        }
        return result;
    }
    public String getTypeOfVehicle(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(17);
        }
        return result;
    }
    public String getProduct(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(18);
        }
        return result;
    }
    public String getProductNotHandled(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(19);
        }
        return result;
    }
    public String getPayment(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(20);
        }
        return result;
    }
    public String getTypeOfPlayer(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(21);
        }
        return result;
    }
    public String getCategoryOfPlayer(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(22);
        }
        return result;
    }
    public String getTransitInsurance(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(23);
        }
        return result;
    }
    public String getDoorPickUp(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(24);
        }
        return result;
    }
    public String getDoorDelivery(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(25);
        }
        return result;
    }
    public String getDetentionCharges(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(26);
        }
        return result;
    }
    public String getPackagingService(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(27);
        }
        return result;
    }
    public String getOther(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(28);
        }
        return result;
    }
    public String getDeliveryPoint(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(29);
        }
        return result;
    }
    public String getBookingPoint(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(30);
        }
        return result;
    }
    public String getRoutes(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_isSynced,KEY_TimeStamp,KEY_NameOfCompany,KEY_CompanyAddress,KEY_Establish,KEY_Website,KEY_PromoterCEOHOS,KEY_ContactNumber1,KEY_ContactPersonName,KEY_ContactPersonNumber,KEY_ContactPersonDesignation,KEY_Email,KEY_BaseLoc,KEY_MinWeight,KEY_GodownSpace,KEY_SpecificProductCategoriesHandled,KEY_TypeOfVehicle,KEY_Product,KEY_ProductNotHandled,KEY_Payment,KEY_TypeOfPlayer,KEY_CategoryOfPlayer,KEY_TransitInsurance,KEY_DoorPickUp,KEY_DoorDelivery,KEY_DetentionCharges,KEY_PackagingService,KEY_Other,KEY_DeliveryPoint,KEY_BookingPoint,KEY_Routes,KEY_imageLink};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(31);
        }
        return result;
    }
    public String getLatitude(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_locationLat};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(1);
        }
        return result;
    }
    public String getLongitude(long row)
    {
        String columns[]=new String[] {KEY_ROWID,KEY_locationLng};
        String result="";
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+row,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            result=c.getString(1);
        }
        return result;
    }
}
