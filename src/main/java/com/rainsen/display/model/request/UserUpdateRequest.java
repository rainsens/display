package com.rainsen.display.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserUpdateRequest {

    @NotNull
    private Long id;

    @Size(max = 256)
    private String avatar;

    @Size(max = 128)
    private String title;

    @Size(max = 500)
    private String address;

    @Size(max = 500)
    private String social;

    @Size(max = 500)
    private String interest;

    @Size(max = 500)
    private String credit;

    private boolean admin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "UserUpdateRequest{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", social='" + social + '\'' +
                ", interest='" + interest + '\'' +
                ", credit='" + credit + '\'' +
                ", admin=" + admin +
                '}';
    }
}
