package com.example.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration //설정부분이다 표시
@EnableJpaAuditing // JPA 감시 활성화 (감시자 설정)
public class JpaConfig {
    
}
