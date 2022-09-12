package GameObject.Item;

import java.util.List;

import GameObject.GameObject;

public class Equipment extends Item{

    public enum ModifierType{
        ATTACK,
        DEFENSE,
        HEALTH
    }


    private ModifierType modType;
    private int modAmount;
    private int amount;

    List<Tier> tiers;

    public Equipment(){
        super(null,null);
    }

    public Equipment(GameObject[][] _gridRef, int[] _gridPosition){
        super(_gridRef, _gridPosition);
        this.utf = "E";
    }

    public void setModType(ModifierType modType) {
        this.modType = modType;
    }
    public ModifierType getModType() {
        return modType;
    }
    public void setModAmount(int modAmount) {
        this.modAmount = modAmount;
    }
    public int getModAmount() {
        return modAmount;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String inspect() {
        StringBuilder string = new StringBuilder();
        string.append(super.inspect());
        string.append("Modifier Type: " + modType + "\n");
        string.append(modType + " Amount: " + modAmount +"\n");
        return string.toString();
    }

    @Override
    public String inspect(boolean indent) {
        StringBuilder string = new StringBuilder();
        String indentation = "";
        if (indent){
            indentation = "  ";
        }
        string.append(super.inspect(true));
        string.append(indentation + "Modifier Type: " + modType + "\n");
        string.append(indentation + modType + " Amount: " + modAmount +"\n");
        return string.toString();
    }

    class Tier{
        int level;
        String[] names;
        String[] descriptions;
        int[] modifierRange;
        Tier(int _level, String[] _names, String[] _descriptions, int[] _modifierRange){
            level = _level;
            names = _names;
            descriptions = _descriptions;
            modifierRange = _modifierRange;
        }
    }

}
