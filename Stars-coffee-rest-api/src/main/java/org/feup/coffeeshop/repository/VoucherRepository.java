package org.feup.coffeeshop.repository;

import java.util.Optional;
import org.feup.coffeeshop.model.entity.VoucherRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends JpaRepository<VoucherRequestEntity, Long> {

}
