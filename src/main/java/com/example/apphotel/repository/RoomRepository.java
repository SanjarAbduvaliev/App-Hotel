package com.example.apphotel.repository;

import com.example.apphotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Integer> {
    List<Room> findAllByHotel_Id(Integer hotel_id);
//    List<Room> findByHotel_Id(Integer hotel_id, Pageable pageable);
}
