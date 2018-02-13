package GameLogic;

import java.util.ArrayList;

public class Board {
    private boolean cheat;
    private Fortress fortress;
    private TankPlacement tankPlacement;
    private Cell[][] gameBoard;
    private AttackCell currentPlayerAttack;
    private final int GRID_DIMENSION = 10;
    private final int INITIALIZER = 0;
    private final int CORDINATES_OF_A_CELL = 2;

    public Board(int numberOfTanks, boolean cheat) {
        if (cheat){
            this.cheat = true;
        }
        this.fortress = new Fortress();
        this.tankPlacement = new TankPlacement(numberOfTanks);
        this.gameBoard = initializeBoard();
        tankPlacement.placeAllTanks(cheat,this.gameBoard);
    }

    private Cell[][] initializeBoard(){
        Cell[][] gameBoard = new Cell[GRID_DIMENSION][GRID_DIMENSION];
        //Set coordinate for all Cell within the gameBoard
        for (int row = INITIALIZER; row < GRID_DIMENSION; row++){
            for (int column = INITIALIZER; column < GRID_DIMENSION; column++){
                int cellCoordinate[] = new int[]{row,column};
                gameBoard[row][column].setCellCoordinate(cellCoordinate);
                gameBoard[row][column].setDestroyed(false);
            }
        }
        return gameBoard;
    }

    public int[] convertCoordinateToInt(String targetingCell) {
        int[] position = new int[CORDINATES_OF_A_CELL];

        //get the first character from targetingCell
        char firstChar = targetingCell.charAt(0);

        position[0] = charAlphabetConvertion(firstChar);
        position[1] = Character.getNumericValue(targetingCell.charAt(1));

        return position;
    }

    private int charAlphabetConvertion(char currentCharacter){
        int position;
        String convertedFirstChar = Character.toString(currentCharacter);
        switch (convertedFirstChar) {
            case "a":
            case "A":
                position = 0;
                break;

            case "b":
            case "B":
                position = 1;
                break;

            case "c":
            case "C":
                position = 2;
                break;

            case "d":
            case "D":
                position = 3;
                break;

            case "e":
            case "E":
                position = 4;
                break;

            case "f":
            case "F":
                position = 5;
                break;

            case "g":
            case "G":
                position = 6;
                break;

            case "h":
            case "H":
                position = 7;
                break;

            case "i":
            case "I":
                position = 8;
                break;

            case "j":
            case "J":
                position = 9;
                break;
            default:
                position = -1;
                break;
        }
        return position;
    }

    public Fortress getFortress() {
        return fortress;
    }

    public TankPlacement getTankPlacement() {
        return tankPlacement;
    }

    public Cell[][] getGameBoard() {
        return gameBoard;
    }

    public AttackCell getCurrentPlayerAttack() {
        return currentPlayerAttack;
    }

    public void userAttack(AttackCell currentPlayerAttack){
        Cell currentCell = currentPlayerAttack.searchCell(gameBoard);
        ArrayList<Tank> listOfTanks = tankPlacement.getListOfTanks();
        for (Tank currentTank : listOfTanks){
            if (currentTank.getListOfTankCell().contains(currentCell)){
                currentTank.setTankHealth();
                currentTank.setTankDamage();
            }
        }
    }
}
