package com.company;

import java.io.*;

/**
 * Created by Deep on 2016-06-01.
 */
public class FileHelper {

    public static int[][] getPuzzleFromFile(String filepath) throws IOException {

        int[][] puzzle = null;

        InputStream stream = new FileInputStream(filepath);
        InputStreamReader isr = new InputStreamReader(stream);
        BufferedReader buffer = new BufferedReader(isr);

        String line;
        int row = 0;
        int size = 0;

        while ((line = buffer.readLine()) != null) {
            String[] values = line.trim().split("");

            if (puzzle == null) {
                size = values.length;
                puzzle = new int[size][size];
            }

            for (int column = 0; column < size; column++)
            {
                puzzle[row][column] = Integer.parseInt(values[column]);
            }

            row ++;
        }

        return puzzle;
    }

}
