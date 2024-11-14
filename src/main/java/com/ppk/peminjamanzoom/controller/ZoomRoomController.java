package com.ppk.peminjamanzoom.controller;

import com.ppk.peminjamanzoom.dto.ZoomRoomDto;
import com.ppk.peminjamanzoom.service.ZoomRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/zoom-rooms")
public class ZoomRoomController {

    @Autowired
    private ZoomRoomService zoomRoomService;

    // Endpoint untuk menambahkan ZoomRoom baru
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createZoomRoom(@RequestBody ZoomRoomDto zoomRoomDto) {
        Map<String, Object> response = new HashMap<>();
        try {
            ZoomRoomDto createdRoom = zoomRoomService.createZoomRoom(zoomRoomDto);
            response.put("success", true);
            response.put("message", "ZoomRoom berhasil dibuat.");
            response.put("data", createdRoom);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Gagal membuat ZoomRoom.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Endpoint untuk menampilkan semua ZoomRoom
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllZoomRooms() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<ZoomRoomDto> rooms = zoomRoomService.getAllZoomRooms();
            response.put("success", true);
            response.put("message", "ZoomRoom berhasil diambil.");
            response.put("data", rooms);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Gagal mengambil daftar ZoomRoom.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Endpoint untuk mengupdate ZoomRoom berdasarkan ID
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateZoomRoom(@PathVariable Long id, @RequestBody ZoomRoomDto zoomRoomDto) {
        Map<String, Object> response = new HashMap<>();
        try {
            ZoomRoomDto updatedRoom = zoomRoomService.updateZoomRoom(id, zoomRoomDto);
            response.put("success", true);
            response.put("message", "ZoomRoom berhasil diperbarui.");
            response.put("data", updatedRoom);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Gagal memperbarui ZoomRoom.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Endpoint untuk menghapus ZoomRoom berdasarkan ID
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteZoomRoom(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            zoomRoomService.deleteZoomRoom(id);
            response.put("success", true);
            response.put("message", "ZoomRoom berhasil dihapus.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Gagal menghapus ZoomRoom.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
