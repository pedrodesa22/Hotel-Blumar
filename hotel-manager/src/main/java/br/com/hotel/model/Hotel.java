package br.com.hotel.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class Hotel {

    private String name;

    private String address;

    private String hotelManager;

    private String phone;

    private Integer availableRooms;

    private Room room;

    public Hotel(
            int id,
            String name,
            String address,
            String hotelManager,
            String phone,
            Integer availableRooms
    ) {
        this.name = name;
        this.address = address;
        this.hotelManager = hotelManager;
        this.phone = phone;
        this.availableRooms = availableRooms;
    }

    public void checkIn(Guest guest, Room room) {

    }

}
