package com.sicholas.Sich;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;




public class Discord extends ListenerAdapter {
   public boolean startBet = false;
   public  User gambler;
   public boolean Checkbet = false;
   public int betActual;
   public boolean firstRSN = false;
   public boolean hasTraded1 = false;
   public boolean secondRSN = false;
   public boolean hasTraded2 = false;
   public User firstUser = null;
   public User secondUser = null;
   public static ServerSocket ss;
	
	
	public static String address = "localhost";
	
    public static int port = 25000;
    public static PrintWriter out;
	public static BufferedReader in;
    private static java.net.Socket s = null; 
   
   public static void main(String[] args) throws LoginException {
       JDABuilder builder = new JDABuilder();
       builder.setToken("NTk2NTU1MTAyMjU3NDE0MTUz.Xf1sAg.LTVmTUrEDdLk_oZEFN5uGpiUyK8");
       builder.setStatus(OnlineStatus.ONLINE);
       builder.setActivity(Activity.watching("1V1 Coin Flips for OSRS GP!"));
       builder.addEventListeners(new Discord());

       builder.build();
       try{      
    	   
    	   s = new Socket(address, 12345);
    	   System.out.println("Connected.");
    	   OutputStream output = s.getOutputStream();
    	   in = new BufferedReader(new InputStreamReader(s.getInputStream()));
           out = new PrintWriter(output, true);
           
           
		
		    
		   }
       catch(UnknownHostException u)
       		   {
			   System.out.println(u);
			   }
			   catch(IOException i)
			   {
			   System.out.println(i);
			   }


       
   }

   @Override
   public void onMessageReceived(MessageReceivedEvent event) {
      
	   Message msg = event.getMessage();
       
       MessageChannel channel = event.getChannel();

       if (msg.getContentRaw().equals("!start") && startBet == false) {
           long time = System.currentTimeMillis();
           gambler = msg.getAuthor();
           firstUser = gambler;
           channel.sendMessage("Welcome! " + gambler.getAsMention() + " has started a 50/50 coin flip!").queue();
           channel.sendMessage("Please insert an amount of GP that you would like to gamble ( >= 100k ): ").queue();
           startBet = true;

       }
       User check = msg.getAuthor();
       if(msg.getContentRaw().substring(0, 4).equals("!bet") &&  !msg.getAuthor().isBot() && startBet == true && gambler.getAsTag().equals(check.getAsTag())){
    	   String bet = msg.getContentRaw().replaceFirst("!bet ", "");
    	  
    	  
    	   betActual = Integer.parseInt(bet);
    	  
    	   firstUser = msg.getAuthor();
    	   if(betActual < 100000) {
    		   channel.sendMessage("Bet is too little, must be greater then 100000! Please restart by typing !start").queue();
    		   startBet = false;
    		   Checkbet = false;
    	   }
    	   else {
    		   channel.sendMessage("Coin Flip Match has started!").queue(); // Please match the bet of " + (betActual)/1000 + "k, please type !match. For example: 100k --> 100000.
    		   channel.sendMessage("Please insert your RSN by typing !RSN and then your OSRS name (Case Sensitive) and be ready to trade Ethan_Klein on W339 @ GE.").queue();
    		   
    		   Checkbet = true;
    	   }
       }
       
       if(msg.getContentRaw().substring(0, 4).equals("!RSN") && Checkbet == true && firstUser.getAsTag().equals(check.getAsTag())) {
    	   
    	   String RSN = msg.getContentRaw().replaceFirst("!RSN ", "");
    	   String magin = "";
    	   System.out.println(RSN);
    	   out.write(RSN);
    	   out.write(betActual);
    	   
//    	   try {
//			oos.writeObject(RSN);
//			oos.writeObject(betActual);
//		
//			System.out.println("It has sent");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			System.out.println("It hasn't sent.");
//			e.printStackTrace();
//		}
    	   
    	   
    	   channel.sendMessage("Please trade our bot named Ethan_Klein on World 388 @ GE. When you have completed the trade, type !Traded.").queue();
    	   
       }
       
       if(msg.getContentRaw().equals("!Traded") && !msg.getAuthor().isBot() &&  Checkbet == true && firstUser.getAsTag().equals(check.getAsTag()))  {
    	   
		   
		
		  
//  		   try {
//			hasTraded1 = din.readBoolean();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    		
       }
       if(hasTraded1 == true) {
    	   channel.sendMessage(check.getAsMention() + " has traded " + betActual + "! To play against him, type !match");
       }
       
       
       
       
       
       
       if(msg.getContentRaw().equals("!match") && !msg.getAuthor().isBot() && hasTraded1 == true ) {
    	   
    	   secondUser = msg.getAuthor();
    	   channel.sendMessage("A match between " + firstUser.getAsMention() + " & " + secondUser.getAsMention() + " @ a flip of "+ (betActual)/1000 + "k.").queue();
    	   int rollNumber = 1 + (int)(Math.random() * ((100 - 1) + 1));
    	   System.out.println(rollNumber);
    	   try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   channel.sendMessage("Rolling now!").queue();
    	   try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   int takeHome = 2 * (int)Math.round(betActual *.9);
    	   if(rollNumber < 51) {
    		   channel.sendMessage(firstUser.getAsMention() + " has won " + ((2*betActual)/1000 * .9) + "k.").queue();
    		   Checkbet = false;
    		   startBet = false;
    	   }
    	   if(rollNumber > 51) {
    		   channel.sendMessage(secondUser.getAsMention() + " has won " + ((2*betActual)/1000 * .9) + "k.").queue();
    		   Checkbet = false;
    		   startBet = false;
    	   }
    	   
//    			   Checkbet = false;
       }
   }
}

