package ru.dstu.labyrinths.core;

import java.util.ArrayList;
import java.util.List;

public class Labyrinth {
    private final char[][] cells;
    private final Cell start;
    private final Cell finish;

    public Labyrinth(char[][] cells, Cell start, Cell finish) {
        this.cells = cells;
        this.start = start;
        this.finish = finish;
    }

    public Cell start() {
        return start;
    }

    public Cell finish() {
        return finish;
    }

    public List<Cell> exitsFrom(Cell cell) {
        List<Cell> result = new ArrayList<>(4);

        addIfOpen(result, cell.row(), cell.column() + 1);
        addIfOpen(result, cell.row() + 1, cell.column());
        addIfOpen(result, cell.row(), cell.column() - 1);
        addIfOpen(result, cell.row() - 1, cell.column());

        return result;
    }

    public char[][] snapshot() {
        char[][] copy = new char[cells.length][cells[0].length];
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[row].length; column++) {
                copy[row][column] = cells[row][column];
            }
        }
        return copy;
    }

    private void addIfOpen(List<Cell> result, int row, int column) {
        if (row < 0 || row >= cells.length || column < 0 || column >= cells[0].length) {
            return;
        }

        char value = cells[row][column];
        if (value == ' ' || value == 'S' || value == 'F') {
            result.add(new Cell(row, column));
        }
    }
}
