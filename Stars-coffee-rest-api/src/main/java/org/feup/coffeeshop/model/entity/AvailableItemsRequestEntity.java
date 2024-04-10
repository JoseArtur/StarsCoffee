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
@Table(name = "STOCK_ITEMS")
public class AvailableItemsRequestEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String itemName;

    @Column
    private String description;

    @Column
    private String itemType;

    @Column
    private String price;

    @Column
    private String imageUrl;

    @Column
    private String customizationOptions;

    @Column
    private String specialAttributes;

}
