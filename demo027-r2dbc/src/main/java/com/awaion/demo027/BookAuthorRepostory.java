package com.awaion.demo027;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BookAuthorRepostory extends R2dbcRepository<TBookAuthor, Long> {

    @Query("select b.*,t.name as name from t_book b LEFT JOIN t_author t on b.author_id = t.id  WHERE b.id = :bookId")
    Mono<TBookAuthor> bookInfo(@Param("bookId") Long bookId);

}
