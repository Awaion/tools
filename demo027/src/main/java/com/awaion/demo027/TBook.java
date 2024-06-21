package com.awaion.demo027;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("t_book")
@Data
public class TBook {
    @Id
    private Long id;
    private String title;
    private Long authorId;
    private Instant publishTime; // 响应式日期用Instant或者LocalXxx
}
