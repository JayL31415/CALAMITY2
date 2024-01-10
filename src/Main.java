import java.util.Scanner;
import java.util.Random;



class Game {
    private Scanner scanner;
    public int currentRound;
    public Player player;
    public Enemy enemy;
    public Map map;

    public Game() {
        scanner = new Scanner(System.in);
        currentRound = 1;
        player = new Player();
        enemy = new Enemy(0,1);
        map = new Map (7,7);
    }

    public void start() {
        while (!isGameOver()) {
            System.out.println("Round " + currentRound);
            playerTurn();
            if (!isGameOver()) {  // Check if the game is over after player's turn
                enemyTurn();
            }
            currentRound++;
        }

        if (player.hasWon(enemy)) {
            System.out.println("Congratulations! You have won!");
        } else {
            System.out.println("Game over! You have lost.");
        }
    }


    private void playerTurn() {
        player.checkRoom(map, enemy);
        player.move(map, scanner);
        player.checkRoom(map, enemy);
        player.placeTrap(map, scanner);
    }

    private void enemyTurn() {
        enemy.move(map);
        player.checkRoom(map, enemy);  // Check room after enemy's move
    }


    private boolean isGameOver() {
        return player.hasWon(enemy) || player.hasLost() || enemy.getIsDead();
    }

}




class Player {
    public int trapsLimit;
    public int trapsPlacedCount;
    public int trapsPlaced;
    private int currentRow;
    private int currentCol;
    public int getCurrentRow() {
        return currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }

    public Player() {
        trapsLimit = 5;
        trapsPlacedCount = 0;
        currentRow = 3; // Starting row
        currentCol = 3; // Starting column

    }

    public void move(Map map, Scanner scanner) {

        System.out.println("Enter direction to move (W/A/S/D): ");
        if (scanner.hasNext()) {
            char direction = scanner.next().charAt(0);



            int newRow = currentRow;
                int newCol = currentCol;

                if (direction == 'W' || direction == 'w') {
                    if (currentRow > 0) {
                        newRow = currentRow - 1;
                    }
                } else if (direction == 'A' || direction == 'a') {
                    if (currentCol > 0) {
                        newCol = currentCol - 1;
                    }
                } else if (direction == 'S' || direction == 's') {
                    if (currentRow < map.getRowCount() - 1) {
                        newRow = currentRow + 1;
                    }
                } else if (direction == 'D' || direction == 'd') {
                    if (currentCol < map.getColCount() - 1) {
                        newCol = currentCol + 1;
                    }
                } else {
                    System.out.println("Invalid direction. Try again.");
                    return; // return here to prevent further execution of the method
                }

                // Check if the destination square is not a wall
                if (map.getRoomType(newRow, newCol) != Map.WALL) {
                    currentRow = newRow;
                    currentCol = newCol;
                    System.out.println("Moved to (" + currentRow + ", " + currentCol + ")");
                } else {
                    System.out.println("You can't walk into a wall!");
                }
            }

        }

    private boolean isDead;

    public boolean getIsDead() {
        return isDead;
    }
    public void setPlayerDead(boolean isDead) {
        this.isDead = isDead;
    }

    public void checkRoom(Map map, Enemy enemy) {
        printMap(map, this, enemy);

        int currentRoomType = map.getRoomType(currentRow, currentCol);

        if (currentRoomType == Map.EMPTY_SPACE) {
            System.out.println("");
        } else if (currentRoomType == Map.WALL) {
            System.out.println("You hit a wall. Ouch!");
        } else if (currentRoomType == Map.TRAP) {
            System.out.println("You stepped on a trap. Game over!");
            setPlayerDead(true);

            // Check if the enemy is on the same position as the trap
            if (enemy.getCurrentRow() == currentRow && enemy.getCurrentCol() == currentCol) {
                System.out.println("The enemy walked into a trap and died!");
                // Implement logic to handle the enemy's death
                enemy.setIsDead(true);
            }
        }
    }


