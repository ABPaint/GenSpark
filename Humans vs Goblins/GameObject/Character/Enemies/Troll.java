package GameObject.Character.Enemies;

import java.util.*;

import GameObject.GameObject;
import GameObject.Character.Character;

public class Troll extends Enemy{
    public Troll(GameObject[][] _gridRef, int[] _gridPosition,ArrayList<Character> _team){
        super(_gridRef, _gridPosition, _team);
        utf = "L";
        setHealth(100);
        setAttack(10);
        setDefense(10);
    }
}
