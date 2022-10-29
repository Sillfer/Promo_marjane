package com.marjane.Dao;

import com.marjane.entity.PromotionEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class PromotionDao extends AbstractHibernateDao<PromotionEntity>{


    public PromotionDao(){
        tableName = "promotion";
        setClazz(PromotionEntity.class);
    }

    // find all promotions
    public List getAllPromotions() {
        return findAll();
    }

    // find one promotion by id
    public PromotionEntity getPromotionById(long id) {
        return findOne(id);
    }

    // find one promotion by category
    public PromotionEntity getPromotionByCategory(int category) {
        return jpaService.runInTransaction(entityManager -> {
            return entityManager.createQuery("select p from PromotionEntity p WHERE p.pCategory = :category", PromotionEntity.class)
                    .setParameter("category", category)
                    .getSingleResult();
        });
    }
    // create promotion
    public boolean createPromotion(PromotionEntity promotion) {
        if (Objects.equals(promotion.getPCategory().getcName(), "Multi Media")) {
            return false;
        } else {
            create(promotion);
            return true;
        }
    }

    // if current Time is between 8 and 12 update promotion status is possible
    public boolean updatePromotionStatusBasedOnTime(PromotionEntity promotion, String status) {
        LocalTime currentTime = LocalTime.now();
        if (currentTime.isAfter(LocalTime.of(12, 0)) && currentTime.isBefore(LocalTime.of(18, 30))) {
            // create update query where promotion category is equal to promotion category
            return jpaService.runInTransaction(entityManager -> {
                entityManager.createQuery("update PromotionEntity p set p.pStatus = :status where p.pCategory = :category AND p.id = :id")
                        .setParameter("status", status)
                        .setParameter("category", promotion.getPCategory())
                        .setParameter("id", promotion.getId())
                        .executeUpdate();
                return true;
            });
        }else{
            return false;
        }
    }


    // function to update automatically promotion status based on expiration date if status is pending change to expired
    public void updatePromotionStatusBasedOnExpirationDate() {
        LocalDate currentDate = LocalDate.now();
        PromotionDao promotionDao = new PromotionDao();
        List<PromotionEntity> promotions = promotionDao.getAllPromotions();
        promotions.forEach(promotion -> {
            if (promotion.getPStatus().equals("PENDING") && promotion.getPExpireDate().isBefore(currentDate)) {
                jpaService.runInTransaction(entityManager -> {
                    return entityManager.createQuery("update PromotionEntity p set p.pStatus = 'EXPIRED' where p.id = :id")
                            .setParameter("id", promotion.getId())
                            .executeUpdate();
                });
            }
        });
    }
}
