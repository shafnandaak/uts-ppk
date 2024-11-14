package com.ppk.peminjamanzoom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "zoom_rooms")
public class ZoomRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_id", nullable = false)
    private String roomID;

    @Column(name = "room_name", nullable = false)
    private String roomName;

    @Column(name = "host", nullable = false)
    private String host;

    public Long getId() {
        return id;
    }

    public String getRoomID() {
        return roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getHost() {
        return host;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
