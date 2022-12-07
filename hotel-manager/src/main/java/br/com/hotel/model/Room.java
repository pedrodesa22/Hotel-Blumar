package br.com.hotel.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Room {

    private String number;

    private Integer guestQty;

    private boolean checked;

    public Room(
            String number,
            Integer guestQty,
            boolean checked){
        this.number = number;
        this.guestQty = guestQty;
        this.checked = checked;
    }

}
