package com.ppk.peminjamanzoom.service;

import com.ppk.peminjamanzoom.dto.ZoomBookingDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZoomBookingServiceImpl implements ZoomBookingService {

    private List<ZoomBookingDto> bookingList = new ArrayList<>();

    @Override
    public void createZoomBooking(ZoomBookingDto booking) {
        bookingList.add(booking);
    }

    @Override
    public List<ZoomBookingDto> getAllZoomBookings() {
        return bookingList;
    }

    @Override
    public List<ZoomBookingDto> searchZoomBookings(String query) {
        List<ZoomBookingDto> searchResult = new ArrayList<>();
        for (ZoomBookingDto booking : bookingList) {
            if (booking.getUsername().contains(query) || booking.getMeetingTitle().contains(query)) {
                searchResult.add(booking);
            }
        }
        return searchResult;
    }
}
