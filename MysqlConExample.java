// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


class MysqlConExample {
    public static Connection con(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/school_manager", "root", "password");
        }
        catch(Exception e){ System.out.println(e);
            return null;
        }

    }
    public static void addSection(ArrayList<String[]> sections, int courseID, int teacherID, ConnectFourFrame frame ) throws SQLException {
        Connection con = con();
        assert con != null;
        Statement state = con.createStatement();
        state.execute("INSERT INTO section (course_id, teacher_id) VALUES (\'" + courseID + "\', \'" + teacherID + "\')");
        sections.clear();
        String sql = ("SELECT * FROM section ORDER BY section_id");
        ResultSet rs = state.executeQuery(sql);
        sections = adder("section");
        ConnectFourFrame.beginnerSetting(adder("student"), adder("teacher"), adder("course"), adder("section"));
        ConnectFourFrame.setterPee(sections);

        frame.refreshFrame();
    }

    public static void addTeacher(ArrayList<String[]> wee, String fName, String lName, ConnectFourFrame frame) throws SQLException {
        Connection con = con();
        assert con != null;
        Statement state = con.createStatement();
        String sql =  ("SELECT * FROM teacher ORDER BY teacher_id");
        ResultSet rs = state.executeQuery(sql);
        boolean x = state.execute("INSERT INTO " + "teacher" + " (first_name, last_name) VALUES (\'" + fName + "\', \'" + lName + "\')");
        wee.clear();
        rs = state.executeQuery(sql);
        while(rs.next()) {
            String[] line = {rs.getString("teacher_id"),rs.getString("first_name"), rs.getString("last_name"), "nuggets"};
            wee.add(line);
            System.out.println(line);
        }
        wee = adder("teacher");
        ConnectFourFrame.beginnerSetting(adder("student"), adder("teacher"), adder("course"), adder("section"));
        ConnectFourFrame.setterPee(wee);
        frame.refreshFrame();
    }

    public static void addStudent(ArrayList<String[]> wee, String fName, String lName, ConnectFourFrame frame) throws SQLException {
        Connection con = con();
        assert con != null;
        Statement state = con.createStatement();
        String sql =  ("SELECT * FROM student ORDER BY student_id");
        ResultSet rs = state.executeQuery(sql);
        boolean x = state.execute("INSERT INTO " + "student" + " (first_name, last_name) VALUES (\'" + fName + "\', \'" + lName + "\')");
        wee.clear();
        rs = state.executeQuery(sql);
        while(rs.next()) {
            String[] line = {rs.getString("student_id"),rs.getString("first_name"), rs.getString("last_name"), "nuggets"};
            wee.add(line);
            System.out.println(line);
        }
        wee = adder("student");
        ConnectFourFrame.beginnerSetting(adder("student"), adder("teacher"), adder("course"), adder("section"));
        ConnectFourFrame.setterPee(wee);
        frame.refreshFrame();
    }
    public static String[] teacherList(){
        String [] teachers = new String[0];
        return teachers;
    }
    public static void addCourse(ArrayList<String[]> wee, String courseName, int type, ConnectFourFrame frame) throws SQLException {
        Connection con = con();
        assert con != null;
        Statement state = con.createStatement();
        String sql =  ("SELECT * FROM course ORDER BY course_id");
        ResultSet rs = state.executeQuery(sql);
        boolean x = state.execute("INSERT INTO " + "course" + " (title, type) VALUES (\'" + courseName + "\', \'" + type + "\')");
        wee.clear();
        rs = state.executeQuery(sql);
        while(rs.next()) {
            String[] line = {rs.getString("course_id"),rs.getString("title"), rs.getString("type"), "nuggets"};
            wee.add(line);
            System.out.println(line);
        }
        wee = adder("course");
        ConnectFourFrame.beginnerSetting(adder("student"), adder("teacher"), adder("course"), adder("section"));
        ConnectFourFrame.setterPee(wee);
        frame.refreshFrame();
    }

    public static void remover( ConnectFourFrame frame, String tableName, int del) throws SQLException {
        Connection con = con();
        assert con != null;
        ArrayList<String[]> wee =  new ArrayList<>();
        Statement state = con.createStatement();
        String tableID = tableName + "_id";

        boolean y = state.execute("DELETE FROM "+ tableName + " WHERE " + tableID + "=" + del);

        if(tableName.equals("course"))
        {
            boolean x = state.execute("DELETE FROM "+ "section" + " WHERE " + "section_id" + "=" + del);
        }
        wee = adder(tableName);
        ConnectFourFrame.beginnerSetting(adder("student"), adder("teacher"), adder("course"), adder("section"));
        ConnectFourFrame.setterPee(wee);
        frame.refreshFrame();

    }
    public static void studentRemover(int sectionID, int studentID) throws SQLException {
        Connection con = con();
        assert con != null;
        Statement state = con.createStatement();
        boolean y = state.execute("DELETE FROM "+ "enrollment" + " WHERE " + "student_id" + "=" + studentID + "AND section_id = " + sectionID);
    }

    public static void fileRead() throws IOException, SQLException{
        Connection con = con();
        assert con != null;
        Scanner sc = new Scanner(new File("C:\\Users\\k1214514\\Desktop\\school-main\\output.csv"));
        String table = "";
        table = sc.nextLine();
        /*        System.out.println(table);*/
        while (sc.hasNextLine())
        {
            String temp = sc.nextLine();
            System.out.println(temp);
            String[] temp2 = temp.split(",");
            if(!temp2[0].equals("\"student\"") && !temp2[0].equals("\"teacher\"") && !temp2[0].equals("\"enrollment\"") && !temp2[0].equals("\"section\"") && !temp2[0].equals("\"course\"")){
                temp2[0] = temp2[0].substring(1, temp2[0].length() - 1);
                temp2[1] = temp2[1].substring(1, temp2[1].length() - 1);
                Statement state = con.createStatement();
                if(table.equals("\"student\"")){
                    state.execute("INSERT INTO "+ "student" + "(first_name, last_name) VALUES (\'" + temp2[0] + "\', \'" + temp2[1] + "\')");
                }
                else if( table.equals("\"teacher\"")){
                    state.execute("INSERT INTO "+ "teacher" + "(first_name, last_name) VALUES (\'" + temp2[0] + "\', \'" + temp2[1] + "\')");
                }
                else if(table.equals("\"section\"")){
                    state.execute("INSERT INTO "+ "section" + "(course_id, teacher_id) VALUES (\'" + temp2[0] + "\', \'" + temp2[1] + "\')");
                }
                else if(table.equals("\"course\"")){
                    state.execute("INSERT INTO "+ "course" + "(title, type) VALUES (\'" + temp2[0] + "\', \'" + temp2[1] + "\')");
                }
                else if(table.equals("\"enrollment\"")){
                    state.execute("INSERT INTO "+ "enrollment" + "(student_id, section_id) VALUES (\'" + temp2[0] + "\', \'" + temp2[1] + "\')");
                }
            }
            else{
                table = temp2[0];
                System.out.println("New category: " + temp2[0]);
            }
        }
        sc.close();
    }

    public static ArrayList<String[]> returnTheValues(String tableName) throws IOException, SQLException{
        Connection con = con();
        assert con != null;
        ArrayList<String[]> wee = new ArrayList<String[]>();
        Statement state = con.createStatement();
        String sql =  ("SELECT * FROM "+ tableName);
        ResultSet rs = state.executeQuery(sql);
        while(rs.next()){
            String[] value = new String[2];
            if(tableName.equals("student") || tableName.equals("teacher")){
                value[0] = rs.getString("first_name");
                value[1] = rs.getString("last_name");
            }
            if(tableName.equals("enrollment")){
                value[0] = rs.getString("student_id");
                value[1] = rs.getString("section_id");
            }
            if(tableName.equals("section")){
                value[0] = rs.getString("course_id");
                value[1] = rs.getString("teacher_id");
            }
            if(tableName.equals("course")){
                value[0] =  rs.getString("title");
                value[1] = rs.getString("type");
            }
            wee.add(value);
        }
        return wee;
    }
    public static void fileDownload() throws IOException, SQLException {
        CSVWriter writer = new CSVWriter(new FileWriter("C:\\Users\\k1214514\\Desktop\\school-main\\output.csv"));
        ArrayList<String[]> students = returnTheValues("student");
        writer.writeNext(new String[] {"student"});
        for(String[] student: students){
            writer.writeNext(student);
        }
        writer.writeNext(new String[] {"teacher"});
        ArrayList<String[]> teachers = returnTheValues("teacher");
        for(String[] teacher : teachers){
            writer.writeNext(teacher);
        }
        writer.writeNext(new String[] {"course"});
        ArrayList<String[]> courses = returnTheValues("course");
        for(String[] course : courses){
            writer.writeNext(course);
        }
        writer.writeNext(new String[] {"section"});
        ArrayList<String[]> sections = returnTheValues("section");
        for(String[] section : sections){
            writer.writeNext(section);
        }
        writer.writeNext(new String[] {"enrollment"});
        ArrayList<String[]> enrollment = returnTheValues("enrollment");
        for(String[] schedules : enrollment){
            writer.writeNext(schedules);
        }
        ConnectFourFrame.beginnerSetting(adder("student"), adder("teacher"), adder("course"), adder("section"));
        writer.close();
    }

    public static void modifier(ConnectFourFrame frame, String tableName, String newV, String column, int ID) throws SQLException {
        Connection con = con();
        assert con != null;
        Statement state = con.createStatement();
        String tableID = tableName + "_id";
        String sql =  ("UPDATE " + tableName +" SET COLUMN " + column + " = " + newV + " WHERE " + tableID + " = " + ID);
    }
    public static void studentSchedule(int studentID, int sectionID, ConnectFourFrame frame) throws SQLException {
        Connection con = con();
        assert con != null;
        Statement state = con.createStatement();
        state.executeQuery("INSERT INTO enrollment (student_id, section_id) VALUES (\'" + studentID + "\', \'" + sectionID + "\')" );
        ArrayList<String[]> sections = adder("section");
        ConnectFourFrame.setterPee(sections);
        frame.refreshFrame();
    }
    public static void enroll(String sectionID, String studentID) throws SQLException {
        Connection con = con();
        assert con != null;
        Statement state = con.createStatement();
        Statement state2 = con.createStatement();
        String sql =  ("SELECT * FROM student WHERE student_id=" + studentID);
        ResultSet rs = state.executeQuery(sql);
        while(rs.next()){
            System.out.println("CHAD");
            state2.execute("INSERT INTO " + "enrollment" + " (student_id, section_id) VALUES (\'" + studentID + "\', \'" + sectionID + "\')");
        }
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
                    "PRIMARY KEY(section_id),\n" +
                    "FOREIGN KEY (course_id) REFERENCES course(course_id) ON DELETE CASCADE,\n" +
                    "FOREIGN KEY (teacher_id) REFERENCES teacher(teacher_id) ON DELETE CASCADE\n" +
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
                    "FOREIGN KEY (student_id) REFERENCES student(student_id) ON DELETE CASCADE,\n" +
                    "FOREIGN KEY (section_id) REFERENCES section(section_id) ON DELETE CASCADE\n" +
                    ");\n";
            state.execute(table1);
            state.execute(table2);
            state.execute(table3);
            state.execute(table4);
            state.execute(table5);
            /*fileRead();*/
/*            fileDownload();*/
            wee = adder("teacher");
            ConnectFourFrame.setterPee(wee);
            ConnectFourFrame.beginnerSetting(adder("student"), adder("teacher"), adder("course"), adder("section"));
//            fileRead();
            ConnectFourFrame frame = new ConnectFourFrame();

            while(true) {
                /*                fileDownload();*/
                String table=frame.currentFrame;
                wee = adder(table);
                ConnectFourFrame.setterPee(wee);
                frame.refreshFrame();
                System.out.println("frame : " + table);
                System.out.println("Enter your choice");
                int choice = b.nextInt();
                String filler = b.nextLine();
                table=frame.currentFrame;
                wee = adder(table);
                ConnectFourFrame.setterPee(wee);
                frame.refreshFrame();

                if(choice == 1)
                {
//                    inserter(wee, frame, table);
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
    public static void murkLol() throws SQLException {
        Connection con = con();
        assert con != null;
        Statement state = con.createStatement();
        state.execute("DROP TABLE IF EXISTS teacher, student, enrollment,course,section;");

    }

    public static ArrayList<String[]> getStudents(int sectionID) throws SQLException{
        ArrayList<String[]> wee = new ArrayList<>();
        Connection con = con();
        assert con != null;
        Statement state = con.createStatement();
        String sql =  ("SELECT * FROM enrollment WHERE section_id = " + sectionID);
        ResultSet studentList = state.executeQuery(sql);
        while(studentList.next()){
            String[] studentString = new String[3];
            String studentID = studentList.getString("student_id");
            Statement getStudents = con.createStatement();
            String studentEnrollment = ("SELECT * FROM student WHERE student_id=" + studentID);
            ResultSet studentInfo = getStudents.executeQuery(studentEnrollment);
            while(studentInfo.next()){
                studentString[0] = studentID;
                studentString[1] = studentInfo.getString("first_name");
                studentString[2] = studentInfo.getString("last_name");
            }
            wee.add(studentString);
        }
        return wee;
    }

    public static ArrayList<String[]> adder(String tableName) throws SQLException {
        ArrayList<String[]> wee = new ArrayList<>();
        Connection con = con();
        assert con != null;
        String tableID = tableName + "_id";
        Statement state = con.createStatement();
        if(tableName.equals("enrollment")){
            tableID = "student_id";
        }
        String sql =  ("SELECT * FROM " + tableName +" ORDER BY " + tableID);
        ResultSet rs = state.executeQuery(sql);
        while(rs.next()) {
            if(tableName.equals("teacher")){
                String teacherID = rs.getString(tableID);
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Statement teacherState = con.createStatement();
                String sqlTeacher = ("SELECT * FROM section WHERE teacher_id=" + teacherID);
                ResultSet sectionsTaught = teacherState.executeQuery(sqlTeacher);
                String sectionTaught = "";
                String courseName = "";
                while(sectionsTaught.next()){
                    sectionTaught += sectionsTaught.getString("section_id");
                    Statement courseState = con.createStatement();
                    String getCourse = ("SELECT * FROM course WHERE course_id=" + sectionsTaught.getString("course_id"));
                    ResultSet courses = courseState.executeQuery(getCourse);
                    while(courses.next()){
                        courseName += courses.getString("title");
                    }
                }
                String[] line = {teacherID,firstName, lastName, sectionTaught, courseName};
                wee.add(line);
                System.out.println(Arrays.toString(line));
            }
            else if(tableName.equals("course"))
            {
                String[] line = {rs.getString(tableID),rs.getString("title"), rs.getString("type"), };
                wee.add(line);
                System.out.println(Arrays.toString(line));
            }
            else if(tableName.equals("section"))
            {
                String sectionID = rs.getString("section_id");
                String courseID = rs.getString("course_id");
                String teacherID = rs.getString("teacher_id");
                String courseName = "";
                Statement courseState = con.createStatement();
                String getCourse = ("SELECT * FROM course WHERE course_id=" + courseID);
                ResultSet courses = courseState.executeQuery(getCourse);
                while(courses.next()){
                    courseName += courses.getString("title");
                }
                Statement teacherState = con.createStatement();
                String sqlTeacher = ("SELECT * FROM teacher WHERE teacher_id=" + teacherID);
                ResultSet teacherInfo = teacherState.executeQuery(sqlTeacher);
                String teacherString = "";
                while(teacherInfo.next()){
                    teacherString += teacherID;
                    teacherString += " - " + teacherInfo.getString("first_name");
                    teacherString += " - " + teacherInfo.getString("last_name");
                }
                Statement sectionEnrollment = con.createStatement();
                String sqlEnrollment = ("SELECT * FROM enrollment WHERE section_id=" + sectionID);
                ResultSet studentList = sectionEnrollment.executeQuery(sqlEnrollment);
                String studentString = "";
                while(studentList.next()){
                    String studentID = studentList.getString("student_id");
                    Statement getStudents = con.createStatement();
                    String studentEnrollment = ("SELECT * FROM student WHERE student_id=" + studentID);
                    ResultSet studentInfo = getStudents.executeQuery(studentEnrollment);
                    while(studentInfo.next()){
                        studentString += studentID;
                        studentString += " - " + studentInfo.getString("first_name");
                        studentString += " - " + studentInfo.getString("last_name");
                    }
                }
                String[] line = {sectionID,  courseName, teacherString, studentString};
                wee.add(line);
                //System.out.println(Arrays.toString(line));
            }
            else if(tableName.equals("student")){
                String studentID = rs.getString(tableID);
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Statement sectionEnrollment = con.createStatement();
                String sqlEnrollment = ("SELECT * FROM enrollment WHERE student_id=" + studentID);
                ResultSet sectionsIn = sectionEnrollment.executeQuery(sqlEnrollment);
                String megaString = "";
                while(sectionsIn.next()){
                    String section_id = sectionsIn.getString("section_id");
                    megaString += section_id;
                    Statement sectionState = con.createStatement();
                    String getSectionInfo = ("SELECT * FROM section WHERE section_id=" + section_id);
                    ResultSet sections = sectionState.executeQuery(getSectionInfo);
                    String teacher_id = "";
                    String course_id = "";
                    while(sections.next()){
                        teacher_id = sections.getString("teacher_id");
                        course_id = sections.getString("course_id");
                    }
                    Statement teacherState = con.createStatement();
                    String sqlTeacher = ("SELECT * FROM teacher WHERE teacher_id=" + teacher_id);
                    ResultSet teacherInfo = teacherState.executeQuery(sqlTeacher);
                    String teacherString = "";
                    while(teacherInfo.next()){
                        teacherString += teacher_id;
                        teacherString += " - " + teacherInfo.getString("first_name");
                        teacherString += " - " + teacherInfo.getString("last_name");
                        teacherString+="%";
                    }
                    Statement courseState = con.createStatement();
                    String courseName = "";
                    String getCourse = ("SELECT * FROM course WHERE course_id=" + course_id);
                    ResultSet courses = courseState.executeQuery(getCourse);
                    while(courses.next()){
                        courseName += courses.getString("title");
                    }
                    megaString += " - " + courseName + " - " + teacherString;
                }
                String[] line = {studentID,firstName, lastName, megaString};
                wee.add(line);
                System.out.println(Arrays.toString(line));
            }
        }


        return wee;
    }

}
