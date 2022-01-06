package com.experiment.accounting.service;

import com.experiment.accounting.domain.base.BaseConstantModel;
import com.experiment.accounting.entity.base.BaseConstantEntity;
import com.experiment.accounting.exception.BusinessException;
import com.experiment.accounting.exception.BusinessExceptions;
import com.experiment.accounting.mapper.ConstantMapper;
import com.experiment.accounting.repository.base.EntityRepository;
import com.experiment.accounting.service.base.EntityService;
import com.experiment.accounting.utils.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConstantService extends EntityService {

    private final ConstantMapper constantMapper;
    private final CacheManager cacheManager;

    public ConstantService(ConstantMapper constantMapper, CacheManager cacheManager, EntityRepository<BaseConstantEntity> entityRepository) {
        super(entityRepository);
        this.cacheManager = cacheManager;
        this.constantMapper = constantMapper;
    }

    @Cacheable(value = "findReference", key = "#name + #c.getName()")
    public <T extends BaseConstantEntity> Optional<T> find(String name, Class<T> c) {
        T instance = createInstance(c);
        instance.setName(name);
        return entityRepository.findOne(Example.of(instance));
    }

    @Cacheable(value = "findReferenceByName", key = "#constantName")
    public <T extends BaseConstantModel> List<T> findAllByConstantName(String constantName) {
        return findAll(ConstantUtils.getReferenceByName(constantName).getClazz())
                .stream()
                .map(constantEntity -> (T) constantMapper.toModel(constantEntity))
                .collect(Collectors.toList());
    }

    public void deleteByConstantNameAndId(String constantName, Long id) {
        delete(id, ConstantUtils.getReferenceByName(constantName).getClazz());
        cacheManager.getCache("findReferenceByName").evictIfPresent(constantName);
    }

    public <T extends BaseConstantEntity> T saveByConstantName(String constantName, BaseConstantModel constantModel) {
        if (constantModel.getId() != null)
            throw new BusinessException("If id field is not null, try to update " + constantName);
        T t = save(constantMapper.toEntity(constantModel, ConstantUtils.getReferenceByName(constantName).getClazz()));
        cacheManager.getCache("findReferenceByName").evictIfPresent(constantName);
        return t;
    }

    public <T extends BaseConstantEntity> T updateByConstantName(String constantName, BaseConstantModel constantModel) {
        if (constantModel.getId() == null)
            throw new BusinessException("If id field is null, try to create " + constantName);
        T t = save(constantMapper.toEntity(constantModel, ConstantUtils.getReferenceByName(constantName).getClazz()));
        cacheManager.getCache("findReferenceByName").evictIfPresent(constantName);
        return t;

    }

}
