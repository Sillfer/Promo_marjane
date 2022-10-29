package com.simplon.marjane.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "sub_category", schema = "public", catalog = "promo_marjane")
public class SubCategoryEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "sc_id")
    private long scId;
    @Basic
    @Column(name = "sc_name")
    private String scName;
    @Basic
    @Column(name = "sc_category")
    private long scCategory;

    @OneToMany(mappedBy = "pSubCategory")
    private Set<PromotionEntity> promotions = new LinkedHashSet<>();

    public Set<PromotionEntity> getPromotions() {
        return promotions;
    }

    public void setPromotions(Set<PromotionEntity> promotions) {
        this.promotions = promotions;
    }

    public long getScId() {
        return scId;
    }

    public void setScId(long scId) {
        this.scId = scId;
    }

    public String getScName() {
        return scName;
    }

    public void setScName(String scName) {
        this.scName = scName;
    }

    public long getScCategory() {
        return scCategory;
    }

    public void setScCategory(long scCategory) {
        this.scCategory = scCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubCategoryEntity that = (SubCategoryEntity) o;

        if (scId != that.scId) return false;
        if (scCategory != that.scCategory) return false;
        if (scName != null ? !scName.equals(that.scName) : that.scName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (scId ^ (scId >>> 32));
        result = 31 * result + (scName != null ? scName.hashCode() : 0);
        result = 31 * result + (int) (scCategory ^ (scCategory >>> 32));
        return result;
    }
}
