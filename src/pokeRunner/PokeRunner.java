package pokeRunner;

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
		gameInfo.pokedex = reader.getPokedex();		
		//Read Player CSV
		gameInfo.players = reader.getPlayers(gameInfo);
		//Read Orders
		Orders orders = reader.getOrders(gameInfo);
		
		//Process Pokemon non-Battle Abilities
		Abilities abilities = new Abilities(gameInfo.pokedex);
		for (Player p: gameInfo.players)
			p.pokeNonCombat(abilities);
		//Process Orders
		gameInfo.players = orders.process(gameInfo, abilities);
		
		PokeWriter writer = new PokeWriter();
		//write Player CSV
		writer.writePlayers(gameInfo);
		//write PM CSV
		writer.writePMs(gameInfo);
		//write Results CSV
		writer.writeResults(gameInfo);
		
	}

}
