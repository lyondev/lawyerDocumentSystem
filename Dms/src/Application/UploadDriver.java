/**
 * Java file containg the upload driver interface
 * for the ui of the application.
 */
package Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Java class that updates the ui with upload functionality
 * buttons, text field, and results list.
 */
public class UploadDriver {
    private JFrame frame;
    private JTextField txtPath;

    UploadDriver (JFrame frame) {
        this.frame = frame;
    }

    /**
     * Function the removes the current ui components,
     * reinitalizes the frame with upload components,
     * and then revalidates and repaints the frame.
     */
    public void reset(){
        frame.getContentPane().removeAll();
        initialize();
        frame.revalidate();
        frame.repaint();
    }

    private void initialize() {

        JButton btnBrowse = new JButton("Browse");
        btnBrowse.setBounds(10, 41, 87, 23);
        frame.getContentPane().add(btnBrowse);

        JButton btnUpload = new JButton("Upload");
        btnUpload.setBounds(120, 41, 87, 23);
        frame.getContentPane().add(btnUpload);

        txtPath = new JTextField();
        txtPath.setBounds(10, 10, 414, 21);
        frame.getContentPane().add(txtPath);
        txtPath.setColumns(10);

        btnBrowse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                // For File
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                fileChooser.setAcceptAllFileFilterUsed(false);

                int rVal = fileChooser.showOpenDialog(null);
                if (rVal == JFileChooser.APPROVE_OPTION) {
                    txtPath.setText(fileChooser.getSelectedFile().toString());
                }
            }
        });

        btnUpload.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String filePath = txtPath.getText();
                if (filePath.equals("") || filePath.equals(null)) {
                    JOptionPane.showMessageDialog(frame, "You must select a valid file");
                } else {
                    int response = JOptionPane.showConfirmDialog(
                            null, "Are you sure you want to upload the file?", "Confirm",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if (response == JOptionPane.NO_OPTION || response == JOptionPane.CLOSED_OPTION) {
                        txtPath.setText("");
                    } else if (response == JOptionPane.YES_OPTION) {
                        BackEndDriver.uploadDocument(filePath);
                        txtPath.setText("");
                        JOptionPane.showMessageDialog(frame,"File upload complete");
                    }
                }
            }

        });
    }
}
