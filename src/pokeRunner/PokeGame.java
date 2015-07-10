package pokeRunner;

import java.util.ArrayList;

public class PokeGame {

    public int day;
    public ArrayList<Player> players;
    public Pokedex pokedex;
    
    public PokeGame(){

    }
    
    public Player getPlayer(String name){
    	for(Player p: players)
    		if(p.equals(name))
    			return p;
    	return null;
    }
}
