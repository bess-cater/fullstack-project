package com.packt.mybase.domain;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported=false)
public interface UserBase extends CrudRepository<User, Long>{
    Boolean existsByUsername(String username);

    User findByUsername(String username);

}
