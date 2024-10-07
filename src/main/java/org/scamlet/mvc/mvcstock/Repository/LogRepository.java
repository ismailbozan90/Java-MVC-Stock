package org.scamlet.mvc.mvcstock.Repository;

import org.scamlet.mvc.mvcstock.Entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
    long count();
}
