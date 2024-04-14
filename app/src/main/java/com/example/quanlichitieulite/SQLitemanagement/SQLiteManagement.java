package com.example.quanlichitieulite.SQLitemanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.quanlichitieulite.Datasqlitemanagement.BillData;
import com.example.quanlichitieulite.Datasqlitemanagement.BudgetData;
import com.example.quanlichitieulite.Datasqlitemanagement.CollectMoney;
import com.example.quanlichitieulite.Datasqlitemanagement.DetailColect;
import com.example.quanlichitieulite.Datasqlitemanagement.DetailSpent;
import com.example.quanlichitieulite.Datasqlitemanagement.PlanMonney;
import com.example.quanlichitieulite.Datasqlitemanagement.ServiceCollect;
import com.example.quanlichitieulite.Datasqlitemanagement.ServiceSpent;
import com.example.quanlichitieulite.Datasqlitemanagement.SpentMoney;
import com.example.quanlichitieulite.Datasqlitemanagement.Users;
import com.example.quanlichitieulite.PlanMoney;

import java.util.ArrayList;
import java.util.List;

public class SQLiteManagement extends SQLiteOpenHelper {

    // name table
    public static String User = "User";
    public static String PlanMoney = "PlanMoney";
    public static String SpentMoney = "SpentMoney";
    public static String CollectMoney = "CollectMoney";
    public static String DetailSpent = "DetailSpent";
    public static String DetailCollect = "DetailCollect";
    public static String ServiceSpent = "ServiceSpent";
    public static String ServiceCollect = "ServiceCollect";


    // name colum in table User
    public static String User_IDuser = "IDuser";
    public static String User_Names = "Names";
    public static String User_Sex = "Sex";
    public static String User_Email = "Email";
    public static String User_NumberPhone = "NumberPhone";

    // name colum in PlanMoney
    public static String PlanMoney_IDplan = "IDplan";
    public static String PlanMoney_IDuser = "IDuser";
    public static String PlanMoney_NamePlanMoney = "NamePlanMoney";
    public static String PlanMoney_SumMoney = "SumMoney";
    public static String PlanMoney_DateStart = "DateStart";
    public static String PlanMoney_DateEnd = "DateEnd";
    public static String PlanMoney_Content = "Content";
    public static String PlanMoney_MoneyNow = "MoneyNow";

    // name colum in SpentMoney
    public static String SpentMoney_IDspent = "IDspent";
    public static String SpentMoney_IDuser = "IDuser";
    public static String SpentMoney_SumSpent = "SumSpent";

    // name colum in CollectMoney
    public static String CollectMoney_IDcollect = "IDcollect";
    public static String CollectMoney_IDuser = "IDuser";
    public static String CollectMoney_SumCollect = "SumCollect";

    // name colum in DetailSpent
    public static String DetailSpent_IDdetailspent = "IDdetailspent";
    public static String DetailSpent_IDspent = "IDspent";
    public static String DetailSpent_IDservicespent = "IDservicespent";
    public static String DetailSpent_Nameservice = "Nameservice";
    public static String DetailSpent_Price = "Price";
    public static String DetailSpent_Content = "Content";
    public static String DetailSpent_Dates = "Dates";
    public static String DetailSpent_Times = "Times";
    public static String DetailSpent_MoneyNow = "MoneyNow";

    // name colum in DetailCollect
    public static String DetailCollect_IDdetailcolect = "IDdetailcolect";
    public static String DetailCollect_IDcollect = "IDcollect";
    public static String DetailCollect_IDservicecollect = "IDservicecollect";
    public static String DetailCollect_Nameservice = "Nameservice";
    public static String DetailCollect_Price = "Price";
    public static String DetailCollect_Content = "Content";
    public static String DetailCollect_Dates = "Dates";
    public static String DetailCollect_Times = "Times";
    public static String DetailCollect_MoneyNow = "MoneyNow";

