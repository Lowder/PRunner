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
    MISTYSWAMP ("Misty Swamp");
    
    private final String name;
    
    Locations(String n){
        name = n;
    }
    
    @Override
    public String toString(){
        return name;
    }
    
    public String explore(){
        return "test";
    }
}
