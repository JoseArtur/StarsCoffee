package org.feup.coffeeshop.model.entity;


import java.util.Date;
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

@Data
@Entity
@Builder
@NoArgsConstructor
@Setter
@ToString
@AllArgsConstructor
@Table(name = "PURCHASE")
public class PurchaseRequestEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String username;

    @Column
    private String itemId;

    @Column
    private Date purchaseDate;

    @Column
    private String quantity;

}
