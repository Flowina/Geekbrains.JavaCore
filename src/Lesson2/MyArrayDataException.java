package Lesson2;

public class MyArrayDataException extends Throwable {
    int row;
    int column;

    public MyArrayDataException(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
