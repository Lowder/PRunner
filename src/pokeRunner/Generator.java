package pokeRunner;

import java.util.ArrayList;
import java.util.Random;

public class Generator {

	Pokedex pokedex;
	public Generator(Pokedex p){
		pokedex = p;
	}
	
	public void getItems(Player player, int num){
		Random r = new Random();
		int itemPos = -1;
		for (int i = 0; i < num; i++){
			int roll = r.nextInt(99);
			//SUPER RARE: pokeball belt
			if (roll < 5) 
				itemPos = ItemType.POKEBALLBELT.ordinal();
			//RARE: escape rope / lucky egg
			else if(roll < 15) {
				int sroll = r.nextInt(1);
				if(sroll == 0)
					itemPos = ItemType.LUCKYEGG.ordinal();
				else
					itemPos = ItemType.ESCAPEROPE.ordinal();
			//UNCOMMON: TM
			} else if(roll < 50) {
				int sroll = r.nextInt(1);
				if(sroll == 0)
					itemPos = ItemType.CANDY.ordinal();
				else {
					int t = r.nextInt(15);
					t +=1 ;
					String tm = "" + t;
					if(t < 10)
						tm = "0" + tm;
					player.tms.add(tm);
				}
			//COMMON: full heal / revive / lure
			} else {
				int sroll = r.nextInt(2);
				if(sroll == 0)
					itemPos = ItemType.FULLHEAL.ordinal();
				else if (sroll ==1 )
					itemPos = ItemType.REVIVE.ordinal();
				else
					itemPos = ItemType.LURE.ordinal();
			}
			if(itemPos >= 0)
				incrementItem(itemPos, player);
		}
	}
	
	public void incrementItem(int itemPos, Player player){
		player.setItems(itemPos, player.items[itemPos]+1);
	}
	
	public void getPokemon(Player player){
		
		ArrayList<Typings> capType = new ArrayList<Typings>();;
		boolean canCap = true;
		switch(player.location){
			case "Steamy Ponds":
				capType.add(Typings.FIRE);
				capType.add(Typings.WATER);
				break;
			case "Destroyed Factory":
				capType.add(Typings.ELECTRIC);
				capType.add(Typings.STEEL);
				break;
			case "Shadowy Tower":
				capType.add(Typings.DARK);
				capType.add(Typings.GHOST);
				break;
			case "Cloudy Heights":
				capType.add(Typings.FAIRY);
				capType.add(Typings.FLYING);
				break;
			case "Frozen Depths":
				capType.add(Typings.DRAGON);
				capType.add(Typings.ICE);
				break;
			case "Buzzing Grassland":
				capType.add(Typings.BUG);
				capType.add(Typings.GRASS);
				break;
			case "Crumbling Mountain":
				capType.add(Typings.ROCK);
				capType.add(Typings.GROUND);
				break;
			case "Abandoned Ruins":
				capType.add(Typings.NORMAL);
				capType.add(Typings.FIGHTING);
				break;
			case "Misty Swamp":
				capType.add(Typings.PSYCHIC);
				capType.add(Typings.POISON);
				break;
			default:
				canCap = false;
				break;			
		}
		//capture one pokemon of type a || b || c
		//if collector then capture two pokemon with 50% chance they are evolved if A || B

		if(canCap){
			ArrayList<Pokemon> possible = new ArrayList<Pokemon>();
			for(PDEntry p:pokedex.pokedex){
				if(p.canCapture() && (capType.contains(p.type1) || capType.contains(p.type2))){
					possible.add(new Pokemon()); //TODO: Add logic to create a new pokemon from entry
				}
					
			}
			Random r = new Random();
			int roll = r.nextInt(possible.size()-1);
			int cRoll = r.nextInt(2);
			Pokemon poke = possible.get(roll);
			if(player.ability == TrainerAbilities.COLLECTOR && cRoll == 2 && poke.pdEntry.canEvolve){
				//assign pokemon to be the evolved version
			}
			player.captured.add(poke);
			if(player.ability == TrainerAbilities.COLLECTOR && player.captured.size() == 1)
				getPokemon(player);
				
		}
		
		
	}
}
