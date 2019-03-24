package com.example.demoSpringData.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Status {

    @org.springframework.data.annotation.Id
    private long statusId;
    private String code;
    private String name;

    @Id
    @Column(name = "STATUS_ID")
    public long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }

    @Basic
    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return statusId == status.statusId &&
                Objects.equals(code, status.code) &&
                Objects.equals(name, status.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(statusId, code, name);
    }
}
