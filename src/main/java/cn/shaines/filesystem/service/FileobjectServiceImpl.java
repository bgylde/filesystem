package cn.shaines.filesystem.service;

import cn.shaines.filesystem.entity.FileObject;
import cn.shaines.filesystem.repository.FileobjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author houyu
 * @createTime 2019/3/9 21:49
 */
@Service
public class FileobjectServiceImpl implements FileobjectService {

    @Autowired
    private FileobjectRepository fileobjectRepository;


    @Override
    public FileObject save(FileObject file) {
        return fileobjectRepository.save(file);
    }

    @Override
    public void deleteById(String id) {
        fileobjectRepository.deleteById(id);
    }

    @Override
    public Optional<FileObject> findById(String id) {
        return fileobjectRepository.findById(id);
    }

    @Override
    public FileObject findByName(String name) {
        return fileobjectRepository.findByName(name);
    }

    @Override
    public List<FileObject> findByIdToPage(int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public int deleteAllByIdIn(String[] ids) {
        return fileobjectRepository.deleteAllByIdIn(ids);
    }

    @Override
    public int deleteAllByNameIn(String[] names) {
        return fileobjectRepository.deleteAllByNameIn(names);
    }

    @Override
    public Page<FileObject> findAll(Pageable pageable) {
        return fileobjectRepository.findAll(pageable);
    }

    @Override
    public Page<FileObject> findAllByNameIsContaining(String name, Pageable pageable) {
        return fileobjectRepository.findAllByNameIsContaining(name, pageable);
    }
}
