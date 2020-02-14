/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientchatapp;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author migo2
 */ class ClientChat extends JFrame{
       Socket mySocket; //create an reference of ANormalSocket
       DataInputStream dis ; //create a stream reference To Recieve
       PrintStream ps;        //create a stream reference of send
        public ClientChat(){
            
        this.setLayout(new FlowLayout());
        JTextArea ta=new JTextArea(20,20);
        JScrollPane scroll=new JScrollPane(ta);
        scroll.setViewportView(ta);
        JTextField tf=new JTextField(30);
        JButton okButton=new JButton("Send");
        okButton.addActionListener(new ActionListener(){
             @Override
            public void actionPerformed(ActionEvent ae){
            String str =tf.getText();
//             ta.append(tf.getText()+"\n");
           ps.println(str);
           tf.setText(" ");
        }
        });
        add(scroll);
        add(tf);
        add(okButton);
        
        try{
          //  mySocket = new Socket("192.168.43.229", 7777); //create normal Socket with port 5005
            mySocket = new Socket("127.0.0.1", 5006);
            dis = new DataInputStream(mySocket.getInputStream()); //create the input stream to recieve 
            ps= new PrintStream(mySocket.getOutputStream());  //create the output stream to send
        }catch(Exception ex){
            ex.printStackTrace();
        }
        new Thread(new Runnable(){
            @Override
            public void run(){
                while(true){
                    try {
                        String replyMsg= dis.readLine();
                        ta.append("\n"+replyMsg);
//                        System.out.println(dis.readLine());
                    } catch (IOException ex) {
                         ex.printStackTrace();
                    }
                }
            }
            
        }).start();
    }
        
      public static void main(String[] args) {
         ClientChat client1 =new ClientChat();
        client1.setSize(400, 500);
        client1.setVisible(true);
    }  
 }     
        
        

    
    
    

    
     

