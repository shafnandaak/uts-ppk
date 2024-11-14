package com.ppk.peminjamanzoom.mapper;

import com.ppk.peminjamanzoom.dto.ZoomBookingRequest;
import com.ppk.peminjamanzoom.dto.ZoomBookingResponse;
import com.ppk.peminjamanzoom.entity.ZoomBooking;

public class ZoomBookingMapper {
    public static ZoomBooking toEntity(ZoomBookingRequest request) {
        if (request == null) return null;

        ZoomBooking zoomBooking = new ZoomBooking();
        zoomBooking.setMeetingTitle(request.getMeetingTitle());
        zoomBooking.setDate(request.getDate());
        zoomBooking.setTime(request.getTime());
        return zoomBooking;
    }

    public static ZoomBookingResponse toResponse(ZoomBooking zoomBooking) {
        if (zoomBooking == null) return null;

        ZoomBookingResponse response = new ZoomBookingResponse();
        response.setId(zoomBooking.getId());
        response.setMeetingTitle(zoomBooking.getMeetingTitle());
        response.setDate(zoomBooking.getDate());
        response.setTime(zoomBooking.getTime());
        return response;
    }
}
