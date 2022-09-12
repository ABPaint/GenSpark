import java.util.*;

import GameObject.*;
import GameObject.Terrain.*;
import GameObject.Character.*;
import GameObject.Character.Character;
import GameObject.Character.Enemies.*;


public class MainGame {
    int gridHeight = 10;
    int gridWidth = 20;
    GameObject[][] grid = new GameObject[gridHeight][gridWidth];
    ArrayList<Character> goblins = new ArrayList<Character>();
    ArrayList<Character> players = new ArrayList<Character>();

    MainGame(){
        //System.out.print("Test");
        populate_grid();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        MainGame game = new MainGame();
        game.print_map();
        String userInput;
        while (true){
            
            userInput = input.nextLine();
            for (Character player : game.players){
                Player p = (Player)player;
                clearScreen();
                p.player_input(userInput);
                
            }

            
            for (Character goblin : game.goblins) {
                if (game.players.size() > 0){
                    if (goblin.target == null){
                        goblin.target = game.players.get(new Random().nextInt(game.players.size()));
                    }
                    goblin.move_towards(goblin.target);
                }
            }
            
            if (game.players.size() == 0){
                break;
            }
            
            game.print_map();
        }
        System.out.println("All players dead. You lose.");
        input.close();
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  

    public void populate_grid(){
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[0].length; j++){
                if ((i == 0 || i == grid.length - 1)|| (j==0 || j == grid[0].length - 1)){
                    grid[i][j] = new Wall(grid, new int[]{i,j});
                }
                else{
                    grid[i][j] = new Terrain(grid, new int[]{i,j});
                }
                
                //grid[i][j].setUtf(Integer.toString(j).charAt(0));
            }
        }
        Random random = new Random();

        int randY = random.nextInt(1,gridHeight-1);
        int randX = random.nextInt(1,gridWidth-1);
        
        for (int i = 0; i < 3; i++) {
            randY = random.nextInt(1,gridHeight-1);
            randX = random.nextInt(1,gridWidth-1);
            Goblin goblin = new Goblin(grid, new int[]{randY,randX}, goblins);
            if (grid[randY][randX] instanceof Terrain){
                grid[randY][randX] = goblin;
                goblins.add(goblin);
            }
        }
        

        randY = random.nextInt(1,gridHeight-1);
        randX = random.nextInt(1,gridWidth-1);
        Player player = new Player(grid, new int[]{randY,randX}, players);
        grid[randY][randX] = player;
        players.add(player);


        randY = random.nextInt(1,gridHeight-1);
        randX = random.nextInt(1,gridWidth-1);
        Tree tree = new Tree(grid, new int[]{randY,randX});
        grid[randY][randX] = tree;
        //players.add(player);

        //goblin = new Goblin(grid, new int[]{2,0});
        //grid[goblin.gridPosition[0]][goblin.gridPosition[1]] = goblin;
        //goblins.add(goblin);


    }


    public void print_map(){
        for (GameObject[] array : this.grid) {
            for (GameObject object : array){
                System.out.print(object.toString()+ " ");
            }
            System.out.println();
        }
    }
}