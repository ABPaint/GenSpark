package GameObject.Character;
import java.util.ArrayList;
import java.util.Random;

import GameObject.GameObject;
import GameObject.Terrain.Terrain;
import GameObject.Item.*;

public abstract class Character extends GameObject{
    int health;
    int attack;
    int defense;
    public Character target;
    public int teamID = 0;
    ArrayList<Character> team;

    public Equipment[] equipment = new Equipment[3];

    public Character(GameObject[][] _gridRef, int[] _gridPosition,ArrayList<Character> _team){
        super(_gridRef, _gridPosition);
        team = _team;
    }

    public void move(String direction){
        switch (direction){
            case "n":
                System.out.println(thisClass + " moved North");
                try_move(new int[]{this.gridPosition[0] - 1, this.gridPosition[1]});
                break;
            case "s":
                System.out.println(thisClass + " moved South");
                try_move(new int[]{this.gridPosition[0] +1, this.gridPosition[1]});
                break;
            case "e":
                System.out.println(thisClass + " moved East");
                try_move(new int[]{this.gridPosition[0], this.gridPosition[1] + 1});
                break;
            case "w":
                System.out.println(thisClass + " moved West");
                try_move(new int[]{this.gridPosition[0], this.gridPosition[1] - 1});
                break;
            default:
                System.out.println("Invalid Move.");

        }
    }

    public void try_move(int[] newGridPos){
        if (newGridPos[0] >= 0 && newGridPos[0] < gridRef.length){
            if (newGridPos[1] >= 0 && newGridPos[1] < gridRef[0].length){
                GameObject object = gridRef[newGridPos[0]][newGridPos[1]];
                if (object instanceof Terrain){
                    terrain_move((Terrain)object);
                }else if(object instanceof Character){
                    attack((Character)object);
                }
                else if (object instanceof Item){
                    pickup((Item)object);
                    try_move(newGridPos);
                }
            }
        }
    }

    public void terrain_move(Terrain terrain){
        //System.out.println("Is Terrain");
        if (terrain.moveable){
            //GameObject previousHolder = gridRef[_gridPosition[0]][_gridPosition[1]];
            gridRef[terrain.gridPosition[0]][terrain.gridPosition[1]] = this;
            gridRef[gridPosition[0]][gridPosition[1]] = new Terrain(gridRef, gridPosition);
            gridPosition = terrain.gridPosition;
        }else if (terrain.attackable){
            attack(terrain);
        }
    }


    public void move_towards(Character character){
        int y = normalize(character.gridPosition[0] -gridPosition[0]);
        int x = normalize(character.gridPosition[1] - gridPosition[1]);
        //System.out.println(y + " , "+ x);
        Random rand = new Random();
        if (y != 0 && x != 0){
            if (rand.nextInt(100)< 50){
                try_move(new int[]{gridPosition[0] + y, gridPosition[1]});
            }
            else{
                try_move(new int[]{gridPosition[0], gridPosition[1] + x});
            }
        }else if(y != 0){
            try_move(new int[]{gridPosition[0] + y, gridPosition[1]});
        }else if (x!=0){
            try_move(new int[]{gridPosition[0], gridPosition[1] + x});
        }


    }


    public void pickup(Item item){
        System.out.println(thisClass +  " picked up " + item.name);
        addInventory(item);
        gridRef[item.gridPosition[0]][item.gridPosition[1]] = new Terrain(gridRef, item.gridPosition);
        item.gridPosition = null;
        auto_equip(item);
    }

    public void auto_equip(Item item){
        if (item instanceof Equipment){
            Equipment newEquip = (Equipment)item;
            int type = newEquip.getModType().ordinal();
            if (equipment[type] == null){
                equipment[type] = newEquip;
                removeInventory(item);
                System.out.println(thisClass + " equipped " + newEquip.name);
            }
            else{
                if(newEquip.getModAmount() > equipment[type].getModAmount()){
                    addInventory(equipment[type]);
                    System.out.println(thisClass + " un-equipped " + equipment[type].name);
                    removeInventory(item);
                    equipment[type] = newEquip;
                    System.out.println(thisClass + " equipped " + newEquip.name);
                }
            }
        }
    }

