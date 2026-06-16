package ru.dstu.labyrinths.search;

import ru.dstu.labyrinths.core.Cell;

public class AStarPathFinder extends BestFirstPathFinder {
    @Override
    protected int priority(int distance, Cell cell, Cell finish) {
        return distance + manhattan(cell, finish);
    }
}
