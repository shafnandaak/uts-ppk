package com.ppk.peminjamanzoom.mapper;

import com.ppk.peminjamanzoom.dto.ZoomRoomDto;
import com.ppk.peminjamanzoom.entity.ZoomRoom;

public class ZoomRoomMapper {
    public static ZoomRoom toEntity(ZoomRoomDto dto) {
        if (dto == null) return null;

        return ZoomRoom.builder()
                .id(dto.getId())
                .roomID(dto.getRoomID())
                .roomName(dto.getRoomName())
                .host(dto.getHost())
                .build();
    }

    public static ZoomRoomDto toDto(ZoomRoom entity) {
        if (entity == null) return null;

        return ZoomRoomDto.builder()
                .id(entity.getId())
                .roomID(entity.getRoomID())
                .roomName(entity.getRoomName())
                .host(entity.getHost())
                .build();
    }
}
