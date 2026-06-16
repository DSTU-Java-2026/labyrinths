package ru.dstu.labyrinths.search;

import ru.dstu.labyrinths.core.Cell;
import ru.dstu.labyrinths.core.Labyrinth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public abstract class BestFirstPathFinder implements PathFinder {
    @Override
    public List<Cell> findPath(Labyrinth labyrinth) {
        Cell start = labyrinth.start();
        Cell finish = labyrinth.finish();

        Map<Cell, Integer> distance = new HashMap<>();
        Map<Cell, Cell> previous = new HashMap<>();
        PriorityQueue<Candidate> queue = new PriorityQueue<>(Comparator.comparingInt(Candidate::priority));

        distance.put(start, 0);
        queue.add(new Candidate(start, priority(0, start, finish)));

        while (!queue.isEmpty()) {
            Cell current = queue.remove().cell();

            if (current.equals(finish)) {
                return restore(previous, finish);
            }

            int currentDistance = distance.get(current);
            for (Cell next : labyrinth.exitsFrom(current)) {
                int nextDistance = currentDistance + 1;

                if (nextDistance < distance.getOrDefault(next, Integer.MAX_VALUE)) {
                    distance.put(next, nextDistance);
                    previous.put(next, current);
                    queue.add(new Candidate(next, priority(nextDistance, next, finish)));
                }
            }
        }

        throw new IllegalStateException("path does not exist");
    }

    protected abstract int priority(int distance, Cell cell, Cell finish);

    protected int manhattan(Cell a, Cell b) {
        return Math.abs(a.row() - b.row()) + Math.abs(a.column() - b.column());
    }

    private List<Cell> restore(Map<Cell, Cell> previous, Cell finish) {
        List<Cell> route = new ArrayList<>();
        Cell current = finish;

        route.add(current);
        while (previous.containsKey(current)) {
            current = previous.get(current);
            route.add(current);
        }

        Collections.reverse(route);
        return route;
    }

    private record Candidate(Cell cell, int priority) {
    }
}
