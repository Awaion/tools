package com.awaion.demo030.a07_cas;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

public class BAtomicReferenceDemo {
    public static void main(String[] args) {
        // AtomicReference 实现自定义对象使用CAS

        AtomicReference<User> atomicReference = new AtomicReference<>();

        User user1 = new User("用户1", 22);
        User user2 = new User("用户2", 28);

        atomicReference.set(user1);

        System.out.println(atomicReference.compareAndSet(user1, user2) + " -> " + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(user1, user2) + " -> " + atomicReference.get().toString());

    }
}

@Getter
@ToString
@AllArgsConstructor
class User {
    String userName;
    int age;
}
