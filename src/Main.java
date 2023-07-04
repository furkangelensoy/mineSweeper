import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the game's row size : ");
        int row = input.nextInt();
        System.out.print("Enter the game's column size : ");
        int column = input.nextInt();
        MineSweeper mineSweeper = new MineSweeper(row, column);
        mineSweeper.run();

    }

}