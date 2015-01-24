package com.example.aakashmaroti.fillup;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.makemyandroidapp.googleformuploader.GoogleFormUploader;

import java.sql.SQLException;

/**
 * Created by Aakash Maroti on 16-Jan-15.
 */
public class ToSync
{

    private Context mContext;

    ToSync(Context c)
    {
        mContext=c;
    }
    public int SyncThis(int pos)
    {

        
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
            String DeliveryPoint="";
            String BookingPoint="";
            String Routes = "";
            String Latitude = "0";
            String Longitude = "0";
            String UserName="";
            String UserNumber ="";
            String UserEmail="";
            TelephonyManager tm = (TelephonyManager)mContext.getSystemService(mContext.TELEPHONY_SERVICE);
            UserNumber = tm.getLine1Number();
            Utils ob=new Utils();
            UserEmail = ob.getEmail(mContext);
            UserName=ob.getUsername(mContext);

            Find z = new Find(mContext);
            try
            {
                z.open();

                NameOfCompany = z.getNameofCompany(pos);
                CompanyAddress=z.getCompanyAddress(pos);
                Establish=z.getEstablish(pos);
                Website=z.getWebsite(pos);
                PromoterCEOHOS=z.getPromoterCEOHOS(pos);
                ContactNumber1=z.getContactNumber1(pos);
                ContactPersonName=z.getContactPersonName(pos);
                ContactPersonNumber=z.getContactPersonNumber(pos);
                ContactPersonDesignation=z.getContactPersonDesignation(pos);
                Email=z.getEmail(pos);
                BaseLoc=z.getBaseLocation(pos);
                MinWeight=z.getMinWeight(pos);
                GodownSpace=z.getGodownSpace(pos);
                SpecificProductCategoriesHandled=z.getSpecificProductCategoriesHandled(pos);
                TypeOfVehicle=z.getTypeOfVehicle(pos);
                Product=z.getProduct(pos);
                ProductNotHandled=z.getProductNotHandled(pos);
                Payment=z.getPayment(pos);
                TypeOfPlayer=z.getTypeOfPlayer(pos);
                CategoryOfPlayer=z.getCategoryOfPlayer(pos);
                TransitInsurance=z.getTransitInsurance(pos);
                DoorPickUp=z.getDoorPickUp(pos);
                DoorDelivery=z.getDoorDelivery(pos);
                DetentionCharges=z.getDetentionCharges(pos);
                PackagingService=z.getPackagingService(pos);
                Other=z.getOther(pos);
                DeliveryPoint=z.getDeliveryPoint(pos);
                BookingPoint=z.getBookingPoint(pos);
                Routes=z.getRoutes(pos);
                Latitude=z.getLatitude(pos);
                Longitude=z.getLongitude(pos);




                z.close();

            } catch (SQLException e)
            {
                e.printStackTrace();
            }

            GoogleFormUploader uploader = new GoogleFormUploader("1NDyZ0KSUwFGoXF_e1g8mg-Q_h75GpgcQveqzeQ3dWjw");
            uploader.addEntry("171500752", NameOfCompany);
            uploader.addEntry("932771706", CompanyAddress);
            uploader.addEntry("1162853420", Establish);
            uploader.addEntry("1262883359", Website);
            uploader.addEntry("1518869660", PromoterCEOHOS);
            uploader.addEntry("1798954835", ContactNumber1);
            uploader.addEntry("1260879836", ContactPersonName);
            uploader.addEntry("2127715693", ContactPersonNumber);
            uploader.addEntry("1171599336", ContactPersonDesignation);
            uploader.addEntry("724663497", Email);
            uploader.addEntry("265536634", BaseLoc);
            uploader.addEntry("1299924834", MinWeight);
            uploader.addEntry("1263915944", GodownSpace);
            uploader.addEntry("811800442", SpecificProductCategoriesHandled);
            uploader.addEntry("62127389", TypeOfVehicle);
            uploader.addEntry("360606211", Product);
            uploader.addEntry("1084630099", ProductNotHandled);
            uploader.addEntry("833023035", Payment);
            uploader.addEntry("54650670", TypeOfPlayer);
            uploader.addEntry("1306941342", CategoryOfPlayer);
            uploader.addEntry("89903802", TransitInsurance);
            uploader.addEntry("285154097", DoorPickUp);
            uploader.addEntry("1236467390", DoorDelivery);
            uploader.addEntry("1505207977", DetentionCharges);
            uploader.addEntry("33973323", PackagingService);
            uploader.addEntry("1191386570", Other);
            uploader.addEntry("367507201", DeliveryPoint);
            uploader.addEntry("969584987", BookingPoint);
            uploader.addEntry("2116838509", Routes );
            uploader.addEntry("831153633", UserName);
            uploader.addEntry("51549752", UserNumber );
            uploader.addEntry("821941442", UserEmail);
            uploader.addEntry("11111111", Latitude);
            uploader.addEntry("11111111", Longitude);

            uploader.upload();

            try
            {
                z.open();
                z.UpdateSync(pos);
                z.close();

            } catch (SQLException e)
            {
                e.printStackTrace();
            }

            return 1;
    }


}
