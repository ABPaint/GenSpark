package GameObject.Character;
import java.text.ParseException;
import java.util.ArrayList;

import GameObject.GameObject;
import GameObject.Character.Enemies.*;
import GameObject.Item.*;
import GameObject.Terrain.Terrain;

public class Player extends Character{
    public Player(GameObject[][] _gridRef, int[] _gridPosition, ArrayList<Character> _team){
        super(_gridRef, _gridPosition, _team);
        super.utf = "P";
        this.setHealth(20);
        this.attack = 10;
        this.teamID = 1;
        //addInventory(random_equipment(Equipment.ModifierType.ATTACK, 1));
    }

    public void player_input(String input){
        String[] inputArgs = input.split(" ");
        if (inputArgs.length <= 1){
            inputArgs = new String[]{input, ""};
        }
        System.out.println(inputArgs[0]);
        switch (inputArgs[0]){
            case "i":
            System.out.println(inputArgs[1]);
                switch(inputArgs[1]){
                    case "h":
                    case "help":
                        System.out.println("Possible arguments:\n'inv' - inventory\n'self' - Player's self\n's' - surroundings. Also the default.");
                        break;
                    case "inv":
                        show_inventory();
                        break;
                    case "self":
                        System.out.println(inspect());
                        break;
                    case "s":
                    default:
                        inspect_surroundings();
                        break;
                }
                break;
            case "w":
            case "n":
            case "e":
            case "s":
                int count = 1;
                try{
                    count = Integer.parseInt(inputArgs[1]);
                }catch(Exception e){
                    if (e instanceof ArrayIndexOutOfBoundsException){
                        count = 1;
                    }else if(e instanceof ParseException){
                        System.out.println("Invalid arguement: " + inputArgs[1]);
                    }
                }
                for (int i = 0; i < count; i++) {
                    move(inputArgs[0]);
                }
                break;
            default:
                System.out.println("Not a valid input: " + input);
                break;
        }
    }

    public void inspect_surroundings(){
        System.out.println("Player position: " + gridPosition[0] + ", " + gridPosition[1]);
        for (int i = gridPosition[0]-1; i <= gridPosition[0]+1; i++) {
            for (int j = gridPosition[1]-1; j <= gridPosition[1]+1; j++) {
                if((i >= 0 && i < gridRef.length) && (j >= 0 && j < gridRef[0].length)){
                    if (i != gridPosition[0] || j != gridPosition[1]){
                        inspect(new int[]{i,j});
                       //System.out.println(i + " " + j);
                    }
                }
            }
        }
    }


    public void inspect(int[] gridPos){
        GameObject object = gridRef[gridPos[0]][gridPos[1]];
        
        if (object instanceof Terrain){
            if (object.attackable){
                System.out.println(object.inspect());
            }
        }
        else if(object instanceof Enemy){
            System.out.println(object.inspect());
        }
        else if(object instanceof Item){
            System.out.println(object.inspect());
        }

    }
}
