package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        String[][] symbols = new String[3][3];
        fillWithEmptySpace(symbols);
        printState2(symbols);
        boolean turn = true;
        boolean finished = false;
        do {
            String line = scanner.nextLine();
            String[] coordinates = line.split(" ");
            //symbols = userInput(move1,move2,symbols);
            try {
                int coordinate1 = Integer.parseInt(coordinates[0]) - 1;
                int coordinate2 = Integer.parseInt(coordinates[1]) - 1;
                if (coordinate1 > -1 && coordinate1 < 3 && coordinate2 > -1 && coordinate2 < 3) {
                    if (symbols[coordinate1][coordinate2].equals(" ")) {
                        if (turn) {
                            symbols[coordinate1][coordinate2] = "X";
                        } else {
                            symbols[coordinate1][coordinate2] = "O";
                        }
                        printState2(symbols);
                        String status = checkWhoWins(symbols,turn);
                        if(status.equals("Draw")){
                            System.out.println(status);
                            finished = true;
                        } else if(!status.equals("Game in progress")) {
                            System.out.println(status + " wins");
                            finished = true;
                        }
                        turn = !turn;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
            }
        } while (!finished);
    }

    private static String[][] printState(String line) {
        String[][] lines = new String[3][3];
        String[] array = new String[3];
        StringBuffer sb = new StringBuffer(" ");
        System.out.println("---------");
        int count = 0;
        for (int i = 1; i < 10; i++) {
            sb.append(line.charAt(i - 1) + " ");
            array[count] = String.valueOf(line.charAt(i - 1));
            count++;
            if (i % 3 == 0) {
                System.out.printf("|%S|\n", sb);
                sb = new StringBuffer(" ");
                lines[(i / 3) - 1] = array;
                array = new String[3];
                count = 0;
            }
        }
        System.out.println("---------");
        return lines;
    }

    private static void printState2(String[][] symbols) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.printf("| %s %s %s |\n", symbols[i][0], symbols[i][1], symbols[i][2]);
        }
        System.out.println("---------");
    }

    private static String checkWhoWins(String[][] lines,boolean turn) {
        String result = "Draw";
       long spaceCount = 0;
        spaceCount += Arrays.stream(lines[0]).filter(x -> x.equals(" ")).count();
        spaceCount += Arrays.stream(lines[1]).filter(x -> x.equals(" ")).count();
        spaceCount += Arrays.stream(lines[2]).filter(x -> x.equals(" ")).count();
        boolean winner = checkingLines(lines);
        if(spaceCount>0 && !winner){
            result = "Game in progress";
        }
        if(winner){
            if(turn){
                result = "X";
            }else{
                result = "O";
            }
        }
        return result;
    }

    private static boolean checkingLines(String[][] lines){
        String symbol1=" ",symbol2=" ",symbol3=" ";
        boolean winner=false;
        for(int i=1;i<9;i++) {
            switch (i){
                case 1:{
                    symbol1 = lines[0][0];
                    symbol2 = lines[0][1];
                    symbol3 = lines[0][2];
                    break;
                }case 2:{
                    symbol1 = lines[1][0];
                    symbol2 = lines[1][1];
                    symbol3 = lines[1][2];
                    break;
                }case 3:{
                    symbol1 = lines[2][0];
                    symbol2 = lines[2][1];
                    symbol3 = lines[2][2];
                    break;
                }case 4:{
                    symbol1 = lines[0][0];
                    symbol2 = lines[1][0];
                    symbol3 = lines[2][0];
                    break;
                }case 5:{
                    symbol1 = lines[0][1];
                    symbol2 = lines[1][1];
                    symbol3 = lines[2][1];
                    break;
                }case 6:{
                    symbol1 = lines[0][2];
                    symbol2 = lines[1][2];
                    symbol3 = lines[2][2];
                    break;
                }case 7:{
                    symbol1 = lines[0][0];
                    symbol2 = lines[1][1];
                    symbol3 = lines[2][2];
                    break;
                }case 8:{
                    symbol1 = lines[0][2];
                    symbol2 = lines[1][1];
                    symbol3 = lines[2][0];
                    break;
                }
            }
            if (!symbol1.equals(" ") & !symbol2.equals(" ") & !symbol3.equals(" ")) {
                if (symbol1.equals(symbol2) & symbol2.equals(symbol3)) {
                    winner = true;
                    break;
                }
            }
        }
        return winner;
    }

    private static void fillWithEmptySpace(String[][] symbols) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                symbols[i][j] = " ";
            }
        }
    }
}
