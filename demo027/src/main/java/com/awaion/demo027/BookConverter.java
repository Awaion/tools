package com.awaion.demo027;

import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.Instant;

/**
 * 自定义对象转换器
 */
@ReadingConverter
public class BookConverter implements Converter<Row, TBookAuthor> {
    @Override
    public TBookAuthor convert(Row source) {
        if (source == null) return null;
        TBookAuthor tBook = new TBookAuthor();
        tBook.setId(source.get("id", Long.class));
        tBook.setTitle(source.get("title", String.class));
        Long author_id = source.get("author_id", Long.class);
        tBook.setAuthorId(author_id);
        tBook.setPublishTime(source.get("publish_time", Instant.class));
        if (source.getMetadata().contains("name")) {
            TAuthor tAuthor = new TAuthor();
            tAuthor.setId(author_id);
            tAuthor.setName(source.get("name", String.class));
            tBook.setAuthor(tAuthor);
        }
        return tBook;
    }
}

