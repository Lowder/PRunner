package pokeRunner;

import java.util.ArrayList;

public class PokeRunner {

	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PokeReader reader = new PokeReader();
		//Get Day information
		PokeGame gameInfo = reader.getConfig();
		
		//Read Pokedex CSV
		Pokedex pokedex = reader.getPokedex();		
		//Read Player CSV
		ArrayList<Player> players = reader.getPlayers(pokedex, gameInfo);
		//Read Orders
		Orders orders = reader.getOrders(gameInfo);
		
		//Process Pokemon non-Battle Abilities
		Abilities abilities = new Abilities();
		for (Player p: players)
			p.pokeNonCombat(abilities);
		//Process Orders
		players = orders.process(players, abilities, gameInfo);
		
		PokeWriter writer = new PokeWriter();
		//write Player CSV
		writer.writePlayers(players, gameInfo);
		//write PM CSV
		writer.writePMs(players, gameInfo);
		//write Results CSV
		writer.writeResults(players, gameInfo);
		
	}

}
