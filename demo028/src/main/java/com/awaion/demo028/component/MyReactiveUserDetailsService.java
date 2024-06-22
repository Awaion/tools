package com.awaion.demo028.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MyReactiveUserDetailsService implements ReactiveUserDetailsService {
    @Autowired
    DatabaseClient databaseClient;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        String sql = "SELECT t1.username AS `username`, t1.`password` AS `password`, t3.`value` AS `roleCode`, t5.`value` AS `permissionCode` FROM t_user t1 LEFT JOIN t_user_role t2 ON t2.user_id = t1.id LEFT JOIN t_roles t3 ON t3.id = t2.role_id LEFT JOIN t_role_perm t4 ON t4.role_id = t3.id LEFT JOIN t_perm t5 ON t4.perm_id = t5.id WHERE t1.username = ?";
        Mono<UserDetails> userDetailsMono = databaseClient.sql(sql)
                .bind(0, username)
                .fetch()
                .one()
                .map(map -> {
                    UserDetails details = User.builder()
                            .username(username)
                            .password(map.get("password").toString())
//                            .roles(map.get("roleCode").toString())
                            .authorities(map.get("permissionCode").toString()) // 源代码是新创建权限集合 AuthorityUtils.createAuthorityList
                            .build();
                    return details;
                });
        return userDetailsMono;
    }
}

