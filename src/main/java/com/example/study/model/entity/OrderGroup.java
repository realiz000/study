package com.example.study.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString(exclude = {"user", "orderDetailList"})
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain=true)
public class OrderGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String orderType; // 주문의 형태 : 일괄 / 개별

    private String revAddress;

    private String revName;

    private String paymentType; // 카드 /  현금 결제

    private BigDecimal totalPrice;

    private int totalQuantity;

    private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    //OrderGroup  : User = N : 1
    @ManyToOne
    private User user; // mappedBy에 있는 변수명과 일치해야 함.

    //OrderGroup : OrderDetal = 1 : N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderGroup")
    private List<OrderDetail> orderDetailList;
}
