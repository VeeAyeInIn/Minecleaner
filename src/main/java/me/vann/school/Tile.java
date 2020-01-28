package me.vann.school;

public class Tile {

    private boolean hidden, flagged, mine;
    private int row, column, adjacentMines;

    public Tile(boolean mine, int row, int column) {
        this.mine = mine;
        this.row = row;
        this.column = column;

        hidden = true;
        flagged = false;
    }

    public boolean isHidden() {
        return hidden;
    }

    public Tile setHidden(boolean hidden) {
        this.hidden = hidden;
        return this;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public Tile setFlagged(boolean flagged) {
        this.flagged = flagged;
        return this;
    }

    public boolean isMine() {
        return mine;
    }

    public Tile setMine(boolean mine) {
        this.mine = mine;
        return this;
    }

    public int getRow() {
        return row;
    }

    public Tile setRow(int row) {
        this.row = row;
        return this;
    }

    public int getColumn() {
        return column;
    }

    public Tile setColumn(int column) {
        this.column = column;
        return this;
    }

    public int getAdjacentMines() {
        return adjacentMines;
    }

    public Tile setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
        return this;
    }

    @Override
    public String toString() {

        String label = "", wrap = "";

        if (hidden) {
            wrap = "?";
        }

        if (flagged) {
            wrap = "!";
        }

        if (mine) {
            label = "MINE";
        } else {
            label = String.valueOf(adjacentMines);
        }

        return wrap + label + wrap;
    }
}
