package org.feup.coffeeshop.repository;

import java.util.Optional;
import org.feup.coffeeshop.model.entity.LoginRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginRequestEntity, Long> {
    @Query("SELECT DISTINCT s FROM LoginRequestEntity s WHERE s.email = :email AND s.password = :password")
    LoginRequestEntity findByEmailPassword(@Param("email") String email, @Param("password") String password);

}
