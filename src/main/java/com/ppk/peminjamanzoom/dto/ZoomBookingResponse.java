package com.ppk.peminjamanzoom.dto;

import lombok.Data;

@Data
public class ZoomBookingResponse {
    private Long id;
    private String meetingTitle;
    private String date;
    private String time;

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
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
}
