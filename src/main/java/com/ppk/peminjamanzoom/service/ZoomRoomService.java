package com.ppk.peminjamanzoom.service;

import com.ppk.peminjamanzoom.dto.ZoomRoomDto;

import java.util.List;

public interface ZoomRoomService {
    ZoomRoomDto createZoomRoom(ZoomRoomDto zoomRoomDto);
    List<ZoomRoomDto> getAllZoomRooms();
    ZoomRoomDto getZoomRoomById(Long id);
    void deleteZoomRoom(Long Id);
    ZoomRoomDto updateZoomRoom(Long id, ZoomRoomDto zoomRoomDto);
}
