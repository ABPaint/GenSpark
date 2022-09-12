package GameObject.Terrain;
import GameObject.GameObject;
public class Wall extends Terrain{
    public Wall(GameObject[][] _gridRef, int[] _gridPosition){
        super(_gridRef, _gridPosition);
        utf = "X";
        moveable = false;
    }
}
