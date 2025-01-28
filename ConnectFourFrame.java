import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ConnectFourFrame extends JFrame implements ActionListener, WindowListener, MouseListener {

    //make new arrayLists starting tomorrow
    private String text = "";
    JFrame frame = new JFrame("School Manager");

    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu viewMenu;
    JMenu helpMenu;

    JMenuItem exportData, importData, purge, exitPeepee, teacherView, studentView, courseView, sectionView, about;

    JTable teacherTable, studentTable, courseTable, sectionTable;

    JButton teacherAdd, teacherRemove, studentAdd, studentRemove, courseAdd, courseRemove, sectionAdd, sectionRemove, rosterDisplay, scheduleDisplay;
    JRadioButton Academic, KAP, AP;
    JTextArea textArea;
    JPanel teacherPanel, studentPanel, coursePanel, helpPanel, sectionPanel;

    JTextField teacherAddFN, teacherAddLN, studentAddFN, studentAddLN, courseAddName, sectionAddName;
    JLabel teacherLabel, studentLabel, courseLabel, sectionLabel, helpLabel, aboutLabel;

    public String[] teacherColumnNames = {"Teacher ID", "First Name", "Last Name", "Sections Taught"};

    public String[] studentColumnNames = {"Student ID", "First Name", "Last Name", "Schedule"};

    public String[] courseColumnNames = {"Course ID", "Course Name", "Type"};

    public String[] sectionColumnNames = {"Section ID", "Course Name", "Teacher Name"};

    JComboBox courseDropDown, teacherDropDown;

    public static ArrayList<String[]> studentList;

    public static ArrayList<String[]> teacherList;

    public static ArrayList<String[]> sectionList;

    public static ArrayList<String[]> courseList;

    public String currentFrame = "teacher";
    public JScrollPane scrollPane;
    public JScrollPane studentScrollPane;
    JTextArea messageBox;
    JScrollPane messagesDisplay;

    JList<String> messagelist;

    JButton send, exit;

    JScrollPane userDisplay;
    JList<String> userlist;
    private String player;


    private boolean gameOver = false;
    public static ArrayList<String[]> wenis;
    public boolean redAlreadyRightClicked = false;
    public boolean blackAlreadyRightClicked = false;


    Font myFont = new Font("Ink Free", Font.BOLD, 18);

    ArrayList<String> finalMessages = new ArrayList<>();


    public ConnectFourFrame() {
        super("School Manager");

        scrollPane = new JScrollPane();
        frame.setLayout(null);
        frame.addMouseListener(this);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.addWindowListener(this);

        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        exportData = new JMenuItem("Export Data");
        exportData.addActionListener(this);
        fileMenu.add(exportData);
        importData = new JMenuItem("Import Data");
        importData.addActionListener(this);
        fileMenu.add(importData);
        purge = new JMenuItem("Purge");
        purge.addActionListener(this);
        fileMenu.add(purge);
        exitPeepee = new JMenuItem("Exit");
        exitPeepee.addActionListener(this);
        fileMenu.add(exitPeepee);
        fileMenu.setBounds(50, 50, 150, 30);
        fileMenu.setFont(myFont);

        viewMenu = new JMenu("View");
        teacherView = new JMenuItem("Teacher");
        teacherView.addActionListener(this);
        viewMenu.add(teacherView);
        studentView = new JMenuItem("Student");
        studentView.addActionListener(this);
        viewMenu.add(studentView);
        courseView = new JMenuItem("Course");
        courseView.addActionListener(this);
        viewMenu.add(courseView);
        sectionView = new JMenuItem("Section");
        sectionView.addActionListener(this);
        viewMenu.add(sectionView);
        viewMenu.setFont(myFont);

        helpMenu = new JMenu("Help");
        about = new JMenuItem("About");
        about.addActionListener(this);
        helpMenu.add(about);
        helpMenu.setFont(myFont);

        teacherPanel = new JPanel();
        teacherPanel.setLayout(null);
        teacherPanel.setSize(750, 530);

        teacherLabel = new JLabel("Teacher:");
        teacherLabel.setFont(myFont);
        teacherLabel.setBounds(50, 20, 100, 30);
        teacherPanel.add(teacherLabel);

        String[][] arr = new String[wenis.size()][];
        for(int i =0; i < wenis.size(); i++){
            arr[i] = wenis.get(i);
        }
        /*System.out.println(wenis.get(0)[0]);
        System.out.println(arr[0][0]);*/
        TableModel tableModel = new DefaultTableModel(arr, teacherColumnNames);
        teacherTable = new JTable(tableModel)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1 || column==2 ? true : false;
            }
        };
        teacherTable.setFont(myFont);
        teacherTable.setBounds(50, 50, 625, 350);
        teacherPanel.add(teacherTable);

        teacherAdd = new JButton("Add");
        teacherAdd.setFont(myFont);
        teacherAdd.setBounds(50, 450, 100, 50);
        teacherAdd.addActionListener(this);
        teacherPanel.add(teacherAdd);

        teacherRemove = new JButton("Remove");
        teacherRemove.setFont(myFont);
        teacherRemove.setBounds(575, 450, 100, 50);
        teacherRemove.addActionListener(this);
        teacherPanel.add(teacherRemove);

