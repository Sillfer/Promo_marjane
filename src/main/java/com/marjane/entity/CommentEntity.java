package com.marjane.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "comment", schema = "public", catalog = "promo_marjane")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id", nullable = false)
    private Long id;

    @Column(name = "c_content", nullable = false)
    private String cContent;

    @Column(name = "c_date", nullable = false)
    private LocalDate cDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "c_user", nullable = false)
    private RespRayonEntity cUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "c_promotion", nullable = false)
    private PromotionEntity cPromotion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCContent() {
        return cContent;
    }

    public void setCContent(String cContent) {
        this.cContent = cContent;
    }

    public LocalDate getCDate() {
        return cDate;
    }

    public void setCDate(LocalDate cDate) {
        this.cDate = cDate;
    }

    public RespRayonEntity getCUser() {
        return cUser;
    }

    public void setCUser(RespRayonEntity cUser) {
        this.cUser = cUser;
    }

    public PromotionEntity getCPromotion() {
        return cPromotion;
    }

    public void setCPromotion(PromotionEntity cPromotion) {
        this.cPromotion = cPromotion;
    }

}