/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverchatapp;

import java.net.*;
import ChatHandler.ChatHandler;
import java.io.*;



/**
 *
 * @author Mohammed Naguib
 */




public class ServerChatApp {
    ServerSocket serverSocket; //create a reference of server socket 
    
    public ServerChatApp() throws FileNotFoundException, IOException
    {
         try{  // incase of connection error
            serverSocket= new ServerSocket(5006); //create server Socket with port 5006
            while(true) //accept many requests to allow multiple clients to connect
            {
            Socket s = serverSocket.accept();
               ChatHandler cht= new ChatHandler(s); //each connection wil create an object of ChatHandler
            
                
            }
         }catch(Exception ex){
             ex.printStackTrace();
         }  
         
         try{
             serverSocket.close();
         }catch(Exception ex){
             ex.printStackTrace();
         }
    
    }
    public static void main(String[] args) throws IOException {
       new  ServerChatApp();
    }
    
}
