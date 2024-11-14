package com.ppk.peminjamanzoom.repository;

import com.ppk.peminjamanzoom.entity.ZoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZoomBookingRepository extends JpaRepository<ZoomBooking, Long> {
    List<ZoomBooking> findByZoomRoomIdAndDate(Long zoomRoomId, String date); // sesuai dengan tipe data 'date' pada entitas
    List<ZoomBooking> findByZoomRoomId(Long zoomRoomId);
}
