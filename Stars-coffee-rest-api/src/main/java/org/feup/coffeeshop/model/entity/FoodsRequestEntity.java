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
import lombok.Getter;
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
public class FoodsRequestEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String foodName;

    @Column
    private String description;

    @Column
    private int price;

    @Column
    private String itemType;

    @Column(name = "image_url")
    private String imageUrl;

    @Column
    private int isFavorite;

    @Column
    private int isAvailable;

    @Column
    private int quantity;

    @Column
    private String customizationOptions;

    @Column
    private String specialAttributes;

}
