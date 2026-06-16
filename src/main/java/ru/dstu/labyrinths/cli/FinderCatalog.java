package ru.dstu.labyrinths.cli;

import ru.dstu.labyrinths.search.AStarPathFinder;
import ru.dstu.labyrinths.search.DijkstraPathFinder;
import ru.dstu.labyrinths.search.PathFinder;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class FinderCatalog {
    private final Map<String, PathFinder> finders = new LinkedHashMap<>();

    public FinderCatalog() {
        finders.put("dijkstra", new DijkstraPathFinder());
        finders.put("astar", new AStarPathFinder());
    }

    public Set<String> names() {
        return finders.keySet();
    }

    public PathFinder get(String name) {
        PathFinder finder = finders.get(name);
        if (finder == null) {
            throw new IllegalArgumentException("unknown algorithm");
        }
        return finder;
    }
}
