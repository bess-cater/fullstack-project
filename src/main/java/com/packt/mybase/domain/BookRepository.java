package com.packt.mybase.domain;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//? Repository of Car Entiry Class, ID is in Long format.
public interface BookRepository extends CrudRepository<Book, Long>{
    List<Book> findByGenre(@Param("genre") String genre);

    List<Book> findByPubYearGreaterThan(@Param("pubYear") int year);

    List<Book> findByRatingGreaterThan(@Param("rating") BigDecimal rating);

    List<Book> findByPubYearGreaterThanAndPubYearLessThan(@Param("startYear") int startYear, @Param("endYear") int endYear);


}