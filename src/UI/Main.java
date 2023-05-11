package UI;

import javax.swing.*;
import java.awt.FlowLayout;

public class Main {
    public static void main(String[] args){
        JFrame j = new JFrame("chatbox");
        j.setVisible(true);
        j.setSize(400, 400);
        j.setLayout(new FlowLayout());

        JLabel message = new JLabel("Enter Message");
        JTextField messageText = new JTextField(20);
        j.add(message);
        j.add(messageText);



    }
}

