package pokeRunner;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PokeWriter {

    FileWriter writer;

    public PokeWriter() {

    }

    public void writePlayers(PokeGame gameInfo) {
        // TODO Auto-generated method stub

    }

    public void writePMs(PokeGame gameInfo) {
        // TODO Auto-generated method stub
        try {
            PrintWriter writer = new PrintWriter("STATUSPM" + gameInfo.day + ".csv", "UTF-8");
            writer.println("PA Forum,Private Message" + '\n');
            for (Player p : gameInfo.players) {
                String newline = "[br]";
                writer.print(p.paName + ",");
                writer.print("Day " + gameInfo.day + 1 + "Status PM for [b]" + p.paName + "[/b]" + newline);
                writer.print("[size=4][b][u]NIGHT" + gameInfo.day + "RESULTS[/b][/u][/size][indent]" + newline);
                for (String s : p.results) {
                    writer.print(s + newline);
                }
                writer.print("[/indent]" + newline);
                writer.print("[size=4][b][u]CURRENT STATUS[/b][/u][/size]" + newline);
                writer.print("[b]Trainer Name[/b] - " + p.alias2);
                if (p.alias2 != p.alias1) {
                    writer.print(" (AKA: " + p.alias1 + ")");
                }
                writer.print("[br]");
                writer.print("[indent][i]Location[/i] - " + p.location + "[br]");
                writer.print("[i]Items[/i] - " + p.printItemsPM() + "[/indent][br]");
                writer.print("[b]Trainer Team[/b] - " + p.getFaction() + "[br]");
                writer.print("[b]Wincon[/b] - " + p.getWinconPM() + "[br]");
                writer.print("[b]Trainer Abilities[/b][indent][br]");
                writer.print("[b]" + p.ability + "[/b] - " + p.ability.description(p.ability.ordinal()) + "[br]");
                if (!p.sAbility.equals("")) {
                    writer.print("[b]" + p.sAbility + "[/b] - " + p.sAbility.description(p.sAbility.ordinal()) + "[br]");
                }
                writer.print("[/indent][br]");
                writer.print("[b]Pokemon Team[/b][br]-----------------------------[br]-----------------------------[br]");
                writer.print("*First Position*[br]");
                writer.print(p.team[0].printPM());
                writer.print("-----------------------------[br]");
                writer.print("*Second Position*[br]");
                writer.print(p.team[1].printPM());
                writer.print("-----------------------------[br]");
                writer.print("*Third Position*[br]");
                writer.print(p.team[2].printPM());
                writer.print("-----------------------------[br]");
                writer.print("-----------------------------[br]");
                writer.print("[b]PokeBox[/b][br]-----------------------------[br]-----------------------------[br]");
                writer.print(p.printPokeBox());
                writer.print("-----------------------------[br]");
                writer.print("-----------------------------[br]");
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeResults(PokeGame gameInfo) {
        // TODO Auto-generated method stub
        try {
            PrintWriter writer = new PrintWriter("Results" + gameInfo.day + ".txt", "UTF-8");
            writer.println("Results" + '\n');
            for (Player p : gameInfo.players) {
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
