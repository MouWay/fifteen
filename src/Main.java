import Model.Fifteen.AStarFifteenSolveStrategy;
import Model.Fifteen.FifteenGame;
import Model.PlayMode;
import jdk.jshell.spi.ExecutionControl;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws ExecutionControl.NotImplementedException {
        var game = new FifteenGame(4, PlayMode.Auto);

        long startTime = System.currentTimeMillis();

        game.start();

        long endTime = System.currentTimeMillis();

        System.out.printf("Algorithm ended in %d seconds", (endTime - startTime) / 1000);
    }
}