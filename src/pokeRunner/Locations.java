/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pokeRunner;

/**
 *
 * @author Steven Lowder
 */
public enum Locations {
    STEAMYPONDS ("SteamyPonds"),
    DESTROYEDFACTORY ("Destroyed Factory"),
    SHADOWYTOWER ("Shadowy Tower"),
    CLOUDYHEIGHTS ("Cloudy Heights"),
    FROZENDEPTHS ("Frozen Depths"),
    BUZZINGGRASSLAND ("Buzzing Grassland"),
    CRUMBLINGMOUNTAIN ("Crumbling Mountain"),
    ABANDONEDRUINS ("Abandoned Ruins"),
    MISTYSWAMP ("Misty Swamp"),
    TOWEROFCHAMPIONS ("Tower of Champions");
    
    private final String pmName;
    
    Locations(String n){
        pmName = n;
    }
    
    @Override
    public String toString(){
        return pmName;
    }
    
    public String explore(){
        return "test";
    }
}
