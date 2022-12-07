package br.com.hotel.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Guest {

    private String name;

    private String address;

    private String phone;

    private Room room;

    public Guest(
            String name,
            String address,
            String phone,
            Room room) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.room = room;
    }
}
