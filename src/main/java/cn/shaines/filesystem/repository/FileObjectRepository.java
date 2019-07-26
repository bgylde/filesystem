package cn.shaines.filesystem.repository;

import cn.shaines.filesystem.entity.FileObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;

/**
 * Fileobject存储库.
 *
 * @author houyu
 * @createTime 2019/3/9 21:47
 */
public interface FileObjectRepository extends JpaRepository<FileObject, String> {

    FileObject findByName(String name);

    @Modifying
    @Transactional
    int deleteAllByIdIn(String[] ids);

    @Modifying
    @Transactional
    int deleteAllByNameIn(String[] names);

    Page<FileObject> findAllByNameIsContaining(String name, Pageable pageable);
}
