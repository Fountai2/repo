package main.java;

public class Monster {
    private int hitpoints;
    private final String name;
    private int armorclass;
    private int charisma;
    private int intelligence;
    private int strength;
    private int wisdom;
    private int consitution;
    private int dexterity;

    public Monster(int hitpoints, String name, int armorclass) {
        this.hitpoints = hitpoints;
        this.name = name;
        this.armorclass = armorclass;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getWisdom() {
        return wisdom;
    }

    public int getWisdomMod() {
        return modifier(wisdom);
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getConsitution() {
        return consitution;
    }

    public void setConsitution(int consitution) {
        this.consitution = consitution;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }


    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public String getName() {
        return name;
    }

    public void setArmorclass(int armorclass) {
        this.armorclass = armorclass;
    }

    @Override
    public String toString() {
        return name +
                ": hitpoints=" + hitpoints +
                ", armorclass=" + armorclass + System.lineSeparator();
    }


    public int modifier(int mod){
        return (mod - 10)/2 + (mod % 2 == 0 ? 1: 0);

    }



}
