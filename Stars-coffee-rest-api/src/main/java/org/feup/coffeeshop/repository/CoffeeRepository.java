package org.feup.coffeeshop.repository;

import org.apache.el.stream.Optional;
import org.feup.coffeeshop.model.entity.OrderRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRepository extends JpaRepository<OrderRequestEntity, Long> {

}