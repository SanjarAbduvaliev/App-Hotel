package com.example.apphotel.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomPayload {
    private Integer number;
    private String floor;
    private String size;
    private  Integer hotelId;
}
