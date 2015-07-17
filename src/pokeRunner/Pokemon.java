package pokeRunner;

public class Pokemon {
	
	public PDEntry pdEntry;
	public String cName;
	public Player trainer;
	
	public Typings tOne;
	public Typings tTwo;
	public Typings tAbility;

	//order information
	public Pokemon[] poTarget;
	public Player[] plTarget;
	
	public boolean isPlayerTarget;
	
	public boolean shiny;
	public int happiness;
	public int happyMod;
	
	//statuses
	public boolean guarded;
	public boolean paralyzed;
	public boolean paralyzedActive;
	public boolean burned;
	public boolean frozen;
	public int fTime;
	public boolean poisoned;
	public boolean knockedOut;
        
        public Pokemon(String[] readerData, Pokedex pd){
		pdEntry = pd.getEntry(Integer.parseInt(readerData[0]));
	}
        
        public Pokemon(PDEntry pd){
            pdEntry = pd;
            initializeStatus();
        }
        
        private void initializeStatus(){
            loadStatus(new String[]{});
        }
        
        private void loadStatus(String[] statusData){
            
        }
        
        public String printPM(){
		String d = "";
		d += "[img]http://www.serebii.net/";
		if(shiny)
			d += "SHINY/XY/"; 
		else
			d += "xy/pokemon/";
		d += pdEntry.number + ".png[/img][br]";
		d += "[b]" + cName + "[/b] - " + pdEntry.name.toUpperCase();
		d += " ([b]" + tOne.description(tOne.ordinal()) + "/" + tTwo.description(tTwo.ordinal()) + "[/b])[br]";
		d += "[indent][i]Status[/i] = " + statusPM() + "[br]";
		d += "[i]Happiness[/i] = " + happinessPM() + "[br]";
//		[b]Synthesis[/b] - Will find two random items each night.[/indent] //Ability Info
//		[b]Synthesis[/b] - Will find two random items each night.[/indent] //If Legendary or Ditto
		d += "-----------------------------[br]";	
		return d;
	}
	
	public String printBoxPM(){
		String bpm = "";
		
		bpm += "[b]" + cName + "[/b] - " + pdEntry.name.toUpperCase();
		bpm += " ([b]" + tOne.description(tOne.ordinal()) + "/" + tTwo.description(tTwo.ordinal()) + "[/b]) ";
		//bpm += ABILITY NAME HERE / SPABILITY NAME HERE
		return bpm;
	}
	
	public String statusPM(){
		String s = "";
		
		return s;
	}
	
	public String happinessPM(){
		String[] hString = {"Dead", "Detest", "Scared", "Neutral", "Friendly", "Very Close", "Favorite!"};
		return hString[happiness];
	}
	
	public void updateHappiness(int num){
		happiness += num;
		if(happiness < 1)
			happiness = 1;
		else if (happiness > 5)
			happiness = 5;
	}
	
	public void makeFavorite(){
		happiness = 6;
	}
	
	public Object[] getTarget(){
		if (plTarget == null)
			return poTarget;
		else
			return plTarget;
	}
	
	public boolean isActive(){
		if(!paralyzedActive && !frozen && !knockedOut)
			return true;
		else
			return false;
	}
	
	public void clearStatus(){
		paralyzed = false;
		paralyzedActive = false;
		burned = false;
		frozen = false;
		fTime = 0;
		poisoned = false;
	}
	
	public void freeze(int time){
		if(!guarded) {
			frozen = true;
			fTime = time;
		}
			
	}
	
	public void burn(){
		if(!guarded)
			burned = true;
	}
	
	public void paralyze(){
		if(!guarded && !paralyzedActive) {
			paralyzed = true;
			paralyzedActive = false;
		}
	}
	
	public void poison(){
		if(!guarded)
			poisoned = true;
	}

	public PDEntry getPdEntry() {
		return pdEntry;
	}

	public void setPdEntry(PDEntry pdEntry) {
		this.pdEntry = pdEntry;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public Player getTrainer() {
		return trainer;
	}

	public void setTrainer(Player trainer) {
		this.trainer = trainer;
	}

	public Typings gettOne() {
		return tOne;
	}

	public void settOne(Typings tOne) {
		this.tOne = tOne;
	}

	public Typings gettTwo() {
		return tTwo;
	}

	public void settTwo(Typings tTwo) {
		this.tTwo = tTwo;
	}

	public Typings gettAbility() {
		return tAbility;
	}

	public void settAbility(Typings tAbility) {
		this.tAbility = tAbility;
	}

	public Pokemon[] getPoTarget() {
		return poTarget;
	}

	public void setPoTarget(Pokemon[] poTarget) {
		this.poTarget = poTarget;
	}

	public Player[] getPlTarget() {
		return plTarget;
	}

	public void setPlTarget(Player[] plTarget) {
		this.plTarget = plTarget;
	}

	public boolean isPlayerTarget() {
		return isPlayerTarget;
	}

	public void setPlayerTarget(boolean isPlayerTarget) {
		this.isPlayerTarget = isPlayerTarget;
	}

	public boolean isShiny() {
		return shiny;
	}

	public void setShiny(boolean shiny) {
		this.shiny = shiny;
	}

	public int getHappiness() {
		return happiness;
	}

	public void setHappiness(int happiness) {
		this.happiness = happiness;
	}

	public int getHappyMod() {
		return happyMod;
	}

	public void setHappyMod(int happyMod) {
		this.happyMod = happyMod;
	}

	public boolean isGuarded() {
		return guarded;
	}

	public void setGuarded(boolean guarded) {
		this.guarded = guarded;
	}

	public boolean isParalyzed() {
		return paralyzed;
	}

	public void setParalyzed(boolean paralyzed) {
		this.paralyzed = paralyzed;
	}

	public boolean isParalyzedActive() {
		return paralyzedActive;
	}

	public void setParalyzedActive(boolean paralyzedActive) {
		this.paralyzedActive = paralyzedActive;
	}

	public boolean isBurned() {
		return burned;
	}

	public void setBurned(boolean burned) {
		this.burned = burned;
	}

	public boolean isFrozen() {
		return frozen;
	}

	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
	}

	public int getfTime() {
		return fTime;
	}

	public void setfTime(int fTime) {
		this.fTime = fTime;
	}

	public boolean isPoisoned() {
		return poisoned;
	}

	public void setPoisoned(boolean poisoned) {
		this.poisoned = poisoned;
	}

	public boolean isKnockedOut() {
		return knockedOut;
	}

	public void setKnockedOut(boolean knockedOut) {
		this.knockedOut = knockedOut;
	}
}
