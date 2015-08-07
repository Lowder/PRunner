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

    public void process(PokeGame gameInfo, Abilities abilities){
    	Generator g = new Generator(gameInfo.pokedex);
        for (Order o: orders) {
            switch(o.action){//subject, action, predicate0, predicate1, ..., predicatex
                case "Move": //player Move, location
                    gameInfo.getPlayer(o.subject).setLocation(Locations.valueOf(o.predicate[0]));
                    break;
                case "Challenge": //attacker,Challenge,defender,evolvenum,type
                    Battle b = new Battle();                 
                    b.challenge(gameInfo.getPlayer(o.subject), gameInfo.getPlayer(o.predicate[0]), abilities, gameInfo);
                    gameInfo.getPlayer(o.subject)
                    	.getTeam()[Integer.parseInt(o.predicate[1])]
            				.evolve(gameInfo.pokedex, Typings.valueOf(o.predicate[2]));
                    break;
                case "Explore": //explorer, Explore, current||location
                    if(o.predicate[0].equalsIgnoreCase("current"))
                        gameInfo.getPlayer(o.subject).results.add(gameInfo.getPlayer(o.subject).location.explore());
                    else
                        gameInfo.getPlayer(o.subject).results.add(Locations.valueOf(o.predicate[0]).explore());
                    if(gameInfo.getPlayer(o.subject).ability.equals(TrainerAbilities.EXPLORER)){
                        g.getItems(gameInfo.getPlayer(o.subject), 1);
                    }
                    for(int i = 0; i < gameInfo.getPlayer(o.subject).team.length; i++){
                    	gameInfo.getPlayer(o.subject).getTeam()[i].updateHappiness(1);
                    }
                    break;
                case "Capture": //player, Capture
                    g.getPokemon(gameInfo.getPlayer(o.subject));
                    break;
                case "Rest": //player, Rest
                	for(int i = 0; i < gameInfo.getPlayer(o.subject).team.length; i++){
                		gameInfo.getPlayer(o.subject).getTeam()[i].clearStatus();
                		gameInfo.getPlayer(o.subject).getTeam()[i].setKnockedOut(false);
                		g.getItems(gameInfo.getPlayer(o.subject), 1);
                	}
                    break;
                case "TradeItem"://player, TradeItem, item, player, num
                	
                    break;
                case "TradePokemon"://player, TradePokemon, player
                    break;
                case "BoxSwap"://player, UseBox, teamNum, boxpokemonNum
                	break;
                case "BoxStore"://player, UseBox, captureNum
                	break;
                case "CaptureChoice"://player, CaptureChoice, captureNum, name, box||team, teamNum
                	//set happiness for Collectors here
                	break;
                case "UseItem"://player, UseItem, item, target<int>, parameter(s)
                	if(gameInfo.getPlayer(o.subject).getItems()[ItemType.valueOf(o.predicate[0]).ordinal()] > 0){
                		String target;
                		if(o.predicate.length < 2)
                			target = "";
                		else
                			target = o.predicate[2];
                		ItemType.valueOf(o.predicate[0]).useItem(gameInfo, gameInfo.getPlayer(o.predicate[1]), target);
                		gameInfo.getPlayer(o.subject).results.add("Used " + ItemType.valueOf(o.predicate[0]).toString());
                	} else {
                		gameInfo.getPlayer(o.subject).results.add("Unable to use " + ItemType.valueOf(o.predicate[0]).toString());
                	}
                    break;
                    
                default:
                    break;
            }
        }
    }

    public void addOrder(String[] order) {
        String[] p = Arrays.copyOfRange(order, 2, order.length);
        orders.add(new Order(order[0], order[1], p));		
    }
	
	
}
