package ru.dstu.labyrinths;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

class SolverIntegrationTest {

    @ParameterizedTest
    @ValueSource(strings = {"dijkstra", "astar"})
    void shouldSolveMazeCorrectly(String algorithm) throws Exception {
        Path input = Files.createTempFile("maze", ".txt");
        Path output = Files.createTempFile("solved", ".txt");

        input.toFile().deleteOnExit();
        output.toFile().deleteOnExit();

        String maze = """
                #####################
                #S          #   #   #
                # ####### ##### # # #
                #     #   #     # # #
                ##### # ### ##### # #
                # #   #   # #     # #
                # # ##### # ##### # #
                # #     # #       # #
                # ##### # ######### #
                #       #F    #   # #
                # ######### # # ### #
                #     #     #   #   #
                # ##### ####### # ###
                # #   # # #   # # # #
                ### # # # # # # # # #
                #   # # #   #   # # #
                # ### # ##### ### # #
                #   #   #   #   # # #
                # # ##### # ##### # #
                # #       #         #
                #####################
                """;

        String expected = """
                #####################
                #S........  #   #   #
                # #######.##### # # #
                #     #...#     # # #
                ##### #.### ##### # #
                # #   #...# #     # #
                # # #####.# ##### # #
                # #     #.#       # #
                # ##### #.######### #
                #       #F    #   # #
                # ######### # # ### #
                #     #     #   #   #
                # ##### ####### # ###
                # #   # # #   # # # #
                ### # # # # # # # # #
                #   # # #   #   # # #
                # ### # ##### ### # #
                #   #   #   #   # # #
                # # ##### # ##### # #
                # #       #         #
                #####################
                """;

        Files.writeString(input, maze);

        String[] args = {
                algorithm,
                input.toString(),
                output.toString(),
                "21",
                "21"
        };

        Main.main(args);

        assertTrue(Files.exists(output));

        String actual = Files.readString(output);
        assertEquals(expected.trim(), actual.trim());
    }
}