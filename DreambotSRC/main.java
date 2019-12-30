package BasicWoodCutter;

import org.apache.tools.ant.taskdefs.condition.Socket;
import org.dreambot.api.input.mouse.destination.AbstractMouseDestination;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodContext;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.api.wrappers.widgets.WidgetChild;
import org.dreambot.api.wrappers.widgets.chatbox.ChatboxMessage;
import org.dreambot.api.wrappers.cache.nodes.NodeWrapper;
import org.dreambot.api.wrappers.cache.nodes.CacheNode;
import org.dreambot.api.script.listener.AdvancedMessageListener;
import org.dreambot.api.wrappers.widgets.message.Message;
import org.dreambot.api.utilities.Timer;
import org.dreambot.api.methods.emotes.Emote;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.dreambot.api.methods.tabs.Tabs;
import org.dreambot.api.methods.world.World;
import org.dreambot.api.wrappers.widgets.message.Message;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.BufferedInputStream;
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
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import javax.swing.*;

import org.dreambot.api.methods.trade.Trade;

@ScriptManifest(author = "Nicholas", category = Category.MONEYMAKING, name = "Server", version = 0)
public class main extends AbstractScript implements AdvancedMessageListener{

	private long timeBegan;
	private long timeRan;
	public static int counter;
	public static int bruh = 0;
	public static int howMany;
	private int costOfItem;
	private int itemsMade;
	private double gpGained;
	private double totalGpGained;
	private int gpPerHour;
	private long time;
	private boolean loggedOut = false;
	private boolean cowhide = false;
	private boolean done = false;
	public boolean trade1 = false;
	public boolean trade2 = false;
	public boolean doneBanking = false;
	public boolean checkRSN1 = false;
	public boolean checkRSN2 = false;
	public boolean checkTrade1 = false;
	public boolean checkTrade2 = false;
	public boolean checkBid = false;
	public String thisPersonSentYouATrade;
	public int bid = 0;
	public String player1 = "";
	public String player2 = "";
	Timer timer;
	Timer timer2;
	Timer timer3;
	Timer timer4;
	private long hour =    3600000;
	private int randomTime = Calculations.random(30000, 600000);
	private int randomTime2 = Calculations.random(60000, 120000);
	private int randomTime3 = Calculations.random(45000, 55000);
	private int randomTime4 = 120000;
	public int port = 25000;
	private static java.net.Socket s = null;; 

	private ServerSocket serverSocket = null;
	private PrintWriter out;
    private BufferedReader in;
	public String str2 = null;
	
	
	
	public void onStart() {
		try
        { 
    		serverSocket = new ServerSocket(54321);
            log("Server started");  
            
            log("Waiting for a client ..."); 
       
  
            // takes input from the client socket 
            s = serverSocket.accept();
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(), true);
            log("It has connected to the client: " + s.getRemoteSocketAddress());
//            str2 = ""; 

            
        } 
        catch(IOException i) 
        { 
            log("No");
           
        } 
//		try {
//		int port = 25000;
//		ServerSocket serverSocket = new ServerSocket(port);
//        log("Server Started and listening to the port 25000");
//        s = serverSocket.accept();
//        
//        in = new DataInputStream(s.getInputStream());
//        out    = new DataOutputStream(s.getOutputStream());
//        
//		}
//            catch(Exception e) {
//	           log("stfu");
//	           e.printStackTrace();
//	       }
		timer = new Timer();
	    timer.setRunTime(randomTime);
	    timer2 = new Timer();
	    timer2.setRunTime(randomTime2);
	    timer3 = new Timer();
	    timer3.setRunTime(randomTime2);
	    timer4 = new Timer();
	    timer4.setRunTime(randomTime4);
	    super.onStart();
		timeBegan = System.currentTimeMillis();
		counter = 1;
		
