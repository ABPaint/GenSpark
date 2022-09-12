package GameObject.Terrain;

import GameObject.GameObject;
import GameObject.Item.Weapon;

public class Tree extends Terrain{
    public Tree(GameObject[][] _gridRef, int[] _gridPosition){
        super(_gridRef, _gridPosition);
        utf = "T";
        moveable = false;
        attackable = true;
        setHealth(10);
        dropChance = 100;
        Weapon item = new Weapon(gridRef, gridPosition, "Stick", "Did you pull this off of a tree?", 1);
        addInventory(item);
    }
}
