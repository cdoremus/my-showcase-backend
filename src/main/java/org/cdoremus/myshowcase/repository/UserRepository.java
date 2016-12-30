package org.cdoremus.myshowcase.repository;

import org.cdoremus.myshowcase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by craig on 12/16/16.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLoginId(String loginId);
}
