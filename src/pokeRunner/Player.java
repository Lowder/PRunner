package pokeRunner;

import java.util.ArrayList;

public class Player {

	public enum ItemType {REVIVE, LUCKYEGG, ESCAPEROPE, LURE, TM, POKEBALL, FULLHEAL, CANDY};
	
	public String faction;
	public String alignment;
	
	public Pokemon[] team;
	public ArrayList<Pokemon> box;
	
	public String paName;
	public String alias1;
	public String alias2;
	
	public int[] items;
	public String location;
	
	public TrainerAbilities ability;
	public SpecialTrainerAbilities sAbility;
	
	public boolean underground;
	public boolean uLength;
	
	public ArrayList<String> results;
	
	public Player(String PN, String A1, String A2, Pokemon[] T, ArrayList<Pokemon> B, int[] I, String L,
					TrainerAbilities a, SpecialTrainerAbilities sa){
		team = T;
		box = B;
		paName = PN;
		alias1 = A1;
		alias2 = A2;
		location = L;
		items = I;
		ability = a;
		sAbility = sa;
	}
	
	public void pokeNonCombat(Abilities abilities){
		for (int i = 0; i < team.length; i++){
			if (team[i].isActive() && abilities.abilityType(team[i]).equals("NonCombat")){
				if (team[i].isPlayerTarget)
					abilities.activateAbility(team[i], team[i].getTarget());
			}
				
		}
	}
	
}
