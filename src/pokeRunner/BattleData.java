package pokeRunner;

public class BattleData {

	private Player attacker;
	private Player defender;
	private double[] aValues;
	private double[] dValues;
	
	private boolean[] aKO;
	private boolean[] dKO;
	
	public BattleData(Player a, Player d){
		attacker = a;
		defender = d;
		aValues = new double[]{1,1,1};
		dValues = new double[]{1,1,1};
		aKO = new boolean[]{false, false, false};
		dKO = new boolean[]{false, false, false};
	}

	public Player getAttacker() {
		return attacker;
	}

	public void setAttacker(Player attacker) {
		this.attacker = attacker;
	}

	public Player getDefender() {
		return defender;
	}

	public void setDefender(Player defender) {
		this.defender = defender;
	}

	public double[] getaValues() {
		return aValues;
	}

	public void setaValues(double[] aValues) {
		this.aValues = aValues;
	}

	public double[] getdValues() {
		return dValues;
	}

	public void setdValues(double[] dValues) {
		this.dValues = dValues;
	}

	public boolean[] getaKO() {
		return aKO;
	}

	public void setaKO(boolean[] aKO) {
		this.aKO = aKO;
	}

	public boolean[] getdKO() {
		return dKO;
	}

	public void setdKO(boolean[] dKO) {
		this.dKO = dKO;
	}
	
}
