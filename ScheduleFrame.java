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

public class ScheduleFrame extends JFrame implements WindowListener{
    JList<String> students;
    JButton studentAdd, studentRemove;

    JTextField StudentAddID, StudentAddLN;

    String schedule;
    public ScheduleFrame(String schedule) throws IOException, SQLException {
        this.schedule = schedule;
        setLayout(null);
        setSize(500, 500);
        setVisible(true);
        setAlwaysOnTop(true);
        ArrayList<String[]> rosterInput = new ArrayList<>();
        String[] classes = schedule.split("%");
        for(String sections : classes){
            String[] sectionSplit = sections.split("-");
            rosterInput.add(sectionSplit);
            for(String ja: sectionSplit){
                System.out.println(ja);
            }
        }
        System.out.println(rosterInput.size());
        String[] roster = new String[rosterInput.size()];
        for(int i = 0; i < rosterInput.size() ; i++)
        {
            roster[i] = rosterInput.get(i)[0] + ", " + rosterInput.get(i)[1] + " (" + rosterInput.get(i)[2] + " " + rosterInput.get(i)[4] + ", "+ rosterInput.get(i)[3] +")" ;
        }
        //String [] studentsArray = {"Jason" , "Daniel" , "Kailin"};
        students = new JList<String>(roster);
        students.setBounds(50, 50, 500, 300);
        //students.addListSelectionListener((ListSelectionListener) this);
        students.setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);
        add(students);


//        StudentAddLN = new JTextField();
//        StudentAddLN.setEditable(true);
//        StudentAddLN.setBounds(150, 430, 180, 20);
//        add(StudentAddLN);

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