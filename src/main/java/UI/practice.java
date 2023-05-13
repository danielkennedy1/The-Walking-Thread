package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class practice {
    private JTextArea messagesArea;
    private JTextArea responseArea;
    private JTextField inputField;
    private String message;

    public static void main(String[] args){
        practice mainwindow = new practice();
    }

    public practice() {
        JFrame frame = new JFrame("Chat GUI Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel messagesPanel = new JPanel(new BorderLayout());
        messagesArea = new JTextArea();
        messagesArea.setEditable(false);
        JScrollPane messagesScrollPane = new JScrollPane(messagesArea);
        messagesPanel.add(messagesScrollPane, BorderLayout.CENTER);
        panel.add(messagesPanel, BorderLayout.CENTER);

        JPanel responsePanel = new JPanel(new BorderLayout());
        responseArea = new JTextArea();
        responseArea.setEditable(false);
        JScrollPane responseScrollPane = new JScrollPane(responseArea);
        messagesPanel.add(responseScrollPane, BorderLayout.CENTER);
        JLabel label123 = new JLabel("this is the response area");
        responseArea.add(label123);
        panel.add(responsePanel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField(20);
        JButton sendButton = new JButton("Send");

        inputField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processInput();
            }
        });

        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processInput();
            }
        });

        JLabel sendlabel = new JLabel("Enter message");

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        inputPanel.add(sendlabel, BorderLayout.WEST);


        panel.add(inputPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void processInput() {
        message = inputField.getText();
        messagesArea.append(message + "\n");
        inputField.setText("");
        String responseText = getText();
        responseArea.setText(responseText);
    }

    private String getText() {
        // Method to fetch the response text
        // Replace with your own logic to retrieve the response
        return "This is the response.";
    }

    public String getMessage(){
        return message;
    }
}

