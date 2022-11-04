package com.example.apphotel.controller;

import com.example.apphotel.entity.Hotel;
import com.example.apphotel.entity.Room;
import com.example.apphotel.payload.RoomPayload;
import com.example.apphotel.repository.HotelRepository;
import com.example.apphotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;

    @PostMapping("/addroom")
    public String add(@RequestBody RoomPayload roomPayload){
        Room room=new Room();
        room.setFloor(roomPayload.getFloor());
        room.setNumber(roomPayload.getNumber());
        room.setSize(roomPayload.getSize());
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomPayload.getHotelId());
        if (!optionalHotel.isPresent()){
            return "not found";
        }
        Hotel hotel = optionalHotel.get();
        room.setHotel(hotel);
        roomRepository.save(room);
        return "Room added";

    }
    @GetMapping("/getroom/{hotelId}")
    public List<Room> getRooms(@PathVariable Integer hotelId){
        return roomRepository.findAllByHotel_Id(hotelId);
    }
    @GetMapping("/getroomspageable/{pageableId}")
    public Page<Room> getPageable(@RequestParam int page){
       Pageable pageable=PageRequest.of(page,10);
        Page<Room> roomPage = roomRepository.findAll(pageable);

        return roomPage;
    }

}
