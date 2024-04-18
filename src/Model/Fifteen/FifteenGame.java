package Model.Fifteen;

import Model.Abstract.Game;
import Model.Abstract.Solver;
import Model.PlayMode;
import jdk.jshell.spi.ExecutionControl;

public class FifteenGame extends Game {
    public FifteenGame(PlayMode mode) {
        super(mode);
        board = new FifteenBoard();
        solver = new Solver(new AutomaticFifteenSolveStrategy(board));
    }

    @Override
    public void start() throws ExecutionControl.NotImplementedException {
        board.generate();
        play();
    }
}
