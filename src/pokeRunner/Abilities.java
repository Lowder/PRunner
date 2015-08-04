package pokeRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
//import java.util.TreeMap;

public class Abilities {

    public String trigger;
    Random rand;
    Pokedex pokedex;

    Map<String, Map<String, String>> poAbilityInfo;
    ArrayList<Typings> combatTypes;

    public Abilities(Pokedex p) {
        rand = new Random();
        createAbilityInfo();
        initializeTypes();
        pokedex = p;
    }

    public String abilityType(Pokemon mon) {
        if (combatTypes.contains(mon.tAbility)) {
            return "Combat";
        } else {
            return "NonCombat";
        }
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
        //randomize based on power level
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
		//Map<String, String> tempAbilities = new TreeMap<String, String>();

        //tempAbilities.put(arg0, arg1)
    }

    private void initializeTypes() {
        Typings[] cTypes = {Typings.FIRE, Typings.ELECTRIC, Typings.ICE, Typings.POISON, Typings.DRAGON, Typings.DARK, Typings.STEEL};
        combatTypes = new ArrayList<Typings>(Arrays.asList(cTypes));
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
                target.setAvoidChallenge(target.avoidChallenge + 1);
                break;
            case 2:
                target.setAvoidChallenge(target.avoidChallenge + 3);
                break;
            case 3:
                target.setAvoidChallenge(target.avoidChallenge + 99);
                break;
            default:
                break;
        }
    }

    private void psychic(Pokemon mon, Player target) {
        switch (mon.pdEntry.pLevel) {
            case 1:
                mon.trainer.results.add(target.abilitySeer("rand"));
            case 2:
            case 3:
                mon.trainer.results.add(target.abilitySeer("all"));
            default:
                break;
        }

    }

    private void fairy(Pokemon mon, Player target) {
        mon.trainer.results.add(target.teamSeer());
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
