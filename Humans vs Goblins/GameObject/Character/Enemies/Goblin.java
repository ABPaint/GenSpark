package GameObject.Character.Enemies;
import java.util.*;

import GameObject.GameObject;
import GameObject.Character.Character;
import GameObject.Item.*;
public class Goblin extends Enemy{
    public Goblin(GameObject[][] _gridRef, int[] _gridPosition,ArrayList<Character> _team){
        super(_gridRef, _gridPosition, _team);
        utf = "G";
        setHealth(20);
        setAttack(1);
        setDefense(1);
        dropChance = 50;
        equipment[0] = random_equipment(Equipment.ModifierType.ATTACK, 1);
    }
}
