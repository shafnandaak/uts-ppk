package com.ppk.peminjamanzoom.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ZoomBookingDto {
    private Long id;
    private String username;
    private String meetingTitle;
    private String date;
    private String time;
    private String status;

    public Long getId() {
        return id;
    }

    public String getUsername() { // Getter untuk username
        return username;
    }

    public String getMeetingTitle() {
        return meetingTitle;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) { // Setter untuk username
        this.username = username;
    }

    public void setMeetingTitle(String meetingTitle) {
        this.meetingTitle = meetingTitle;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
