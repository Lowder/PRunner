package pokeRunner;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class PokeGame {

    public int day;
    private ArrayList<Player> players;
    private Map<String, ArrayList<Player>> factionBadges;
    public Pokedex pokedex;

    public PokeGame() {
        players = new ArrayList<Player>();
        factionBadges = new TreeMap<String, ArrayList<Player>>();

    }

    public Player getPlayer(String name) {
        for (Player p : players) {
            if (p.equals(name)) {
                return p;
            }
        }
        return null;
    }

    public void cleanUp() {
        for (Player p : players) {
            for (Pokemon po : p.getTeam()) {
                if (po.paralyzed) {
                    po.setParalyzedActive(!po.paralyzedActive);
                }
                if (po.frozen) {
                    po.setfTime(po.fTime - 1);
                }
                if (po.fTime == 0) {
                    po.setFrozen(false);
                }
            }
            for (Pokemon po : p.getBox()) {
                po.clearStatus();
                po.setKnockedOut(false);
            }
        }
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Pokedex getPokedex() {
        return pokedex;
    }

    public void setPokedex(Pokedex pokedex) {
        this.pokedex = pokedex;
    }
}
