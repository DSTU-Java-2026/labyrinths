package ru.dstu.labyrinths;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CliTest {

    @Test
    void shouldPrintAvailableAlgorithms() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(new String[]{});

        String output = out.toString();

        assertTrue(output.contains("dijkstra"));
        assertTrue(output.contains("astar"));
    }
}