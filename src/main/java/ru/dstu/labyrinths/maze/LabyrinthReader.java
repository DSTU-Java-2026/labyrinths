package ru.dstu.labyrinths.maze;

import ru.dstu.labyrinths.core.Cell;
import ru.dstu.labyrinths.core.Labyrinth;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LabyrinthReader {
    public Labyrinth load(Path file, int rows, int columns) throws IOException {
        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException("size must be positive");
        }

        List<String> lines = Files.readAllLines(file);
        if (lines.size() != rows) {
            throw new IllegalArgumentException("wrong row count");
        }

        char[][] grid = new char[rows][columns];
        Cell start = null;
        Cell finish = null;

        for (int row = 0; row < rows; row++) {
            String line = lines.get(row);
            if (line.length() != columns) {
                throw new IllegalArgumentException("wrong column count");
            }

            for (int column = 0; column < columns; column++) {
                char symbol = line.charAt(column);
                requireAllowedSymbol(symbol);

                if (symbol == 'S') {
                    if (start != null) {
                        throw new IllegalArgumentException("duplicate start");
                    }
                    start = new Cell(row, column);
                } else if (symbol == 'F') {
                    if (finish != null) {
                        throw new IllegalArgumentException("duplicate finish");
                    }
                    finish = new Cell(row, column);
                }

                grid[row][column] = symbol;
            }
        }

        requireClosedBorder(grid);
        if (start == null || finish == null) {
            throw new IllegalArgumentException("start or finish is missing");
        }

        return new Labyrinth(grid, start, finish);
    }

    private void requireAllowedSymbol(char symbol) {
        if (symbol != '#' && symbol != ' ' && symbol != 'S' && symbol != 'F') {
            throw new IllegalArgumentException("unexpected symbol");
        }
    }

    private void requireClosedBorder(char[][] grid) {
        int lastRow = grid.length - 1;
        int lastColumn = grid[0].length - 1;

        for (int column = 0; column <= lastColumn; column++) {
            if (grid[0][column] != '#' || grid[lastRow][column] != '#') {
                throw new IllegalArgumentException("border must be closed");
            }
        }

        for (int row = 0; row <= lastRow; row++) {
            if (grid[row][0] != '#' || grid[row][lastColumn] != '#') {
                throw new IllegalArgumentException("border must be closed");
            }
        }
    }
}
