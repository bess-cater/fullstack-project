package com.packt.mybase.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends CrudRepository<Author, Long> {


}