    private void printMap(Map map, Player player, Enemy enemy) {
        for (int i = 0; i < map.getRowCount(); i++) {
            for (int j = 0; j < map.getColCount(); j++) {
                if (i == player.getCurrentRow() && j == player.getCurrentCol()) {
                    System.out.print("[✦] "); // Display '✦' for the player's current position
                } else if (i == enemy.getCurrentRow() && j == enemy.getCurrentCol()) {
                    System.out.print("[☠] "); // Display '☠' for the enemy's current position
                } else {
                    int roomType = map.getRoomType(i, j);
                    switch (roomType) {
                        case Map.EMPTY_SPACE:
                            System.out.print("[ ] "); // Empty space
                            break;
                        case Map.WALL:
                            System.out.print("[◼] "); // Wall
                            break;
                        case Map.TRAP:
                            System.out.print("[^] "); // Trap
                            break;
                        // Add more cases for other room types if needed
                    }
                }
            }
            System.out.println();
        }
        System.out.println("✦ = YOU     ☠ = ENEMY    ◼ = WALL    ^ = TRAP");
        System.out.println();
    }



    public void placeTrap(Map map, Scanner scanner) {
        try {
            System.out.println("Do you want to place a trap? (E [YES]/ Q [NO]): ");
            String choice = scanner.next();  // Use next() instead of nextLine()

            if (choice.length() > 0) {
                char decision = choice.toUpperCase().charAt(0);

                if (decision == 'E') {
                    System.out.println("Enter direction to place the trap (W/A/S/D): ");
                    String trapDirection = scanner.next();
                    if (trapDirection.length() > 0) {
                        char direction = trapDirection.toUpperCase().charAt(0);
                        placeTrapInDirection(map, direction);
                    } else {
                        System.out.println("Invalid direction. Trap placement canceled.");
                    }
                } else if (decision == 'Q') {
                    System.out.println("Trap placement canceled.");
                    return;
                } else {
                    System.out.println("Invalid choice. Please enter E or Q.");
                    return;
                }
            }
        } finally {
            // Do not close the scanner in this method
        }
    }


    private void placeTrapInDirection(Map map, char direction) {
        int trapRow = getCurrentRow();
        int trapCol = getCurrentCol();

        // Adjust the trap position based on the chosen direction
        switch (direction) {
            case 'W':
            case 'w':
                if (trapRow > 0) {
                    trapRow--;
                }
                break;
            case 'A':
            case 'a':
                if (trapCol > 0) {
                    trapCol--;
                }
                break;
            case 'S':
            case 's':
                if (trapRow < map.getRowCount() - 1) {
                    trapRow++;
                }
                break;
            case 'D':
            case 'd':
                if (trapCol < map.getColCount() - 1) {
                    trapCol++;
                }
                break;
            default:
                System.out.println("Invalid direction. Trap placement canceled.");
                return;
        }

        // Place the trap on the map
        map.placeTrap(trapRow, trapCol);
        trapsPlacedCount++;
        trapsPlaced++;

        System.out.println("Trap placed at (" + trapRow + ", " + trapCol + ")");

        // Additional print statements for debugging
        System.out.println("Traps Placed Count: " + trapsPlacedCount);
        System.out.println("Traps Placed: " + trapsPlaced);
        return;
    }




    public boolean hasWon(Enemy enemy) {
        // Implement win conditions (e.g., surviving a certain number of rounds)
        if (trapsPlaced >= 40) {
            enemy.setIsDead(true);
            return true; // Example condition
        }
        if (enemy.getIsDead()) {
            return true;
        }
        return false;
    }


    public boolean hasLost() {
            return getIsDead();
        }
    }


class Enemy {
    private int currentRow;
    private int currentCol;

        private boolean isDead;

    public boolean getIsDead() {
        return isDead;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }



    public Enemy(int initialRow, int initialCol) {
        currentRow = initialRow;
        currentCol = initialCol;


    }

