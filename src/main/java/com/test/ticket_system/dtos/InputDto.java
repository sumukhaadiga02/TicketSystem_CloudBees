package com.test.ticket_system.dtos;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class InputDto {

    @NonNull
    private String From_station;
    @NonNull
    private String To_station;
    @NonNull
    private User user;
    @NonNull
    private String section;
    @NonNull
    private int price_Paid;
    @NonNull
    private int seatNumber;

}
