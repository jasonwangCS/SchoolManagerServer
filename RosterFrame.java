import javax.swing.*;
import javax.swing.event.ListSelectionListener;
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

public class RosterFrame extends JFrame implements ActionListener, WindowListener{
    JList<String> students;
    JButton studentAdd, studentRemove;

    JTextField StudentAddID, StudentAddLN;

    int sectionID;
    public RosterFrame(int sectionID) throws IOException, SQLException {
        this.sectionID = sectionID;
        setLayout(null);
        setSize(500, 500);
        setVisible(true);
        setAlwaysOnTop(true);

        ArrayList<String[]> rosterInput = MysqlConExample.getStudents(sectionID);
        System.out.println(rosterInput.size());
        String[] roster = new String[rosterInput.size()];
        for(int i = 0; i < rosterInput.size() ; i++)
        {
            roster[i] = rosterInput.get(i)[2] + ", " + rosterInput.get(i)[1] + " (" + rosterInput.get(i)[0] + ")";
        }
        //String [] studentsArray = {"Jason" , "Daniel" , "Kailin"};
        students = new JList<String>(roster);
        students.setBounds(50, 50, 200, 300);
        //students.addListSelectionListener((ListSelectionListener) this);
        students.setVisible(true);
        add(students);

        studentAdd = new JButton("Add");
        //studentAdd.setFont(myFont);
        studentAdd.setBounds(25, 400, 100, 50);
        studentAdd.addActionListener(this);
        add(studentAdd);

        studentRemove = new JButton("Remove");
        //studentAdd.setFont(myFont);
        studentRemove.setBounds(350, 400, 100, 50);
        studentRemove.addActionListener(this);
        add(studentRemove);

        StudentAddID = new JTextField();
        StudentAddID.setEditable(true);
        StudentAddID.setBounds(150, 400, 180, 20);
        add(StudentAddID);

//        StudentAddLN = new JTextField();
//        StudentAddLN.setEditable(true);
//        StudentAddLN.setBounds(150, 430, 180, 20);
//        add(StudentAddLN);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == studentAdd) {
            if (!StudentAddID.getText().isEmpty()) {
                try {
                    MysqlConExample.enroll(String.valueOf(sectionID), StudentAddID.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                //remove(students);
                DefaultListModel model = new DefaultListModel();
                //model.addElement( "one" );
                //model.addElement( "two" );

                ArrayList<String[]> rosterInput = new ArrayList<>();
                try {
                    rosterInput = MysqlConExample.getStudents(sectionID);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println(rosterInput.size());
                String[] roster = new String[rosterInput.size()];
                for (int i = 0; i < rosterInput.size(); i++) {
                    roster[i] = rosterInput.get(i)[2] + ", " + rosterInput.get(i)[1] + " (" + rosterInput.get(i)[0] + ")";
                    model.addElement(roster[i]);
                }
                students.setModel(model);
                //students = new JList<String>(roster);
                students.setBounds(50, 50, 200, 300);
                //students.addListSelectionListener((ListSelectionListener) this);
                students.setVisible(true);
//                add(students);
                StudentAddID.setText("");
            }
        }
        if (e.getSource() == studentRemove) {
            String tmp = students.getSelectedValue();
            System.out.println(tmp);
            String [] tmpLol = tmp.split("\\(|\\)");
            System.out.println("WEE " + tmpLol[1]);
            for(int i = 0; i < tmpLol.length; i++)
            {
                System.out.println(tmpLol[i]);
            }
            try {
                MysqlConExample.studentRemover(sectionID, Integer.parseInt(tmpLol[1]));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            DefaultListModel model = new DefaultListModel();
            //model.addElement( "one" );
            //model.addElement( "two" );

            ArrayList<String[]> rosterInput = new ArrayList<>();
            try {
                rosterInput = MysqlConExample.getStudents(sectionID);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(rosterInput.size());
            String[] roster = new String[rosterInput.size()];
            for (int i = 0; i < rosterInput.size(); i++) {
                roster[i] = rosterInput.get(i)[2] + ", " + rosterInput.get(i)[1] + " (" + rosterInput.get(i)[0] + ")";
                model.addElement(roster[i]);
            }
            students.setModel(model);
            //students = new JList<String>(roster);
            students.setBounds(50, 50, 200, 300);
            //students.addListSelectionListener((ListSelectionListener) this);
            students.setVisible(true);
/*            if(studentTable.getSelectedRow() != -1)
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
        }*/
        }
    }
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}