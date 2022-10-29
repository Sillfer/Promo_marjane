package com.simplon.marjane.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "admin", schema = "public", catalog = "promo_marjane")
public class AdminEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "a_id")
    private long aId;
    @Basic
    @Column(name = "a_name")
    private String aName;
    @Basic
    @Column(name = "a_email")
    private String aEmail;
    @Basic
    @Column(name = "a_password")
    private String aPassword;

    @OneToMany(mappedBy = "bAdmin")
    private Set<BranchEntity> branches = new LinkedHashSet<>();

    public Set<BranchEntity> getBranches() {
        return branches;
    }

    public void setBranches(Set<BranchEntity> branches) {
        this.branches = branches;
    }

    public long getaId() {
        return aId;
    }

    public void setaId(long aId) {
        this.aId = aId;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getaEmail() {
        return aEmail;
    }

    public void setaEmail(String aEmail) {
        this.aEmail = aEmail;
    }

    public String getaPassword() {
        return aPassword;
    }

    public void setaPassword(String aPassword) {
        this.aPassword = aPassword;
    }

    //Constructor with parameters
    public AdminEntity( String aName, String aEmail, String aPassword) {
        this.aName = aName;
        this.aEmail = aEmail;
        this.aPassword = aPassword;
    }

    // default constructor
    public AdminEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminEntity that = (AdminEntity) o;

        if (aId != that.aId) return false;
        if (aName != null ? !aName.equals(that.aName) : that.aName != null) return false;
        if (aEmail != null ? !aEmail.equals(that.aEmail) : that.aEmail != null) return false;
        if (aPassword != null ? !aPassword.equals(that.aPassword) : that.aPassword != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (aId ^ (aId >>> 32));
        result = 31 * result + (aName != null ? aName.hashCode() : 0);
        result = 31 * result + (aEmail != null ? aEmail.hashCode() : 0);
        result = 31 * result + (aPassword != null ? aPassword.hashCode() : 0);
        return result;
    }
}
