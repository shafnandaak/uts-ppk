package com.ppk.peminjamanzoom.service;

import com.ppk.peminjamanzoom.dto.ZoomRoomDto;
import com.ppk.peminjamanzoom.entity.ZoomRoom;
import com.ppk.peminjamanzoom.mapper.ZoomRoomMapper;
import com.ppk.peminjamanzoom.repository.ZoomRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ZoomRoomServiceImpl implements ZoomRoomService {

    @Autowired
    private ZoomRoomRepository zoomRoomRepository;

    @Override
    public ZoomRoomDto createZoomRoom(ZoomRoomDto zoomRoomDto) {
        ZoomRoom zoomRoom = ZoomRoomMapper.toEntity(zoomRoomDto);
        zoomRoom = zoomRoomRepository.save(zoomRoom);
        return ZoomRoomMapper.toDto(zoomRoom);
    }

    @Override
    public List<ZoomRoomDto> getAllZoomRooms() {
        List<ZoomRoom> zoomRooms = this.zoomRoomRepository.findAll();
        return zoomRooms.stream()
                .map(ZoomRoomMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ZoomRoomDto getZoomRoomById(Long id) {
        Optional<ZoomRoom> zoomRoom = zoomRoomRepository.findById(id);
        return zoomRoom.map(ZoomRoomMapper::toDto).orElse(null);
    }

    @Override
    public void deleteZoomRoom(Long id) {
        zoomRoomRepository.deleteById(id);
    }

    @Override
    public ZoomRoomDto updateZoomRoom(Long id, ZoomRoomDto zoomRoomDto) {
        // Cari ZoomRoom berdasarkan ID
        Optional<ZoomRoom> existingRoomOptional = zoomRoomRepository.findById(id);

        if (!existingRoomOptional.isPresent()) {
            throw new RuntimeException("ZoomRoom not found with ID: " + id);
        }

        // Update properties dari ZoomRoom yang ada
        ZoomRoom existingRoom = existingRoomOptional.get();
        existingRoom.setRoomID(zoomRoomDto.getRoomID());
        existingRoom.setRoomName(zoomRoomDto.getRoomName());
        existingRoom.setHost(zoomRoomDto.getHost());

        // Simpan perubahan
        ZoomRoom updatedRoom = zoomRoomRepository.save(existingRoom);

        // Kembalikan ZoomRoomDto yang sudah diperbarui
        return ZoomRoomMapper.toDto(updatedRoom);
    }
}
