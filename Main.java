// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


class MysqlConExample {
    public static Connection con(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/school_manager", "root", "password");
        }
        catch(Exception e){ System.out.println(e);
            return null;
        }

    }
    public static void inserter(ArrayList<String[]> wee, ConnectFourFrame frame, String tableName) throws SQLException {
        Connection con = con();
        assert con != null;
        Statement state = con.createStatement();
        String tableID = tableName + "_id";
        String sql =  ("SELECT * FROM " + tableName +" ORDER BY "  + tableID);
        ResultSet rs = state.executeQuery(sql);
        Scanner b = new Scanner(System.in);
        System.out.println("Enter the first name of the student: ");
        String fName = b.nextLine();
        System.out.println("Enter the last name of the student: ");
        String lName = b.nextLine();
        boolean x = state.execute("INSERT INTO " + tableName + " (first_name, last_name) VALUES (\'" + fName + "\', \'" + lName + "\')");
        System.out.println(x);
        wee.clear();
        rs = state.executeQuery(sql);
        while(rs.next()) {
            String[] line = {rs.getString(tableID),rs.getString("first_name"), rs.getString("last_name"), "nuggets"};
            wee.add(line);
            System.out.println(line);
        }
        wee = adder(tableName);
        ConnectFourFrame.setterPee(wee);
        frame.refreshFrame();
        //   state.executeUpdate();
    }
    public static void remover(ConnectFourFrame frame, String tableName, int del) throws SQLException {
        Connection con = con();
        assert con != null;
        Statement state = con.createStatement();
        String tableID = tableName + "_id";
        String sql =  ("SELECT * FROM " + tableName +" ORDER BY " + tableID);
        ResultSet rs = state.executeQuery(sql);
        boolean y = state.execute("DELETE FROM "+ tableName + " WHERE id=" + del);
        frame.refreshFrame();
    }
    public static void modifier(ConnectFourFrame frame, String tableName, String newV, String column, int ID) throws SQLException {
        Connection con = con();
        assert con != null;
        Statement state = con.createStatement();
        String tableID = tableName + "_id";
        String sql =  ("UPDATE " + tableName +" SET COLUMN " + column + " = " + newV + " WHERE " + tableID + " = " + ID);
    }
    public static void main(String[] args){
        try{
            ArrayList<String[]> wee = new ArrayList<>();

            Scanner b = new Scanner(System.in);

            Connection con = con();
            assert con != null;
            Statement state = con.createStatement();
            String table1 = "CREATE TABLE IF NOT EXISTS teacher(\n" +
                    "teacher_id INTEGER NOT NULL AUTO_INCREMENT,\n" +
                    "first_name TEXT NOT NULL,\n" +
                    "last_name TEXT NOT NULL,\n" +
                    "PRIMARY KEY(teacher_id)\n" +
                    ");\n";
            String table2 = "CREATE TABLE IF NOT EXISTS course(\n" +
                    "course_id INTEGER NOT NULL AUTO_INCREMENT,\n" +
                    "title TEXT NOT NULL,\n" +
                    "type INTEGER NOT NULL,\n" +
                    "PRIMARY KEY(course_id)\n" +
                    ");\n";
            String table3 = "CREATE TABLE IF NOT EXISTS section(\n" +
                    "section_id INTEGER NOT NULL AUTO_INCREMENT,\n" +
                    "course_id INTEGER NOT NULL,\n" +
                    "teacher_id INTEGER NOT NULL,\n" +
                    "PRIMARY KEY(section_id)\n" +
                    ");\n";
            String table4 = "CREATE TABLE IF NOT EXISTS student(\n" +
                    "student_id INTEGER NOT NULL AUTO_INCREMENT,\n" +
                    "first_name TEXT NOT NULL,\n" +
                    "last_name TEXT NOT NULL,\n" +
                    "PRIMARY KEY(student_id)\n" +
                    ");\n";
            String table5 = "CREATE TABLE IF NOT EXISTS enrollment(\n" +
                    "student_id INTEGER,\n" +
                    "section_id INTEGER,\n" +
                    "PRIMARY KEY (student_id, section_id),\n" +
                    "FOREIGN KEY (student_id) REFERENCES student(student_id),\n" +
                    "FOREIGN KEY (section_id) REFERENCES section(section_id)\n" +
                    ");\n";
            state.execute(table1);
            state.execute(table2);
            state.execute(table3);
            state.execute(table4);
            state.execute(table5);
//            CSVWriter writer = new CSVWriter(new FileWriter("C:\\Users\\k1214514\\Desktop\\SQL\\output.csv"));
            System.out.println("OOO1");
            wee = adder("teacher");
            ConnectFourFrame.setterPee(wee);
            ConnectFourFrame frame = new ConnectFourFrame();
//            writer.flush();
//            List<String[]> hi = readLineByLine(Path.of("C:\\Users\\k1214514\\Desktop\\SQL\\output.csv"));
            while(true) {
                String table=frame.currentFrame;
                wee = adder(table);
                ConnectFourFrame.setterPee(wee);
                frame.refreshFrame();
                System.out.println("penis sframe : " + table);
                System.out.println("Enter your choice");
                int choice = b.nextInt();
                String filler = b.nextLine();

                if(choice == 1)
                {
                    inserter(wee, frame, table);
                }
                if(choice == 2){
                    int doobie = 1;
                    remover(frame, table, doobie);
                }
                if(choice == 3){
                    state.execute("DROP TABLE IF EXISTS teacher, student, enrollment,course,section;");
                    frame.refreshFrame();
                    frame.murked();
                }
            }
//            con.close();
        }catch(Exception e){ System.out.println(e);}
    }
    public static List<String[]> readLineByLine(Path filePath) throws Exception {
        List<String[]> list = new ArrayList<>();
        try (FileReader reader = new FileReader(String.valueOf(filePath))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    list.add(line);
//                    System.out.println(Arrays.toString(line));
                }
            }
        }
        return list;
    }

    public static ArrayList<String[]> adder(String tableName) throws SQLException {
        ArrayList<String[]> wee = new ArrayList<>();
        Connection con = con();
        assert con != null;
        String tableID = tableName + "_id";
        Statement state = con.createStatement();
        String sql =  ("SELECT * FROM " + tableName +" ORDER BY " + tableID);
        ResultSet rs = state.executeQuery(sql);

        while(rs.next()) {
            Statement state2 = con.createStatement();
            String penis = rs.getString(tableID);
            String longPenis = "";
            String vLongPenis = "";
            String sql2 =  String.format("SELECT * FROM section WHERE teacher_id='%s'", penis);
            ResultSet rs2 = state2.executeQuery(sql2);
            while(rs2.next()){
                longPenis += rs2.getString("section_id");
                Statement state3 = con.createStatement();
                String sql3 =  String.format("SELECT * FROM course WHERE course_id='%s'", rs2.getString("course_id"));
                ResultSet rs3 = state3.executeQuery(sql3);
                while(rs3.next()){
                    vLongPenis += rs3.getString("title") + ", ";
                }
            }
            if(longPenis == ("")){
                longPenis = "None";
                vLongPenis = "None";
            }
            String[] line = {rs.getString(tableID),rs.getString("first_name"), rs.getString("last_name"), vLongPenis};
            wee.add(line);

        }
        for (String[] strings : wee) {
            System.out.println(Arrays.toString(strings));
        }
        return wee;
    }

}