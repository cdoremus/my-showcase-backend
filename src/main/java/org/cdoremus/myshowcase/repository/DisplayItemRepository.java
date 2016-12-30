package org.cdoremus.myshowcase.repository;

import org.cdoremus.myshowcase.model.DisplayItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JUnit test of {@link DisplayItemRepository}.
 */
@Repository
public interface DisplayItemRepository extends JpaRepository<DisplayItem, Long> {

    @Query(value = "select di from DisplayItem di, User u where di.user.userId=u.userId and u.loginId = ?1")
    List<DisplayItem> findByLoginId(String loginId);

    @Query(value = "select di from DisplayItem di, User u where di.user.userId=u.userId and u.userId = ?1")
    List<DisplayItem> findByUserId(long userId);
}
