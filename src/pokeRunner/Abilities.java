package pokeRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Abilities {

    public String trigger;
    Random rand;
    Pokedex pokedex;

    Map<Typings, String[]> poAbilityInfo;
    ArrayList<Typings> combatTypes;
    ArrayList<Typings> conditionalCombatTypes;

    public Abilities(Pokedex p) {
        rand = new Random();
        createAbilityInfo();
        initializeTypes();
        pokedex = p;
    }
    
    public Abilities(){
    	rand = new Random();
        createAbilityInfo();
        initializeTypes();
    }

    public String abilityType(Pokemon mon) {
        if (combatTypes.contains(mon.tAbility)) {
            return "Combat";
        } else if (conditionalCombatTypes.contains(mon.tAbility)){
        	return "ConditionalCombat";
        } else {
            return "NonCombat";
        }
    }
    
    public BattleData useCombatAbility(int pos, BattleData bd, PokeGame gi){
    	Pokemon typeTest;
    	if(pos < 3)
    		typeTest = bd.getAttacker().getTeam()[pos];
    	else
    		typeTest = bd.getDefender().getTeam()[pos-3];
    	
    	if(abilityType(typeTest) == "Combat")
    		switch (typeTest.tAbility){
    			case FIRE:
    				break;
    			case ELECTRIC:
    				break;
    			case ICE:
    				break;
    			case POISON: 
    				break;
    			case DRAGON: 
    				break;
    			case DARK:
    				break;
				default:
					break;
    					
    				
    		}
    	
    	return bd;
    }
    
    public String targetType(Typings type){
    	return "Pokemon";
    }
    
    public boolean checkCondition(Pokemon mon, Pokemon[] team, Pokemon[] oppTeam){
    	boolean result = false;
    	
    	switch (mon.tAbility) {
	        case ROCK:
	            break;
	        case STEEL:
	            break;
	        default:
	            break;
	
	    }
    	return result;
    }

    public void activateAbility(Pokemon mon, Pokemon target) {
        switch (mon.tAbility) {
            case FIRE:
                fire(mon, target);
                break;
            case ELECTRIC:
                electric(mon, target);
                break;
            case ICE:
                ice(mon, target);
                break;
            case FIGHTING:
                fighting(mon, target);
                break;
            case POISON:
                poison(mon, target);
                break;
            case BUG:
                bug(mon, target);
                break;
            case ROCK:
                rock(mon, target);
                break;
            case DRAGON:
                dragon(mon, target);
                break;
            case DARK:
                dark(mon, target);
                break;
            case STEEL:
                steel(mon, target);
                break;
            case NORMAL:
                normal(mon, target);
                break;
            default:
                break;

        }
    }

    public void activateAbility(Pokemon mon, Player target) {
        switch (mon.tAbility) {
            case WATER:
                water(mon, target);
                break;
            case GROUND:
                ground(mon, target);
                break;
            case PSYCHIC:
                psychic(mon, target);
                break;
            case FAIRY:
                fairy(mon, target);
                break;
            case GRASS:
                grass(mon, target);
                break;
            default:
                break;

        }

    }

    public void activateAbility(Pokemon mon, Object[] targets) {
        //TODO: randomize based on power level?
        int i = targets.length - 1;
        if (targets[i] instanceof Pokemon) {
            for (int j = 0; j < targets.length; j++) {
                activateAbility(mon, (Pokemon) targets[j]);
            }
        } else {
            for (int j = 0; j < targets.length; j++) {
                activateAbility(mon, (Player) targets[j]);
            }
        }
    }

    private void createAbilityInfo() {
        poAbilityInfo = new TreeMap<Typings, String[]>();
        for(Typings t : Typings.values()){
            poAbilityInfo.put(t, new String[3]);
        }
        
        
        poAbilityInfo.get(Typings.valueOf("NORMAL"))[0] = "[b]Recover[/b] [i](Battle Passive)[/i]: Ignore the first pokemon ability in each fight";
        poAbilityInfo.get(Typings.valueOf("NORMAL"))[1] = "[b]Disable[/b] [i](Targetable)[/i]: Target pokemon is immune to all pokemon abilities during battles that night.";
        poAbilityInfo.get(Typings.valueOf("NORMAL"))[2] = "[b]Safeguard[/b] [i](Battle Passive)[/i]: All of your pokemon are protected from all pokemon abilities during battle.";

        poAbilityInfo.get(Typings.valueOf("FIRE"))[0] = "[b]Fire Punch[/b] [i](Battle Passive)[/i]: 50% chance to Burn (reduce attack effectiveness by one factor) a challenger's pokemon (random)";
        poAbilityInfo.get(Typings.valueOf("FIRE"))[1] = "[b]Flamethrower[/b] [i](Battle Passive)[/i]: Burn (reduce attack effectiveness by one factor) a challenger's pokemon (random)";
        poAbilityInfo.get(Typings.valueOf("FIRE"))[2] = "[b]Blast Burn[/b] [i](Battle Passive)[/i]: Burn (reduce attack effectiveness by one factor) two of your challenger's pokemon (random)";
        
        poAbilityInfo.get(Typings.valueOf("WATER"))[0] = "[b]Bubble[/b] [i](Targetable)[/i]: Target one trainer in your area: all of their pokemon are cured of status effects.";
        poAbilityInfo.get(Typings.valueOf("WATER"))[1] = "[b]Soak[/b] [i](Targetable)[/i]: Target two trainers in your area: all of their pokemon are cured of status effects.";
        poAbilityInfo.get(Typings.valueOf("WATER"))[2] = "[b]Rain Dance[/b] [i](Targetable)[/i]: Target any number of trainers in your area: all of their pokemon are cured of all status effects. ";

        poAbilityInfo.get(Typings.valueOf("ELECTRIC"))[0] = "[b]Spark[/b] [i](Battle Passive)[/i]: 50% chance to Paralyze (won't fight every other night) a challenger's pokemon (random)";
        poAbilityInfo.get(Typings.valueOf("ELECTRIC"))[1] = "[b]Thunder[/b] [i](Battle Passive)[/i]: Paralyze (won't fight every other night) a challenger's pokemon (random)";
        poAbilityInfo.get(Typings.valueOf("ELECTRIC"))[2] = "[b]Bolt Strike[/b] [i](Battle Passive)[/i]: Paralyze (won't fight every other night) two of your Challenger's pokemon (random)";

        poAbilityInfo.get(Typings.valueOf("GRASS"))[0] = "[b]Synthesis[/b] [i](Passive)[/i]: Will find two random items each night.";
        poAbilityInfo.get(Typings.valueOf("GRASS"))[1] = "[b]Grass Pledge[/b] [i](Passive)[/i]: Will find three random items each night";
        poAbilityInfo.get(Typings.valueOf("GRASS"))[2] = "[b]Magical Leaf[/b]: Gain 8 points worth in items, where common = 1, uncommon = 2, rare = 4, super rare = 8";

        poAbilityInfo.get(Typings.valueOf("ICE"))[0] = "[b]Ice Punch[/b] [i](Battle Passive)[/i]: 50%  chance to Freeze (won't fight for 3 nights) a Challenger's pokemon (random)";
        poAbilityInfo.get(Typings.valueOf("ICE"))[1] = "[b]Ice Beam[/b] [i](Battle Passive)[/i]: Freeze (won't fight for 3 nights) a challenger's pokemon (random)";
        poAbilityInfo.get(Typings.valueOf("ICE"))[2] = "[b]Freeze Shock[/b] [i](Battle Passive)[/i]: Freeze (won't fight for 3 nights) two of your Challenger's pokemon (random)";

        poAbilityInfo.get(Typings.valueOf("FIGHTING"))[0] = "[b]Detect[/b] [i](Targetable)[/i]: Target one of your pokemon, it evolves.";
        poAbilityInfo.get(Typings.valueOf("FIGHTING"))[1] = "[b]Bulk Up[/b] [i](Targetable)[/i]: Target any pokemon. It evolves.";
        poAbilityInfo.get(Typings.valueOf("FIGHTING"))[2] = "[b]Final Gambit[/b] [i](Targetable)[/i]: Target any pokemon, it evolves to max level";

        poAbilityInfo.get(Typings.valueOf("POISON"))[0] = "[b]Poison Sting[/b] [i](Battle Passive)[/i]: 50% chance to Poison (take 2x damage) a Challenger's pokemon (random).";
        poAbilityInfo.get(Typings.valueOf("POISON"))[1] = "[b]Acid Spray[/b] [i](Battle Passive)[/i]: Poison (take 2x damage) a challenger's pokemon (random)";
        poAbilityInfo.get(Typings.valueOf("POISON"))[2] = "[b]Belch[/b] [i](Battle Passive)[/i]: Poison (take 2x damage) two of your challenger's pokemon (random)";

        poAbilityInfo.get(Typings.valueOf("GROUND"))[0] = "[b]Dig[/b] [i](Targetable)[/i]: Protect target trainer from one challenge tonight";
        poAbilityInfo.get(Typings.valueOf("GROUND"))[1] = "[b]Rototiller[/b] [i](Targetable)[/i]: Protect target trainer from three challenges tonight";
        poAbilityInfo.get(Typings.valueOf("GROUND"))[2] = "[b]Sand Tomb[/b] [i](Targetable)[/i]: Protect target trainer from all challenges";
        
        poAbilityInfo.get(Typings.valueOf("FLYING"))[0] = "[b]Gust[/b] [i](Passive)[/i]: This pokemon's trainer can move up to two areas in a night";
        poAbilityInfo.get(Typings.valueOf("FLYING"))[1] = "[b]Tailwind[/b] [i](Passive)[/i]: This pokemon's trainer can move up to three areas in a night";
        poAbilityInfo.get(Typings.valueOf("FLYING"))[2] = "[b]Fly[/b] [i](Targetable)[/i]: This pokemon's trainer can move up to two target trainers to any area(s) including.";

        poAbilityInfo.get(Typings.valueOf("PSYCHIC"))[0] = "[b]Trick[/b] [i](Targetable)[/i]: Target one trainer in your area and seer one of their trainer abilities (or none if you've already learned all of their abilites)";
        poAbilityInfo.get(Typings.valueOf("PSYCHIC"))[1] = "[b]Dream Eater[/b] [i](Targetable)[/i]: Target one trainer in your area and seer all of their trainer abilities";
        poAbilityInfo.get(Typings.valueOf("PSYCHIC"))[2] = "[b]Miracle Eye[/b] [i](Targetable)[/i]: Target two trainers in any area and seer all of their abilities.";
        
        poAbilityInfo.get(Typings.valueOf("BUG"))[0] = "[b]Bug Buzz[/b] [i](Targetable)[/i]: Target pokemon's happiness gains are doubled tonight";
        poAbilityInfo.get(Typings.valueOf("BUG"))[1] = "[b]Powder[/b] [i](Targetable)[/i]: Target pokemon gains max happiness with their trainer";
        poAbilityInfo.get(Typings.valueOf("BUG"))[2] = "[b]Quiver Dance[/b] [i](Passive / Target Required)[/i]: Always at maximum happiness. Choose a trainer, they gain max happiness with all of their pokemon.";

        poAbilityInfo.get(Typings.valueOf("ROCK"))[0] = "[b]Rock Tomb[/b] [i](Targetable)[/i]: Target a pokemon. If that pokemon is knocked out tonight, this pokemon is knocked out instead.";
        poAbilityInfo.get(Typings.valueOf("ROCK"))[1] = "[b]Wide Guard[/b] [i](Passive)[/i]: If any of your pokemon are knocked out tonight, this pokemon is knocked out instead.";
        poAbilityInfo.get(Typings.valueOf("ROCK"))[2] = "[b]Ancient Power[/b] [i](Passive)[/i]: Prevent your other pokemon from being knocked out.";

        poAbilityInfo.get(Typings.valueOf("GHOST"))[0] = "[b]Night Shade[/b]: Once per game you can enter a secret area (but not interact)";
        poAbilityInfo.get(Typings.valueOf("GHOST"))[1] = "[b]Shadow Sneak[/b]: Twice per game you can enter a secret area (but not interact)";
        poAbilityInfo.get(Typings.valueOf("GHOST"))[2] = "[b]Trick-or-Treat[/b]: Once per game you can enter a secret area (and interact), except for the Tower of Champions";

        poAbilityInfo.get(Typings.valueOf("DRAGON"))[0] = "[b]Dragon Breath[/b] [i](Battle Passive)[/i]: 50% chance to cause a normal attack to another challenger's pokemon (random)";
        poAbilityInfo.get(Typings.valueOf("DRAGON"))[1] = "[b]Dragon Pulse[/b] [i](Battle Passive)[/i]: Deal a normal attack to one other challenger pokemon";
        poAbilityInfo.get(Typings.valueOf("DRAGON"))[2] = "[b]Roar of Time[/b] [i](Battle Passive)[/i]: Deal a normal attack to two other challenger's pokemon";

        poAbilityInfo.get(Typings.valueOf("DARK"))[0] = "[b]Thief[/b] [i](Battle Passive)[/i]: 50% chance to Siphon (copy and use) a random challenger's pokemon ability for that fight only";
        poAbilityInfo.get(Typings.valueOf("DARK"))[1] = "[b]Snatch[/b] [i](Battle Passive)[/i]: Siphon (copy and use) a random challenger's pokemon ability for that fight only";
        poAbilityInfo.get(Typings.valueOf("DARK"))[2] = "[b]Nasty Plot[/b] [i](Battle Passive)[/i]: Once per battle, steal (disable and then use) a challenger's pokemon ability ";

        poAbilityInfo.get(Typings.valueOf("STEEL"))[0] = "[b]Iron Defense[/b] [i](Battle Passive)[/i]: When knocked out, challenger pokemon attack effectiveness is reduced by one level";
        poAbilityInfo.get(Typings.valueOf("STEEL"))[1] = "[b]Iron Head[/b] [i](Battle Passive)[/i]: When knocked out, all other challenger pokemon's attack effectiveness is reduced to weak";
        poAbilityInfo.get(Typings.valueOf("STEEL"))[2] = "[b]King's Shield[/b] [i](Battle Passive)[/i]: If this pokemon is knocked out, all your other pokemon are immune to damage";

        poAbilityInfo.get(Typings.valueOf("FAIRY"))[0] = "[b]Charm[/b] [i](Targetable)[/i]: Target one trainer in your area and learn their team lineup";
        poAbilityInfo.get(Typings.valueOf("FAIRY"))[1] = "[b]Draining Kiss[/b] [i](Targetable)[/i]: Target two trainers in your area and learn their team lineup";
        poAbilityInfo.get(Typings.valueOf("FAIRY"))[2] = "[b]Baby-Doll Eyes[/b] [i](Targetable)[/i]: Learn the team lineup of all trainers in your area";

    }

    private void initializeTypes() {
        Typings[] cTypes = {Typings.FIRE, Typings.ELECTRIC, Typings.ICE, Typings.POISON, Typings.DRAGON, Typings.DARK};
        combatTypes = new ArrayList<Typings>(Arrays.asList(cTypes));
        Typings[] condTypes = {Typings.STEEL, Typings.ROCK};
        conditionalCombatTypes = new ArrayList<Typings>(Arrays.asList(condTypes));
    }

    private void fire(Pokemon mon, Pokemon target) {
        switch (mon.pdEntry.pLevel) {
            case 1:
                Random r = new Random();
                int roll = r.nextInt(1);
                if (roll == 1) {
                    target.burn();
                }
                break;
            case 2:
            case 3:
                target.burn();
                break;
            default:
                break;
        }
    }

    private void electric(Pokemon mon, Pokemon target) {
        switch (mon.pdEntry.pLevel) {
            case 1:
                Random r = new Random();
                int roll = r.nextInt(1);
                if (roll == 1) {
                    target.paralyze();
                }
                break;
            case 2:
            case 3:
                target.paralyze();
                break;
            default:
                break;
        }
    }

    private void ice(Pokemon mon, Pokemon target) {
        switch (mon.pdEntry.pLevel) {
            case 1:
                Random r = new Random();
                int roll = r.nextInt(1);
                if (roll == 1) {
                    target.freeze(3);
                }
                break;
            case 2:
            case 3:
                target.freeze(3);
                break;
            default:
                break;
        }
    }

    private void fighting(Pokemon mon, Pokemon target) {
    	switch (mon.pdEntry.pLevel) {
        case 1:
            target.evolve(pokedex, null);
            break;
        case 2:
        	target.evolve(pokedex, null);
        case 3:
            while(target.pdEntry.canEvolve)
            	target.evolve(pokedex, null);
            break;
        default:
            break;
    }
    }

    private void poison(Pokemon mon, Pokemon target) {
        switch (mon.pdEntry.pLevel) {
            case 1:
                Random r = new Random();
                int roll = r.nextInt(1);
                if (roll == 1) {
                    target.poison();
                }
                break;
            case 2:
            case 3:
                target.poison();
                break;
            default:
                break;
        }
    }

    private void bug(Pokemon mon, Pokemon target) {
        switch (mon.pdEntry.pLevel) {
            case 1:
                target.setHappyMod(2);
                break;
            case 2:
                target.setHappiness(5);
                break;
            case 3:
                for (Pokemon p : target.trainer.team) {
                    p.setHappiness(5);
                }
                break;
            default:
                break;
        }
    }

    private void rock(Pokemon mon, Pokemon target) {
    }

    private void dragon(Pokemon mon, Pokemon target) {
    }

    private void dark(Pokemon mon, Pokemon target) {
    }

    private void steel(Pokemon mon, Pokemon target) {
    }

    private void normal(Pokemon mon, Pokemon target) {
        switch (mon.pdEntry.pLevel) {
            case 1:
                mon.setGuarded(true);
                break;
            case 2:
                target.setGuarded(true);
                break;
            case 3:
                for (Pokemon p : mon.trainer.team) {
                    p.setGuarded(true);
                }
                break;
            default:
                break;
        }
    }

    private void water(Pokemon mon, Player target) {
        for (Pokemon p : target.team) {
            p.clearStatus();
        }
    }

    private void ground(Pokemon mon, Player target) {
        switch (mon.pdEntry.pLevel) {
            case 1:
                target.setUnderground(target.underground + 1);
                break;
            case 2:
            	target.setUnderground(target.underground + + 3);
                break;
            case 3:
            	target.setUnderground(target.underground + + 99);
                break;
            default:
                break;
        }
    }

    private void psychic(Pokemon mon, Player target) {
        switch (mon.pdEntry.pLevel) {
            case 1:
                mon.getTrainer().getResults().add(target.abilitySeer("rand"));
            case 2:
            case 3:
                mon.getTrainer().getResults().add(target.abilitySeer("all"));
            default:
                break;
        }

    }

    private void fairy(Pokemon mon, Player target) {
        mon.getTrainer().getResults().add(target.teamSeer());
    }

    private void grass(Pokemon mon, Player target) {
        Generator g = new Generator(pokedex);
        switch (mon.pdEntry.pLevel) {
            case 1:
                g.getItems(target, 2);
                break;
            case 2:
                g.getItems(target, 3);
                break;
            default:
                break;
        }
    }

}
