package pokeRunner;

import java.util.ArrayList;

public class Orders {
	
	public class Order{
		public String subject;
		public String action;
		public String[] predicate;
		
		public Order(String s, String a, String[] p){
			subject = s;
			action = a;
			predicate = p;
		}
	}
	
	public ArrayList<Order> orders;
	
	public Orders (){
		orders = new ArrayList<Order>();
	}
	
	public ArrayList<Player> process(ArrayList<Player> players, Abilities abilities, PokeGame gameInfo){
		return players;
	}
	
	public void addOrder(String s, String a, String[] p){
		orders.add(new Order(s, a, p));		
	}

}
