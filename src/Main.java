import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int gameBoardSize = 7;

        char[][] gameBoard = new char[gameBoardSize][gameBoardSize];

        char water = '#';
        // Ship name = {char (in integer), length of ship, width of ship}
        int[] destroyer = new int[] {51, 3, 1}; // ship with 3 squares length
        int[] submarine = new int[] {50, 2, 2}; // ship with 3 squares length
        int[] boat = new int[] {49, 1, 4};      // ship with 3 squares length

        int destroyerNumber = 1;
        int submarineNumber = 2;
        int boatNumber = 4;

        char miss = '0';
        char hit  = 'X';
        char sunk = '*';

        gameBoard = createGameBoard (gameBoardSize, water, destroyer, submarine, boat);

    }

    public static char[][] createGameBoard(int gameBoardSize, char water, int[] destroyer, int[] submarine, int[] boat) {
        char[][] gameBoard = new char[gameBoardSize][gameBoardSize];

        for (char[] row: gameBoard) {
            Arrays.fill(row, water);
        }

        placeDestroyer (gameBoard, gameBoardSize, destroyer);
        placeSubmarine (gameBoard, gameBoardSize, submarine, water);
        placeBoat (gameBoard, gameBoardSize, boat, water);

        return gameBoard;
    }
    public static boolean chooseShipDirection() {
        Random random = new Random();
        return random.nextBoolean();
    }
    public static void placeDestroyer(char[][] gameBoard, int gameBoardSize, int[] destroyer){
        Random random = new Random();
        int placedDestroyerNumber = 0;
        while (placedDestroyerNumber != destroyer[2]) {
            if (chooseShipDirection()) {
                int randomRawNumber = random.nextInt(gameBoardSize - 2);
                int randomColumnNumber = random.nextInt(gameBoardSize);

                for (int j = 0; j < 3; j++) {
                    gameBoard[randomRawNumber + j][randomColumnNumber] = (char) destroyer[0];
                }
                placedDestroyerNumber++;
            } else {
                int randomRawNumber = random.nextInt(gameBoardSize);
                int randomColumnNumber = random.nextInt(gameBoardSize - 2);

                for (int i = 0; i < 3; i++) {
                    gameBoard[randomRawNumber][randomColumnNumber + i] = (char) destroyer[0];
                }
                placedDestroyerNumber++;
            }
        }
    }
    public static void placeSubmarine (char[][] gameBoard, int gameBoardSize, int[] submarine, char water)
    {
        Random random = new Random();
        int placedSubmarineNumber = 0;
        boolean canPlaceShip = true;

        while (placedSubmarineNumber != submarine[2]) {
            if (chooseShipDirection()) {
                int randomRawNumber = random.nextInt(gameBoardSize - 1);
                int randomColumnNumber = random.nextInt(gameBoardSize);

                if (randomRawNumber == 0) {
                    for (int j = 0; j < 3; j++) {
                        if (randomColumnNumber == 0) {
                            for (int i = 0; i < 2; i++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else if (randomColumnNumber + 1 == gameBoardSize) {
                            for (int i = -1; i < 1; i++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else {
                            for (int i = -1; i < 2; i++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        }
                    }
                } else if (randomColumnNumber + 2 == gameBoardSize) {
                    for (int j = -1; j < 2; j++) {
                        if (randomColumnNumber == 0) {
                            for (int i = 0; i < 2; i++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else if (randomColumnNumber + 1 == gameBoardSize) {
                            for (int i = -1; i < 1; i++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else {
                            for (int i = -1; i < 2; i++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        }
                    }
                } else if (randomRawNumber + 2 < gameBoardSize) {
                    for (int j = -1; j < 3; j++) {
                        if (randomColumnNumber == 0) {
                            for (int i = 0; i < 2; i++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else if (randomColumnNumber + 1 == gameBoardSize) {
                            for (int i = -1; i < 1; i++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else {
                            for (int i = -1; i < 2; i++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        }
                    }
                } else {
                    canPlaceShip = false;
                }

                if (canPlaceShip) {
                    for (int j = 0; j < 2; j++) {
                        gameBoard[randomRawNumber + j][randomColumnNumber] = (char) submarine[0];
                    }
                    placedSubmarineNumber++;
                }

            } else {
                int randomRawNumber = random.nextInt(gameBoardSize);
                int randomColumnNumber = random.nextInt(gameBoardSize - 1);

                if (randomColumnNumber == 0) {
                    for (int i = 0; i < 3; i++) {
                        if (randomRawNumber == 0) {
                            for (int j = 0; j < 2; j++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else if (randomRawNumber + 1 == gameBoardSize) {
                            for (int j = -1; j < 1; j++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else {
                            for (int j = -1; j < 2; j++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        }
                    }
                } else if (randomColumnNumber + 2 == gameBoardSize) {
                    for (int i = -1; i < 2; i++) {
                        if (randomRawNumber == 0) {
                            for (int j = 0; j < 2; j++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else if (randomRawNumber + 1 == gameBoardSize) {
                            for (int j = -1; j < 1; j++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else {
                            for (int j = -1; j < 2; j++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        }
                    }
                } else if (randomColumnNumber + 2 < gameBoardSize) {
                    for (int i = -1; i < 3; i++) {
                        if (randomRawNumber == 0) {
                            for (int j = 0; j < 2; j++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else if (randomRawNumber + 1 == gameBoardSize) {
                            for (int j = -1; j < 1; j++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else {
                            for (int j = -1; j < 2; j++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        }
                    }
                } else {
                    canPlaceShip = false;
                }

                if (canPlaceShip) {
                    for (int i = 0; i < 2; i++) {
                        gameBoard[randomRawNumber][randomColumnNumber + i] = (char) submarine[0];
                    }
                    placedSubmarineNumber++;
                }
            }
            canPlaceShip = true;
        }
    }
    /*
    public static void placeShip (char[][] gameBoard, int gameBoardSize, int ship[], char water)
    {
        Random random = new Random();
        int placedShipNumber = 0;
        boolean canPlaceShip = true;

        while (placedShipNumber != ship[2]) {
            if (chooseShipDirection()) {
                int randomRawNumber = random.nextInt(gameBoardSize - 1);
                int randomColumnNumber = random.nextInt(gameBoardSize);

                if (randomRawNumber == 0) {
                    for (int j = 0; j <= ship[1]; j++) {
                        if (randomColumnNumber == 0) {
                            for (int i = 0; i < 2; i++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else if (randomColumnNumber + 1 == gameBoardSize) {
                            for (int i = -1; i < 1; i++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else if (randomColumnNumber + 1 < gameBoardSize) {
                            for (int i = -1; i < 2; i++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else {
                            canPlaceShip = false;
                        }
                    }
                } else if (randomColumnNumber + ship[1] == gameBoardSize) {
                    for (int j = -1; j <= ship[1]; j++) {
                        if (randomColumnNumber == 0) {
                            for (int i = 0; i < 2; i++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else if (randomColumnNumber + 1 == gameBoardSize) {
                            for (int i = -1; i < 1; i++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else if (randomColumnNumber + 1 < gameBoardSize){
                            for (int i = -1; i < 2; i++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else {
                            canPlaceShip = false;
                        }
                    }
                } else if (randomRawNumber + ship[1] < gameBoardSize) {
                    for (int j = -1; j <= ship[1]; j++) {
                        if (randomColumnNumber == 0) {
                            for (int i = 0; i < 2; i++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else if (randomColumnNumber + 1 == gameBoardSize) {
                            for (int i = -1; i < 1; i++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else if (randomColumnNumber + 1 < gameBoardSize) {
                            for (int i = -1; i < 2; i++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else {
                            canPlaceShip = false;
                        }
                    }
                } else {
                    canPlaceShip = false;
                }

                if (canPlaceShip) {
                    for (int j = 0; j < ship[1]; j++) {
                        gameBoard[randomRawNumber + j][randomColumnNumber] = (char) ship[0];
                    }
                    placedShipNumber++;
                }

            } else {
                int randomRawNumber = random.nextInt(gameBoardSize);
                int randomColumnNumber = random.nextInt(gameBoardSize - 1);

                if (randomColumnNumber == 0) {
                    for (int i = 0; i <= ship[1]; i++) {
                        if (randomRawNumber == 0) {
                            for (int j = 0; j < 2; j++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else if (randomRawNumber + 1 == gameBoardSize) {
                            for (int j = -1; j < 1; j++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else if (randomRawNumber + 1 < gameBoardSize) {
                            for (int j = -1; j < 2; j++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else {
                            canPlaceShip = false;
                        }
                    }
                } else if (randomColumnNumber + ship[1] == gameBoardSize) {
                    for (int i = -1; i <= ship[1]; i++) {
                        if (randomRawNumber == 0) {
                            for (int j = 0; j < 2; j++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else if (randomRawNumber + 1 == gameBoardSize) {
                            for (int j = -1; j < 1; j++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else if (randomRawNumber + 1 < gameBoardSize) {
                            for (int j = -1; j < 2; j++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else {
                            canPlaceShip = false;
                        }
                    }
                } else if (randomColumnNumber + ship[1] < gameBoardSize) {
                    for (int i = -1; i <= ship[1]; i++) {
                        if (randomRawNumber == 0) {
                            for (int j = 0; j < 2; j++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else if (randomRawNumber + 1 == gameBoardSize) {
                            for (int j = -1; j < 1; j++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else if (randomRawNumber + 1 < gameBoardSize){
                            for (int j = -1; j < 2; j++) {
                                if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                    canPlaceShip = false;
                            }
                        } else {
                            canPlaceShip = false;
                        }
                    }
                } else {
                    canPlaceShip = false;
                }

                if (canPlaceShip) {
                    for (int i = 0; i < ship[1]; i++) {
                        gameBoard[randomRawNumber][randomColumnNumber + i] = (char) ship[0];
                    }
                    placedShipNumber++;
                }
            }
            canPlaceShip = true;
        }
    }

     */
    public static void placeBoat (char[][] gameBoard, int gameBoardSize, int[] boat, char water) {
        Random random = new Random();
        int placedBoatNumber = 0;
        boolean canPlaceShip = true;

        while (placedBoatNumber != boat[2]) {
            int randomRawNumber = random.nextInt(gameBoardSize);
            int randomColumnNumber = random.nextInt(gameBoardSize);

            if (randomRawNumber == 0) {
                for (int j = 0; j < 2; j++) {
                    if (randomColumnNumber == 0) {
                        for (int i = 0; i < 2; i++) {
                            if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                canPlaceShip = false;
                        }
                    } else if (randomColumnNumber + 1 == gameBoardSize) {
                        for (int i = -1; i < 1; i++) {
                            if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                canPlaceShip = false;
                        }
                    } else {
                        for (int i = -1; i < 2; i++) {
                            if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                canPlaceShip = false;
                        }
                    }
                }
            } else if (randomRawNumber + 1 == gameBoardSize) {
                for (int j = -1; j < 1; j++) {
                    if (randomColumnNumber == 0) {
                        for (int i = 0; i < 2; i++) {
                            if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                canPlaceShip = false;
                        }
                    } else if (randomColumnNumber + 1 == gameBoardSize) {
                        for (int i = -1; i < 1; i++) {
                            if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                canPlaceShip = false;
                        }
                    } else {
                        for (int i = -1; i < 2; i++) {
                            if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                canPlaceShip = false;
                        }
                    }
                }
            } else if (randomRawNumber + 1 < gameBoardSize) {
                for (int j = -1; j < 2; j++) {
                    if (randomColumnNumber == 0) {
                        for (int i = 0; i < 2; i++) {
                            if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                canPlaceShip = false;
                        }
                    } else if (randomColumnNumber + 1 == gameBoardSize) {
                        for (int i = -1; i < 1; i++) {
                            if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                canPlaceShip = false;
                        }
                    } else {
                        for (int i = -1; i < 2; i++) {
                            if (gameBoard[randomRawNumber + j][randomColumnNumber + i] != water)
                                canPlaceShip = false;
                        }
                    }
                }
            } else {
                canPlaceShip = false;
            }

            if (canPlaceShip) {
                gameBoard[randomRawNumber][randomColumnNumber] = (char) boat[0];
                placedBoatNumber++;
            }

            canPlaceShip = true;
        }
    }
}