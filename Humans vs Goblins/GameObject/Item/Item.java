package GameObject.Item;
import GameObject.GameObject;

public abstract class Item extends GameObject{

    
    
    public String name;
    public String description;

    public Item(){
        super(null,null);
    }

    public Item(GameObject[][] _gridRef, int[] _gridPosition){
        super(_gridRef, _gridPosition);
        this.utf = "I";
    }

    @Override
    public String inspect() {
        StringBuilder string  =  new StringBuilder();
        string.append("Name: " + name + "\n");
        string.append("Description: " + description + "\n");
        return string.toString();
    }
    public String inspect(boolean indent) {
        StringBuilder string  =  new StringBuilder();
        String indentation = "";
        if (indent){
            indentation = "  ";
        }
        string.append(indentation + "Name: " + name + "\n");
        string.append(indentation + "Description: " + description + "\n");
        return string.toString();
    }
}