//        teacherAddID = new JTextField();
//        teacherAddID.setFont(myFont);
//        teacherAddID.setEditable(true);
//        teacherAddID.setBounds(250, 410, 200, 20);
//        teacherPanel.add(teacherAddID);

        teacherAddFN = new JTextField();
        teacherAddFN.setFont(myFont);
        teacherAddFN.setEditable(true);
        teacherAddFN.setBounds(250, 440, 200, 20);
        teacherPanel.add(teacherAddFN);

        teacherAddLN = new JTextField();
        teacherAddLN.setFont(myFont);
        teacherAddLN.setEditable(true);
        teacherAddLN.setBounds(250, 470, 200, 20);
        teacherPanel.add(teacherAddLN);

//        teacherAddCourse = new JTextField();
//        teacherAddCourse.setFont(myFont);
//        teacherAddCourse.setEditable(true);
//        teacherAddCourse.setBounds(250, 500, 200, 20);
//        teacherPanel.add(teacherAddCourse);






        studentPanel = new JPanel();
        studentPanel.setLayout(null);
        studentPanel.setSize(750, 530);

        studentLabel = new JLabel("Student:");
        studentLabel.setFont(myFont);
        studentLabel.setBounds(50, 20, 100, 30);
        studentPanel.add(studentLabel);

//        String[][] studentArr = new String[wenis.size()][];
//        for(int i =0; i < wenis.size(); i++){
//            arr[i] = wenis.get(i);
//        }
        /*System.out.println(wenis.get(0)[0]);
        System.out.println(arr[0][0]);*/
        String[][] studentArr = new String[0][0];
        TableModel studentTableModel = new DefaultTableModel(studentArr, studentColumnNames);
        studentTable = new JTable(tableModel)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1 || column==2 ? true : false;
            }
        };
        studentTable.setFont(myFont);
        studentTable.setBounds(50, 50, 625, 350);
        studentPanel.add(studentTable);

        studentAdd = new JButton("Add");
        studentAdd.setFont(myFont);
        studentAdd.setBounds(50, 450, 100, 50);
        studentAdd.addActionListener(this);
        studentPanel.add(studentAdd);

        studentRemove = new JButton("Remove");
        studentRemove.setFont(myFont);
        studentRemove.setBounds(575, 450, 100, 50);
        studentRemove.addActionListener(this);
        studentPanel.add(studentRemove);

        scheduleDisplay = new JButton("Display Schedule");
        scheduleDisplay.setFont(myFont);
        scheduleDisplay.setBounds(450, 450, 100, 50);
        scheduleDisplay.addActionListener(this);
        studentPanel.add(scheduleDisplay);
//
//        teacherAddID = new JTextField();
//        teacherAddID.setFont(myFont);
//        teacherAddID.setEditable(true);
//        teacherAddID.setBounds(250, 410, 200, 20);
//        teacherPanel.add(teacherAddID);

        studentAddFN = new JTextField();
        studentAddFN.setFont(myFont);
        studentAddFN.setEditable(true);
        studentAddFN.setBounds(250, 440, 200, 20);
        studentPanel.add(studentAddFN);

        studentAddLN = new JTextField();
        studentAddLN.setFont(myFont);
        studentAddLN.setEditable(true);
        studentAddLN.setBounds(250, 470, 200, 20);
        studentPanel.add(studentAddLN);
//
//        teacherAddCourse = new JTextField();
//        teacherAddCourse.setFont(myFont);
//        teacherAddCourse.setEditable(true);
//        teacherAddCourse.setBounds(250, 500, 200, 20);
//        teacherPanel.add(teacherAddCourse);



        coursePanel = new JPanel();
        coursePanel.setLayout(null);
        coursePanel.setSize(750, 530);

        courseLabel = new JLabel("Course:");
        courseLabel.setFont(myFont);
        courseLabel.setBounds(50, 20, 100, 30);
        coursePanel.add(courseLabel);

//        String[][] studentArr = new String[wenis.size()][];
//        for(int i =0; i < wenis.size(); i++){
//            arr[i] = wenis.get(i);
//        }
        /*System.out.println(wenis.get(0)[0]);
        System.out.println(arr[0][0]);*/
        String[][] courseArr = new String[0][0];
        TableModel courseTableModel = new DefaultTableModel(studentArr, studentColumnNames);
        courseTable = new JTable(tableModel)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1 || column==2 ? true : false;
            }
        };
        courseTable.setFont(myFont);
        courseTable.setBounds(50, 50, 625, 350);
        coursePanel.add(courseTable);

        courseAdd = new JButton("Add");
        courseAdd.setFont(myFont);
        courseAdd.setBounds(50, 450, 100, 50);
        courseAdd.addActionListener(this);
        coursePanel.add(courseAdd);

        courseRemove = new JButton("Remove");
        courseRemove.setFont(myFont);
        courseRemove.setBounds(575, 450, 100, 50);
        courseRemove.addActionListener(this);
        coursePanel.add(courseRemove);

        courseAddName = new JTextField();
        courseAddName.setFont(myFont);
        courseAddName.setEditable(true);
        courseAddName.setBounds(250, 440, 200, 20);
        coursePanel.add(courseAddName);

        Academic = new JRadioButton("Academic");
        Academic.addActionListener(this);
        Academic.setBounds(250, 460, 100, 20);
        Academic.setFont(myFont);
        coursePanel.add(Academic);

        KAP = new JRadioButton("KAP");
        KAP.addActionListener(this);
        KAP.setBounds(250, 480, 100, 20);
        KAP.setFont(myFont);
        coursePanel.add(KAP);

        AP = new JRadioButton("AP");
        AP.addActionListener(this);
        AP.setBounds(250, 500, 100, 20);
        AP.setFont(myFont);
        coursePanel.add(AP);

