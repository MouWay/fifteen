import Model.Fifteen.FifteenGame;
import Model.PlayMode;
import jdk.jshell.spi.ExecutionControl;

public class Main {
    public static void main(String[] args) throws ExecutionControl.NotImplementedException {
        var game = new FifteenGame(PlayMode.Auto);
        game.start();
        game.showState();
    }
}