package cn.shaines.filesystem.repository;

import cn.shaines.filesystem.entity.VisitObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Visitobject存储库.
 *
 * @author houyu
 * @createTime 2019/3/9 21:47
 */
public interface VisitObjectRepository extends JpaRepository<VisitObject, String> {
    Page<VisitObject> findAllByUriIsContainingOrParamsIsContaining(String uri, String params, Pageable pageable);
}