    public int getCurrentRow() {
        return currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }


    public void move(Map map) {

            // Generate a random direction using the helper method
            char direction = generateRandomDirection();

            // Attempt to move the enemy based on the random direction
            moveInDirection(map, direction);



    }

    public void moveInDirection(Map map, char direction) {
        if (isDead) {
            return;
        }

        int newRow = currentRow;
        int newCol = currentCol;

        // Save the current position before attempting to move
        int prevRow = newRow;
        int prevCol = newCol;

        // Attempt to move in the specified direction
        if (direction == 'W' && newRow > 0) {
            newRow--;
        } else if (direction == 'A' && newCol > 0) {
            newCol--;
        } else if (direction == 'S' && newRow < map.getRowCount() - 1) {
            newRow++;
        } else if (direction == 'D' && newCol < map.getColCount() - 1) {
            newCol++;
        }

        // Check if the new position is a wall
        while (map.getRoomType(newRow, newCol) == Map.WALL) {
            // Revert to the previous position
            newRow = prevRow;
            newCol = prevCol;

            // Try the next possible direction (you can customize the order if needed)
            if (direction == 'W' && newRow > 0) {
                newRow--;
            } else if (direction == 'A' && newCol > 0) {
                newCol--;
            } else if (direction == 'S' && newRow < map.getRowCount() - 1) {
                newRow++;
            } else if (direction == 'D' && newCol < map.getColCount() - 1) {
                newCol++;
            }
        }

        // Set the new position if it's not a wall
        currentRow = newRow;
        currentCol = newCol;

        // Check if the new position is a trap
        if (map.getRoomType(newRow, newCol) == Map.TRAP) {
            System.out.println("Enemy walked into a trap and died!");
            setIsDead(true);
        } else {
            System.out.println("Enemy moved to (" + currentRow + ", " + currentCol + ")");
        }
    }




    private char generateRandomDirection() {
        Random random = new Random();
        int randomDirection = random.nextInt(4); // Change this to include 3 as well
        switch (randomDirection) {
            case 0:
                return 'W';
            case 1:
                return 'A';
            case 2:
                return 'S';
            case 3:
                return 'D';
            default:
                return 'W'; // Default to 'W' in case of unexpected value
        }
    }



    public void useAbility() {
        // Logic to make the enemy use its ability
    }
}

class Map {
    // Constants to represent different types of squares
    public static final int EMPTY_SPACE = 0;
    public static final int WALL = 1;
    public static final int TRAP = 2;

    public static final int ENEMY = 3;


    private int[][] map; // Representing the 2D map

    public Map(int rowCount, int colCount) {
        map = new int[rowCount][colCount];
        initializeMap();
    }

    private void initializeMap() {
        // Loop through each row and column of the map
        map[1][5] = ENEMY;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                // Set the initial value for each square in the map
                map[i][j] = EMPTY_SPACE; // Default: Empty space
            }
        }

        // Example: Set a wall in the middle of the map

        map[0][0] = WALL;
        map[1][0] = WALL;
        map[5][0] = WALL;
        map[6][0] = WALL;
        map[6][1] = WALL;
        map[1][1] = WALL;
        map[3][1] = WALL;
        map[4][2] = WALL;
        map[1][3] = WALL;
        map[2][3] = WALL;
        map[5][4] = WALL;
        map[0][5] = WALL;
        map[2][5] = WALL;
        map[0][6] = WALL;
        map[4][6] = WALL;
        map[6][6] = WALL;

    }

    // Other methods for interacting with the map

    public void placeTrap(int row, int col) {
        map[row][col] = TRAP;
    }

    public int getRowCount() {
        return map.length;
    }

    public int getColCount() {
        return map[0].length;
    }
    public int getRoomType(int row, int col) {
        return map[row][col];
    }


    // Implement other methods to interact with the map (e.g., place traps, reveal rooms)
}

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();

    }
}