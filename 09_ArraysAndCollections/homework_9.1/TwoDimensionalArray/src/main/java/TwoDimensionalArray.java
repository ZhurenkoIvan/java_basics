public class TwoDimensionalArray {
    public static char symbol = 'X';

    public static char[][] getTwoDimensionalArray(int size) {
        char[][] twoDimensionalArray = new char[size][size];
        for (int i = 0; i < twoDimensionalArray.length; i++) {
            for (int j = 0; j < twoDimensionalArray.length; j++) {
                if (i == j || i + j == twoDimensionalArray.length - 1) {
                    twoDimensionalArray[i][j] = 'X';
                } else {
                    twoDimensionalArray[i][j] = ' ';
                }
            }
        }

        //TODO: Написать метод, который создаст двумерный массив char заданного размера.
        // массив должен содержать символ symbol по диагоналям, пример для size = 3
        // [X,  , X]
        // [ , X,  ]
        // [X,  , X]

        return twoDimensionalArray;
    }
}
