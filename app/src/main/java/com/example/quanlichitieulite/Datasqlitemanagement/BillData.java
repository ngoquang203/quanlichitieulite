package com.example.quanlichitieulite.Datasqlitemanagement;

import android.database.sqlite.SQLiteDatabase;

public class BillData implements Comparable<BillData> {
    private int Id;
    private int idDataCategory;
    private String IDservicecollect,Nameservice;
    private long Price;
    private String Content;
    private String Dates;
    private String Times;
    private long MoneyNow;
    public BillData(){};
    public BillData(int id, int idDataCategory, String IDservicecollect,String Nameservice, long price, String content, String dates,String times,long moneyNow) {
        Id = id;
        this.idDataCategory = idDataCategory;
        this.IDservicecollect = IDservicecollect;
        this.Nameservice = Nameservice;
        Price = price;
        Content = content;
        Dates = dates;
        Times = times;
        MoneyNow = moneyNow;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public long getMoneyNow() {
        return MoneyNow;
    }

    public void setMoneyNow(long moneyNow) {
        MoneyNow = moneyNow;
    }

    public String getNameservice() {
        return Nameservice;
    }

    public String getTimes() {
        return Times;
    }

    public void setTimes(String times) {
        Times = times;
    }

    public void setNameservice(String nameservice) {
        Nameservice = nameservice;
    }

    public int getIdDataCategory() {
        return idDataCategory;
    }

    public void setIdDataCategory(int idDataCategory) {
        this.idDataCategory = idDataCategory;
    }


    public String getIDservicecollect() {
        return IDservicecollect;
    }

    public void setIDservicecollect(String IDservicecollect) {
        this.IDservicecollect = IDservicecollect;
    }



    public long getPrice() {
        return Price;
    }

    public void setPrice(long price) {
        Price = price;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDates() {
        return Dates;
    }

    public void setDates(String dates) {
        Dates = dates;
    }


    @Override
    public int compareTo(BillData o) {
        return 0;
    }
}
