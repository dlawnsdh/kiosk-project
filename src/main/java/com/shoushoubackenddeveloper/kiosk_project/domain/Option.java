package com.shoushoubackenddeveloper.kiosk_project.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "korName"),
        @Index(columnList = "engName"),
        @Index(columnList = "price"),
        @Index(columnList = "createdAt")
})
@Entity
public class Option extends AuditingFields {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false, length = 20)
    private String korName;

    @Setter @Column(length = 20)
    private String engName;

    @Setter
    private Integer price;

    private Option(String korName, String engName, Integer price) {
        this.korName = korName;
        this.engName = engName;
        this.price = price;
    }

    public static Option of(String korName, String engName, Integer price){
        return new Option(korName, engName, price);
    }

}
