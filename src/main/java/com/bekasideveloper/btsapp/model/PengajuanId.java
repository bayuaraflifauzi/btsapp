package com.bekasideveloper.btsapp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PengajuanId implements Serializable {
    @Column(name = "id_pengajuan")
    private Long idPengajuan;
    @Column(name = "user_id")
    private String userId;

    public PengajuanId() {
    }

    public PengajuanId(Long idPengajuan, String userId) {
        this.idPengajuan = idPengajuan;
        this.userId = userId;
    }

    public Long getIdPengajuan() {
        return idPengajuan;
    }

    public void setIdPengajuan(Long idPengajuan) {
        this.idPengajuan = idPengajuan;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PengajuanId that = (PengajuanId) o;
        return idPengajuan.equals(that.idPengajuan) &&
                userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPengajuan, userId);
    }
}
