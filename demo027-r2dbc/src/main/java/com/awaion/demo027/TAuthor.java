package com.awaion.demo027;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("t_author")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TAuthor {
    @Id
    private Long id;
    private String name;
    @Transient
    private List<TBook> books;
}