    public void attack(Terrain terrain){
        attack((GameObject)terrain);
    }

    public void attack(Character character){
        if (character.teamID != this.teamID){
            System.out.println(thisClass + " attacked " + character.thisClass);
            
            int damage = Math.max((getAttack() + getEquipAttack()) - (character.getDefense() + character.getEquipDefense()), 0);
            System.out.println(thisClass + " did " + damage + " damage.");
            System.out.print(character.getHealth() + " -> ");
            character.setHealth(character.getHealth() - damage);
            System.out.println(character.getHealth());
        }
    }

    public void attack(GameObject object){
        System.out.println(thisClass + " attacked " + object.thisClass);
        int damage = Math.max((getAttack() + getEquipAttack()), 0);
        System.out.println(thisClass + " did " + damage + " damage.");
        System.out.print(object.getHealth() + " -> ");
        object.setHealth(object.getHealth() - this.attack);
        System.out.println(object.getHealth());
    }
    @Override
    public void death(){
        if (!equipment_drop()){
            super.death();
        }
        else{
            System.out.println(thisClass + " died.");
        }
        team.remove(this);
    }

    public int getEquipAttack(){
        if (equipment[0] != null){
            return equipment[0].getModAmount();
        }
        else{
            return 0;
        }
    }

    public int getEquipDefense(){
        if (equipment[1] != null){
            return equipment[1].getModAmount();
        }
        else{
            return 0;
        }
    }

    public boolean equipment_drop(){
        return this.equipment_drop(dropChance);
    }

    public boolean equipment_drop(int _dropChance){
        Item randomItem = null;
        Random rand = new Random();
        for (Equipment equip : equipment) {
            if (equip != null && randomItem == null){
                if (rand.nextInt(100) < _dropChance)
                randomItem = equip;
            }   
        }
        if (randomItem == null){
            gridRef[gridPosition[0]][gridPosition[1]] = new Terrain(gridRef, gridPosition);
            return false;
        }else{
            System.out.println(thisClass + " dropped " + randomItem.name);
            randomItem.gridRef = gridRef;
            randomItem.gridPosition = gridPosition;
            gridRef[gridPosition[0]][gridPosition[1]] = randomItem; 
            return true;
        }
    }


    @Override
    public String inspect() {
        StringBuilder string = new StringBuilder();
        String[] classStrings = getClass().toString().split("\\.");

        string.append("Type: " + classStrings[classStrings.length-1] + "\n");
        if (equipment[2] != null){
            string.append("Health: " + getHealth() + " + " + equipment[2].getModAmount() + " = " + (getHealth() + equipment[2].getModAmount()) +"\n");
        }
        else{
            string.append("Health: " + getHealth() + "\n");
        }
        if (equipment[0] != null){
            string.append("Attack: " + getAttack() + " + " + equipment[0].getModAmount() + " = " + (getAttack() + equipment[0].getModAmount()) +"\n");
        }
        else{
            string.append("Attack: "+ getAttack() + "\n");
        }
        if (equipment[1] != null){
            string.append("Defense: " + getDefense() + " + " + equipment[1].getModAmount() + " = " + (getDefense() + equipment[1].getModAmount()) +"\n");
        }
        else{
            string.append("Defense: " + getDefense() + "\n");
        }
        string.append("Equipment: \n");
        for (Equipment equipItem : equipment) {
            if (equipItem != null){
                string.append(equipItem.inspect(true));
            }
        }

        return string.toString();
    }

    public void setHealth(int health) {
        this.health = health;
        if (health <= 0){
            death();
        }
    }

    public int getAttack() {
        return attack;
    }
    public int getDefense() {
        return defense;
    }
    public int getHealth() {
        return health;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }

}
