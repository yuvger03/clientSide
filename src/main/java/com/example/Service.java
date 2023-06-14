package com.example;

import com.example.Game.Tile;
import com.example.Game.Word;

public class Service {
    public String WordToString(Word word) { //make obj WORD to string- "word,row,col,vertical"
        int row=word.getRow();
        int col= word.getCol();
        String direction;
        if(word.isVertical()){
            direction="T";
        }
        else{
            direction="F";
        }
        int word_len = word.getTiles().length;
        String txt[]=new String[word_len];
        Tile tile;
        for(int i=0;i<txt.length;i++) {
            tile = word.getTiles()[i];
            txt[i] = ""+ tile.letter;
        }
        StringBuilder sb = new StringBuilder();
        for (String text : txt) {
            sb.append(text);
        }
        sb.append(",").append(row).append(",").append(col);
        sb.append(",").append(direction);
        return sb.toString();
    }

    public Word stringToWord(String wordString) {
        int length = wordString.length();
        String[] array = wordString.split(",");
        String wordText=array[0];
        int score;
        Tile[] tiles = new Tile[wordText.length()];
        for (int i = 0; i < wordText.length(); i++) {
            score = calculateScore(wordText.charAt(i));
            tiles[i] = new Tile(wordText.charAt(i), score);
        }
        int row = Integer.parseInt(array[1]);
        int col=Integer.parseInt(array[2]);
        //int row = Character.getNumericValue(wordString.charAt(length - 4));
        //int col = Character.getNumericValue(wordString.charAt(length - 3));
        boolean vertical;
        if(array[3].equals("F"))
            vertical = false;
        else vertical=true;
        //String wordText = wordString.substring(0, length - 4);

        Word word = new Word(tiles,row, col, vertical);
        return word;
    }

    public String matrixToString(Tile [][] t) {
        StringBuilder sb = new StringBuilder();
        //Tile [][] t = board.getTiles();
        for (int row = 0; row < t.length; row++) {
            for (int col = 0; col < t[row].length; col++) {
                if (t[row][col] != null) {
                    sb.append(t[row][col].letter);
                } else {
                    sb.append("n");
                }
            }
        }
        return sb.toString();
    }

    public Tile[][] stringToMatrix(String input) {
        if (input.length() != 225) {
            throw new IllegalArgumentException("Invalid input string length. Expected length: 225");
        }

        Tile[][] matrix = new Tile[15][15];
        int index = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if(input.charAt(index)!='n') {
                    matrix[row][col].letter = input.charAt(index++);
                    matrix[row][col].score = input.charAt(index++);
                }
                else {
                    matrix[row][col]=null;
                }
            }
        }
        return matrix;
    }


    private int calculateScore(char c) {
        {
            switch (Character.toUpperCase(c)) {
                case 'A':
                    return 1;
                case 'B':
                    return 3;
                case 'C':
                    return 3;
                case 'D':
                    return 2;
                case 'E':
                    return 1;
                case 'F':
                    return 4;
                case 'G':
                    return 2;
                case 'H':
                    return 4;
                case 'I':
                    return 1;
                case 'J':
                    return 8;
                case 'K':
                    return 5;
                case 'L':
                    return 1;
                case 'M':
                    return 3;
                case 'N':
                    return 1;
                case 'O':
                    return 1;
                case 'P':
                    return 3;
                case 'Q':
                    return 10;
                case 'R':
                    return 1;
                case 'S':
                    return 1;
                case 'T':
                    return 1;
                case 'U':
                    return 1;
                case 'V':
                    return 4;
                case 'W':
                    return 4;
                case 'X':
                    return 8;
                case 'Y':
                    return 4;
                case 'Z':
                    return 10;

            }
            return 0;
        }
    }


}
