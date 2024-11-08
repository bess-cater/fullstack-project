package com.packt.mybase.domain;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

//? Repository of Car Entiry Class, ID is in Long format.
public interface BookRepository extends CrudRepository<Book, Long>{
    //List<Car> findByBrand(String brand);


}
