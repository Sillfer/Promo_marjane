package com.simplon.marjane.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "super_admin", schema = "public", catalog = "promo_marjane")
public class SuperAdminEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "sa_id")
    private long saId;
    @Basic
    @Column(name = "sa_name")
    private String saName;
    @Basic
    @Column(name = "sa_email")
    private String saEmail;
    @Basic
    @Column(name = "sa_password")
    private String saPassword;

    private static String tableName = "super_admin";

    public static String getTableName() {
        return tableName;
    }
    public long getSaId() {
        return saId;
    }

    public void setSaId(long saId) {
        this.saId = saId;
    }

    public String getSaName() {
        return saName;
    }

    public void setSaName(String saName) {
        this.saName = saName;
    }

    public String getSaEmail() {
        return saEmail;
    }

    public void setSaEmail(String saEmail) {
        this.saEmail = saEmail;
    }

    public String getSaPassword() {
        return saPassword;
    }

    public void setSaPassword(String saPassword) {
        this.saPassword = saPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuperAdminEntity that = (SuperAdminEntity) o;

        if (saId != that.saId) return false;
        if (saName != null ? !saName.equals(that.saName) : that.saName != null) return false;
        if (saEmail != null ? !saEmail.equals(that.saEmail) : that.saEmail != null) return false;
        if (saPassword != null ? !saPassword.equals(that.saPassword) : that.saPassword != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (saId ^ (saId >>> 32));
        result = 31 * result + (saName != null ? saName.hashCode() : 0);
        result = 31 * result + (saEmail != null ? saEmail.hashCode() : 0);
        result = 31 * result + (saPassword != null ? saPassword.hashCode() : 0);
        return result;
    }


}
