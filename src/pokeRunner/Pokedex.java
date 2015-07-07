package pokeRunner;

import java.util.ArrayList;
import java.util.Arrays;

public class Pokedex {
        
        public enum PokedexColumns {SNUM, RNUM, PNAME, RNUMTWO, POWERLVL,
                                CAPTYPE, EVOPOSS, BASE, CANEVO, EVOTO, FIRST,
                                CANEVOTWO, EVOTOTWO, SECOND, NORMALSPRITE,	
                                TYPEONE, TYPETWO, HITPOINTS, ATK, DEFENSE, SPATK,
                                SPDEF, SPEED}
	
	public ArrayList<PDEntry> pokedex;
	public ArrayList<Integer> legendaries;
	public double[][] typeChart;
	
	public Pokedex(){
		pokedex = new ArrayList<PDEntry>();
		buildLegendaries();
		buildTypeChart();
	}
	
	public void buildLegendaries(){
		legendaries = new ArrayList<Integer>(
						Arrays.asList(	493, 144, 482, 251, 638, 488, 491, 386, 483, 718,
								244, 649, 487, 383, 485, 250, 385, 647, 382, 646,
								645, 380, 381, 249, 490, 648, 481, 151, 150, 146, 
								484, 243, 384, 378, 486, 377, 379, 643, 492, 245, 
								639, 642, 641, 480, 494, 640, 716, 717, 145, 644));
	}
	
	public void buildTypeChart(){
		
		typeChart = new double[18][18];
		typeChart[0] = new double[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0.5, 0, 1, 1, 0.5, 1};
		typeChart[1] = new double[] {1, 0.5, 0.5, 1, 2, 2, 1, 1, 1, 1, 1, 2, 0.5, 1, 0.5, 1, 2, 1};
		typeChart[2] = new double[] {1, 2, 0.5, 1, 0.5, 1, 1, 1, 2, 1, 1, 1, 2, 1, 0.5, 1, 1, 1};
		typeChart[3] = new double[] {1, 1, 2, 0.5, 0.5, 1, 1, 1, 0, 2, 1, 1, 1, 1, 0.5, 1, 1, 1};
		typeChart[4] = new double[] {1, 0.5, 2, 1, 0.5, 1, 1, 0.5, 2, 0.5, 1, 0.5, 2, 1, 0.5, 1, 0.5, 1};
		typeChart[5] = new double[] {1, 0.5, 0.5, 1, 2, 0.5, 1, 1, 2, 2, 1, 1, 1, 1, 2, 1, 0.5, 0.5};
		typeChart[6] = new double[] {2, 1, 1, 1, 1, 2, 1, 0.5, 1, 0.5, 0.5, 0.5, 2, 0, 1, 2, 2, 0.5};
		typeChart[7] = new double[] {1, 1, 1, 1, 2, 1, 1, 0.5, 0.5, 1, 1, 1, 0.5, 0.5, 1, 1, 0, 1};
		typeChart[8] = new double[] {1, 2, 1, 2, 0.5, 1, 1, 2, 1, 0, 1, 0.5, 2, 1, 1, 1, 2, 1};
		typeChart[9] = new double[] {1, 1, 1, 0.5, 2, 1, 2, 1, 1, 1, 1, 2, 0.5, 1, 1, 1, 0.5, 1};
		typeChart[10] = new double[] {1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 0.5, 1, 1, 1, 1, 0, 0.5, 1};
		typeChart[11] = new double[] {1, 0.5, 1, 1, 2, 1, 0.5, 0.5, 1, 0.5, 2, 1, 1, 0.5, 1, 2, 0.5, 0.5};
		typeChart[12] = new double[] {1, 2, 1, 1, 1, 2, 0.5, 1, 0.5, 2, 1, 2, 1, 1, 1, 1, 0.5, 1};
		typeChart[13] = new double[] {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 0.5, 1, 1};
		typeChart[14] = new double[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 0.5, 0};
		typeChart[15] = new double[] {1, 1, 1, 1, 1, 1, 0.5, 1, 1, 1, 2, 1, 1, 2, 1, 0.5, 1, 0.5};
		typeChart[16] = new double[] {1, 0.5, 0.5, 0.5, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 0.5, 2};
		typeChart[17] = new double[] {1, 0.5, 1, 1, 1, 1, 2, 0.5, 1, 1, 1, 1, 1, 1, 2, 2, 0.5, 1};

	}
	
	public void addEntry(String[] values){
		PDEntry newEntry = new PDEntry();
                newEntry.number = Integer.parseInt(values[PokedexColumns.RNUM.ordinal()]);
                newEntry.spriteNum = values[PokedexColumns.SNUM.ordinal()];
		newEntry.name = values[PokedexColumns.PNAME.ordinal()];
		newEntry.type1 = Typings.valueOf(values[PokedexColumns.TYPEONE.ordinal()]);
                if(!values[PokedexColumns.TYPETWO.ordinal()].isEmpty())
                    newEntry.type2 = Typings.valueOf(values[PokedexColumns.TYPETWO.ordinal()]);
		newEntry.captureType = values[PokedexColumns.CAPTYPE.ordinal()].charAt(0);
		
		//ability info
		newEntry.pLevel = Integer.parseInt(values[PokedexColumns.POWERLVL.ordinal()]);

		//evolution info
                if ( Integer.parseInt(values[PokedexColumns.BASE.ordinal()]) == 1) {
                    newEntry.isBase = true;
                    newEntry.evolLevel = 0;
                } else {
                    newEntry.isBase = false;
                    if ( Integer.parseInt(values[PokedexColumns.FIRST.ordinal()]) == 1)
                        newEntry.evolLevel = 1;
                    else
                        newEntry.evolLevel = 2;
                }
                if ( Integer.parseInt(values[PokedexColumns.CANEVO.ordinal()]) == 1 ) {
                    newEntry.canEvolve = true;
                    newEntry.evolTo = Integer.parseInt(values[PokedexColumns.EVOTO.ordinal()]);
                }
                if ( Integer.parseInt(values[PokedexColumns.CANEVOTWO.ordinal()]) == 1 ) {
                    newEntry.canEvolve = true;
                    newEntry.evolTo = Integer.parseInt(values[PokedexColumns.EVOTOTWO.ordinal()]);
                }
		
		//stats
		newEntry.hp = Integer.parseInt(values[PokedexColumns.HITPOINTS.ordinal()]);
		newEntry.attack = Integer.parseInt(values[PokedexColumns.ATK.ordinal()]);
		newEntry.defense = Integer.parseInt(values[PokedexColumns.DEFENSE.ordinal()]);
		newEntry.spAttack = Integer.parseInt(values[PokedexColumns.SPATK.ordinal()]);
		newEntry.spDefense = Integer.parseInt(values[PokedexColumns.SPDEF.ordinal()]);
		newEntry.speed = Integer.parseInt(values[PokedexColumns.SPEED.ordinal()]);
		pokedex.add(newEntry);
	}
	
}