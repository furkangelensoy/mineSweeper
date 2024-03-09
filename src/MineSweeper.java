import java.util.Scanner;

public class MineSweeper { //Değerlendirme Formu(5)
    //This field has properties about game like mines count, game size.
    Scanner input = new Scanner(System.in);
    public int row;
    public int column;
    private int mineCount;
    private boolean isGameOver;
    private int winCondition;

    MineSweeper() { //Değerlendirme Formu(6)
        //This constructor method defines values for properties.
        int[] gameSize = takeGameSize();
        this.row = gameSize[0];
        this.column = gameSize[1];
        this.mineCount = row * column / 4;
        System.out.println("Mine count: " + mineCount);
        this.isGameOver = false;
        this.winCondition = row * column - mineCount;
    }


    public int[] takeGameSize() { //Değerlendirme Formu(7)
    /*
        - This method takes game size (row size and column size) from user until row and column value equals or bigger than 2.
        - When User enters a integer value smaller than 2 or enters a not integer value
            method will print a warning message and takes value again from user.
        - This method returns an array and this array's first element is row size, second element is column size.
     */
        System.out.println("Welcome to MineSweeper! Please enter the game size.");
        System.out.print("Row Size: ");
        int rowSize = takeAndCheckInput();
        System.out.print("Column Size: ");
        int columnSize = takeAndCheckInput();
        while (rowSize < 2 || columnSize < 2) {
            System.out.println("Row and Column size must be greater than or equal '2'. Try again! ");
            System.out.print("Row Size: ");
            rowSize = takeAndCheckInput();
            System.out.print("Column Size: ");
            columnSize = takeAndCheckInput();
        }
        int[] gameSize = {rowSize, columnSize};
        return gameSize;
    }

    public void run() {
        /*
            - This method calls other methods from this class for run the game according to game's rule.
            - Game map and mine map created here.
            - This method runs until user win the game or lose the game.
            - If user wins the game, this method prints a message to user.
         */
        String[][] gameMap = mapping();
        String[][] map = mapping();
        String[][] mineMap = mineMap(map);
        System.out.println("Location of Mines");
        printMap(mineMap);
        System.out.println("Welcome to Minesweeper!");
        while (!isGameOver) {
            printMap(gameMap); //Değerlendirme Formu(11)
            int[] coordinate = takeCoordinate(); //Değerlendirme Formu(9)
            checkCoordinate(coordinate[0], coordinate[1], mineMap, gameMap);
            if (winCondition == 0) { //Değerlendirme Formu(14)
                System.out.println("Congratulations, You Won!");
                printMap(gameMap);
                break;
            }
        }
    }

    public String[][] mapping() {
        /*
            - This method creates blank map according to row and column values taken from user.
            - This method returns (String[][]) the map.
         */
        String[][] map = new String[row][column];
        for (String[] i : map) {
            for (int j = 0; j < i.length; j++) {
                i[j] = "-";
            }
        }
        return map;
    }

    public String[][] mineMap(String[][] mineMap) { //Değerlendirme Formu(8)
        /*
            - This method creates mines's coordinate randomly according to mines count.
            - And fill the mine map. This method returns (String[][]) mine map.
         */
        for (int i = 1; i <= mineCount; i++) {
            int mineRow = (int) (Math.random() * row);
            int mineColumn = (int) (Math.random() * column);
            if (mineMap[mineRow][mineColumn].equals("*")) {
                i--;
            }
            mineMap[mineRow][mineColumn] = "*";
        }
        return mineMap;
    }

    public int[] takeCoordinate() { //Değerlendirme Formu(9)
        /*
            - This method takes row and column coordinate from user, until the value is valid.
            - Checks row and column coordinate according to game map.
            - It returns (int[]) an array. first element is row value, second element is column value.
            - The row and column values received from the user are reduced by 1, because the index of the
                first row is 1 (relative to the user) but the index of the first row is 0 relative to the array.
        */
        System.out.print("Enter the row: ");
        int rowFromUser = takeAndCheckInput();
        System.out.print("Enter the column: ");
        int columnFromUser = takeAndCheckInput();
        while ((rowFromUser <= 0 || rowFromUser > row) || (columnFromUser <= 0 || columnFromUser > column)) {
            System.out.println("You have entered invalid 'row' or 'column' value try again!"); //Değerlendirme Formu(10)
            System.out.print("Enter the row: ");
            rowFromUser = takeAndCheckInput();
            System.out.print("Enter the column: ");
            columnFromUser = takeAndCheckInput();
        }
        int[] coordinate = {rowFromUser - 1, columnFromUser - 1};
        return coordinate;
    }

    public void checkCoordinate(int rowFromUser, int columnFromUser, String[][] mineMap, String[][] gameMap) {
        /*
            - This method checks "is there a mine" according to the coordinate rowFromUser and columnFromUSer.
            - If this coordinate has a mine, isGameOVer = true and user lose the game. Method prints a message and prints mine map.
            - Checks whether the coordinate value has been entered before, prints a message if it has been entered before
         */
        String coordinate = mineMap[rowFromUser][columnFromUser];
        if (coordinate.equals("*")) { //Değerlendirme Formu(13)
            System.out.println("Game Over!"); //Değerlendirme Formu(15)
            printMap(mineMap);
            isGameOver = true;
        } else if (coordinate.equals("-")) {
            checkMinesCount(rowFromUser, columnFromUser, mineMap, gameMap);
            winCondition--;
        } else {
            System.out.println("You have selected a previously selected coordinate! Enter another location!");
        }
    }

    public void checkMinesCount(int rowFromUser, int columnFromUser, String[][] mineMap, String[][] gameMap) {
        /*
            - According to the coordinate entered by the user, the number of mines around is calculated.
            - An integer variable (count) keeps number of neighboring mines so using type casting for change it string.
            - Then this value assign to "mine map" and "game map" according to the coordinate entered by the user.
         */
        //Değerlendirme Formu(12)
        int count = 0;
        for (int i = rowFromUser - 1; i <= rowFromUser + 1; i++) {
            if (i < 0 || i >= row) {
                continue;
            }
            for (int j = columnFromUser - 1; j <= columnFromUser + 1; j++) {
                if (!(j < 0 || j >= column)) {
                    if (mineMap[i][j].equals("*")) {
                        count++;
                    }
                }
            }
        }
        gameMap[rowFromUser][columnFromUser] = String.valueOf(count); //Değerlendirme Formu(11)
        mineMap[rowFromUser][columnFromUser] = String.valueOf(count); //Değerlendirme Formu(11)
    }

    public void printMap(String[][] map) {
        /*
            This method prints 2d array's elements.
         */
        for (String[] i : map) {
            for (int j = 0; j < i.length; j++) {
                System.out.print(i[j] + " ");
            }
            System.out.println();
        }
        System.out.println("======================");
    }

    public int takeAndCheckInput() { //Değerlendirme Formu(10)
        /*
            This method takes a value from user and checks "is this an integer?"
            if it is an integer returns value received from user, if it is not an integer returns -1.
         */
        int value = -1;
        if (input.hasNextInt()) {
            value = input.nextInt();
            return value;
        }
        input.next();
        return value;
    }
}