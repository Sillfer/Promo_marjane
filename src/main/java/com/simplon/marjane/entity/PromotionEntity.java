package com.simplon.marjane.entity;

import com.sun.istack.Nullable;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "promotion", schema = "public", catalog = "promo_marjane")
public class PromotionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "p_category", nullable = false)
    private CategoryEntity pCategory;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "p_sub_category", nullable = false)
    @Nullable
    private SubCategoryEntity pSubCategory;

    @Column(name = "p_start_date", nullable = false)
    private LocalDate pStartDate;

    @Column(name = "p_expire_date", nullable = false)
    private LocalDate pExpireDate;

    @Column(name = "p_rate", nullable = false, precision = 5, scale = 2)
    private BigDecimal pRate;

    @Column(name = "p_point_fidelite")
    private Integer pPointFidelite;

    @Column(name = "p_status", length = 100 , nullable = false)
    private String pStatus = "Pending";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryEntity getPCategory() {
        return pCategory;
    }

    public void setPCategory(CategoryEntity pCategory) {
        this.pCategory = pCategory;
    }

    public SubCategoryEntity getPSubCategory() {
        return pSubCategory;
    }

    public void setPSubCategory(SubCategoryEntity pSubCategory) {
        this.pSubCategory = pSubCategory;
    }

    public LocalDate getPStartDate() {
        return pStartDate;
    }

    public void setPStartDate(LocalDate pStartDate) {
        this.pStartDate = pStartDate;
    }

    public LocalDate getPExpireDate() {
        return pExpireDate;
    }

    public void setPExpireDate(LocalDate pExpireDate) {
        this.pExpireDate = pExpireDate;
    }

    public BigDecimal getPRate() {
        return pRate;
    }

    public void setPRate(BigDecimal pRate) {
        this.pRate = pRate;
    }

    public Integer getPPointFidelite() {
        return pPointFidelite;
    }

    public void setPPointFidelite(Integer pPointFidelite) {
        this.pPointFidelite = pPointFidelite;
    }

    public String getPStatus() {
        return pStatus;
    }

    public void setPStatus(String pStatus) {
        this.pStatus = pStatus;
    }


    public PromotionEntity(CategoryEntity pCategory, SubCategoryEntity pSubCategory, LocalDate pStartDate, LocalDate pExpireDate, BigDecimal pRate, Integer pPointFidelite) {
        this.pCategory = pCategory;
        this.pSubCategory = pSubCategory;
        this.pStartDate = pStartDate;
        this.pExpireDate = pExpireDate;
        this.pRate = pRate;
        this.pPointFidelite = pPointFidelite;
    }
    public PromotionEntity() {
    }

    // toString
    @Override
    public String toString() {
        return
                "\n--------------------------- PromotionEntity  " + id +" -------------------------"+
                "\n Category = " + pCategory.getcName() +
                "\n SubCategory = " + pSubCategory.getScName() +
                "\n StartDate = " + pStartDate +
                "\n ExpireDate = " + pExpireDate +
                "\n Rate = " + pRate +"%"+
                "\n Point Fidelite =" + pPointFidelite +
                "\n Status = " + pStatus;
    }
}