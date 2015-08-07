package pokeRunner;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    public enum RH {

        PNAME, AONE, ATWO, F, ALIGN, ROLE, TYPE,
        LOC, RIV, ACT, TAR1, SPACT, TAR2, MV,
        P1, S1, N1, H1, ST1, A1, T1, //Pokemon 1
        P2, S2, N2, H2, ST2, A2, T2, //Pokemon 2
        P3, S3, N3, H3, ST3, A3, T3, //Pokemon 3
        RE, LE, ER, LU, PB, FH, CA, TM, // Inventory
        CAP, BOX
    };

    public String faction;
    public String alignment;
    public String role;
    public Player rival;

    public Pokemon[] team;
    public ArrayList<Pokemon> box;
    public ArrayList<Pokemon> captured;

    public String paName;
    public String alias1;
    public String alias2;

    public int[] items;
    public ArrayList<String> tms;
    public Locations location;

    //item flags
    public int avoidChallenge;
    public String lure;

    public TrainerAbilities ability;
    public SpecialTrainerAbilities sAbility;

    public int underground;

    public ArrayList<String> results;

    public Player(String[] playerInfo, PokeGame gameInfo) {

        faction = playerInfo[RH.F.ordinal()];
        alignment = playerInfo[RH.ALIGN.ordinal()];
        role = playerInfo[RH.ROLE.ordinal()];
        rival = gameInfo.getPlayer(playerInfo[RH.RIV.ordinal()]);

        team = new Pokemon[3];
        addTeam(playerInfo, gameInfo);
     	addBox(playerInfo[RH.BOX.ordinal()], gameInfo);
        addCapd(playerInfo[RH.CAP.ordinal()], gameInfo);
//
        paName = playerInfo[RH.PNAME.ordinal()];
        alias1 = playerInfo[RH.AONE.ordinal()];
        alias2 = playerInfo[RH.ATWO.ordinal()];
//
        int itemNum = 7;
        items = new int[itemNum];
        for(int i = 0; i < itemNum; i++)
        	items[i] = Integer.parseInt(playerInfo[RH.RE.ordinal()+i]);
        tms = new ArrayList<String>(Arrays.asList(playerInfo[RH.TM.ordinal()].split("|")));
        location = Locations.valueOf(playerInfo[RH.LOC.ordinal()]);
        avoidChallenge = 0;
        ability = TrainerAbilities.valueOf(playerInfo[RH.TYPE.ordinal()]);
        sAbility = SpecialTrainerAbilities.valueOf(playerInfo[RH.SPACT.ordinal()]);
        underground = 0;
//
        results = new ArrayList<>();
    }
    
    private void addBox(String boxInfo, PokeGame gi){
    	box = new ArrayList<Pokemon>();
    	String[] boxArray = boxInfo.split("|");
    	for(int i = 0; i < boxArray.length; i++) {
    		String[] boxArrayPart = boxArray[i].split(":");
    		Pokemon mon = new Pokemon(gi, Integer.parseInt(boxArrayPart[0]), boxArrayPart[1], Typings.valueOf(boxArrayPart[2]));
    		box.add(mon);
    	}
    }
    
    private void addCapd(String capInfo, PokeGame gi){
    	captured = new ArrayList<Pokemon>();
    	String[] capArray = capInfo.split("|");
    	for(int i = 0; i < capArray.length; i++) {
    		String[] capArrayPart = capArray[i].split(":");
    		Pokemon mon = new Pokemon(gi, Integer.parseInt(capArrayPart[0]), capArrayPart[1], Typings.valueOf(capArrayPart[2]));
    		captured.add(mon);
    	}
    }

    private void addTeam(String[] pokemonTeam, PokeGame gameInfo) {
        int pDataSize = 7;
        int startColumn = 13;
        for (int i = 0; i < 3; i++) {
            String[] pokemon = new String[7];
            System.arraycopy(pokemonTeam, i * pDataSize + startColumn, pokemon, 0, pDataSize);
            addPokemonToTeam(pokemon, i, gameInfo);
        }
    }

    public void addPokemonToTeam(String[] pokeData, int pos, PokeGame gameInfo) {
        Pokemon poke = new Pokemon(pokeData, gameInfo, this);
        team[pos] = poke;
    }

    public void pokeNonCombat(Abilities abilities) {
        for (int i = 0; i < team.length; i++) {
            if (team[i].isActive() && abilities.abilityType(team[i]).equals("NonCombat")) {
                if (team[i].isPlayerTarget) {
                    abilities.activateAbility(team[i], team[i].getTarget());
                }
            }

        }
    }

    public String teamSeer() {
        String seerResult = "";
        seerResult += alias2;
        seerResult += " has the following team:";
        for (int i = 0; i < team.length; i++) {
            seerResult += team[i].pdEntry.name + ", ";
        }
        return seerResult.substring(0, seerResult.length() - 2);
    }

    public String abilitySeer(String type) {
        if ("type".equalsIgnoreCase("rand")) {
            return "One ability chosen at random";
        } else {
            return "Put ability seer info here";
        }
    }

    public String printItemsPM() {
        String itemResult = "";
        for (int i = 0; i < items.length; i++) {
            if (items[i] > 0) {
                itemResult += ItemType.printPM(i) + "(" + items[i] + "), ";
            }
        }
        if (!tms.isEmpty()) {
            itemResult += "TMs: [";
            for (int i = 0; i < tms.size(); i++) {
                itemResult += tms.get(i) + ", ";
            }
            itemResult = itemResult.substring(0, itemResult.length() - 2) + "]  ";
        }

        return itemResult.substring(0, itemResult.length() - 2);
    }

    public String getWinconPM() {
        String pmInfo = "";
        switch (faction) {
            case "Faction1":
            case "Faction2":
            case "Faction3":
                pmInfo += faction + " is the first team to complete the following steps in order and as a group:[indent][br]";
                pmInfo += "1: Defeat all Gym Leaders[br]";
                pmInfo += "2: Defeat the Elite Four[br]";
                pmInfo += "3: Have a Trainer (still alive in thread) defeat the Champion[/indent]";
                break;
            case "Team Rocket":
                pmInfo += "No Trainer Team is able to complete their wincon.";
                break;
            case "Defender of the League":
                pmInfo += "The only players in the thread are Defenders of the League. This can be accomplished by:[indent][br]";
                pmInfo += "1: No Trainer Team is able to complete their wincon AND[br]";
                pmInfo += "2: All members of Team Rocket have been eliminated from the thread[/indent][br]";
                break;
            case "Pokemon League Champion":
            	pmInfo += "Only one trainer battles you for the Championship and loses";
            default:
                break;
        }
        return pmInfo;
    }

    public String printPokeBox() {
        String pb = "";
        for (Pokemon p : box) {
            pb += p.printBoxPM() + "[br]";
        }
        return pb.substring(0, pb.length());
    }

    public String printFactionPM() {
        return faction;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public Player getRival() {
        return rival;
    }

    public void setRival(Player rival) {
        this.rival = rival;
    }

    public Pokemon[] getTeam() {
        return team;
    }

    public void setTeam(Pokemon[] team) {
        this.team = team;
    }

    public void setTeam(int pos, Pokemon poke) {
        team[pos] = poke;
    }

    public ArrayList<Pokemon> getBox() {
        return box;
    }

    public void setBox(ArrayList<Pokemon> box) {
        this.box = box;
    }

    public ArrayList<Pokemon> getCaptured() {
        return captured;
    }

    public void setCaptured(ArrayList<Pokemon> captured) {
        this.captured = captured;
    }

    public String getPaName() {
        return paName;
    }

    public void setPaName(String paName) {
        this.paName = paName;
    }

    public String getAlias1() {
        return alias1;
    }

    public void setAlias1(String alias1) {
        this.alias1 = alias1;
    }

    public String getAlias2() {
        return alias2;
    }

    public void setAlias2(String alias2) {
        this.alias2 = alias2;
    }

    public int[] getItems() {
        return items;
    }

    public void setItems(int[] items) {
        this.items = items;
    }

    public void setItems(int pos, int value) {
        this.items[pos] = value;
    }

    public ArrayList<String> getTms() {
        return tms;
    }

    public void setTms(ArrayList<String> tms) {
        this.tms = tms;
    }

    public Locations getLocation() {
        return location;
    }

    public void setLocation(Locations location) {
        this.location = location;
    }

    public int getAvoidChallenge() {
        return avoidChallenge;
    }

    public void setAvoidChallenge(int avoidChallenge) {
        this.avoidChallenge = avoidChallenge;
    }

    public TrainerAbilities getAbility() {
        return ability;
    }

    public void setAbility(TrainerAbilities ability) {
        this.ability = ability;
    }

    public SpecialTrainerAbilities getsAbility() {
        return sAbility;
    }

    public void setsAbility(SpecialTrainerAbilities sAbility) {
        this.sAbility = sAbility;
    }

    public int isUnderground() {
        return underground;
    }

    public void setUnderground(int underground) {
        this.underground = underground;
    }

    public ArrayList<String> getResults() {
        return results;
    }

    public void setResults(ArrayList<String> results) {
        this.results = results;
    }
    
    public void setLure(String l){
    	this.lure = l;
    }
}
