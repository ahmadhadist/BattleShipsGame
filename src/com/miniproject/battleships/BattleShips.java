package com.miniproject.battleships;

import java.util.Scanner;

public class BattleShips {
    public static int row = 10;
    public static int col = 10;
    public static String[][] ocean = new String[row][col];
    public static int computerShips;
    public static int playerShips;
    public static int[][] missed = new int[row][col];


    public static void main(String[] args) {
        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println("Right now, sea is empty\n");

        oceanMap();

        deployPlayerShips();

        deployComputerShips();

        do {
            Battle();
        }while (playerShips != 0 && computerShips != 0);

        gameOver();


    }

    public static void oceanMap(){

        System.out.print("  ");
        for (int i = 0; i < col; i++)
            System.out.print(i);
        System.out.println();

        for (int i = 0; i < ocean.length; i++){
            System.out.print(i + "|");
            for (int j = 0; j < ocean[i].length; j++){
                if (ocean[i][j] == null){
                    System.out.print(" ");
                }else {
                    System.out.print(ocean[i][j]);
                }
            }
            System.out.println("|" + i);
        }

        System.out.print("  ");
        for (int i = 0; i < col; i++)
            System.out.print(i);
        System.out.println();
    }

    public static void deployPlayerShips() {
        Scanner input = new Scanner(System.in);

        System.out.println("\nPlace Your Ships!");
        playerShips = 5;
        for (int i = 0; i < playerShips; ) {
            System.out.print("Enter X coordinate for your ships: ");
            int x = input.nextInt();
            System.out.print("Enter Y coordinate for your ships: ");
            int y = input.nextInt();

            if ((x >= 0 && x < row) && (y >= 0 && y < col) && (ocean[x][y] == null)) {
                ocean[x][y] = "@";
                i++;
            } else if ((x >= 0 && x < row) && (y >= 0 && y < col) && (ocean[x][y] == "@"))
                System.out.println("You can't deploy on the same location");
            else if ((x < 0 || x >= row) || (y < 0 || y >= col))
                System.out.println("You can't place ships outside the " + row + " by " + col + " grid");


        }
        oceanMap();

    }

    public static void deployComputerShips(){
        System.out.println("\nComputer is Deploying Ships");

        computerShips = 5;
        for (int i = 0; i < computerShips; ){
            int x = (int)(Math.random() * 10);
            int y = (int)(Math.random() * 10);

            if ((x >= 0 && x < row) && (y >= 0 && y < col) && (ocean[x][y] == null)) {
                ocean[x][y] = " ";
                System.out.println(i + " ship DEPLOYED\n");
                i++;
            }

        }
        oceanMap();
    }

    public static void Battle(){
        playerTurn();
        computerTurn();
        oceanMap();

        System.out.println();
        System.out.println("Your ships: " + playerShips + " | Computer ships: " + computerShips);
        System.out.println();


    }

    public static void playerTurn(){
        System.out.println("Turn now!");
        int x = -1, y = -1;

        do {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter X Coordinate: ");
            x = input.nextInt();
            System.out.print("Enter Y Coordinate: ");
            y = input.nextInt();

            if ((x >= 0 && x < row) && (y >= 0 && y < col)){
                if (ocean[x][y] == " "){
                    System.out.println("Boom! You sunk the ship!");
                    ocean[x][y] = "!";
                    --computerShips;
                }
                else if (ocean[x][y] == "@") {
                    System.out.println("Oh no, you sunk your own ship :(");
                    ocean[x][y] = "x";
                    --playerShips;

                }
                else if (ocean[x][y] == null) {
                    System.out.println("Sorry, you missed");
                    ocean[x][y] = "-";
                }
            } else if ((x < 0 || x > row) || (y < 0 || y >= col))
                System.out.println("You can't place ships outside the map");

        }while ((x < 0 || x >= row) || (y < 0 || y >= col));
    }

    public static void computerTurn(){
        System.out.println("Computer's TURN");
        int x = -1, y = -1;
        do {
            x = (int)(Math.random() * 10);
            y = (int)(Math.random() * 10);

            if (ocean[x][y] == "@"){
                System.out.println("The Computer sunk your ship!");
                ocean[x][y] = "x";
                --playerShips;
            }else if (ocean[x][y] == " "){
                System.out.println("The Computer sunk one of its own ships");
                ocean[x][y] = "!";
                --computerShips;
            }else if (ocean[x][y] == null){
                System.out.println("Computer Missed");

                if (missed[x][y] != 1)
                    missed[x][y] = 1;

            }
        }while ((x < 0 || x >= row) || (y < 0 || y >= col));

    }

    public static void gameOver(){
        if (computerShips == 0){
            System.out.println("Congratulation, You Won the Battle!");
        }else if (playerShips == 0){
            System.out.println("You lost the battle, Let's try again!");
            System.out.println();
        }
    }



}
