package pokeRunner;

public enum ItemType {
    REVIVE, LUCKYEGG, ESCAPEROPE, LURE, POKEBALLBELT, FULLHEAL, CANDY;

    public static String printPM(int i){
        return ItemType.values()[i].toString().substring(0, 1) +ItemType.values()[i].toString().substring(1).toLowerCase();
    }
}
