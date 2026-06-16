package ru.dstu.labyrinths.cli;

import ru.dstu.labyrinths.core.Cell;
import ru.dstu.labyrinths.core.Labyrinth;
import ru.dstu.labyrinths.maze.LabyrinthReader;
import ru.dstu.labyrinths.maze.LabyrinthWriter;
import ru.dstu.labyrinths.search.PathFinder;

import java.nio.file.Path;
import java.util.List;

public class LabyrinthApplication {
    private final FinderCatalog catalog = new FinderCatalog();
    private final LabyrinthReader reader = new LabyrinthReader();
    private final LabyrinthWriter writer = new LabyrinthWriter();

    public void run(String[] args) {
        if (args.length == 0) {
            printAlgorithms();
            return;
        }

        if (args.length != 5) {
            System.out.println("Usage: <algorithm> <input_file> <output_file> <height> <width>");
            return;
        }

        Path input = Path.of(args[1]);
        Path output = Path.of(args[2]);

        try {
            String algorithm = args[0];
            int height = Integer.parseInt(args[3]);
            int width = Integer.parseInt(args[4]);

            Labyrinth labyrinth = reader.load(input, height, width);
            PathFinder finder = catalog.get(algorithm);
            List<Cell> path = finder.findPath(labyrinth);
            writer.solved(output, labyrinth, path);
        } catch (Exception e) {
            try {
                writer.empty(output);
            } catch (Exception ignored) {
            }
            System.out.println("Failed to solve labyrinth: " + e.getMessage());
        }
    }

    private void printAlgorithms() {
        System.out.println("Available solvers:");
        for (String name : catalog.names()) {
            System.out.println("- " + name);
        }
    }
}
