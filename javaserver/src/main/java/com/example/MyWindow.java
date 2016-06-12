package com.example;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;


public class MyWindow extends JFrame implements Runnable {

    private Server obj;
    private JLabel jLabel;
    Thread thread;
    public MyWindow(Server obj){
        this.obj = obj;
        this.setLayout(null);

        jLabel = new JLabel("");

        this.add(jLabel);
        jLabel.setBounds(0, 0, 400, 100);
        jLabel.setText(obj.getResult());

        thread = new Thread(this); // new thread
        thread.start();

    }

    public void run() {
        while(true) {
            if(obj.getResult()==null) jLabel.setText("The result from app is  ");
            else jLabel.setText("The result from app is  "+obj.getResult());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}