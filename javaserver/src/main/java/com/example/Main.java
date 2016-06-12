package com.example;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args){

        final Server obj = new Server();
        SwingUtilities.invokeLater(new Runnable(){	//multiple threads
            @Override
            public void run(){
                MyWindow window = new MyWindow(obj);
                window.setLayout(null);
                window.setSize(400,300);
                window.setLocation(100,100);
                window.setResizable(false);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setVisible(true);
            }
        });
    }


}