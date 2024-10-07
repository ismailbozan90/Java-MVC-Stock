package org.scamlet.mvc.mvcstock.Repository;

import org.scamlet.mvc.mvcstock.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    long count();
}
