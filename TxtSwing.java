import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
/**
 * This class is extends JFrame, it is for showing the results
 */
public class TxtSwing extends JFrame {

    private JTextArea textAreaOutput;

    public TxtSwing() throws IOException {
        super("txt");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.FlowLayout());
        textAreaOutput = new JTextArea("111", 45, 80);
        textAreaOutput.setSelectedTextColor(Color.RED);
        textAreaOutput.setLineWrap(true); 
        textAreaOutput.setWrapStyleWord(true); 
        BufferedReader reader = new BufferedReader(new FileReader(
                "test.txt"));
        String str = reader.readLine();
        String end = null;
        while ((str != null)) {
            end = end + str + "\n";
            str = reader.readLine();
        }
        textAreaOutput.setText(end);

        getContentPane().add(textAreaOutput);
        setSize(1024, 768);

    }

    /**
     * @param args
     * @throws IOException
     */
}