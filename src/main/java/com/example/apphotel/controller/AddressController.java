package com.example.apphotel.controller;

import com.example.apphotel.entity.Address;
import com.example.apphotel.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addres")
public class AddressController {
    @Autowired
    AddressRepository addressRepository;
    @PutMapping("/addAddress")
    public String add(@RequestBody Address addressPayload){
        Address address=new Address();
        address.setRegion(addressPayload.getRegion());
        address.setDistrict(addressPayload.getDistrict());
        address.setStreet(addressPayload.getStreet());
        address.setHomeNumber(addressPayload.getHomeNumber());
        addressRepository.save(address);
        return "Address added";
    }
    @GetMapping("/showAllAddress")
    public List<Address> get(){
        return addressRepository.findAll();
    }
    @PutMapping("/editAddress/{id}")
    public String edit(@RequestBody Address addressDto, @PathVariable Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()){
            return "Not found";
        }
        Address address = optionalAddress.get();
        address.setRegion(addressDto.getRegion());
        address.setDistrict(addressDto.getDistrict());
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        addressRepository.save(address);
        return "Address successfull edited";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        addressRepository.deleteById(id);
        return "deleted";
    }
}
