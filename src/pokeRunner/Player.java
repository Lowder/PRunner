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

        paName = playerInfo[RH.PNAME.ordinal()];
        alias1 = playerInfo[RH.AONE.ordinal()];
        alias2 = playerInfo[RH.ATWO.ordinal()];
        
        faction = playerInfo[RH.F.ordinal()];
        alignment = playerInfo[RH.ALIGN.ordinal()];
        role = playerInfo[RH.ROLE.ordinal()];
        rival = gameInfo.getPlayer(playerInfo[RH.RIV.ordinal()]);

        ability = TrainerAbilities.valueOf(playerInfo[RH.TYPE.ordinal()]);
        if(!playerInfo[RH.SPACT.ordinal()].isEmpty())
            sAbility = SpecialTrainerAbilities.valueOf(playerInfo[RH.SPACT.ordinal()]);
        location = Locations.valueOf(playerInfo[RH.LOC.ordinal()]);
        avoidChallenge = 0;
        underground = 0;
        
        team = new Pokemon[3];
        addTeam(playerInfo, gameInfo);
        
        int itemNum = 7;
        items = new int[itemNum];
        for (int i = 0; i < itemNum; i++) {
            String amt = playerInfo[RH.RE.ordinal() + i];
            if ("".equals(amt)) {
                items[i] = 0;
            } else {
                items[i] = Integer.parseInt(amt);
            }
        }
        
        if(!playerInfo[RH.TM.ordinal()].isEmpty()) {
            tms = new ArrayList<String>(Arrays.asList(playerInfo[RH.TM.ordinal()].split("\\|")));
            if(tms.size() < 2 && tms.get(0).length() < 2 && !tms.isEmpty())
                tms.set(0, "0" + tms.get(0));
        } else {
            tms = new ArrayList<String>();
        }
        
        box = new ArrayList<>();
        if(!playerInfo[RH.BOX.ordinal()].isEmpty())
            addBox(playerInfo[RH.BOX.ordinal()], gameInfo);
        
        captured = new ArrayList<Pokemon>();        
        if(!playerInfo[RH.CAP.ordinal()].isEmpty())
            addCapd(playerInfo[RH.CAP.ordinal()], gameInfo);
        
        results = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;

        if (o instanceof String) {
            String test = (String) o;
            if (test.equalsIgnoreCase(paName) || test.equalsIgnoreCase(alias1) || test.equalsIgnoreCase(alias2)) {
                result = true;
            }
        } else if (o instanceof Player) {
            if (this.equals(((Player) o).paName)) {
                result = true;
            }
        }
        return result;
    }

    private void addBox(String boxInfo, PokeGame gi) {
        String[] boxArray = boxInfo.split("\\|");
        for (int i = 0; i < boxArray.length; i++) {
            String[] boxArrayPart = boxArray[i].split(":");
            Pokemon mon = new Pokemon(gi, Integer.parseInt(boxArrayPart[0]), boxArrayPart[1], Typings.valueOf(boxArrayPart[2]));
            box.add(mon);
        }
    }

    private void addCapd(String capInfo, PokeGame gi) {
        String[] capArray = capInfo.split("\\|");
        for (int i = 0; i < capArray.length; i++) {
            String[] capArrayPart = capArray[i].split(":");
            Pokemon mon = new Pokemon(gi, Integer.parseInt(capArrayPart[0]), capArrayPart[1], Typings.valueOf(capArrayPart[2]));
            captured.add(mon);
        }
    }

    private void addTeam(String[] pokemonTeam, PokeGame gameInfo) {
        int pDataSize = 7;
        int startColumn = 14;
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

    public void pokeNonCombat(PokeGame gi, Abilities abilities) {
        for (int i = 0; i < team.length; i++) {
            if (team[i].isActive() && abilities.abilityType(team[i]).equals("NonCombat")) {
                if (team[i].isPlayerTarget) {

                    Player[] temp = new Player[team[i].getTarget().length];
                    for (int j = 0; j < team[i].getTarget().length; j++) {
                        temp[j] = gi.getPlayer(team[i].getTarget()[j]);
                    }

                    abilities.activateAbility(team[i], temp);
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
                itemResult += ItemType.printPM(i) + "(" + items[i] + ") ";
            }
        }
        if (!tms.isEmpty()) {
            itemResult += "TMs: [";
            for (int i = 0; i < tms.size(); i++) {
                itemResult += tms.get(i) + " ";
            }
            itemResult = itemResult.substring(0, itemResult.length() - 1) + "]";
        }

        return itemResult;
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
        return pb;
    }

    public String dataDump() {
//        public enum RH {
//
//            PNAME, AONE, ATWO, F, ALIGN, ROLE, TYPE,
//            LOC, RIV, ACT, TAR1, SPACT, TAR2, MV,
//            P1, S1, N1, H1, ST1, A1, T1, //Pokemon 1
//            P2, S2, N2, H2, ST2, A2, T2, //Pokemon 2
//            P3, S3, N3, H3, ST3, A3, T3, //Pokemon 3
//            RE, LE, ER, LU, PB, FH, CA, TM, // Inventory
//            CAP, BOX
//        };
        String dataStream = "";
        dataStream += this.paName + ",";
        dataStream += this.alias1 + ",";
        dataStream += this.alias2 + ",";
        dataStream += this.faction + ",";
        dataStream += this.alignment + ",";
        dataStream += this.role + ",";
        dataStream += this.ability + ",";
        dataStream += this.location + ",";
        if(this.rival != null)
            dataStream += this.rival.alias2;
        dataStream += ",";
        dataStream += ",";
        dataStream += ",";
        dataStream += this.sAbility + ",";
        dataStream += ",";
        dataStream += ",";
        for (Pokemon p : this.getTeam()) {
            dataStream += p.dataDump();
        }
        for (int i = 0; i < this.items.length; i++) {
            dataStream += items[i] + ",";
        }
        for (int i = 0; i < tms.size(); i++) {
            dataStream += tms.get(i);
                if(i < tms.size()-1)
                    dataStream += "|";
                else
                    dataStream += ",";
                    
        }
        for (int i = 0; i < captured.size(); i++){
            dataStream += captured.get(i).secDataDump();
            if(i < captured.size()-1)
                    dataStream += "|";
                else
                    dataStream += ",";
        }
        for (int i = 0; i < box.size(); i++){
            dataStream += box.get(i).secDataDump();
            if(i < box.size()-1)
                    dataStream += "|";
                else
                    dataStream += ",";
        }
        return dataStream;
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

    public void setLure(String l) {
        this.lure = l;
    }
}
