package com.example.quanlichitieulite.Datasqlitemanagement;


public class BudgetData {
    private String dates;
    private String IDservice;
    private String Nameservice;
    private long Price;

    public BudgetData(String dates, String IDservice, String nameservice, long price) {
        this.dates = dates;
        this.IDservice = IDservice;
        Nameservice = nameservice;
        Price = price;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getIDservice() {
        return IDservice;
    }

    public void setIDservice(String IDservice) {
        this.IDservice = IDservice;
    }

    public String getNameservice() {
        return Nameservice;
    }

    public void setNameservice(String nameservice) {
        Nameservice = nameservice;
    }

    public long getPrice() {
        return Price;
    }

    public void setPrice(long price) {
        Price = price;
    }
}