//        studentAddLN = new JTextField();
//        studentAddLN.setFont(myFont);
//        studentAddLN.setEditable(true);
//        studentAddLN.setBounds(250, 470, 200, 20);
//        coursePanel.add(studentAddLN);

        sectionPanel = new JPanel();
        sectionPanel.setLayout(null);
        sectionPanel.setSize(750, 530);

        sectionLabel = new JLabel("Section:");
        sectionLabel.setFont(myFont);
        sectionLabel.setBounds(50, 20, 100, 30);
        sectionPanel.add(sectionLabel);

//        String[][] studentArr = new String[wenis.size()][];
//        for(int i =0; i < wenis.size(); i++){
//            arr[i] = wenis.get(i);
//        }
        /*System.out.println(wenis.get(0)[0]);
        System.out.println(arr[0][0]);*/
        String[][] sectionArr = new String[0][0];
        TableModel sectionTableModel = new DefaultTableModel(sectionArr, sectionColumnNames);
        sectionTable = new JTable(sectionTableModel)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1 || column==2 ? true : false;
            }
        };


        sectionAdd = new JButton("Add");
        sectionAdd.setFont(myFont);
        sectionAdd.setBounds(50, 450, 100, 50);
        sectionAdd.addActionListener(this);
        sectionPanel.add(sectionAdd);

        sectionRemove = new JButton("Remove");
        sectionRemove.setFont(myFont);
        sectionRemove.setBounds(575, 450, 100, 50);
        sectionRemove.addActionListener(this);
        sectionPanel.add(sectionRemove);

        rosterDisplay = new JButton("Display Roster");
        rosterDisplay.setFont(myFont);
        rosterDisplay.setBounds(550, 10, 175, 30);
        rosterDisplay.addActionListener(this);
        sectionPanel.add(rosterDisplay);

        String [] courses = new String[courseList.size()];
        for(int i = 0; i < courseList.size(); i++){
            courses[i] = courseList.get(i)[1];
        }
        courseDropDown = new JComboBox(courses);
        courseDropDown.addActionListener(this);
        courseDropDown.setFont(myFont);
        courseDropDown.setBounds(250, 440, 200, 20);
/*        courseDropDown.setSelectedIndex(0);*/
        sectionPanel.add(courseDropDown);
        String [] teachers = new String[teacherList.size()];
        for(int i = 0; i < teacherList.size(); i++){
            System.out.println(teacherList.get(i)[1] + " " + teacherList.get(i)[2]);
            teachers[i] = teacherList.get(i)[1] + " " + teacherList.get(i)[2];
        }
        teacherDropDown = new JComboBox(teachers);
        teacherDropDown.addActionListener(this);
        teacherDropDown.setFont(myFont);
        teacherDropDown.setBounds(250, 470, 200, 20);
