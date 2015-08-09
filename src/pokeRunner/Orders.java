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
                        gameInfo.getPlayer(o.subject).getResults().add(gameInfo.getPlayer(o.subject).location.explore());
                    else
                        gameInfo.getPlayer(o.subject).getResults().add(Locations.valueOf(o.predicate[0]).explore());
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
                	if(o.predicate[0].substring(0, 2) == "TM"){
                		int index = gameInfo.getPlayer(o.subject).getTms().indexOf(o.predicate[0]);
                		gameInfo.getPlayer(o.predicate[1]).getTms().add(gameInfo.getPlayer(o.subject).getTms().remove(index));
                	} else {
                		gameInfo.getPlayer(o.subject).getItems()[ItemType.valueOf(o.predicate[0]).ordinal()] -= Integer.parseInt(o.predicate[2]);
                		gameInfo.getPlayer(o.predicate[1]).getItems()[ItemType.valueOf(o.predicate[0]).ordinal()] -= Integer.parseInt(o.predicate[2]);
                	}
                    break;
                case "GiftPokemon"://player, TradePokemon, box||team||captured, num, player
                	if(o.predicate[0] == "box"){
                		gameInfo.getPlayer(o.predicate[2]).getCaptured().add(
                				gameInfo.getPlayer(o.subject).getBox().remove(Integer.parseInt(o.predicate[1])));
                	} else if(o.predicate[0] == "captured"){ 
                		gameInfo.getPlayer(o.predicate[2]).getCaptured().add(
                				gameInfo.getPlayer(o.subject).getCaptured().remove(Integer.parseInt(o.predicate[1])));
                	} else {
                		gameInfo.getPlayer(o.predicate[2]).getCaptured().add(
                				gameInfo.getPlayer(o.subject).getTeam()[Integer.parseInt(o.predicate[1])]);
                		gameInfo.getPlayer(o.subject).setTeam(Integer.parseInt(o.predicate[1]), null);
                	}
                	gameInfo.getPlayer(o.predicate[2]).getResults().add("Someone gifted you a pokemon!");
                    break;
                case "PokemonSwap": //player, PokemonSwap, team||box, num, player, team||box, num
                	if(o.predicate[0] == "team"){
                		Pokemon m1 = gameInfo.getPlayer(o.subject).getTeam()[Integer.parseInt(o.predicate[1])];
                		if(o.predicate[3] == "team"){
                			gameInfo.getPlayer(o.subject).setTeam(
                					Integer.parseInt(o.predicate[1]), 
                					gameInfo.getPlayer(o.predicate[2]).getTeam()[Integer.parseInt(o.predicate[4])]);
                			gameInfo.getPlayer(o.predicate[2]).setTeam(Integer.parseInt(o.predicate[4]), m1);
                		} else {
                			gameInfo.getPlayer(o.subject).setTeam(
                					Integer.parseInt(o.predicate[1]), 
                					gameInfo.getPlayer(o.predicate[2]).getBox().remove(Integer.parseInt(o.predicate[4])));
                			gameInfo.getPlayer(o.predicate[2]).getBox().add(m1);
                		}
                	} else {
                		Pokemon m1 = gameInfo.getPlayer(o.subject).getBox().remove(Integer.parseInt(o.predicate[1]));
                		if(o.predicate[3] == "team"){
                			gameInfo.getPlayer(o.subject).getBox().add( 
                					gameInfo.getPlayer(o.predicate[2]).getTeam()[Integer.parseInt(o.predicate[4])]);
                			gameInfo.getPlayer(o.predicate[2]).setTeam(Integer.parseInt(o.predicate[4]), m1);
                		} else {
                			gameInfo.getPlayer(o.subject).getBox().add(gameInfo.getPlayer(o.predicate[2]).getBox().remove(Integer.parseInt(o.predicate[4])));
                			gameInfo.getPlayer(o.predicate[2]).getBox().add(m1);
                		}
                	}
                	break;
                case "BoxSwap"://player, UseBox, teamNum, boxpokemonNum
                	if(gameInfo.getPlayer(o.subject).getTeam()[Integer.parseInt(o.predicate[0])] != null)
                		gameInfo.getPlayer(o.subject).getBox().add(gameInfo.getPlayer(o.subject).getTeam()[Integer.parseInt(o.predicate[0])]);
                		
                	gameInfo.getPlayer(o.subject).setTeam(
                		Integer.parseInt(o.predicate[0]), 
                		gameInfo.getPlayer(o.subject).getBox().remove(Integer.parseInt(o.predicate[1])));
                	break;
                case "BoxStore"://player, UseBox, captureNum
                	gameInfo.getPlayer(o.subject).getBox().add(gameInfo.getPlayer(o.subject).getCaptured().remove(Integer.parseInt(o.predicate[0])));
                	break;
                case "CaptureChoice"://player, CaptureChoice, captureNum, name, box||team, teamNum
                	if(gameInfo.getPlayer(o.subject).ability == TrainerAbilities.COLLECTOR){
                		gameInfo.getPlayer(o.subject).getCaptured().get(Integer.parseInt(o.predicate[0])).setHappiness(4);
                	}
                	if(o.predicate[2] == "box"){
                		gameInfo.getPlayer(o.subject).getBox().add(gameInfo.getPlayer(o.subject).getCaptured().remove(Integer.parseInt(o.predicate[0])));
                	} else {
                		gameInfo.getPlayer(o.subject).getBox().add(gameInfo.getPlayer(o.subject).getTeam()[Integer.parseInt(o.predicate[3])]);
                		gameInfo.getPlayer(o.subject).setTeam(
                				Integer.parseInt(o.predicate[3]), 
                				gameInfo.getPlayer(o.subject).getCaptured().remove(Integer.parseInt(o.predicate[0])));
                	}
                	break;
                case "UseItem"://player, UseItem, item, target<int>, parameter(s)
                	if(gameInfo.getPlayer(o.subject).getItems()[ItemType.valueOf(o.predicate[0]).ordinal()] > 0){
                		String target;
                		if(o.predicate.length < 2)
                			target = "";
                		else
                			target = o.predicate[2];
                		ItemType.valueOf(o.predicate[0]).useItem(gameInfo, gameInfo.getPlayer(o.predicate[1]), target);
                		gameInfo.getPlayer(o.subject).getResults().add("Used " + ItemType.valueOf(o.predicate[0]).toString());
                	} else {
                		gameInfo.getPlayer(o.subject).getResults().add("Unable to use " + ItemType.valueOf(o.predicate[0]).toString());
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
