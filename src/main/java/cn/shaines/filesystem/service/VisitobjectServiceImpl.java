package cn.shaines.filesystem.service;

import cn.shaines.filesystem.entity.VisitObject;
import cn.shaines.filesystem.repository.VisitobjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author houyu
 * @createTime 2019/3/9 21:49
 */
@Service
public class VisitobjectServiceImpl implements VisitobjectService {

    @Autowired
    private VisitobjectRepository VisitobjectRepository;

    @Override
    public VisitObject save(VisitObject visitobject) {
        return VisitobjectRepository.save(visitobject);
    }

    @Override
    public void deleteById(String id) {
        VisitobjectRepository.deleteById(id);
    }

    @Override
    public Page<VisitObject> findAll(Pageable pageable) {
        return VisitobjectRepository.findAll(pageable);
    }

    @Override
    public Page<VisitObject> findAllByUriIsContainingOrParamsIsContaining(String uri, String params, Pageable pageable) {
        return VisitobjectRepository.findAllByUriIsContainingOrParamsIsContaining(uri, params, pageable);
    }

}
