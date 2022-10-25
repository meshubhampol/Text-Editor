import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;

class editor extends JFrame implements ActionListener {
    // text object
    JTextArea t;

    // frame object
    JFrame f;

    editor() {
        //initializing the frame object
        f=new JFrame("Text Editor");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set theme
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch(Exception e) {}

        // initialize text area
        t=new JTextArea();

        // initialize menu bar
        JMenuBar mb=new JMenuBar();

        // create file menu for the menu bar
        JMenu m1=new JMenu("File");

        //  create menu items for file menu
        JMenuItem i1=new JMenuItem("New");
        JMenuItem i2=new JMenuItem("Open");
        JMenuItem i3=new JMenuItem("Save");
        JMenuItem i4=new JMenuItem("Print");

        // adding action listeners to each menu item
        i1.addActionListener(this);
        i2.addActionListener(this);
        i3.addActionListener(this);
        i4.addActionListener(this);

        // adding all menu items to the desired menu
        m1.add(i1);
        m1.add(i2);
        m1.add(i3);
        m1.add(i4);

        // creating the edit menu
        JMenu m2=new JMenu("Edit");

        // creating menu items for edit menu
        JMenuItem i5=new JMenuItem("cut");
        JMenuItem i6=new JMenuItem("copy");
        JMenuItem i7=new JMenuItem("paste");

        // adding action listeners to each edit menu item
        i5.addActionListener(this);
        i6.addActionListener(this);
        i7.addActionListener(this);

        // adding menu items to the menu
        m2.add(i5);
        m2.add(i6);
        m2.add(i7);

        // creating the close button
        JMenuItem c=new JMenuItem("close");
        c.addActionListener(this);

        // adding all the menus to menu bar
        mb.add(m1);
        mb.add(m2);
        mb.add(c);

        f.setJMenuBar(mb);
        f.add(t);
        f.setSize(1920,1080);
        f.show();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s=e.getActionCommand();
        if(s.equals("cut")) {
            t.cut();
        }
        else if(s.equals("copy")) {
            t.copy();
        }
        else if(s.equals("paste")) {
            t.paste();
        }
        else if(s.equals("Print")) {
            try {
                t.print();
            }
            catch(Exception e4) {
                JOptionPane.showMessageDialog(f,e4.getMessage());
            }
        }
        else if(s.equals("close")) {
            JOptionPane.showConfirmDialog(f,"do you want to close?"); // popup

            f.setVisible(false);
        }
        else if(s.equals("New")) {
            t.setText("");
        }
        else if(s.equals("Open")) {
            JFileChooser j=new JFileChooser("C:");

            int r=j.showOpenDialog(null);
            if(r == JFileChooser.APPROVE_OPTION) {
                File fi= new File(j.getSelectedFile().getAbsolutePath());

                try {
                    String s1 = "", s2="";
                    FileReader read=new FileReader(fi);
                    BufferedReader br=new BufferedReader(read);

                    // reading line by line
                    s1 = br.readLine();
                    while((s2 = br.readLine()) !=null) {
                        s1=s1 +"\n" + s2;
                    }

                    t.setText(s1);
                }
                catch(Exception e2) {
                    JOptionPane.showMessageDialog(f,e2.getMessage());
                }
            }
            else {
                JOptionPane.showMessageDialog(f, "user has cancelled the operation");
            }
        }
        else if(s.equals("Save")){
            JFileChooser j=new JFileChooser("D:");

            // invoke the save dialog box
            int r=j.showSaveDialog(null);

            if(r == JFileChooser.APPROVE_OPTION) {

                // set file label to the path of the selected directory path
                File fi=new File(j.getSelectedFile().getAbsolutePath());

                try {

                    // create file writer (create a file)
                    FileWriter write = new FileWriter(fi, false);

                    // create buffer writer (create a space)
                    BufferedWriter buffer = new BufferedWriter(write);

                    // write a file to path which created by buffered writer
                    buffer.write(t.getText());

                    // clear the buffer and close buffer
                    buffer.flush();
                    buffer.close();
                }
                catch(Exception e3) {
                    JOptionPane.showMessageDialog(f,e3.getMessage());
                }
            }
            else {
                JOptionPane.showMessageDialog(f, "user has cancelled the operation");
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        new editor();
    }
}