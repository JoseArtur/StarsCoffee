package org.feup.coffeeshop.repository;

import org.feup.coffeeshop.model.entity.PurchaseRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseRequestEntity, Long> {

}
