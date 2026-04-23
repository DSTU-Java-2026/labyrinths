package ru.dstu.labyrinths;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

class InvalidMazeTest {

    @ParameterizedTest
    @ValueSource(strings = {
            """
            ###
            #S#
            ###
            """,

            """
            ###
            #S#
            #F#
            """,

            """
            ###
            #S#
            #O#
            #F#
            ###
            """
    })
    void shouldFailOnInvalidMazeAndCreateEmptyFile(String maze) throws Exception {
        Path input = Files.createTempFile("maze", ".txt");
        Path output = Files.createTempFile("out", ".txt");

        input.toFile().deleteOnExit();
        output.toFile().deleteOnExit();

        Files.writeString(input, maze);

        String[] args = {
                "astar",
                input.toString(),
                output.toString(),
                "5",
                "3"
        };

        Main.main(args);

        assertTrue(Files.exists(output));
        assertEquals(0, Files.size(output));
    }
}