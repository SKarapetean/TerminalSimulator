package org.example.fileSystem;

public enum Permission {

    none(0),
    __x(1),
    _w_(2),
    _wx(3),
    r__(4),
    r_x(5),
    rw_(6),
    rwx(7);

    private final int value;
    Permission(int value) {
        this.value = value;
    }
    public int getValue() {
        return  value;
    }

}
