package ru.dstu.labyrinths;

import org.junit.jupiter.api.Test;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

class InvalidSizeTest {

    @Test
    void shouldFailOnWrongSizeAndCreateEmptyFile() throws Exception {
        Path input = Files.createTempFile("maze", ".txt");
        Path output = Files.createTempFile("out", ".txt");

        input.toFile().deleteOnExit();
        output.toFile().deleteOnExit();

        String maze = """
                ###
                #S#
                #F#
                ###
                """;

        Files.writeString(input, maze);

        String[] args = {
                "dijkstra",
                input.toString(),
                output.toString(),
                "10",
                "10"
        };

        Main.main(args);

        assertTrue(Files.exists(output));
        assertEquals(0, Files.size(output));
    }
}