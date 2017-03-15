/**
 * Java file containg the search driver interface
 * for the ui of the application.
 */
package Application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Java class that updates the ui with search functionality
 * buttons, text field, and results list.
 */
public class SearchDriver {
    private JFrame frame;
    private JTextField txtPath;
    private JList list;
    private ArrayList<String> results;

    SearchDriver (JFrame frame) {
        this.frame = frame;
    }

    /**
     * Function the removes the current ui components,
     * reinitalizes the frame with search components,
     * and then revalidates and repaints the frame.
     */
    public void reset(){
        frame.getContentPane().removeAll();
        initialize();
        frame.revalidate();
        frame.repaint();
    }

    private void initialize() {

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(10, 41, 87, 23);
        frame.getContentPane().add(btnSearch);

        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(120, 41, 87, 23);
        frame.getContentPane().add(btnClear);

        txtPath = new JTextField();
        txtPath.setBounds(10, 10, 414, 21);
        txtPath.setColumns(10);
        frame.getContentPane().add(txtPath);

        String[] startingList = {"No files found"};

        list = new JList(startingList);
        list.setBounds(10, 80, 414, 40);
        frame.getContentPane().add(list);

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String keyword = txtPath.getText();
                if (keyword.equals("") || keyword.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Keyword is required to search.");
                } else {
                    results = BackEndDriver.getResults(BackEndDriver.searchDocuments(keyword));
                    String[] listArray = new String[results.size()];
                    for (int i = 0; i < listArray.length; i++) {
                        listArray[i] = results.get(i);
                    }
                    frame.getContentPane().remove(list);
                    list = new JList(listArray);
                    list.setBounds(10, 80, 414, 40);
                    frame.getContentPane().add(list);

                    setFileOpenListener();

                    frame.revalidate();
                    frame.repaint();
                    System.out.println("Searched");
                }
            }
        });

        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtPath.setText("");
            }

        });
    }

    private void setFileOpenListener() {
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    Object selected = list.getSelectedValue();
                    openFile(selected.toString());
                }
            }
        });
    }

    private void openFile(String fileName){
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File("/home/centhian/Documents/DmsFiles/" + fileName);
                Desktop.getDesktop().open(myFile);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, "File not found. Make sure file is in correct location.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame,"No application found to open file type.");
            }
        }
    }
}

