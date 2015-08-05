package pokeRunner;

import java.util.ArrayList;
import java.util.Arrays;

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

    public ArrayList<Player> process(PokeGame gameInfo, Abilities abilities){
        for (Order o: orders) {
            switch(o.action){
                case "Move":
                    gameInfo.getPlayer(o.subject).setLocation(Locations.valueOf(o.predicate[0]));
                    break;
                case "Challenge":
                    Battle b = new Battle();
                    Player attacker = gameInfo.getPlayer(o.subject);
                    Player defender = gameInfo.getPlayer(o.predicate[0]);
                    b.challenge(attacker, defender, abilities, gameInfo);
                    break;
                case "Explore":
                    if(o.predicate[0].equalsIgnoreCase("current"))
                        gameInfo.getPlayer(o.subject).results.add(gameInfo.getPlayer(o.subject).location.explore());
                    else
                        gameInfo.getPlayer(o.subject).results.add(Locations.valueOf(o.predicate[0]).explore());
                    if(gameInfo.getPlayer(o.subject).ability.equals(TrainerAbilities.EXPLORER)){
                        Generator g = new Generator(gameInfo.pokedex);
                        g.getItems(gameInfo.getPlayer(o.subject), 1);
                    }        
                    break;
                case "Capture":
                    Generator g = new Generator(gameInfo.pokedex);
                    g.getPokemon(gameInfo.getPlayer(o.subject));
                    break;
                case "Rest":
                    break;
                case "TradeItem":
                    break;
                case "TradePokemon":
                    break;
                case "UseItem":
                    break;
                    
                default:
                    break;
                }
            }

        return gameInfo.players;
    }

    public void addOrder(String[] order) {
        String[] p = Arrays.copyOfRange(order, 2, order.length);
        orders.add(new Order(order[0], order[1], p));		
    }
	
	
}
