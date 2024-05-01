package com.example.quanlichitieulite.Datasqlitemanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.quanlichitieulite.SQLitemanagement.SQLiteManagement;


public class Users {
    private int IDusers;
    private String Names;
    private String Sex;
    private String NumberPhone;
    private String Email;
    public Users(){};
    public Users(int idUsers,String names,String sex, String numberPhone, String email) {
        IDusers = idUsers;
        Names = names;
        Sex = sex;
        NumberPhone = numberPhone;
        Email = email;
    }

    public static void UpdateInfoUsers(String Name,String Sex,String Email,String phone) throws Exception{
        String sql = "update Users set Names = N'" + Name + "',Sex = N'" + Sex + "',Email = '" + Email + "' where ID = '" + phone + "'\n";
    }



    public int getIDusers() {
        return IDusers;
    }

    public void setIDusers(int IDusers) {
        this.IDusers = IDusers;
    }

    public String getNames() {
        return Names;
    }

    public void setNames(String names) {
        Names = names;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getNumberPhone() {
        return NumberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        NumberPhone = numberPhone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


}
