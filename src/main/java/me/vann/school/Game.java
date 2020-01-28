package me.vann.school;

import java.util.concurrent.ThreadLocalRandom;

public class Game {

    /** Used to make pseudo-random decisions. */
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    private Matrix<Tile> board;
    private int mines;

    public Game(int rows, int columns, int mines) {
        board = new Matrix<>(rows, columns);
        this.mines = mines;
    }

    public void start() {

        int row = RANDOM.nextInt(board.getRows()), column = RANDOM.nextInt(board.getColumns()), counter = mines;
        while (counter > 0) {
            if (board.get(row, column) == null) {
                board.set(row, column, new Tile(true, row, column));
                counter--;
            }
            row = RANDOM.nextInt(board.getRows());
            column = RANDOM.nextInt(board.getColumns());
        }

        fillTiles();
        updateTiles();
    }

    private void fillTiles() {
        for (int row = 0; row < board.getRows(); row++) {
            for (int column = 0; column < board.getColumns(); column++) {
                if (board.get(row, column) == null) {
                    board.set(row, column, new Tile(false, row, column));
                }
            }
        }
    }

    private void updateTiles() {
        board.forEach(tile -> {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (!(i == 0 && j == 0) && board.inBounds(tile.getRow() + i, tile.getColumn() + j)) {
                        if (board.get(tile.getRow() + i, tile.getColumn() + j).isMine()) {
                            tile.setAdjacentMines(tile.getAdjacentMines() + 1);
                        }
                    }
                }
            }
        });
    }

    public void flag(int row, int column) {
        board.forEach(tile -> {
            if (tile.getRow() == row && tile.getColumn() == column) tile.setFlagged(!tile.isFlagged());
        });
    }

    public boolean flip(int row, int column) {
        if (board.get(row, column).isHidden()) {
            return board.get(row, column).setHidden(false).isMine();
        } else return false;
    }

    public Matrix<Tile> getBoard() {
        return board;
    }

    public Game setBoard(Matrix<Tile> board) {
        this.board = board;
        return this;
    }

    public int getMines() {
        return mines;
    }

    public Game setMines(int mines) {
        this.mines = mines;
        return this;
    }
}
