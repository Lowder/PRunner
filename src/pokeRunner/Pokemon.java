package pokeRunner;

public class Pokemon {
	
	public String pdNumber;
	public String cName;
	
	public Typings tOne;
	public Typings tTwo;
	public Typings tAbility;

	//order information
	public Pokemon[] poTarget;
	public Player[] plTarget;
	
	public boolean isPlayerTarget;
	
	public boolean shiny;
	public int happiness;
	
	//statuses
	public boolean paralyzed;
	public boolean burned;
	public boolean frozen;
	public int fTime;
	public boolean poisoned;
	public boolean knockedOut;
	
	public Object[] getTarget(){
		if (plTarget == null)
			return poTarget;
		else
			return plTarget;
	}
	
	public boolean isActive(){
		if(!paralyzed && !frozen && !knockedOut)
			return true;
		else
			return false;
	}
}
