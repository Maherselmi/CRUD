package com.boostmytool;

import javax.swing.*;

public class HelloWorld extends JFrame {
    private JPanel mainPanel;
    public HelloWorld(){
        add(mainPanel);
        setTitle("Welcome");
        setSize(450,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        HelloWorld myFrame = new HelloWorld();
    }
}
