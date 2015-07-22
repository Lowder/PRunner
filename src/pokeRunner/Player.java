package pokeRunner;

import java.util.ArrayList;

public class Player {
        
        public enum RH {PNAME, AONE, ATWO, F, ALIGN, ROLE, TYPE,
                            LOC, ACT, TAR1, SPACT, TAR2, MV, 
                            P1, S1, N1, H1, ST1, A1, T1, 
                            P2, S2, N2, H2, ST2, A2, T2,
                            P3, S3, N3, H3, ST3, A3, T3};

	public String faction;
	public String alignment;
	public Player rival;
	
	public Pokemon[] team;
	public ArrayList<Pokemon> box;
	public ArrayList<Pokemon> captured;
	
	public String paName;
	public String alias1;
	public String alias2;
	
	public int[] items;
	public ArrayList<String> tms;
	public String location;
	
	public int avoidChallenge;
	
	public TrainerAbilities ability;
	public SpecialTrainerAbilities sAbility;
	
	public int underground;
	
	public ArrayList<String> results;
        
        public Player (String[] playerInfo, Pokedex pd){
         
            
            faction = playerInfo[RH.F.ordinal()];
            alignment = playerInfo[RH.ALIGN.ordinal()];
            //rival = ;

            team = new Pokemon[3];
            addTeam(playerInfo, pd);
//            public ArrayList<Pokemon> box;
            captured = new ArrayList<>();
//
            paName = playerInfo[RH.PNAME.ordinal()];
            alias1 = playerInfo[RH.AONE.ordinal()];
            alias2 = playerInfo[RH.ATWO.ordinal()];
//
//            public int[] items;
//            public ArrayList<String> tms;
            location = playerInfo[RH.LOC.ordinal()];
            avoidChallenge = 0;
            ability = TrainerAbilities.valueOf(playerInfo[RH.TYPE.ordinal()]);
            sAbility = SpecialTrainerAbilities.valueOf(playerInfo[RH.SPACT.ordinal()]);
            underground = 0;
//
            results = new ArrayList<>();
        }
        
        private void addTeam(String[] pokemonTeam, Pokedex pd){
            int pDataSize = 7;
            int startColumn = 13;
            for (int i = 0; i < 3; i++){
                String[] pokemon = new String[7];
                System.arraycopy(pokemonTeam, i*pDataSize+startColumn, pokemon, 0, pDataSize);
                addPokemonToTeam(pokemon, i, pd);
            }
        }
        
        public void addPokemonToTeam(String[] pokeData, int pos, Pokedex pd){
            Pokemon poke = new Pokemon(pokeData, pd);
            team[pos] = poke;
        }

	
	public void pokeNonCombat(Abilities abilities){
            for (int i = 0; i < team.length; i++){
                if (team[i].isActive() && abilities.abilityType(team[i]).equals("NonCombat")){
                    if (team[i].isPlayerTarget)
                        abilities.activateAbility(team[i], team[i].getTarget());
                }

            }
	}
	
	public String teamSeer(){
            String seerResult = "";
            seerResult += alias2;
            seerResult += " has the following team:";
            for (int i = 0; i < team.length; i++)
                seerResult += team[i].pdEntry.name + ", ";
            return seerResult.substring(0, seerResult.length()-2);
	}
	
	public String abilitySeer(String type){
            if("type".equalsIgnoreCase("rand"))
                return "One ability chosen at random";
            else
                return "Put ability seer info here";
	}
	
	public String printItemsPM(){
            String itemResult = "";
            for(int i = 0; i < items.length; i++){
                if(items[i] > 0)
                    itemResult += ItemType.printPM(i) + "(" + items[i] + "), ";
            }
            if(!tms.isEmpty()) {
                itemResult += "TMs: [";
                for(int i = 0; i < tms.size(); i++)
                    itemResult += tms.get(i) + ", ";
                itemResult = itemResult.substring(0, itemResult.length()-2) + "]  ";
            }

            return itemResult.substring(0, itemResult.length()-2);
	}
	
	public String getWinconPM(){
            String pmInfo = "";
            switch (faction){
                case "Faction1":
                case "Faction2":
                case "Faction3":
                    pmInfo += "Your Trainer Team is the first to complete the following steps, in order, as a group:[indent][br]";
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
                default:
                    break;
            }
            return pmInfo;
	}
	
	public String printPokeBox(){
            String pb = "";
            for (Pokemon p: box) {
                pb += p.printBoxPM() + "[br]";
            }	
            return pb.substring(0, pb.length());
	}
	
	public String printFactionPM(){
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
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
}
