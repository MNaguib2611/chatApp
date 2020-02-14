/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatHandler;


import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author migo2
 */
public class ChatHandler extends Thread{  //extends thred sothat each client is working seperately 
   public DataInputStream dis;   //refrence of recieve stream 
    public PrintStream ps;        //refrence of send stream 
      FileWriter fw =new FileWriter("conversation.txt", true);
      FileInputStream fis= new FileInputStream("conversation.txt");
    static Vector<ChatHandler> clientsVector= new Vector<ChatHandler>(); //static to make it only for the class
    public ChatHandler(Socket cs) throws IOException //constructor taking the server socket
    {
      
        try{
            dis = new DataInputStream(cs.getInputStream()); //highLevel stream for recieving 
            ps= new PrintStream(cs.getOutputStream());      //highLevel stream for sending 
            clientsVector.add(this); //adding each ChatHandler object to the vector
        }catch(Exception ex){
        }  
         start(); //start each thread
    }
    @Override
    public void run()
    {
       try {
           int size = fis.available();
           byte[] b = new byte[size];
           fis.read(b);
           String conv = new String(b);
           this.ps.println(conv);
           
       } catch (IOException ex) {
           Logger.getLogger(ChatHandler.class.getName()).log(Level.SEVERE, null, ex);
       }
    while(true)
        {
            String str;
            try {
                str = dis.readLine();
                sendMessageToAll(str);
                 fw.write(str+"\n");
                 fw.flush();
            } catch(Exception ex){
            }   
        }
    }
    void sendMessageToAll(String msg)
    {
        clientsVector.stream().forEach((ch) -> {
            ch.ps.println(msg);
       });
    }
    
}
