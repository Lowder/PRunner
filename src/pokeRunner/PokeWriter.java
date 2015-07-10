package pokeRunner;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PokeWriter {
	
	FileWriter writer;
	
	public PokeWriter(){
		
	}

	public void writePlayers(PokeGame gameInfo) {
		// TODO Auto-generated method stub
		
	}

	public void writePMs(PokeGame gameInfo) {
		// TODO Auto-generated method stub
		
	}

	public void writeResults(PokeGame gameInfo) {
		// TODO Auto-generated method stub
		try { 
			PrintWriter writer = new PrintWriter("Results" + gameInfo.day+ ".txt", "UTF-8");
			writer.println("Results" + '\n');
			for(Player p: gameInfo.players){
		    	writer.println(p.paName + "Results:");
		    	for (String s: p.results)
		    		writer.println(s);
		    }
		    writer.flush();
		    writer.close();
		} catch(IOException e) {
		     e.printStackTrace();
		}
	}
}
