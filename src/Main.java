import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

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

        playOnePlayerMode (gameBoard, gameBoardSize, water, destroyer, submarine, boat, miss, hit, sunk, destroyerNumber, submarineNumber, boatNumber);

    }

    public static void playOnePlayerMode (char[][] gameBoard, int gameBoardSize, char water, int[] destroyer, int[] submarine, int[] boat, char miss, char hit, char sunk, int destroyerNumber, int submarineNumber, int boatNumber) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

        while (checkIsThereAnyShip (gameBoard, destroyerNumber, submarineNumber, boatNumber, hit, sunk))
        {
            //showAllGameBoard(gameBoard);
            showGameBoard(gameBoard, water, destroyer, submarine, boat);

            System.out.print("Enter the coordinate: ");
            String estimatedCoordinate = scanner.nextLine();
            clearScreen();

            showMessage(gameBoard, water, boat,  miss, sunk, hit, estimatedCoordinate);
            updateGameBoard(gameBoard, gameBoardSize, water, destroyer, submarine, boat, miss, hit, sunk, estimatedCoordinate);
        }

    }

    public static boolean checkIsThereAnyShip (char[][] gameBoard, int destroyerNumber, int submarineNumber, int boatNumber, char hit, char sunk) {
        int hitNumber = destroyerNumber * 3 + submarineNumber * 2 + boatNumber;
        int hitShipNumber = 0;

        for (char[] row: gameBoard) {
            for (char column: row) {
                if (column == hit || column == sunk)
                    hitShipNumber++;
            }
        }

        return hitNumber != hitShipNumber;
    }

    public static void showGameBoard (char[][] gameBoard, char water, int[] destroyer, int[] submarine, int[] boat) {
        int rawNumber = 1;
        char columnNumber = 'A';

        System.out.print("  ");
        for (int i = 0; i <  gameBoard.length; i++)
        {
            System.out.print(columnNumber++ + " ");
        }
        System.out.println();

        for (char[] row: gameBoard) {
            System.out.print(rawNumber++ + " ");
            for (char column: row) {

                if (column == (char)destroyer[0] || column == (char)submarine[0] || column == (char)boat[0])
                {
                    System.out.print(water + " ");
                } else {
                    System.out.print(column + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void showAllGameBoard (char[][] gameBoard) {
        clearScreen();

        for (char[] row: gameBoard) {
            for (char column: row) {
                System.out.print(column + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void updateGameBoard (char[][] gameBoard, int gameBoardSize, char water, int[] destroyer, int[] submarine, int[] boat, char miss, char hit, char sunk, String estimatedCoordinate) {
        char destroyerChar = (char) destroyer[0];
        char submarineChar = (char) submarine[0];
        char boatChar = (char) boat[0];

        int rawForUpdate = checkCoordinateRaw(estimatedCoordinate);
        int columnForUpdate = checkCoordinateColumn(estimatedCoordinate);
        boolean isShipSunk = true;

        if (gameBoard[rawForUpdate][columnForUpdate] == water) {
            gameBoard[rawForUpdate][columnForUpdate] = miss;
        }
        else if (gameBoard[rawForUpdate][columnForUpdate] == destroyerChar || gameBoard[rawForUpdate][columnForUpdate] == submarineChar
                || gameBoard[rawForUpdate][columnForUpdate] == boatChar){
            gameBoard[rawForUpdate][columnForUpdate] = hit;

            while (isShipSunk && rawForUpdate < gameBoardSize && gameBoard[rawForUpdate][columnForUpdate] != water && gameBoard[rawForUpdate][columnForUpdate] != miss) {
                isShipSunk = gameBoard[rawForUpdate][columnForUpdate] != destroyerChar && gameBoard[rawForUpdate][columnForUpdate] != submarineChar;
                System.out.println(1);
                rawForUpdate++;
            }

            rawForUpdate = checkCoordinateRaw(estimatedCoordinate);
            while (isShipSunk && rawForUpdate >= 0 && gameBoard[rawForUpdate][columnForUpdate] != water && gameBoard[rawForUpdate][columnForUpdate] != miss) {
                isShipSunk = gameBoard[rawForUpdate][columnForUpdate] != destroyerChar && gameBoard[rawForUpdate][columnForUpdate] != submarineChar;
                System.out.println(2);
                rawForUpdate--;
            }

            rawForUpdate = checkCoordinateRaw(estimatedCoordinate);
            while (isShipSunk && columnForUpdate < gameBoardSize && gameBoard[rawForUpdate][columnForUpdate] != water && gameBoard[rawForUpdate][columnForUpdate] != miss) {
                isShipSunk = gameBoard[rawForUpdate][columnForUpdate] != destroyerChar && gameBoard[rawForUpdate][columnForUpdate] != submarineChar;
                System.out.println(3);
                columnForUpdate++;
            }

            columnForUpdate = checkCoordinateColumn(estimatedCoordinate);
            while (isShipSunk && columnForUpdate >= 0 && gameBoard[rawForUpdate][columnForUpdate] != water && gameBoard[rawForUpdate][columnForUpdate] != miss) {
                isShipSunk = gameBoard[rawForUpdate][columnForUpdate] != destroyerChar && gameBoard[rawForUpdate][columnForUpdate] != submarineChar;
                System.out.println(4);
                columnForUpdate--;
            }

            if (isShipSunk) {
                rawForUpdate = checkCoordinateRaw(estimatedCoordinate);
                columnForUpdate = checkCoordinateColumn(estimatedCoordinate);

                gameBoard[rawForUpdate][columnForUpdate] = sunk;
                System.out.println("A");
                while (rawForUpdate < gameBoardSize && gameBoard[rawForUpdate][columnForUpdate] != water && gameBoard[rawForUpdate][columnForUpdate] != miss) {
                    if (gameBoard[rawForUpdate][columnForUpdate] == hit) {
                        gameBoard[rawForUpdate][columnForUpdate] = sunk;
                    }
                    rawForUpdate++;
                    System.out.println("AA");
                }
                System.out.println("B");
                rawForUpdate = checkCoordinateRaw(estimatedCoordinate);
                while (rawForUpdate >= 0 && gameBoard[rawForUpdate][columnForUpdate] != water && gameBoard[rawForUpdate][columnForUpdate] != miss) {
                    if (gameBoard[rawForUpdate][columnForUpdate] == hit) {
                        gameBoard[rawForUpdate][columnForUpdate] = sunk;
                    }
                    rawForUpdate--;
                    System.out.println("BB");
                }
                System.out.println("C");
                rawForUpdate = checkCoordinateRaw(estimatedCoordinate);
                columnForUpdate = checkCoordinateColumn(estimatedCoordinate);
                while (columnForUpdate < gameBoardSize && gameBoard[rawForUpdate][columnForUpdate] != water && gameBoard[rawForUpdate][columnForUpdate] != miss) {
                    if (gameBoard[rawForUpdate][columnForUpdate] == hit) {
                        gameBoard[rawForUpdate][columnForUpdate] = sunk;
                    }
                    columnForUpdate++;
                    System.out.println("CC");
                }
                System.out.println("D");
                columnForUpdate = checkCoordinateColumn(estimatedCoordinate);
                while (columnForUpdate >= 0 && gameBoard[rawForUpdate][columnForUpdate] != water && gameBoard[rawForUpdate][columnForUpdate] != miss) {
                    if (gameBoard[rawForUpdate][columnForUpdate] == hit) {
                        gameBoard[rawForUpdate][columnForUpdate] = sunk;
                    }
                    columnForUpdate--;
                    System.out.println("DD");
                }
            }
        }
    }

    public static int checkCoordinateRaw(String estimatedCoordinate) {
        int firstRawInChar = 49;
        int raw = 0;
        int estimatedRawNumber = estimatedCoordinate.charAt(1);

        if (estimatedRawNumber > 48 && estimatedRawNumber < 58) {
            while (firstRawInChar != estimatedRawNumber) {
                raw++;
                firstRawInChar++;
            }
        } else {
            estimatedRawNumber = estimatedCoordinate.charAt(0);

            while (firstRawInChar != estimatedRawNumber) {
                raw++;
                firstRawInChar++;
            }
        }
        return raw;
    }
    public static int checkCoordinateColumn(String estimatedCoordinate) {
        int firstColumn = 65;
        int column = 0;
        int estimatedColumnNumber = estimatedCoordinate.charAt(0);

        if (estimatedColumnNumber > 64 && estimatedColumnNumber < 74) {
            while (firstColumn != estimatedColumnNumber) {
                column++;
                firstColumn++;
            }
        } else {
            estimatedColumnNumber = estimatedCoordinate.charAt(1);

            while (firstColumn != estimatedColumnNumber) {
                column++;
                firstColumn++;
            }
        }
        return column;
    }
    public static void showMessage(char[][] gameBoard, char water, int[] boat, char miss, char sunk, char hit, String estimatedCoordinate){
        if (gameBoard[checkCoordinateRaw(estimatedCoordinate)][checkCoordinateColumn(estimatedCoordinate)] == water)
        {
            System.out.println("You missed. Try again!");
        } else if (gameBoard[checkCoordinateRaw(estimatedCoordinate)][checkCoordinateColumn(estimatedCoordinate)] == (char) boat[0]) {
            System.out.println("You sank the boat. Congratulation!");
        } else if (gameBoard[checkCoordinateRaw(estimatedCoordinate)][checkCoordinateColumn(estimatedCoordinate)] == hit) {
            System.out.println("You already hit this ship.");
        } else if (gameBoard[checkCoordinateRaw(estimatedCoordinate)][checkCoordinateColumn(estimatedCoordinate)] == sunk) {
            System.out.println("This ship already sunk.");
        } else if (gameBoard[checkCoordinateRaw(estimatedCoordinate)][checkCoordinateColumn(estimatedCoordinate)] == miss) {
            System.out.println("You already chose this coordinate.");
        } else {
            System.out.println("You hit the ship!");
        }
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