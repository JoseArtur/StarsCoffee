package org.feup.coffeeshop.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@Builder
@NoArgsConstructor
@Setter
@ToString
@AllArgsConstructor
@Table(name = "CUSTOMER")
public class LoginRequestEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String telephone;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private boolean deleted = Boolean.FALSE;

}
