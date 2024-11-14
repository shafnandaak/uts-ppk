package com.ppk.peminjamanzoom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZoomRoomDto {
    private Long id;
    private String roomID;
    private String roomName;
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

