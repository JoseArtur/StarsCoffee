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
@Table(name = "VOUCHER")
public class VoucherRequestEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String pointsRequired;
    @Column
    private String userId;
    @Column
    private String voucherCode;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Double value;
    @Column
    private String image;
    @Column
    private int restaurant;
    @Column
    private String type;

}
