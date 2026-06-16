package ru.dstu.labyrinths.search;

import ru.dstu.labyrinths.core.Cell;
import ru.dstu.labyrinths.core.Labyrinth;

import java.util.List;

public interface PathFinder {
    List<Cell> findPath(Labyrinth labyrinth);
}
