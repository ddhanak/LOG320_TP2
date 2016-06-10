package com.company;

public class Move {
    private Position start;
    private Position end;

    public Move(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    /**
     * The position jumped over between start and end
     */
    public Position getMiddle() {
        if (end.X > start.X)
            return new Position(start.X + 1, start.Y);

        if (end.X < start.X)
            return new Position(start.X - 1, start.Y);

        if (end.Y > start.Y)
            return new Position(start.X, start.Y + 1);

        if (end.Y < start.Y)
            return new Position(start.X, start.Y - 1);

        return null;
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }
}
