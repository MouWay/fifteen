package Model;

import Model.Abstract.Board;

public class State {
    private int g;
    private int h;
    private State parent;
    private Board board;

    public State(Board board){
        this.board = board;
        setH();
    }

    public Board getBoard(){
        return board;
    }

    public int getF(){
        return getG() + getH();
    }

    public int getG(){
        return g;
    }

    public  int getH(){
        return h;
    }

    public State getParent(){
        return parent;
    }

    public void setParent(State state){
        parent = state;
    }

    public boolean equals(State state){
        for (int i = 0; i < board.getSize() * board.getSize() - 1; i++)
            if (state.board.getTiles()[i] != this.board.getTiles()[i])
                return false;
        return true;
    }

    public boolean isTerminate(){
        return getH() == 0;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setH() {
        h = board.getHeuristic();
    }
}
