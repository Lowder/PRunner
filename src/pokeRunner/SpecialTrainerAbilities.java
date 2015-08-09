package pokeRunner;

public enum SpecialTrainerAbilities {

    PSYCHIC,
    COOLGUY,
    BULLY,
    THIEF,
    THUG;

    public String description(int i) {
        String d = "";
        switch (i) {
            case 0:
                d += "Target a player, you learn their trainer name(s).";
                break;
            case 1:
                d += "Target a player, prevent all abilities targeting them or their Trainer.";
                break;
            case 2:
                d += "Target a player and remove them from the thread.";
                break;
            case 3:
                d += "When you win a challenge against a trainer, you will steal one of their pokemon that have neutral or worse happiness.";
                break;
            case 4:
                d += "Target a trainer in your area, lower all of their pokemon's happiness by 1. If no target is given, all of your pokemon will lose 2 happiness.";
                break;
            default:
                break;
        }

        return d;
    }
    @Override
    public String toString(){
        return name().toString().substring(0, 1).toUpperCase() + name().toString().substring(1).toLowerCase();
    }
    
}
