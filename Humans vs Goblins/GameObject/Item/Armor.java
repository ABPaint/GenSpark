package GameObject.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import GameObject.GameObject;

public class Armor extends Equipment{
    public Armor(int tier){
        this(new GameObject[1][1], new int[1], tier);       
    }
    public Armor(GameObject[][] _gridRef, int[] _gridPosition, int tier){
        super(_gridRef, _gridPosition);
        tiers = new ArrayList<>(Arrays.asList(tier0, tier1, tier2, tier3, tier4));
        Random rand = new Random();
        Tier selectedTier = tiers.get(tier);
        int Armor = rand.nextInt(0,selectedTier.names.length);
        name = selectedTier.names[Armor];
        description = selectedTier.descriptions[Armor];
        setModAmount(rand.nextInt(selectedTier.modifierRange[0], selectedTier.modifierRange[1]));
        setModType(ModifierType.ATTACK);
        

    }
    public Armor(String _name, String _description, int _damage){
        this(new GameObject[1][1], new int[1], _name, _description, _damage);       
    }
    public Armor(GameObject[][] _gridRef, int[] _gridPosition, String _name, String _description, int _damage){
        super(_gridRef, _gridPosition);
        tiers = new ArrayList<>(Arrays.asList(tier0, tier1, tier2, tier3, tier4));
        name = _name;
        description = _description;
        setModAmount(_damage); 
        setModType(ModifierType.ATTACK);
    }


    Tier tier0 = new Tier(0, new String[]{"Grass Armour"}, 
                            new String[]{"Looks like a splattering of grass clippings..."}, 
                            new int[]{0,0});
    Tier tier1 = new Tier(1, new String[]{"Wooden Sword", "Broken Dagger"}, 
                            new String[]{"I guess it technically is a Armor...", "You sure about this?"}, 
                            new int[]{2,3});
    Tier tier2 = new Tier(2, new String[]{"Basic Sword", "Shovel"}, 
                            new String[]{"Wow an actually Armor!", "You can do better."}, 
                            new int[]{3,4});
    Tier tier3 = new Tier(3, new String[]{"Iron Sword", "Iron Dagger"}, 
                            new String[]{"Rock", "Stick"}, 
                            new int[]{4,5});
    Tier tier4 = new Tier(4, new String[]{"Big Sword", "Stick"}, 
                            new String[]{"Rock", "Stick"}, 
                            new int[]{6,10});
}
