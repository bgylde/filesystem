package cn.shaines.filesystem.service;

import cn.shaines.filesystem.entity.VisitObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VisitObjectService {

    /**
     * 保存
     * @return
     */
    VisitObject save(VisitObject visitobject);

    /**
     * 删除
     * @return
     */
    void deleteById(String id);

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    public Page<VisitObject> findAll(Pageable pageable);

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<VisitObject> findAllByUriIsContainingOrParamsIsContaining(String uri, String params, Pageable pageable);

}
