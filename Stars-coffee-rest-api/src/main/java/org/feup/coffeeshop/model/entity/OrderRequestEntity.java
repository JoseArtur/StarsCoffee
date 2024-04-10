package org.feup.coffeeshop.model.entity;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUSTOMER")
@SQLDelete(sql = "UPDATE CUSTOMER SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class OrderRequestEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String nif;

    @Column
    private String telephone;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private boolean deleted = Boolean.FALSE;

}
