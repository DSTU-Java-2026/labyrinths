package ru.dstu.labyrinths.maze;

import ru.dstu.labyrinths.core.Cell;
import ru.dstu.labyrinths.core.Labyrinth;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LabyrinthWriter {
    public void solved(Path file, Labyrinth labyrinth, List<Cell> route) throws IOException {
        char[][] picture = labyrinth.snapshot();

        for (Cell cell : route) {
            if (picture[cell.row()][cell.column()] == ' ') {
                picture[cell.row()][cell.column()] = '.';
            }
        }

        StringBuilder text = new StringBuilder();
        for (int row = 0; row < picture.length; row++) {
            if (row > 0) {
                text.append('\n');
            }
            text.append(picture[row]);
        }

        Files.writeString(file, text.toString());
    }

    public void empty(Path file) throws IOException {
        Files.writeString(file, "");
    }
}