		Tile gamble = new Tile(3165, 3483, 0);
		if(getLocalPlayer().getTile() != gamble) {
			getWalking().walk(gamble);
		}
	}

	public void onPaint(Graphics g) {
		Color orange = new Color(254, 127, 0);
		g.setColor(orange);
//		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
//		timeRan = System.currentTimeMillis() - this.timeBegan;
		g.drawString("Timer: " + time , 20, 70);
//		gpGained = itemsMade * costOfItem;
//		DecimalFormat df = new DecimalFormat("#");
//		g.drawString("GP: " + "" + df.format(gpGained), 20, 105);
//		g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
//		g.drawString(
//				"You will make " + ((howMany * 155) / 1000) + "k GP, in around " + ((60 * howMany) / 275) + " minutes",
//				20, 330);
	}

	private String ft(long duration) {
		String res = "";
		long days = TimeUnit.MILLISECONDS.toDays(duration);
		long hours = TimeUnit.MILLISECONDS.toHours(duration)
				- TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));
		long minutes = TimeUnit.MILLISECONDS.toMinutes(duration)
				- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration));
		long seconds = TimeUnit.MILLISECONDS.toSeconds(duration)
				- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration));
		if (days == 0) {
			res = (hours + ":" + minutes + ":" + seconds);
		} else {
			res = (days + ":" + hours + ":" + minutes + ":" + seconds);
		}
		return res;
	}
	class Camera{
		public void moveCam() {
			
			getCamera().rotateTo(Calculations.random(2400), Calculations.random(getClient().getLowestPitch(), 372));
			
		}
	}
	
	class readFile{
		
		public void readName() throws IOException {
			//Null Check
			if((player1 = in.readLine()) != null && checkRSN1 == false) {
					checkRSN1 = true;
					log("Player 1: " + player1);
			}
			if((bid = in.read()) != 0 && checkRSN1 == true && checkRSN2 == false && checkBid == false) {
					checkBid = true;
					log("Bid: " + bid);
			}
			if((player2 = in.readLine()) != null && checkRSN1 == true && checkRSN2 == false && checkBid == true) {
					checkRSN2 = true;
					log("Player 2: " + player2);
			}
				
		}
	}
	  
	@Override
	public int onLoop() {
	int bid = 0;
	
	readFile read = new readFile();
	
	try {
		read.readName();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//	try
//	{
//		if((str2 = in.readLine()) != null) {
//			log(str2);
//			checkRSN1 = true;
//			bid = in.read();
//		}
//		else {
//			log("nope");
//		}
//	}
//	catch(IOException i)
//	{
//	log("Fuck");
//	}

		return 500;
	}
	


	public void onExit() {
		try {
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void randomSleep() {
		int randomSleepNumber = Calculations.random(0, 20);
		if (randomSleepNumber <= 4) {
			sleep(1200, 1600);
		} else if (randomSleepNumber <= 9) {
			sleep(1000, 1400);
		} else if (randomSleepNumber <= 16) {
			sleep(1300, 1800);
		} else if (randomSleepNumber <= 20) {
			sleep(2100, 2500);
		}
	}

	@Override
	public void onAutoMessage(Message arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClanMessage(Message arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameMessage(Message arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerMessage(Message arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPrivateInMessage(Message arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPrivateInfoMessage(Message arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPrivateOutMessage(Message arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTradeMessage(Message m) {
		// TODO Auto-generated method stub
		if(!getTrade().isOpen()) {
		thisPersonSentYouATrade = m.getUsername();
//		Player playerTrade = getPlayers().closest(p -> Objects.nonNull(p) && p.getName().equals(thisPersonSentYouATrade) && Area.contains(p));
//		MethodContext.sleepUntil(getTrade()::isOpen, 10_000);
		String coins = "Coins";
		
		Tile gamble = new Tile(3165, 3483, 0);
	
		if(thisPersonSentYouATrade.equalsIgnoreCase(str2)) {
			getTrade().tradeWithPlayer(thisPersonSentYouATrade);
			MethodContext.sleepUntil(getTrade()::isOpen, 10_000);
		}
		if(thisPersonSentYouATrade.equalsIgnoreCase(player2)) {
			getTrade().tradeWithPlayer(thisPersonSentYouATrade);
			MethodContext.sleepUntil(getTrade()::isOpen, 10_000);
		}
	
//		sleep(4500);
//		if(getTrade().contains(true, "Coins")) {
//			getTrade().declineTrade();
//		}
		
		}
	
	}
}
