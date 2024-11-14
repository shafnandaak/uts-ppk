package com.ppk.peminjamanzoom.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.ppk.peminjamanzoom.entity.Member;
import com.ppk.peminjamanzoom.entity.ZoomBooking;
import com.ppk.peminjamanzoom.entity.ZoomRoom;
import com.ppk.peminjamanzoom.repository.MemberRepository;
import com.ppk.peminjamanzoom.repository.ZoomBookingRepository;
import com.ppk.peminjamanzoom.repository.ZoomRoomRepository;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/zoom-bookings")
public class ZoomBookingController {

    @Autowired
    private ZoomBookingRepository zoomBookingRepository;

    @Autowired
    private ZoomRoomRepository zoomRoomRepository;

    @Autowired
    private MemberRepository memberRepository;

    // Endpoint untuk membuat pemesanan Zoom
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createZoomBooking(@RequestBody ZoomBooking zoomBookingRequest, Principal principal) {
        Map<String, Object> response = new HashMap<>();

        String email = principal.getName();
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pengguna tidak ditemukan"));

        Optional<ZoomRoom> zoomRoom = zoomRoomRepository.findById(zoomBookingRequest.getZoomRoom().getId());
        if (zoomRoom.isEmpty()) {
            response.put("success", false);
            response.put("message", "Ruang Zoom dengan ID tersebut tidak ditemukan.");
            return ResponseEntity.badRequest().body(response);
        }

        List<ZoomBooking> existingBookings = zoomBookingRepository.findByZoomRoomIdAndDate(
                zoomBookingRequest.getZoomRoom().getId(), zoomBookingRequest.getDate());

        for (ZoomBooking existingBooking : existingBookings) {
            if (existingBooking.getTime().equals(zoomBookingRequest.getTime())) {
                response.put("success", false);
                response.put("message", "Ruang Zoom ini sudah dipinjam pada waktu yang sama.");
                return ResponseEntity.badRequest().body(response);
            }
        }

        zoomBookingRequest.setStatus(ZoomBooking.BookingStatus.PENDING);
        zoomBookingRequest.setMember(member);
        zoomBookingRequest.setZoomRoom(zoomRoom.get());

        ZoomBooking newBooking = zoomBookingRepository.save(zoomBookingRequest);

        response.put("success", true);
        response.put("message", "Pemesanan Zoom berhasil dibuat.");
        response.put("data", newBooking);

        return ResponseEntity.ok(response);
    }

    // Endpoint untuk menampilkan daftar pemesanan
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> listZoomBookings() {
        List<ZoomBooking> bookings = zoomBookingRepository.findAll();
        Map<String, Object> response = new HashMap<>();

        response.put("success", true);
        response.put("message", "Daftar pemesanan Zoom berhasil ditampilkan.");
        response.put("data", bookings);

        return ResponseEntity.ok(response);
    }

    // Endpoint untuk membatalkan pemesanan Zoom (hanya member)
    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @PutMapping("/cancel/{bookingId}")
    public ResponseEntity<Map<String, Object>> cancelZoomBooking(@PathVariable Long bookingId, Principal principal) {
        Map<String, Object> response = new HashMap<>();

        ZoomBooking zoomBooking = zoomBookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Peminjaman Zoom tidak ditemukan"));

        String email = principal.getName();
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pengguna tidak ditemukan"));

        if (!zoomBooking.getMember().equals(member)) {
            response.put("success", false);
            response.put("message", "Hanya member yang dapat membatalkan pemesanan mereka sendiri.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        zoomBooking.setStatus(ZoomBooking.BookingStatus.CANCELLED);
        ZoomBooking updatedBooking = zoomBookingRepository.save(zoomBooking);

        response.put("success", true);
        response.put("message", "Pemesanan Zoom berhasil dibatalkan.");
        response.put("data", updatedBooking);

        return ResponseEntity.ok(response);
    }

    // Endpoint untuk menyetujui pemesanan Zoom (hanya admin)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/approve/{bookingId}")
    public ResponseEntity<Map<String, Object>> approveZoomBooking(@PathVariable Long bookingId) {
        Map<String, Object> response = new HashMap<>();

        ZoomBooking zoomBooking = zoomBookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Peminjaman Zoom tidak ditemukan"));

        zoomBooking.setStatus(ZoomBooking.BookingStatus.RECEIVED);
        ZoomBooking updatedBooking = zoomBookingRepository.save(zoomBooking);

        response.put("success", true);
        response.put("message", "Pemesanan Zoom berhasil disetujui.");
        response.put("data", updatedBooking);

        return ResponseEntity.ok(response);
    }

    // Endpoint untuk menolak pemesanan Zoom (hanya admin)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/reject/{bookingId}")
    public ResponseEntity<Map<String, Object>> rejectZoomBooking(@PathVariable Long bookingId) {
        Map<String, Object> response = new HashMap<>();

        ZoomBooking zoomBooking = zoomBookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Peminjaman Zoom tidak ditemukan"));

        zoomBooking.setStatus(ZoomBooking.BookingStatus.REJECTED);
        ZoomBooking updatedBooking = zoomBookingRepository.save(zoomBooking);

        response.put("success", true);
        response.put("message", "Pemesanan Zoom berhasil ditolak.");
        response.put("data", updatedBooking);

        return ResponseEntity.ok(response);
    }
}
