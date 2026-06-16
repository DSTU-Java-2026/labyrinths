package ru.dstu.labyrinths.search;

import ru.dstu.labyrinths.core.Cell;

public class DijkstraPathFinder extends BestFirstPathFinder {
    @Override
    protected int priority(int distance, Cell cell, Cell finish) {
        return distance;
    }
}
