package com.example.study.model.entity;

import com.example.study.model.enumclass.UserStatus;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//@Table(name="user") // 클래스명이 테이블명과 같다면 굳이 명시하지 않아도됨. 자동으로 매칭됨.
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // == table
@ToString(exclude = {"orderGroupList"})
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain=true)
public class User {

    @Id //식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 어떤식으로 관리할 것인지 전략설정
    private Long id;

    private String account;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status; // 고정된 값 사용 REGISTERED / UNREGISTERED / WAITING /

    private String email;

    private String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    //User : OrderGroup = 1 : N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderGroup> orderGroupList;

}
