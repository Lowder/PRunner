package pokeRunner;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PokeWriter {

    FileWriter writer;

    public PokeWriter() {

    }

    public void writePlayers(PokeGame gameInfo) {
        try {
            PrintWriter writer = new PrintWriter("Day" + gameInfo.day + "\\PLAYERS" + gameInfo.day + ".csv", "UTF-8");
            String header = "";
            for (Player.RH rh : Player.RH.values()) {
                header += rh.toString() + ", ";
            }
            writer.println(header.substring(0, header.length() - 2)); //Header
            for (Player p : gameInfo.getPlayers()) {
                writer.println(p.dataDump());
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writePMs(PokeGame gameInfo) {
        // TODO Auto-generated method stub
        try {
            PrintWriter writer = new PrintWriter("Day" + gameInfo.day + "\\STATUSPM" + gameInfo.day + ".csv", "UTF-8");
            writer.println("PA Forum,Private Message" + '\n');
            for (Player p : gameInfo.getPlayers()) {
                String newline = "[br]";
                String statusPM = "";
                statusPM += p.paName + ",";
                statusPM += "Day " + (gameInfo.day + 1) + " Status PM for [b]" + p.paName + "[/b]" + newline;
                statusPM += "[size=4][b][u]NIGHT " + gameInfo.day + " RESULTS[/b][/u][/size][indent]" + newline;
                for (String s : p.results) {
                    statusPM += s + newline;
                }
                if (!p.captured.isEmpty()) {
                    statusPM += "DECIDE WHAT TO DO WITH THE FOLLOWING POKEMON:" + newline;
                    for (int i = 0; i < p.captured.size(); i++) {
                        statusPM += p.captured.get(i).printCapPM(gameInfo);
                    }
                }
                statusPM += "[/indent]" + newline;
                statusPM += "[size=4][b][u]CURRENT STATUS[/b][/u][/size]" + newline;
                statusPM += "[b]Trainer Name[/b] - " + p.alias2;
                if (p.alias2 != p.alias1) {
                    statusPM += " (AKA: " + p.alias1 + ")";
                }
                statusPM += "[br]";
                statusPM += "[indent][i]Location[/i] - " + p.location + "[br]";
                statusPM += "[i]Items[/i] - " + p.printItemsPM() + "[/indent][br]";
                statusPM += "[b]Trainer Team[/b] - " + p.getFaction() + "[br]";
                statusPM += "[b]Wincon[/b] - " + p.getWinconPM() + "[br]";
                statusPM += "[b]Trainer Abilities[/b][indent][br]";
                statusPM += "[b]" + p.ability + "[/b] - " + p.ability.description(p.ability.ordinal()) + "[br]";
                if (p.sAbility != null) {
                    statusPM += "[b]" + p.sAbility + "[/b] - " + p.sAbility.description(p.sAbility.ordinal()) + "[br]";
                }
                statusPM += "[/indent][br]";
                statusPM += "[b]CURRENT TEAM[/b][br]----------------------------- -----------------------------[br]";
                statusPM += "*First Position*[br]";
                statusPM += p.team[0].printPM(gameInfo);
                statusPM += "-----------------------------[br]";
                statusPM += "*Second Position*[br]";
                statusPM += p.team[1].printPM(gameInfo);
                statusPM += "-----------------------------[br]";
                statusPM += "*Third Position*[br]";
                statusPM += p.team[2].printPM(gameInfo);
                statusPM += "----------------------------- ";
                statusPM += "-----------------------------[br]";
                statusPM += "[b]POKEBOX[/b][br]-----------------------------[br]";
                statusPM += p.printPokeBox();
                statusPM += "----------------------------- ";
                statusPM += "-----------------------------[br]";
                writer.println(statusPM);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeResults(PokeGame gameInfo) {
        // TODO Auto-generated method stub
        try {
            PrintWriter writer = new PrintWriter("Day" + gameInfo.day + "\\RESULTS" + gameInfo.day + ".txt", "UTF-8");
            writer.println("Results" + '\n');
            for (Player p : gameInfo.getPlayers()) {
                writer.println(p.paName + "Results:");
                for (String s : p.results) {
                    writer.println(s);
                }
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