/*        teacherDropDown.setSelectedIndex(0);*/
        sectionPanel.add(teacherDropDown);
        //coursePanel.remove(sectionTable);

        helpPanel = new JPanel();
        helpPanel.setLayout(null);
        helpPanel.setSize(750, 530);

        helpLabel = new JLabel("About:");
        helpLabel.setFont(myFont);
        helpLabel.setBounds(50, 20, 100, 30);
        helpPanel.add(helpLabel);

        aboutLabel = new JLabel("Jason and Daniel's School Manager project");
        aboutLabel.setFont(myFont);
        aboutLabel.setBounds(200, 250, 400, 50);
        helpPanel.add(aboutLabel);


        frame.setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);
        frame.add(teacherPanel);

        frame.setSize(750, 600);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }
    public static void setterPee(ArrayList<String[]> x){
        wenis = x;
    }

    public static void beginnerSetting(ArrayList<String[]> students, ArrayList<String[]> teachers, ArrayList<String[]> courses, ArrayList<String[]> sections){
        System.out.println(teachers);
        teacherList = teachers;
        studentList = students;
        courseList = courses;
        sectionList = sections;
    }

    public void murked(){
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
    public void refreshFrame()
    {
        if(Objects.equals(currentFrame, "teacher"))
        {
            teacherPanel.remove(teacherTable);
            show_User();

            SwingUtilities.updateComponentTreeUI(frame);
        }
        if(Objects.equals(currentFrame, "student"))
        {
            studentPanel.remove(studentTable);
            if(studentScrollPane != null)
            {
                studentPanel.remove(studentScrollPane);
            }

            show_User();

            SwingUtilities.updateComponentTreeUI(frame);
        }
        if(Objects.equals(currentFrame, "course"))
        {
            coursePanel.remove(courseTable);
            show_User();
            SwingUtilities.updateComponentTreeUI(frame);
        }
        if(Objects.equals(currentFrame, "section"))
        {
            String [] courses = new String[courseList.size()];
            for(int i = 0; i < courseList.size(); i++){
                courses[i] = courseList.get(i)[1];
            }
            sectionPanel.remove(courseDropDown);
            courseDropDown = new JComboBox(courses);
            courseDropDown.addActionListener(this);
            courseDropDown.setFont(myFont);
            courseDropDown.setBounds(250, 440, 200, 20);
/*            courseDropDown.setSelectedIndex(0);*/
            sectionPanel.add(courseDropDown);
            String [] teachers = new String[teacherList.size()];
            for(int i = 0; i < teacherList.size(); i++){
                System.out.println(teacherList.get(i)[1] + " " + teacherList.get(i)[2]);
                teachers[i] = teacherList.get(i)[1] + " " + teacherList.get(i)[2];
            }
            sectionPanel.remove(teacherDropDown);
            teacherDropDown = new JComboBox(teachers);
            teacherDropDown.addActionListener(this);
            teacherDropDown.setFont(myFont);
            teacherDropDown.setBounds(250, 470, 200, 20);
/*            teacherDropDown.setSelectedIndex(0);*/
            sectionPanel.add(teacherDropDown);
            sectionPanel.remove(sectionTable);
            sectionPanel.remove(scrollPane);
            show_User();
            SwingUtilities.updateComponentTreeUI(frame);
        }
//        TableModel tableModel = new DefaultTableModel(teacherColumnNames, 0);
//        JTable toDoTable = new JTable(tableModel);
//        DefaultTableModel model = (DefaultTableModel)toDoTable.getModel();
//        model.setRowCount(0);
//        teacherTable.setModel(teacherTable.getModel());


//        String[][] arr = new String[wenis.size()][];
//        for(int i =0; i < wenis.size(); i++){
//            arr[i] = wenis.get(i);
//        }
//        teacherTable = new JTable(arr, teacherColumnNames);
//        teacherPanel.add(teacherTable);
        //System.out.println(teacherTable.getValueAt(6, 1));
//        System.out.println(wenis.size());
//        //System.out.println(arr[11].toString());
    }

    private void show_User() {
        if(currentFrame.equals("teacher"))
        {
            ArrayList<String[]> list = wenis;
            DefaultTableModel model = new DefaultTableModel(teacherColumnNames, 0);

//        DefaultTableModel model = (DefaultTableModel) teacherTable.getModel();
            Object [] row = new Object[4];
            for(int i = 0; i < wenis.size(); i++)
            {
                row[0] = wenis.get(i)[0];
                row[1] = wenis.get(i)[1];
                row[2] = wenis.get(i)[2];
                row[3] = wenis.get(i)[3];
                model.addRow(row);
            }
            model.fireTableDataChanged();
            //teacherTable = new JTable(model);
            //teacherPanel.add(teacherTable);

            //TableModel tableModel = new DefaultTableModel(arr, teacherColumnNames);
            teacherTable = new JTable(model)
            {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 1 || column==2 ? true : false;
                }
            };
            teacherTable.setFont(myFont);
            teacherTable.setBounds(50, 50, 625, 350);
            teacherPanel.add(teacherTable);
        }
        if(currentFrame.equals("student"))
        {
            if(studentScrollPane != null)
            {
                frame.remove(studentScrollPane);
                studentPanel.remove(studentScrollPane);
            }
            ArrayList<String[]> list = wenis;
            DefaultTableModel model = new DefaultTableModel(studentColumnNames, 0);

//        DefaultTableModel model = (DefaultTableModel) teacherTable.getModel();
            Object [] row = new Object[4];
            for(int i = 0; i < wenis.size(); i++)
            {
                row[0] = wenis.get(i)[0];
                row[1] = wenis.get(i)[1];
                row[2] = wenis.get(i)[2];
                row[3] = wenis.get(i)[3];
                model.addRow(row);
            }
            model.fireTableDataChanged();
            //teacherTable = new JTable(model);
            //System.out.println(teacherTable.getRowCount());
            //teacherPanel.add(teacherTable);

            //TableModel tableModel = new DefaultTableModel(arr, teacherColumnNames);
            studentTable = new JTable(model)
            {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 1 || column==2 ? true : false;
                }
            };
            studentScrollPane = new JScrollPane(studentTable);
            studentTable.setFont(myFont);
            studentScrollPane.setBounds(50, 50, 625, 350);
            studentPanel.add(studentScrollPane);
            studentTable.setFont(myFont);
            studentPanel.add(studentScrollPane);
            frame.add(studentPanel);
        }
        if(currentFrame.equals("course"))
        {
            ArrayList<String[]> list = wenis;
            DefaultTableModel model = new DefaultTableModel(courseColumnNames, 0);

//        DefaultTableModel model = (DefaultTableModel) teacherTable.getModel();
            Object [] row = new Object[3];
            for(int i = 0; i < wenis.size(); i++)
            {
                row[0] = wenis.get(i)[0];
                row[1] = wenis.get(i)[1];
                row[2] = wenis.get(i)[2];
                //row[3] = wenis.get(i)[3];
                model.addRow(row);
            }
            model.fireTableDataChanged();
            //teacherTable = new JTable(model);
            //System.out.println(teacherTable.getRowCount());
            //teacherPanel.add(teacherTable);

            //TableModel tableModel = new DefaultTableModel(arr, teacherColumnNames);
            courseTable = new JTable(model)
            {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 1 || column==2 ? true : false;
                }
            };
            courseTable.setFont(myFont);
            courseTable.setBounds(50, 50, 625, 350);
            coursePanel.add(courseTable);
        }
        if(currentFrame.equals("section"))
        {
            //ArrayList<String[]> list = wenis;
            //setterPee(wenis);
            sectionPanel.remove(sectionTable);
            sectionPanel.remove(scrollPane);
            currentFrame = "section";
            ArrayList<String[]> wee = new ArrayList<String[]>();
            try {
                wee = MysqlConExample.adder(currentFrame);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            ConnectFourFrame.setterPee(wee);
            DefaultTableModel model = new DefaultTableModel(sectionColumnNames, 0);

//        DefaultTableModel model = (DefaultTableModel) teacherTable.getModel();
            Object [] row = new Object[3];
            for(int i = 0; i < wenis.size(); i++)
            {
                row[0] = wenis.get(i)[0];
                row[1] = wenis.get(i)[1];
                row[2] = wenis.get(i)[2];
                //row[3] = wenis.get(i)[3];
                model.addRow(row);
                System.out.println(Arrays.toString(row));
            }
            model.fireTableDataChanged();

            //teacherTable = new JTable(model);
            //System.out.println(teacherTable.getRowCount());
            //teacherPanel.add(teacherTable);

            //TableModel tableModel = new DefaultTableModel(arr, teacherColumnNames);

            sectionTable = new JTable(model)
            {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 1 || column==2 ? true : false;
                }
            };

            JScrollPane scrollPane = new JScrollPane(sectionTable);
            sectionTable.setFont(myFont);
            scrollPane.setBounds(50, 50, 625, 350);
            sectionPanel.add(scrollPane);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void windowOpened(WindowEvent e) { }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {



    }

    @Override
    public void windowIconified(WindowEvent e) { }

    @Override
    public void windowDeiconified(WindowEvent e) { }

    @Override
    public void windowActivated(WindowEvent e) { }

    @Override
    public void windowDeactivated(WindowEvent e) { }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == studentView)
        {
            frame.remove(teacherPanel);
            frame.remove(studentPanel);
            frame.remove(helpPanel);
            frame.remove(coursePanel);
            frame.remove(sectionPanel);

            SwingUtilities.updateComponentTreeUI(frame);
            currentFrame = "student";
            ArrayList<String[]> wee = new ArrayList<String[]>();
            try {
                wee = MysqlConExample.adder(currentFrame);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            ConnectFourFrame.setterPee(wee);
            refreshFrame();
            frame.add(studentPanel);
            refreshFrame();
        }
        if(e.getSource() == teacherView)
        {
            frame.remove(teacherPanel);
            frame.remove(studentPanel);
            frame.remove(helpPanel);
            frame.remove(coursePanel);
            frame.remove(sectionPanel);
            frame.add(teacherPanel);
            SwingUtilities.updateComponentTreeUI(frame);
            currentFrame = "teacher";
            ArrayList<String[]> wee = new ArrayList<String[]>();
            try {
                wee = MysqlConExample.adder(currentFrame);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            ConnectFourFrame.setterPee(wee);
            refreshFrame();
        }
        if(e.getSource() == courseView)
        {
            frame.remove(teacherPanel);
            frame.remove(studentPanel);
            frame.remove(coursePanel);
            frame.remove(helpPanel);
            frame.remove(sectionPanel);
            frame.add(coursePanel);
            SwingUtilities.updateComponentTreeUI(frame);
            currentFrame = "course";
            ArrayList<String[]> wee = new ArrayList<String[]>();
            try {
                wee = MysqlConExample.adder(currentFrame);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            ConnectFourFrame.setterPee(wee);
            refreshFrame();
        }
        if(e.getSource() == sectionView)
        {

            frame.remove(teacherPanel);
            frame.remove(studentPanel);
            frame.remove(coursePanel);
            frame.remove(helpPanel);
            frame.remove(sectionPanel);

            sectionPanel = new JPanel();
            sectionPanel.setLayout(null);
            sectionPanel.setSize(750, 530);

            sectionLabel = new JLabel("Section:");
            sectionLabel.setFont(myFont);
            sectionLabel.setBounds(50, 20, 100, 30);
            sectionPanel.add(sectionLabel);

            sectionAdd = new JButton("Add");
            sectionAdd.setFont(myFont);
            sectionAdd.setBounds(50, 450, 100, 50);
            sectionAdd.addActionListener(this);
            sectionPanel.add(sectionAdd);

            sectionRemove = new JButton("Remove");
            sectionRemove.setFont(myFont);
            sectionRemove.setBounds(575, 450, 100, 50);
            sectionRemove.addActionListener(this);
            sectionPanel.add(sectionRemove);

            rosterDisplay = new JButton("Display Roster");
            rosterDisplay.setFont(myFont);
            rosterDisplay.setBounds(550, 10, 175, 30);
            rosterDisplay.addActionListener(this);
            sectionPanel.add(rosterDisplay);

            String [] courses = new String[courseList.size()];
            for(int i = 0; i < courseList.size(); i++){
                courses[i] = courseList.get(i)[1];
            }
            courseDropDown = new JComboBox(courses);
            courseDropDown.addActionListener(this);
            courseDropDown.setFont(myFont);
            courseDropDown.setBounds(250, 440, 200, 20);
/*            courseDropDown.setSelectedIndex(0);*/
            sectionPanel.add(courseDropDown);
            String [] teachers = new String[teacherList.size()];
            for(int i = 0; i < teacherList.size(); i++){
                System.out.println(teacherList.get(i)[1] + " " + teacherList.get(i)[2]);
                teachers[i] = teacherList.get(i)[1] + " " + teacherList.get(i)[2];
            }
            teacherDropDown = new JComboBox(teachers);
            teacherDropDown.addActionListener(this);
            teacherDropDown.setFont(myFont);
            teacherDropDown.setBounds(250, 470, 200, 20);
/*            teacherDropDown.setSelectedIndex(0);*/
            sectionPanel.add(teacherDropDown);

            SwingUtilities.updateComponentTreeUI(frame);
            currentFrame = "section";
            ArrayList<String[]> wee = new ArrayList<String[]>();
            try {
                wee = MysqlConExample.adder(currentFrame);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            ConnectFourFrame.setterPee(wee);
            System.out.println(wenis.size() + " PEEPEEPPEEPPEEEEE");
            //refreshFrame();
            //repaint();
            //sectionPanel.remove(sectionTable);
            //sectionPanel.remove(scrollPane);
            repaint();


            DefaultTableModel model = new DefaultTableModel(sectionColumnNames, 0);

//        DefaultTableModel model = (DefaultTableModel) teacherTable.getModel();
            Object [] row = new Object[3];
            for(int i = 0; i < wenis.size(); i++)
            {
                row[0] = wenis.get(i)[0];
                row[1] = wenis.get(i)[1];
                row[2] = wenis.get(i)[2];
                //row[3] = wenis.get(i)[3];
                model.addRow(row);
                //System.out.println(Arrays.toString(row));
            }
            model.fireTableDataChanged();
            //teacherTable = new JTable(model);
            //System.out.println(teacherTable.getRowCount());
            //teacherPanel.add(teacherTable);

            //TableModel tableModel = new DefaultTableModel(arr, teacherColumnNames);

            sectionTable = new JTable(model)
            {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 1 || column==2 ? true : false;
                }
            };
            //sectionPanel.remove(scrollPane);
            scrollPane = new JScrollPane(sectionTable);
            System.out.println("WEEE " + sectionTable.getRowCount());

            //repaint();
            sectionTable.setFont(myFont);
            scrollPane.setBounds(50, 50, 625, 350);
            scrollPane.setVisible(true);
            sectionPanel.add(scrollPane);
            //refreshFrame();
            frame.add(sectionPanel);
            refreshFrame();
            repaint();
        }
        if(e.getSource() == about)
        {
            frame.remove(teacherPanel);
            frame.remove(studentPanel);
            frame.remove(helpPanel);
            frame.remove(coursePanel);
            frame.remove(sectionPanel);
            frame.add(helpPanel);
            SwingUtilities.updateComponentTreeUI(frame);
            currentFrame = "about";

        }
        if(e.getSource() == teacherAdd)
        {
            if(!teacherAddFN.getText().isEmpty() && !teacherAddLN.getText().isEmpty())
            {
                //addShit to database and refresh frame
                try {
                    MysqlConExample.addTeacher(wenis, teacherAddFN.getText(), teacherAddLN.getText(), this);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                refreshFrame();
                teacherAddFN.setText("");
                teacherAddLN.setText("");
            }
        }
        if(e.getSource() == studentAdd)
        {
            if(!studentAddFN.getText().isEmpty() && !studentAddLN.getText().isEmpty())
            {
                //addShit to database and refresh frame
                try {
                    MysqlConExample.addStudent(wenis, studentAddFN.getText(), studentAddLN.getText(), this);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                refreshFrame();
                studentAddFN.setText("");
                studentAddLN.setText("");
            }
        }
        if(e.getSource() == courseAdd)
        {
            if((!courseAddName.getText().isEmpty() && Academic.isSelected() && !KAP.isSelected() && !AP.isSelected())||
                    (!courseAddName.getText().isEmpty() && !Academic.isSelected() && KAP.isSelected() && !AP.isSelected())||
                    (!courseAddName.getText().isEmpty() && !Academic.isSelected() && !KAP.isSelected() && AP.isSelected()))
            {
                //addShit to database and refresh frame
                try {
                    if(Academic.isSelected())
                    {
                        MysqlConExample.addCourse(wenis, courseAddName.getText(), 0, this);
                    }
                    if(KAP.isSelected())
                    {
                        MysqlConExample.addCourse(wenis, courseAddName.getText(), 1, this);
                    }
                    if(AP.isSelected())
                    {
                        MysqlConExample.addCourse(wenis, courseAddName.getText(), 2, this);
                    }
                    Academic.setSelected(false);
                    KAP.setSelected(false);
                    AP.setSelected(false);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                refreshFrame();
                courseAddName.setText("");
            }
        }
        if(e.getSource() == sectionRemove){
            if(sectionTable.getSelectedRow() != -1)
            {
                System.out.println("remove this row: " + sectionTable.getSelectedRow());
                try {
                    String[] tmp = sectionList.get(sectionTable.getSelectedRow());
                    MysqlConExample.remover(this, "section", Integer.parseInt(tmp[0]));

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            frame.remove(sectionPanel);

            sectionPanel = new JPanel();
            sectionPanel.setLayout(null);
            sectionPanel.setSize(750, 530);

            sectionLabel = new JLabel("Section:");
            sectionLabel.setFont(myFont);
            sectionLabel.setBounds(50, 20, 100, 30);
            sectionPanel.add(sectionLabel);

            sectionAdd = new JButton("Add");
            sectionAdd.setFont(myFont);
            sectionAdd.setBounds(50, 450, 100, 50);
            sectionAdd.addActionListener(this);
            sectionPanel.add(sectionAdd);

            sectionRemove = new JButton("Remove");
            sectionRemove.setFont(myFont);
            sectionRemove.setBounds(575, 450, 100, 50);
            sectionRemove.addActionListener(this);
            sectionPanel.add(sectionRemove);

            rosterDisplay = new JButton("Display Roster");
            rosterDisplay.setFont(myFont);
            rosterDisplay.setBounds(550, 10, 175, 30);
            rosterDisplay.addActionListener(this);
            sectionPanel.add(rosterDisplay);

            String [] courses = new String[courseList.size()];
            for(int i = 0; i < courseList.size(); i++){
                courses[i] = courseList.get(i)[1];
            }
            courseDropDown = new JComboBox(courses);
            courseDropDown.addActionListener(this);
            courseDropDown.setFont(myFont);
            courseDropDown.setBounds(250, 440, 200, 20);
/*            courseDropDown.setSelectedIndex(0);*/
            sectionPanel.add(courseDropDown);
            String [] teachers = new String[teacherList.size()];
            for(int i = 0; i < teacherList.size(); i++){
                System.out.println(teacherList.get(i)[1] + " " + teacherList.get(i)[2]);
                teachers[i] = teacherList.get(i)[1] + " " + teacherList.get(i)[2];
            }
            teacherDropDown = new JComboBox(teachers);
            teacherDropDown.addActionListener(this);
            teacherDropDown.setFont(myFont);
            teacherDropDown.setBounds(250, 470, 200, 20);
/*            teacherDropDown.setSelectedIndex(0);*/
            sectionPanel.add(teacherDropDown);

            SwingUtilities.updateComponentTreeUI(frame);
            currentFrame = "section";
            ArrayList<String[]> wee = new ArrayList<String[]>();
            try {
                wee = MysqlConExample.adder(currentFrame);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            ConnectFourFrame.setterPee(wee);
            System.out.println(wenis.size() + " PEEPEEPPEEPPEEEEE");
            //refreshFrame();
            //repaint();
            //sectionPanel.remove(sectionTable);
            //sectionPanel.remove(scrollPane);
            repaint();


            DefaultTableModel model = new DefaultTableModel(sectionColumnNames, 0);

//        DefaultTableModel model = (DefaultTableModel) teacherTable.getModel();
            Object [] row = new Object[3];
            for(int i = 0; i < wenis.size(); i++)
            {
                row[0] = wenis.get(i)[0];
                row[1] = wenis.get(i)[1];
                row[2] = wenis.get(i)[2];
                //row[3] = wenis.get(i)[3];
                model.addRow(row);
                //System.out.println(Arrays.toString(row));
            }
            model.fireTableDataChanged();
            //teacherTable = new JTable(model);
            //System.out.println(teacherTable.getRowCount());
            //teacherPanel.add(teacherTable);

            //TableModel tableModel = new DefaultTableModel(arr, teacherColumnNames);

            sectionTable = new JTable(model)
            {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 1 || column==2 ? true : false;
                }
            };
            //sectionPanel.remove(scrollPane);
            scrollPane = new JScrollPane(sectionTable);
            System.out.println("WEEE " + sectionTable.getRowCount());

            //repaint();
            sectionTable.setFont(myFont);
            scrollPane.setBounds(50, 50, 625, 350);
            scrollPane.setVisible(true);
            sectionPanel.add(scrollPane);
            //refreshFrame();
            frame.add(sectionPanel);
            refreshFrame();
            repaint();
/*            courseDropDown.setSelectedIndex(0);
            teacherDropDown.setSelectedIndex(0);*/
        }
        if(e.getSource() == sectionAdd)
        {
            System.out.println("SECTION ADD");
            int courseID = Integer.parseInt(courseList.get(courseDropDown.getSelectedIndex())[0]);
            int teacherID = Integer.parseInt(teacherList.get(teacherDropDown.getSelectedIndex())[0]);
            if(teacherDropDown.getSelectedIndex() != 0)
            {
                //addShit to database and refresh frame
                try {
                    MysqlConExample.addSection(wenis, courseID, teacherID, this);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                refreshFrame();
/*                courseDropDown.setSelectedIndex(0);
                teacherDropDown.setSelectedIndex(0);*/
            }
            frame.remove(sectionPanel);

            sectionPanel = new JPanel();
            sectionPanel.setLayout(null);
            sectionPanel.setSize(750, 530);

            sectionLabel = new JLabel("Section:");
            sectionLabel.setFont(myFont);
            sectionLabel.setBounds(50, 20, 100, 30);
            sectionPanel.add(sectionLabel);

            sectionAdd = new JButton("Add");
            sectionAdd.setFont(myFont);
            sectionAdd.setBounds(50, 450, 100, 50);
            sectionAdd.addActionListener(this);
            sectionPanel.add(sectionAdd);

            sectionRemove = new JButton("Remove");
            sectionRemove.setFont(myFont);
            sectionRemove.setBounds(575, 450, 100, 50);
            sectionRemove.addActionListener(this);
            sectionPanel.add(sectionRemove);

            rosterDisplay = new JButton("Display Roster");
            rosterDisplay.setFont(myFont);
            rosterDisplay.setBounds(550, 10, 175, 30);
            rosterDisplay.addActionListener(this);
            sectionPanel.add(rosterDisplay);

            String [] courses = new String[courseList.size()];
            for(int i = 0; i < courseList.size(); i++){
                courses[i] = courseList.get(i)[1];
            }
            courseDropDown = new JComboBox(courses);
            courseDropDown.addActionListener(this);
            courseDropDown.setFont(myFont);
            courseDropDown.setBounds(250, 440, 200, 20);
/*            courseDropDown.setSelectedIndex(0);*/
            sectionPanel.add(courseDropDown);
            String [] teachers = new String[teacherList.size()];
            for(int i = 0; i < teacherList.size(); i++){
                System.out.println(teacherList.get(i)[1] + " " + teacherList.get(i)[2]);
                teachers[i] = teacherList.get(i)[1] + " " + teacherList.get(i)[2];
            }
            teacherDropDown = new JComboBox(teachers);
            teacherDropDown.addActionListener(this);
            teacherDropDown.setFont(myFont);
            teacherDropDown.setBounds(250, 470, 200, 20);
/*            teacherDropDown.setSelectedIndex(0);*/
            sectionPanel.add(teacherDropDown);

            SwingUtilities.updateComponentTreeUI(frame);
            currentFrame = "section";
            ArrayList<String[]> wee = new ArrayList<String[]>();
            try {
                wee = MysqlConExample.adder(currentFrame);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            ConnectFourFrame.setterPee(wee);
            System.out.println(wenis.size() + " PEEPEEPPEEPPEEEEE");
            //refreshFrame();
            //repaint();
            //sectionPanel.remove(sectionTable);
            //sectionPanel.remove(scrollPane);
            repaint();


            DefaultTableModel model = new DefaultTableModel(sectionColumnNames, 0);

//        DefaultTableModel model = (DefaultTableModel) teacherTable.getModel();
            Object [] row = new Object[3];
            for(int i = 0; i < wenis.size(); i++)
            {
                row[0] = wenis.get(i)[0];
                row[1] = wenis.get(i)[1];
                row[2] = wenis.get(i)[2];
                //row[3] = wenis.get(i)[3];
                model.addRow(row);
                //System.out.println(Arrays.toString(row));
            }
            model.fireTableDataChanged();
            //teacherTable = new JTable(model);
            //System.out.println(teacherTable.getRowCount());
            //teacherPanel.add(teacherTable);

            //TableModel tableModel = new DefaultTableModel(arr, teacherColumnNames);

            sectionTable = new JTable(model)
            {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 1 || column==2 ? true : false;
                }
            };
            //sectionPanel.remove(scrollPane);
            scrollPane = new JScrollPane(sectionTable);
            System.out.println("WEEE " + sectionTable.getRowCount());

            //repaint();
            sectionTable.setFont(myFont);
            scrollPane.setBounds(50, 50, 625, 350);
            scrollPane.setVisible(true);
            sectionPanel.add(scrollPane);
            //refreshFrame();
            frame.add(sectionPanel);
            refreshFrame();
            repaint();
        }
        if(e.getSource() == courseRemove)
        {
            if(courseTable.getSelectedRow() != -1)
            {
                System.out.println("remove this row: " + courseTable.getSelectedRow());
                try {
                    String[] tmp = courseList.get(courseTable.getSelectedRow());
                    MysqlConExample.remover(this, "course", Integer.parseInt(tmp[0]));

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                refreshFrame();
            }
        }
        if(e.getSource() == teacherRemove)
        {
            if(teacherTable.getSelectedRow() != -1)
            {
                System.out.println("remove this row: " + teacherTable.getSelectedRow());
                try {
                    String[] tmp = teacherList.get(teacherTable.getSelectedRow());
                    MysqlConExample.remover(this, "teacher", Integer.parseInt(tmp[0]));

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                refreshFrame();
            }
        }
        if(e.getSource() == studentRemove)
        {
            if(studentTable.getSelectedRow() != -1)
            {
                System.out.println("remove this row: " + studentTable.getSelectedRow());
                try {
                    String[] tmp = studentList.get(studentTable.getSelectedRow());
                    MysqlConExample.remover(this, "student", Integer.parseInt(tmp[0]));

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                refreshFrame();
            }
        }
        if(e.getSource() == purge)
        {
            try {
                MysqlConExample.murkLol();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            murked();
        }
        if(e.getSource() == importData){
            try {
                MysqlConExample.fileRead();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource() == exportData){
            try {
                MysqlConExample.fileDownload();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource() == exitPeepee)
        {
            //close window
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
        if(e.getSource() == rosterDisplay)
        {
            if(sectionTable.getSelectedRow() != -1)
            {
//                String name = JOptionPane.showInputDialog(this,
//                        "What is your name?", null);
                try {
                    String[] tmp = sectionList.get(sectionTable.getSelectedRow());
                    RosterFrame rFrame  = new RosterFrame(Integer.parseInt(tmp[0]));
                    rFrame.setVisible(true);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
        if(e.getSource() == scheduleDisplay){
            try {
                String[] tmp = studentList.get(studentTable.getSelectedRow());
                for(String ret : tmp){
                    System.out.println(ret);
                }
                ScheduleFrame sFrame  = new ScheduleFrame(tmp[3]);
                sFrame.setVisible(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}