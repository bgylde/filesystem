package cn.shaines.filesystem.service;

import cn.shaines.filesystem.entity.VisitObject;
import cn.shaines.filesystem.repository.VisitObjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author houyu
 * @createTime 2019/3/9 21:49
 */
@Service
public class VisitObjectServiceImpl implements VisitObjectService {

    private final VisitObjectRepository repository;

    @Autowired
    public VisitObjectServiceImpl(VisitObjectRepository VisitobjectRepository) {
        this.repository = VisitobjectRepository;
    }

    @Override
    public VisitObject save(VisitObject visitobject) {
        return repository.save(visitobject);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public Page<VisitObject> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<VisitObject> findAllByUriIsContainingOrParamsIsContaining(String uri, String params, Pageable pageable) {
        return repository.findAllByUriIsContainingOrParamsIsContaining(uri, params, pageable);
    }

}
