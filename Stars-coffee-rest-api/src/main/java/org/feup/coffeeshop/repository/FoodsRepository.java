package org.feup.coffeeshop.repository;

import org.feup.coffeeshop.model.entity.FoodsRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodsRepository extends JpaRepository<FoodsRequestEntity, Long> {

}
