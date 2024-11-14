package com.ppk.peminjamanzoom.repository;

import com.ppk.peminjamanzoom.entity.ZoomRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoomRoomRepository extends JpaRepository<ZoomRoom, Long> {
}
