package com.example.quanlichitieulite.Datasqlitemanagement;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Logins {
    private String ID;
    private String Passwords;

    public Logins(){};
    public Logins(String ID, String passwords) {
        this.ID = ID;
        this.Passwords = passwords;
    }

//    public static ArrayList<Logins> getuserlist() throws SQLException {
//        Connection connection = SQLmanagement.connectionSQLSever();
//        ArrayList<Logins> list = new ArrayList<>();
//        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
//        String sql = "select * from Logins";
//        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
//        ResultSet rs = statement.executeQuery(sql);
//        while (rs.next()) {
//            list.add(new Logins(
//                    rs.getString("ID").trim(),
//                    rs.getString("Passwords").trim()));// Đọc dữ liệu từ ResultSet
//        }
//        connection.close();// Đóng kết nối
//        return list;
//    }
//    public static void changePassword(String phone,String passwords) throws SQLException {
//        Connection connection = SQLmanagement.connectionSQLSever();
//        Statement statement = connection.createStatement();
//        String sql = "UPDATE Logins SET Passwords = '" + passwords + "' WHERE ID = '" + phone + "'";
//        statement.executeUpdate(sql);
//        connection.close();
//    }
//    public static Logins getLogins(String phone) throws SQLException{
//        Connection connection = SQLmanagement.connectionSQLSever();
//        Statement statement = connection.createStatement();
//        String sql = "select * from Logins where ID = '" + phone + "'";
//        Logins logins = new Logins();
//        ResultSet rs = statement.executeQuery(sql);
//        while (rs.next()){
//            logins = new Logins(rs.getString("ID"),rs.getString("Passwords"));
//        }
//        connection.close();
//        return logins;
//    }



    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPasswords() {
        return Passwords;
    }

    public void setPasswords(String passwords) {
        Passwords = passwords;
    }
}
