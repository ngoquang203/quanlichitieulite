package com.example.quanlichitieulite.Datasqlitemanagement;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServiceCollect {
    private int IDservicecollect;
    private String Nameservice,Explain;

    public ServiceCollect(int IDservicecollect, String nameservice, String explain) {
        this.IDservicecollect = IDservicecollect;
        Nameservice = nameservice;
        Explain = explain;
    }
    public ServiceCollect(String nameservice,String explain){
        Nameservice = nameservice;
        Explain = explain;
    }

//    public static ArrayList<ServiceCollect> getuserlist() throws SQLException {
//        Connection connection = SQLmanagement.connectionSQLSever();
//        ArrayList<ServiceCollect> list = new ArrayList<>();
//        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
//        String sql = "select * from ServiceCollect";
//        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
//        ResultSet rs = statement.executeQuery(sql);
//        while (rs.next()) {
//            list.add(new ServiceCollect(
//                    rs.getString("IDservicecollect").trim(),
//                    rs.getString("Nameservice").trim(),
//                    rs.getString("Explain").trim())
//            );// Đọc dữ liệu từ ResultSet
//        }
//        connection.close();// Đóng kết nối
//        return list;
//    }

    public int getIDservicecollect() {
        return IDservicecollect;
    }

    public void setIDservicecollect(int IDservicecollect) {
        this.IDservicecollect = IDservicecollect;
    }

    public String getNameservice() {
        return Nameservice;
    }

    public void setNameservice(String nameservice) {
        Nameservice = nameservice;
    }

    public String getExplain() {
        return Explain;
    }

    public void setExplain(String explain) {
        Explain = explain;
    }


}
