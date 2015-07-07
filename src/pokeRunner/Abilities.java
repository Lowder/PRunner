package pokeRunner;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Abilities {

	public String trigger;
	Random rand;
	
	Map<String, Map<String, String>> poAbilityInfo;
	
	public Abilities(){
		rand = new Random();
		createAbilityInfo();
	}
	
	public String abilityType(Pokemon mon){
		return "NonCombat";
	}
	
	public void activateAbility (Pokemon mon, Pokemon target){
		
	}
	
	public void activateAbility (Pokemon mon, Player target){
		
	}
	
	public void activateAbility (Pokemon mon, Object[] targets){
		//randomize based on power level
		int i = rand.nextInt(targets.length-1);
		if (targets[i] instanceof Pokemon){
			activateAbility(mon, (Pokemon)targets[i]);
		} else {
			activateAbility(mon, (Player)targets[i]);
		}
	}
	
	public void createAbilityInfo(){
		Map<String, String> tempAbilities = new TreeMap<String, String>();
		
		//tempAbilities.put(arg0, arg1)
	}
}
