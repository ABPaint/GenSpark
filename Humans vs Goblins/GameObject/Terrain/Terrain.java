package GameObject.Terrain;

import GameObject.GameObject;
public class Terrain extends GameObject{
    public boolean moveable = true;
    public Terrain(GameObject[][] _gridRef, int[] _gridPosition){
        super(_gridRef, _gridPosition);
        super.utf = " ";
    }
    @Override
    public String inspect() {
        StringBuilder string = new StringBuilder();
        string.append("Terrain Type: " + thisClass + "\n");
        string.append("Breakable!");
        //string.append("Possible drops: " + "\n");
        //string.append(inspect_inventory(true));
        return string.toString();
    }
}
