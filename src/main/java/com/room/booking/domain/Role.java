package com.room.booking.domain;

/**
 * Created by Alexey on 19.07.2017.
 */
public enum Role {

    ADMIN(1),
    USER(2);

    private Integer id;

    Role(Integer num) {
        this.id = num;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
