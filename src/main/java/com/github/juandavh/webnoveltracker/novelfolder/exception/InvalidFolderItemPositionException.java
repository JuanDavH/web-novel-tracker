package com.github.juandavh.webnoveltracker.novelfolder.exception;

public class InvalidFolderItemPositionException extends RuntimeException {
    public InvalidFolderItemPositionException(int newPosition, int maxPosition) {
        super("Position " + newPosition + " is out of bounds: must be between 0 and " + maxPosition);
    }
}
