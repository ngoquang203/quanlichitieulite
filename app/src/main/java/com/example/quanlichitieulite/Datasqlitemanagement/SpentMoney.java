package com.example.quanlichitieulite.Datasqlitemanagement;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanlichitieulite.SQLitemanagement.SQLiteManagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SpentMoney {
    private int IDuser,IDspent;
    private long SumSpent;
    public SpentMoney(){};
    public SpentMoney(int IDuser, int IDspent, long sumSpent) {
        this.IDuser = IDuser;
        this.IDspent = IDspent;
        SumSpent = sumSpent;
    }




//    public static void addIDSpent(int IDUsers) throws SQLException {
//        Connection connection = SQLmanagement.connectionSQLSever();
//        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
//        String sql = "insert into SpentMoney(IDuser) values(" + IDUsers + ")";
//        ResultSet rs = statement.executeQuery(sql);
//        connection.close();
//    }
//    public static SpentMoney getuserlist(int IDUsers) throws SQLException {
//
//        Connection connection = SQLmanagement.connectionSQLSever();
//        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
//        String sql = "select * from SpentMoney where IDuser = '" + IDUsers +"'";
//        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
//        ResultSet rs = statement.executeQuery(sql);
//        SpentMoney spentMoney = new SpentMoney();
//        while(rs.next()){
//            spentMoney = new SpentMoney(
//                    rs.getInt("IDuser"),
//                    rs.getInt("IDspent"),
//                    rs.getLong("SumSpent"));
//        }
//        // Đọc dữ liệu từ ResultSet
//        connection.close();// Đóng kết nối
//        return spentMoney;
//    }

    public int getIDuser() {
        return IDuser;
    }

    public void setIDuser(int IDuser) {
        this.IDuser = IDuser;
    }

    public int getIDspent() {
        return IDspent;
    }

    public void setIDspent(int IDspent) {
        this.IDspent = IDspent;
    }

    public long getSumSpent() {
        return SumSpent;
    }

    public void setSumSpent(long sumSpent) {
        SumSpent = sumSpent;
    }
}