    // name colum in ServiceSpent
    public static String ServiceSpent_IDservicespent = "IDservicespent";
    public static String ServiceSpent_Nameservice = "Nameservice";
    public static String ServiceSpent_Content = "Content";

    // name colum in ServiceCollect
    public static String ServiceCollect_IDservicecollect = "IDservicecollect";
    public static String ServiceCollect_Nameservice = "Nameservice";
    public static String ServiceCollect_Content = "Content";
    public SQLiteManagement(@Nullable Context context) {
        super(context,"Database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbUser = "CREATE TABLE " + User + " ( " +
                User_IDuser + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                User_Names + " TEXT," +
                User_Sex + " TEXT," +
                User_NumberPhone + " TEXT," +
                User_Email + " TEXT)";

        String tbPlanMoney = "CREATE TABLE " +  PlanMoney  + "(" +
                PlanMoney_IDuser + " INTEGER," +
                PlanMoney_IDplan +  " INTEGER PRIMARY KEY AUTOINCREMENT," +
                PlanMoney_NamePlanMoney + " INTEGER," +
                PlanMoney_SumMoney + " BIGINT," +
                PlanMoney_DateStart + " REAL," +
                PlanMoney_DateEnd + " REAL," +
                PlanMoney_Content + " TEXT," +
                PlanMoney_MoneyNow + " INTEGER)";

        String tbSpentMoney = "CREATE TABLE " + SpentMoney + "(" +
                SpentMoney_IDspent + " INTEGER PRIMARY KEY ," +
                SpentMoney_IDuser + " INTEGER," +
                SpentMoney_SumSpent + " BIGINT)";

        String tbCollectMoney = "CREATE TABLE " + CollectMoney + "(" +
                CollectMoney_IDcollect + " INTEGER PRIMARY KEY ," +
                CollectMoney_IDuser + " INTEGER," +
                CollectMoney_SumCollect + " BIGINT)";

        String tbServiceSpent = "CREATE TABLE " + ServiceSpent + "(" +
                ServiceSpent_IDservicespent + " TEXT PRIMARY KEY," +
                ServiceSpent_Nameservice + " TEXT," +
                ServiceSpent_Content + " TEXT)";

        String tbServiceCollect  = "CREATE TABLE " + ServiceCollect  + "(" +
                ServiceCollect_IDservicecollect + " TEXT PRIMARY KEY," +
                ServiceCollect_Nameservice + " TEXT," +
                ServiceCollect_Content + " TEXT)";

        String tbDetailSpent = "CREATE TABLE " + DetailSpent + "(" +
                DetailSpent_IDdetailspent + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DetailSpent_IDspent + " INTEGER," +
                DetailSpent_IDservicespent + " INTEGER," +
                DetailSpent_Nameservice + " TEXT," +
                DetailSpent_Price + " BIGINT," +
                DetailSpent_Content + " TEXT," +
                DetailSpent_Dates + " REAL," +
                DetailSpent_Times + " TEXT," +
                DetailSpent_MoneyNow + " BIGINT)";

        String tbDetailCollect = "CREATE TABLE " + DetailCollect + "(" +
                DetailCollect_IDdetailcolect + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DetailCollect_IDcollect + " INTEGER," +
                DetailCollect_IDservicecollect + " INTEGER," +
                DetailCollect_Nameservice + " TEXT," +
                DetailCollect_Price + " BIGINT," +
                DetailCollect_Content + " TEXT," +
                DetailCollect_Dates + " REAL," +
                DetailCollect_Times + " TEXT," +
                DetailCollect_MoneyNow + " BIGINT)";
        db.execSQL(tbUser);
        db.execSQL(tbPlanMoney);
        db.execSQL(tbSpentMoney);
        db.execSQL(tbCollectMoney);
        db.execSQL(tbServiceSpent);
        db.execSQL(tbServiceCollect);
        db.execSQL(tbDetailSpent);
        db.execSQL(tbDetailCollect);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }

    public void deletePlanMoney(int id){
        SQLiteDatabase db = this.open();
        String str = "DELETE FROM " + PlanMoney + " WHERE IDplan = " + id;
        db.execSQL(str);
        db.close();
    }

    public void InsertPlanMoney(int id,long money,String dateStart,String dateEnd,String content){
        SQLiteDatabase db = this.open();
        String str = "INSERT INTO " + PlanMoney + "(" + PlanMoney_NamePlanMoney + "," + PlanMoney_SumMoney + ","
                + PlanMoney_DateStart + "," + PlanMoney_DateEnd + ","
                + PlanMoney_Content + "," + PlanMoney_MoneyNow + ")" + "VALUES( " + id + "," +
                money + ",julianday('" + dateStart + "'),julianday('" + dateEnd + "'),'" + content + "',0)";
        db.execSQL(str);
        db.close();
    }
    public void createTriggerSpent(){
        SQLiteDatabase db = this.open();
        String str = "CREATE TRIGGER UpdatePlanMoneySpent\n" +
                "AFTER INSERT ON DetailSpent\n" +
                "BEGIN\n" +
                "    UPDATE PlanMoney\n" +
                "    SET MoneyNow = MoneyNow + NEW.Price\n" +
                "    WHERE NamePlanMoney = NEW.IDspent\n" +
                "        AND DateStart <= NEW.Dates\n" +
                "        AND DateEnd >= NEW.Dates;\n" +
                "END;";
        db.execSQL(str);
        db.close();
    }
    public void createTriggerCollect(){
        SQLiteDatabase db = this.open();
        String str = "CREATE TRIGGER UpdatePlanMoneyCollect\n" +
                "AFTER INSERT ON DetailCollect\n" +
                "BEGIN\n" +
                "    UPDATE PlanMoney\n" +
                "    SET MoneyNow = MoneyNow + NEW.Price\n" +
                "    WHERE NamePlanMoney = NEW.IDcollect\n" +
                "        AND DateStart <= NEW.Dates\n" +
                "        AND DateEnd >= NEW.Dates;\n" +
                "END;";
        db.execSQL(str);
        db.close();
    }

    public List<PlanMonney> getListDataPlanMoney(){
        SQLiteDatabase db = this.open();
        List<PlanMonney> list = new ArrayList<>();
        String str = "select " + PlanMoney_IDuser + "," + PlanMoney_IDplan + "," + PlanMoney_NamePlanMoney + "," +
                PlanMoney_SumMoney + ", strftime('%d/%m/%Y', " + PlanMoney_DateStart + "), " +
                "strftime('%d/%m/%Y', " + PlanMoney_DateEnd + ")," + PlanMoney_Content + "," + PlanMoney_MoneyNow + " FROM " + PlanMoney;
        Log.e("DATA",str);
        Cursor cursor = db.rawQuery(str,null);
        if(cursor.moveToFirst()){
            do{
                list.add(new PlanMonney(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getLong(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getLong(7)
                ));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }



    public void InsertUser(String name,String sex,String phone,String email){
        SQLiteDatabase db = this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteManagement.User_Names,name);
        contentValues.put(SQLiteManagement.User_Sex,sex);
        contentValues.put(SQLiteManagement.User_NumberPhone,phone);
        contentValues.put(SQLiteManagement.User_Email,email);
        long newInsert = db.update(SQLiteManagement.User,contentValues,"1",null);
        db.close();
    }

    public Users getUsers(){
        SQLiteDatabase db = this.open();
        Users users = new Users();
        String str = "SELECT * FROM " + SQLiteManagement.User;
        Cursor cursor = db.rawQuery(str,null);
        if(cursor.moveToFirst()){
            do{
                users = new Users(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)

                );
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return users;
    }

    public List<BudgetData> getListDataBudgetSpent(String dateStart,String dateEnd){
        SQLiteDatabase db = this.open();
        List<BudgetData> list = new ArrayList<>();
        String str = "select " + SQLiteManagement.DetailSpent_Dates +
                "," + SQLiteManagement.DetailSpent_IDservicespent + ","+
                SQLiteManagement.DetailSpent_Nameservice + "," +
                SQLiteManagement.DetailSpent_Price + " from " + "" +
                SQLiteManagement.DetailSpent + " WHERE " + SQLiteManagement.DetailSpent_Dates + " >= "
                + "julianday('" + dateStart + "') AND " + SQLiteManagement.DetailSpent_Dates + " <= " + "julianday('" + dateEnd + "')";
        Log.e("DATA",str);
        Cursor cursor = db.rawQuery(str,null);
        if(cursor.moveToFirst()){
            do{
                list.add(new BudgetData(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getLong(3)
                ));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public List<BudgetData> getListDataBudgetCollect(String dateStart,String dateEnd){
        SQLiteDatabase db = this.open();
        List<BudgetData> list = new ArrayList<>();
        String str = "select " + SQLiteManagement.DetailCollect_Dates +
                "," + SQLiteManagement.DetailCollect_IDservicecollect + ","+
                SQLiteManagement.DetailCollect_Nameservice + "," +
                SQLiteManagement.DetailCollect_Price + " from " + "" +
                SQLiteManagement.DetailCollect + " WHERE " + SQLiteManagement.DetailCollect_Dates + " >="
                + "julianday('" + dateStart + "') AND " + SQLiteManagement.DetailCollect_Dates + " <= " + "julianday('" + dateEnd + "')";
        Cursor cursor = db.rawQuery(str,null);
        if(cursor.moveToFirst()){
            do{
                list.add(new BudgetData(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getLong(3)
                ));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public List<BillData> getListDataSpentAndCollect(){
        SQLiteDatabase db = this.open();
        List<BillData> list = new ArrayList<>();
        String strSpent = "SELECT " + DetailSpent_IDdetailspent + ","
                + DetailSpent_IDspent + ","
                + DetailSpent_IDservicespent + ","
                + DetailSpent_Nameservice + ","
                + DetailSpent_Price + ","
                + DetailSpent_Content + ","
                + "strftime('%d/%m/%Y', Dates)" + ","
                + DetailSpent_Times + ","
                + DetailSpent_MoneyNow
                + " FROM " + SQLiteManagement.DetailSpent;
        Cursor cursor = db.rawQuery(strSpent,null);
        if(cursor.moveToFirst()){
            do{
                list.add(new BillData(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getLong(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getLong(8)
                ));
            }while (cursor.moveToNext());
        }
        String strCollect = "SELECT " + DetailCollect_IDdetailcolect + ","
                + DetailCollect_IDcollect + ","
                + DetailCollect_IDservicecollect + ","
                + DetailCollect_Nameservice + ","
                + DetailCollect_Price + ","
                + DetailCollect_Content + ","
                + "strftime('%d/%m/%Y', Dates)" + ","
                + DetailCollect_Times + ","
                + DetailCollect_MoneyNow
                + " FROM " + SQLiteManagement.DetailCollect;
        cursor = db.rawQuery(strCollect,null);
        if(cursor.moveToFirst()){
            do{
                list.add(new BillData(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getLong(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getLong(8)
                ));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public void updateSumCollect(){
        SQLiteDatabase db = this.open();
        String str = "UPDATE " + SQLiteManagement.CollectMoney +
                " SET " + SQLiteManagement.CollectMoney_SumCollect + " = (SELECT SUM(" + SQLiteManagement.DetailCollect_Price +
                ") FROM " + SQLiteManagement.DetailCollect + ")";
        db.execSQL(str);
        db.close();
    }

    public void updateSumSpent(){
        SQLiteDatabase db = this.open();
        String str = "UPDATE " + SQLiteManagement.SpentMoney +
                " SET " + SQLiteManagement.SpentMoney_SumSpent + " = (SELECT SUM(" + SQLiteManagement.DetailSpent_Price +
                ") FROM " + SQLiteManagement.DetailSpent + ")";
        db.execSQL(str);
        db.close();
    }

    public void InsertDetailSpent(String IDservicespent,String Nameservice,long Price,String Content,String Date,String Time,long MoneyNow){
        SQLiteDatabase db = this.open();
        String str = "INSERT INTO " + DetailSpent + "(" + DetailSpent_IDspent + "," + DetailSpent_IDservicespent + ","
                + DetailSpent_Nameservice + "," + DetailSpent_Price + ","
                + DetailSpent_Content + "," + DetailSpent_Dates + ","
                + DetailSpent_Times + "," + DetailSpent_MoneyNow + ")" + "VALUES(2,'" + IDservicespent + "','" +
                Nameservice + "'," + Price + ",'" + Content + "'," + Date + ",'" + Time + "'," + MoneyNow + ")";
        db.execSQL(str);
        db.close();
    }
    public List<DetailSpent> getDataDetailSpent(){
        SQLiteDatabase db = this.open();
        List<DetailSpent> detailSpents = new ArrayList<>();
        String str = "SELECT " + DetailSpent_IDdetailspent + ","
                + DetailSpent_IDspent + ","
                + DetailSpent_IDservicespent + ","
                + DetailSpent_Nameservice + ","
                + DetailSpent_Price + ","
                + DetailSpent_Content + ","
                +  DetailSpent_Dates + ","
                + DetailSpent_Times + ","
                + DetailSpent_MoneyNow
                + " FROM " + SQLiteManagement.DetailSpent;
        Cursor cursor = db.rawQuery(str,null);
        if(cursor.moveToFirst()){
            do{
                detailSpents.add(new DetailSpent(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getLong(4),
                        cursor.getString(5),
                        cursor.getDouble(6),
                        cursor.getString(7),
                        cursor.getLong(8)
                ));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return detailSpents;
    }

    public void InsertDetailCollect(String IDservicecollect,String Nameservice,long Price,String Content,String Date,String Time,long MoneyNow){
        SQLiteDatabase db = this.open();
        String str = "INSERT INTO " + DetailCollect + "(" + DetailCollect_IDcollect + "," + DetailCollect_IDservicecollect + ","
                + DetailCollect_Nameservice + "," + DetailCollect_Price + ","
                + DetailCollect_Content + "," + DetailCollect_Dates + ","
                + DetailCollect_Times + "," + DetailCollect_MoneyNow + ")" + "VALUES(1,'" + IDservicecollect + "','" +
                Nameservice + "'," + Price + ",'" + Content + "'," + Date + ",'" + Time + "'," + MoneyNow + ")";
        db.execSQL(str);
        db.close();
    }
    public List<DetailColect> getDataDetailColect(){
        SQLiteDatabase db = this.open();
        List<DetailColect> detailColects = new ArrayList<>();
        String str = "SELECT " + DetailCollect_IDdetailcolect + ","
                + DetailCollect_IDcollect + ","
                + DetailCollect_IDservicecollect + ","
                + DetailCollect_Nameservice + ","
                + DetailCollect_Price + ","
                + DetailCollect_Content + ","
                + DetailCollect_Dates + ","
                + DetailCollect_Times + ","
                + DetailCollect_MoneyNow
                + " FROM " + SQLiteManagement.DetailCollect;
        Cursor cursor = db.rawQuery(str,null);
        if(cursor.moveToFirst()){
            do{
                detailColects.add(new DetailColect(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getLong(4),
                        cursor.getString(5),
                        cursor.getDouble(6),
                        cursor.getString(7),
                        cursor.getLong(8)
                ));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return detailColects;
    }

    public void InsertServiceSpent(String ID, String Name ,String SupName){
        SQLiteDatabase db = this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteManagement.ServiceSpent_IDservicespent,ID);
        contentValues.put(SQLiteManagement.ServiceSpent_Nameservice,Name);
        contentValues.put(SQLiteManagement.ServiceSpent_Content,SupName);
        long newInsert = db.insert(SQLiteManagement.ServiceSpent,null,contentValues);
        db.close();
    }

    public List<ServiceSpent> getDataServiceSpent(){
        SQLiteDatabase db = this.open();
        List<ServiceSpent> serviceSpents = new ArrayList<>();
        String str = "SELECT * FROM " + SQLiteManagement.ServiceSpent;
        Cursor cursor = db.rawQuery(str,null);
        if(cursor.moveToFirst()){
            do{
                serviceSpents.add(new ServiceSpent(
                       cursor.getString(0),
                       cursor.getString(1),
                       cursor.getString(2)
                ));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return serviceSpents;
    }

    public void InsertServiceCollect(String ID, String Name ,String SupName){
        SQLiteDatabase db = this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteManagement.ServiceCollect_IDservicecollect,ID);
        contentValues.put(SQLiteManagement.ServiceCollect_Nameservice,Name);
        contentValues.put(SQLiteManagement.ServiceCollect_Content,SupName);
        long newInsert = db.insert(SQLiteManagement.ServiceCollect,null,contentValues);
        db.close();
    }

    public List<ServiceCollect> getDataServiceCollect(){
        SQLiteDatabase db = this.open();
        List<ServiceCollect> serviceCollects = new ArrayList<>();
        String str = "SELECT * FROM " + SQLiteManagement.ServiceCollect;
        Cursor cursor = db.rawQuery(str,null);
        if(cursor.moveToFirst()){
            do{
                serviceCollects.add(new ServiceCollect(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2)
                ));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return serviceCollects;
    }

    public void InsertNameUser(String Name){
        SQLiteDatabase db = this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteManagement.User_Names,Name);
        long newName = db.insert(SQLiteManagement.User,null,contentValues);
        db.close();
    }

    public Users getDataUser(){
        SQLiteDatabase db = this.open();
        Users users = new Users();
        String str = "SELECT * FROM " + SQLiteManagement.User;
        Cursor cursor = db.rawQuery(str,null);
        if(cursor.moveToFirst()){
            do{
                users = new Users(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return users;
    }

    public void InsertMoneySpent(long Money){
        SQLiteDatabase db = this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteManagement.SpentMoney_IDspent,2);
        contentValues.put(SQLiteManagement.SpentMoney_SumSpent,Money);
        long newName = db.insert(SQLiteManagement.SpentMoney,null,contentValues);
        db.close();
    }

    public SpentMoney getDataSpentMoney(){
        SQLiteDatabase db = this.open();
        SpentMoney spentMoney = new SpentMoney();
        String str = "SELECT * FROM " + SQLiteManagement.SpentMoney;
        Cursor cursor = db.rawQuery(str,null);
        if(cursor.moveToFirst()){
            do{
                spentMoney = new SpentMoney(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return spentMoney;
    }

    public void InsertMoneyCollect(long Money){
        SQLiteDatabase db = this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteManagement.CollectMoney_IDcollect,1);
        contentValues.put(SQLiteManagement.CollectMoney_SumCollect,Money);
        long newName = db.insert(SQLiteManagement.CollectMoney,null,contentValues);
        db.close();
    }

    public CollectMoney getDataCollectMoney(){
        SQLiteDatabase db = this.open();
        CollectMoney collectMoney = new CollectMoney();
        String str = "SELECT * FROM " + SQLiteManagement.CollectMoney;
        Cursor cursor = db.rawQuery(str,null);
        if(cursor.moveToFirst()){
            do{
                collectMoney = new CollectMoney(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return collectMoney;
    }

}
