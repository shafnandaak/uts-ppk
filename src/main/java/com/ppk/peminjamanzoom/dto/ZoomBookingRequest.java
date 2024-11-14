package com.ppk.peminjamanzoom.dto;

import lombok.Data;

@Data
public class ZoomBookingRequest {
    private Long memberId;
    private Long zoomRoomId;
    private String meetingTitle;
    private String date;
    private String time;

    public Long getMemberId() {
        return memberId;
    }

    public Long getZoomRoomId() {
        return zoomRoomId;
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

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setZoomRoomId(Long zoomRoomId) {
        this.zoomRoomId = zoomRoomId;
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
