package com.awaion.demo030.a07_cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicStampedReference;

public class DAtomicStampedDemo {
    public static void main(String[] args) {
        // AtomicStampedReference 带有版本号的CAS,处理ABA问题
        Book javaBook = new Book(1, "javaBook");

        AtomicStampedReference<Book> stampedReference = new AtomicStampedReference<>(javaBook, 1);
        System.out.println(stampedReference.getReference() + " -> " + stampedReference.getStamp());

        Book mysqlBook = new Book(2, "mysqlBook");
        boolean b;

        b = stampedReference.compareAndSet(javaBook, mysqlBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println(b + " -> " + stampedReference.getReference() + " -> " + stampedReference.getStamp());


        b = stampedReference.compareAndSet(mysqlBook, javaBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println(b + " -> " + stampedReference.getReference() + " -> " + stampedReference.getStamp());
    }
}

@NoArgsConstructor
@AllArgsConstructor
@Data
class Book {
    private int id;
    private String bookName;
}
