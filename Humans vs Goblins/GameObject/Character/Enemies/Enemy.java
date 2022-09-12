package GameObject.Character.Enemies;

import java.util.ArrayList;

import GameObject.GameObject;
import GameObject.Character.Character;

public class Enemy extends Character{
    public Enemy(GameObject[][] _gridRef, int[] _gridPosition,ArrayList<Character>  _team){
        super(_gridRef, _gridPosition, _team);
        super.utf = "E";
        this.teamID = 2;
        setHealth(40);
    }
    
}
