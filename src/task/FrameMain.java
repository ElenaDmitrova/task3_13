package task;

import util.ArrayUtils;
import util.JTableUtils;
import util.SimpleLinkedListStack;
import util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Stack;

public class FrameMain extends JFrame {
    private JPanel panelMain;
    private JTable tableInput1;
    private JTable tableOutput1;
    private JScrollPane scrollPaneTableOutput1;
    private JScrollPane scrollPaneTableInput1;
    private JButton buttonExecute;
    private JTable tableInput2;
    private JScrollPane scrollPaneTableInput2;
    private JTable tableOutput2;
    private JButton input1Button;
    private JButton input2Button;
    private JScrollPane scrollPaneTableOutput2;

    private final JFileChooser FILE_CH00SER_OPEN = new JFileChooser();
    private final JFileChooser FILE_CHOOSER_SAVE = new JFileChooser();

    public FrameMain() {
        this.setTitle("FrameMain");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(tableInput1, 40, false,
                true, false, true);
        JTableUtils.initJTableForArray(tableInput2, 40, false,
                true, false, true);
        JTableUtils.initJTableForArray(tableOutput1, 40, false,
                true, false, true);
        JTableUtils.initJTableForArray(tableOutput2, 40, false,
                true, false, true);
        tableInput1.setRowHeight(25);
        tableInput2.setRowHeight(25);
        tableOutput1.setRowHeight(25);
        scrollPaneTableInput1.setPreferredSize(new Dimension(-1, 90));
        scrollPaneTableInput2.setPreferredSize(new Dimension(-1, 90));
        scrollPaneTableOutput1.setPreferredSize(new Dimension(-1, 90));


        FILE_CH00SER_OPEN.setCurrentDirectory(new File("."));
        FILE_CHOOSER_SAVE.setCurrentDirectory(new File("."));

        final FileFilter FILE_NAME_TXT_EXTENSION_FILTER = new FileNameExtensionFilter("Text files",
                "txt");

        FILE_CH00SER_OPEN.addChoosableFileFilter(FILE_NAME_TXT_EXTENSION_FILTER);
        FILE_CHOOSER_SAVE.addChoosableFileFilter(FILE_NAME_TXT_EXTENSION_FILTER);

        FILE_CHOOSER_SAVE.setAcceptAllFileFilterUsed(false);
        FILE_CHOOSER_SAVE.setDialogType(JFileChooser.SAVE_DIALOG);
        FILE_CHOOSER_SAVE.setApproveButtonText("Save");

        addExecuteButton();
        input1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (FILE_CH00SER_OPEN.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        String[] arr = ArrayUtils.readLinesFromFile(FILE_CH00SER_OPEN.getSelectedFile().getPath())[0].split(" ");
                        JTableUtils.writeArrayToJTable(tableInput1, arr);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        input2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (FILE_CH00SER_OPEN.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        String[] arr = ArrayUtils.readLinesFromFile(FILE_CH00SER_OPEN.getSelectedFile().getPath())[0].split(" ");
                        JTableUtils.writeArrayToJTable(tableInput2, arr);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
    }


    private void addExecuteButton() {
        buttonExecute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String[] arr1 = JTableUtils.readStringArrayFromJTable(tableInput1);
                String[] arr2 = JTableUtils.readStringArrayFromJTable(tableInput2);
                SimpleLinkedListStack<String> answerLinkedListStack = null;
                Stack<String> answerStack = null;
                try {
                    answerLinkedListStack = Program.execute1(arr1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String[] answerArr1 = new String[answerLinkedListStack.size()];
                int temp = answerLinkedListStack.size();
                for (int i = 0; i < temp; i++) {

                    try {
                        //System.out.println(answerLinkedListStack.peek());
                        answerArr1[i] = String.valueOf(answerLinkedListStack.pop());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                try {
                    answerStack = Program.execute2(arr2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String[] answerArr2 = new String[answerStack.size()];
                int temp1 = answerStack.size();
                for (int i = 0; i < temp1; i++) {
                    try {
                        answerArr2[i] = answerStack.pop();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                JTableUtils.writeArrayToJTable(tableOutput1, answerArr1);
                JTableUtils.writeArrayToJTable(tableOutput2, answerArr2);
            }
        });
    }
}
