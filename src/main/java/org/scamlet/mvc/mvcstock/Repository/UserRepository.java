package org.scamlet.mvc.mvcstock.Repository;

import org.scamlet.mvc.mvcstock.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    long count();
    User findByUsername(String username);
}
