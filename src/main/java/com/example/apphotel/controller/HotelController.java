package com.example.apphotel.controller;

import com.example.apphotel.entity.Address;
import com.example.apphotel.entity.Hotel;
import com.example.apphotel.payload.HotelDTO;
import com.example.apphotel.repository.AddressRepository;
import com.example.apphotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotelhyatregency")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    AddressRepository addressRepository;
    @PostMapping("/addHotel")
    public String add(@RequestBody HotelDTO hotelDTO){
        Hotel hotel=new Hotel();
        hotel.setName(hotelDTO.getName());
        Optional<Address> optionalAddress = addressRepository.findById(hotelDTO.getAddressId());
        hotel.setAddress(optionalAddress.get());
        hotelRepository.save(hotel);
        return "Hotel added";
    }
    @GetMapping("/showAllHotels")
    public List<Hotel> getAllHotel(){
        return hotelRepository.findAll();
    }
    @PutMapping("/edithotel/{hotelId}")
    public String edit(@RequestBody HotelDTO hotelDTO, @PathVariable Integer hotelId){
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
        if (!optionalHotel.isPresent()){
            return "No such hotel exists!";
        }
        Hotel hotel = optionalHotel.get();
        hotel.setName(hotelDTO.getName());
        Optional<Address> optionalAddress = addressRepository.findById(hotelDTO.getAddressId());
        if (!optionalAddress.isPresent())
            return "Address not found";
        hotel.setAddress(optionalAddress.get());
        hotelRepository.save(hotel);
        return "Hotel edited";

    }
    @DeleteMapping("/deletehotelList/{id}")
    public String delete(@PathVariable Integer id){
        hotelRepository.deleteById(id);
        return "Hotel deleted";
    }
}
