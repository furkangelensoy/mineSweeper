import java.util.Random;
import java.util.Scanner;

public class MineSweeper {

    int row;
    int column;
    String[][] mineMap;
    String[][] gameMap;
    int mineCount;


    public MineSweeper(int row, int column) {
        this.row = row;
        this.column = column;
        this.mineMap = new String[row][column];
        this.gameMap = new String[row][column];
        this.mineCount = (row * column) / 4;
    }

    public void gameBoards() {
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                gameMap[x][y] = "-";
                mineMap[x][y] = "-";

            }
        }
    }

    public void mineCoordinate() {
        Random random = new Random();
        for (int i = 1; i <= mineCount; i++) {
            int x = random.nextInt(row);
            int y = random.nextInt(column);

            if (mineMap[x][y] == "*") {
                i--;
            }

            mineMap[x][y] = "*";
        }
    }

    public void printMineMap() {
        System.out.println("Mine's coordinate");
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                System.out.print(mineMap[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println("=================");
        System.out.println("Welcome to MineSweeper Game!");
    }

    public void printGameMap() {
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                System.out.print(gameMap[x][y] + " ");
            }
            System.out.println();
        }

        System.out.println("=================");
    }

    public void selectCoordinate() {
        Scanner input = new Scanner(System.in);
        boolean isWin = true;
        int winCondition = (row * column) - mineCount;
        int counter = 0;
        while (isWin == true) {

            System.out.print("Enter the row : ");
            int m = input.nextInt();
            System.out.print("Enter the column : ");
            int n = input.nextInt();
            System.out.println("================");

            if (mineMap[m][n] == "*") {
                isWin = false;
                System.out.println("Game Over!");
            } else {
                int mineCounter = 0;
                for (int i = m - 1; i <= m + 1; i++) {
                    if (i < 0) {
                        continue;
                    }
                    if (i > row - 1) {
                        continue;
                    }
                    for (int j = n - 1; j <= n + 1; j++) {
                        if (j < 0) {
                            continue;
                        }
                        if (j > column - 1) {
                            continue;
                        }
                        if (mineMap[i][j] == "*") {
                            mineCounter++;
                        }
                    }
                }
                String mine = Integer.toString(mineCounter);
                gameMap[m][n] = mine;

                for (int x = 0; x < row; x++) {
                    for (int y = 0; y < column; y++) {
                        System.out.print(gameMap[x][y] + " ");
                    }
                    System.out.println();
                }
                counter++;
                System.out.println("================");
                if (counter == winCondition) {
                    isWin = true;
                    break;
                }
            }

        }
        if (isWin) {
            System.out.println("Congratulations You Won!");
        }

    }

    public void run(){
        gameBoards();
        mineCoordinate();
        printMineMap();
        printGameMap();
        selectCoordinate();


    }

}
