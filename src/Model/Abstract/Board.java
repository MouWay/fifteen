package Model.Abstract;

public abstract class Board {
    protected int size;
    protected int[] tiles;

    public Board(int size){
        this.size = size;
        tiles = new int[size * size];
    }

    public abstract void generate();

    public abstract Iterable<Board> getPossibleMoves();

    @Override
    public String toString() {
        var result = new StringBuilder();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int number = tiles[i * size + j];
                result.append(number == 0 ? "_" : number).append(" ");
            }

            result.append("\n");
        }

        return result.toString();
    }

    public int getSize() {
        return size;
    }

    public int[] getTiles() {
        return tiles;
    }

    public abstract int getHeuristic();
}
