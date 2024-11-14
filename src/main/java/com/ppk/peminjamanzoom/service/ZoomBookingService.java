package com.ppk.peminjamanzoom.service;

import com.ppk.peminjamanzoom.dto.ZoomBookingDto;

import java.util.List;

public interface ZoomBookingService {
    void createZoomBooking(ZoomBookingDto booking);
    List<ZoomBookingDto> getAllZoomBookings();
    List<ZoomBookingDto> searchZoomBookings(String query);
}
