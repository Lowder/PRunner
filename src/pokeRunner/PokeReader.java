package pokeRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PokeReader {

	public BufferedReader br;
	public String line = "";
	public String csvSplitBy = ",";
        
        private String directory = "C:\\Users\\Steven Lowder\\Documents\\PA Stuff\\Pokemafia\\";
	
	public PokeReader(){
		
	}
	
	public void openFile(String fileName){
            br = null;
            try {
                br = new BufferedReader(new FileReader(fileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
	}
        
        public void closeReader() {
           if (br != null) {
               try {
                   br.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
        }
	
	public PokeGame getConfig(){
		openFile("Config.csv");
		PokeGame game = new PokeGame();
		try {
                    while ((line = br.readLine()) != null) {

                        // use comma as separator
                        String[] gameInfo = line.split(csvSplitBy);
                        game.day = Integer.parseInt(gameInfo[0]);
                    }
		}catch (IOException e) {
			e.printStackTrace();
		}
		closeReader();
		return new PokeGame();
	}
	
	public ArrayList<Player> getPlayers(Pokedex dex, PokeGame gameInfo){
            openFile("Current.csv");

            try {
                while ((line = br.readLine()) != null) {
                    // use comma as separator
                    String[] playerInfo = line.split(csvSplitBy);

                    System.out.println("Country [code= " + playerInfo[4] 
                         + " , name=" + playerInfo[5] + "]");
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
            closeReader();
            return new ArrayList<Player>();
	}
	
	public Orders getOrders(PokeGame gameInfo){
            openFile("Orders.csv");
            return new Orders();
	}
	
	public Pokedex getPokedex(){
            //openFile(directory + "Pokedex.csv");
            openFile("Pokedex.csv");
            Pokedex pokedex = new Pokedex();
            try {
                br.readLine();
                while ((line = br.readLine()) != null) {	 
                    // use comma as separator
                    pokedex.addEntry(line.split(csvSplitBy));
                }
            }catch (IOException e) {
                    e.printStackTrace();
            }
            closeReader();
            return pokedex;
	}
}