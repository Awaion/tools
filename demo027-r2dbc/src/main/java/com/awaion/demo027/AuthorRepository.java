package com.awaion.demo027;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Collection;

@Repository
public interface AuthorRepository extends R2dbcRepository<TAuthor, Long> {
    /**
     * 规范命名,自动实现
     *
     * @param id
     * @param name
     * @return
     */
    Flux<TAuthor> findAllByIdInAndNameLike(Collection<Long> id, String name);

    /**
     * 自定义SQL
     *
     * @return
     */
    @Query("select * from t_author")
    Flux<TAuthor> findAll();
}

