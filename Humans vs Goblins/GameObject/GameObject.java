package GameObject;

import java.util.*;

import GameObject.Item.*;
import GameObject.Terrain.Terrain;

public abstract class GameObject {
    public String utf = "O";
    public int[] gridPosition;
    public GameObject[][] gridRef;
    public String thisClass;
    protected int dropChance = 50;

    public boolean attackable = false;
    protected int health = 999;
    protected List<Item> inventory = new ArrayList<Item>();

    public GameObject(GameObject[][] _gridRef, int[] _gridPosition){
        this.gridRef = _gridRef;
        this.gridPosition = _gridPosition;
        String[] temp = this.getClass().toString().split("\\.");
        thisClass = temp[temp.length-1];
    }

    public Equipment random_equipment(Equipment.ModifierType type, int tier){
        Equipment equip = new Weapon(tier);
        switch (type){
            case ATTACK:
                equip = new Weapon(tier);
                break;
            case DEFENSE:
                break;
            case HEALTH:
                break;
            default:
                System.out.println("Non-valid Item type in GameObject.random_item()");
                break;
        }

        return equip;
    }


    public static int normalize(int value) {
        return Math.min(Math.max(value, -1), 1);
    }

    public void drop(){
        this.drop(dropChance);
    }

    public void drop(int _dropChance){
        Item randomItem = null;
        Random rand = new Random();
        for (Item item : inventory) {
            if (item != null && randomItem == null){
                if (rand.nextInt(100) < _dropChance)
                randomItem = item;
            }   
        }
        if (randomItem == null){
            gridRef[gridPosition[0]][gridPosition[1]] = new Terrain(gridRef, gridPosition);
        }else{
            System.out.println(thisClass + " dropped " + randomItem.name);
            randomItem.gridRef = gridRef;
            randomItem.gridPosition = gridPosition;
            gridRef[gridPosition[0]][gridPosition[1]] = randomItem;  
        }
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
        if (health <= 0){
            death();
        }
    }
    public String inspect_inventory(){
        return inspect_inventory(false);
    }   
    public String inspect_inventory(boolean indent){
        StringBuilder string = new StringBuilder(); 
        for (Item i : getInventory()) {
            string.append(i.inspect(indent));
        }
        return string.toString();
    }

    public void show_inventory(){
        System.out.println(thisClass + " Inventory:");
        System.out.print(inspect_inventory(true));
    }

    public void death(){
        System.out.println(thisClass +  " died.");
        drop();
    }
    public List<Item> getInventory() {
        return inventory;
    }
    public void addInventory(Item item){
        inventory.add(item);
    }
    public void removeInventory(Item item){
        inventory.remove(item);
    }

    @Override
    public String toString() {
        return "" + this.utf;
    }
    public void setUtf(String utf) {
        this.utf = utf;
    }

    public String inspect(){
        return "";
    }
}