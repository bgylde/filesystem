package cn.shaines.filesystem.service;

import cn.shaines.filesystem.entity.FileObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface FileobjectService {

    /**
     * 保存文件
     * @return
     */
    FileObject save(FileObject file);

    /**
     * 删除文件
     * @return
     */
    void deleteById(String id);

    /**
     * 根据id获取文件
     * @return
     */
    Optional<FileObject> findById(String id);

    /**
     * 根据name获取文件
     * @return
     */
    FileObject findByName(String name);

    /**
     * 分页查询，按上传时间降序
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<FileObject> findByIdToPage(int pageIndex, int pageSize);

    /**
     * 根据ids 批量删除
     * @param ids
     * @return
     */
    int deleteAllByIdIn(String[] ids);


    /**
     * 根据names 批量删除
     * @param 根据names
     * @return
     */
    int deleteAllByNameIn(String[] 根据names);


    /**
     * 分页查询
     * @param pageable
     * @return
     */
    public Page<FileObject> findAll(Pageable pageable);


    /**
     * 分页查询
     * @param pageable
     * @return
     */
    public Page<FileObject> findAllByNameIsContaining(String name, Pageable pageable);

}
