package BasicWoodCutter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.interactive.Player;



@ScriptManifest(author = "Nicholas", category = Category.MONEYMAKING, name = "Discord Dawg RUFF", version = 0)
public class main extends AbstractScript{
	
	public ArrayList<Player> players = new ArrayList<Player>();
	public ArrayList<String> names = new ArrayList<String>();
	
	public void onStart() {
		players = new ArrayList<Player>(getPlayers().all());
		for(Player p : players) {
			
			
				log(p.getName());
				names.add(p.getName());
			
			
			
		}
	}
	
	public int onLoop() {
		
			
			players = new ArrayList<Player>(getPlayers().all());
			for(Player p : players) {
				for(String w : names) {
				if(!w.equalsIgnoreCase(p.getName())) {
					log(p.getName());
					names.add(p.getName());
				}
			  }
			}
			
			

		
		
		return 600;
		
		
	}
	public void onExit() {
		
	}				
	
	
}
