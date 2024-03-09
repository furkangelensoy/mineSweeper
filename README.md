# MineSweeper Game
It receives the game dimensions from the user and determines the number of mines according to the game dimensions. It then places these mines at random coordinates. The program runs until the player wins or loses the game.

## Technologies
Project is created with:
* Java version: 19

## How To Use
Clone this repository
```shell 
git clone https://github.com/furkangelensoy/mineSweeper.git
```

## How to play
- When program run, it takes game size (row and column size) from user until row and column value (must be integer) equals or bigger than 2. ![](https://github.com/furkangelensoy/mineSweeper/blob/master/pictures/invalidGameSize.PNG)
- Program randomly assigns mines to map according to mine count (mine count = row * column / 4).
- Receives coordinate value from the user until the user steps on the mine or wins the game.
- The winning condition is to enter all mine-free coordinates without stepping on a mine.
- Invalid coordinate input. ![](https://github.com/furkangelensoy/mineSweeper/blob/master/pictures/invalidCoordinate.PNG)
- Lose game! ![](https://github.com/furkangelensoy/mineSweeper/blob/master/pictures/loseGame.png)
- Win game! ![](https://github.com/furkangelensoy/mineSweeper/blob/master/pictures/winGame.PNG)
### ENJOY!
