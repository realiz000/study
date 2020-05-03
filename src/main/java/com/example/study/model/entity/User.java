package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // == table
@Table(name="user") // 클래스명이 테이블명과 같다면 굳이 명시하지 않아도됨. 자동으로 매칭됨.
public class User {

    @Id //식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 어떤식으로 관리할 것인지 전략설정
    private Long id;

    private String account;

    private String email;

    private String phoneNumber;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;
}
