package pokeRunner;

public class Battle {

    public Battle() {

    }

    public void challenge(Player attacker, Player defender, Abilities abilities, PokeGame gi) {
    	
    	if(defender.underground > 0){
    		attacker.results.add(defender.alias1 + " used DIG!");
    		defender.results.add("DIG helped you escape a challenge!");
    		defender.setUnderground(defender.underground-1);
    	} else if (defender.avoidChallenge > 0){
    		attacker.results.add(defender.alias1 + " escaped!");
    		defender.results.add("You used an escape rope to avoid a challenge!");
    		defender.setAvoidChallenge(defender.avoidChallenge-1);
    		defender.getItems()[ItemType.ESCAPEROPE.ordinal()] -= 1;
    	} else {
		    double[] aValues = new double[3];
		    double[] dValues = new double[3];
		
		    for (int i = 0; i < 3; i++) {
		        aValues[i] = pBattle(attacker.team[i], defender.team[i], gi);
		        dValues[i] = pBattle(defender.team[i], attacker.team[i], gi);
		    }
		    
		    //TODO: Use normal combat abilities 
		    
		    boolean checkAbilities = true;
		    boolean[] aAbility = {true, true, true};
		    boolean[] dAbility = {true, true, true};
		    
		    //TODO: Assign knockouts
		    while (checkAbilities){
		    	checkAbilities = false;
		    	for(int i = 0; i < 3; i++){
		    		if(abilities.abilityType(attacker.team[i]) == "ConditionalCombat" 
		    				&& attacker.team[i].isActive() && aAbility[i])
		    			if(abilities.checkCondition(attacker.team[i], attacker.team, defender.team)) {
		    				abilities.activateAbility(attacker.team[i], attacker.team, defender.team);
		    				checkAbilities = true;
		    				aAbility[i] = false;
		    			}
		    		if(abilities.abilityType(defender.team[i]) == "ConditionalCombat" 
		    				&& defender.team[i].isActive() && dAbility[i])
		    			if(abilities.checkCondition(defender.team[i], defender.team, attacker.team)) {
		    				abilities.activateAbility(defender.team[i], defender.team, attacker.team);
		    				checkAbilities = true;
		    				dAbility[i] = false;
		    			}
		    	}
		    }
		    
		    //TODO: Apply knockouts
		    
		    int winner = 0;
		    int[] bolds = {1, 1, 1};
		    for (int i = 0; i < 3; i++) {
		        if (aValues[i] > dValues[i]) {
		            winner += 1;
		        } else if (dValues[i] > aValues[i]) {
		            winner -= 1;
		            bolds[i] = 0;
		        } else {
		            if (attacker.team[i].happiness > defender.team[i].happiness) {
		                winner += 1;
		            } else if (attacker.team[i].happiness < defender.team[i].happiness) {
		                winner -= 1;
		                bolds[i] = 0;
		            } else {
		                bolds[i] = 2;
		            }
		        }
		    }
		
		    String[] values = convertAStoString(aValues, dValues);
		
		    String win = "tie";
		    if (winner < 0) {
		        win = "defender";
		        if(attacker.getAbility() != TrainerAbilities.FIGHTER)
		        	for(int i = 0; i < 3; i++)
		        		attacker.getTeam()[i].updateHappiness(-1);
		    } else if (winner > 0) {
		        win = "attacker";
		    }
		
		    String result = printBResult(attacker, defender, values, win, bolds);
		    attacker.results.add(result);
		    defender.results.add(result);
    	}
    }

    public double pBattle(Pokemon a, Pokemon d, PokeGame gi) {
        double result = gi.pokedex.typeChart[a.pdEntry.type1.ordinal()][d.pdEntry.type1.ordinal()];
        if (a.pdEntry.type2 != null) {
            result *= gi.pokedex.typeChart[a.pdEntry.type2.ordinal()][d.pdEntry.type1.ordinal()];
        }
        if (d.pdEntry.type2 != null) {
            result *= gi.pokedex.typeChart[a.pdEntry.type1.ordinal()][d.pdEntry.type2.ordinal()];
            if (a.pdEntry.type2 != null) {
                result *= gi.pokedex.typeChart[a.pdEntry.type2.ordinal()][d.pdEntry.type2.ordinal()];
            }
        }
        if (a.pdEntry.captureType == 'L')
            result *= 2;
        return result;
    }

    public String printBResult(Player a, Player d, String[] values, String winner, int[] bolds) {

        String aStrings = "";
        String dStrings = "";

        for (int i = 0; i < bolds.length; i++) {
            if (bolds[i] == 1) {
                aStrings += "[b]" + values[i] + "[/b], ";
                dStrings += values[i + 3] + ", ";
            } else if (bolds[i] == 0) {
                dStrings += "[b]" + values[i + 3] + "[/b], ";
                aStrings += values[i] + ", ";
            } else {
                aStrings += values[i] + ", ";
                dStrings += values[i + 3] + ", ";
            }
        }
        aStrings = aStrings.substring(0, aStrings.length() - 2);
        dStrings = dStrings.substring(0, dStrings.length() - 2);

        String result = "[b]" + a.alias2 + "[/b] (";
        result += aStrings + ") ";
        if (winner.equals("tie")) {
            result += "tied ";
        } else if (winner.equals("attacker")) {
            result += "defeated ";
        } else {
            result += "defeated by ";
        }
        result += "[b]" + d.alias2 + "[/b] (";
        result += dStrings + ") ";
        return result;
    }

    public String[] convertAStoString(double[] av, double[] dv) {
        String[] results = new String[6];

        int aLen = av.length;
        int bLen = dv.length;
        double[] temp = new double[aLen + bLen];
        System.arraycopy(av, 0, temp, 0, aLen);
        System.arraycopy(dv, 0, temp, aLen, bLen);

        for (int i = 0; i < temp.length; i++) {
            results[i] = avToString(temp[i]);
        }

        return results;
    }

    public String avToString(double val) {
        String result = "";

        if (val == 0) {
            result = "Immune";
        } else if (val < .125) {
            result = "Weak x4";
        } else if (val < .25) {
            result = "Weak x3";
        } else if (val < .5) {
            result = "Super Weak";
        } else if (val < 1) {
            result = "Weak";
        } else if (val < 2) {
            result = "Normal";
        } else if (val < 4) {
            result = "Effective";
        } else if (val < 8) {
            result = "Super Effective";
        } else if (val < 16) {
            result = "Strong x3";
        } else {
            result = "Strong x4";
        }

        return result;
    }

}
