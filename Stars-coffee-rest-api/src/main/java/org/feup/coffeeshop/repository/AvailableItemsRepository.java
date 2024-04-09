package org.feup.coffeeshop.repository;

import org.feup.coffeeshop.model.entity.AvailableItemsRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableItemsRepository extends JpaRepository<AvailableItemsRequestEntity, Long> {

}
