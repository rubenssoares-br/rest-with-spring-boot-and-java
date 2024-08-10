package com.spring.second.services;

import com.spring.second.exceptions.ResourceNotFoundException;
import com.spring.second.mapper.ModelMapperUtils;
import com.spring.second.mapper.custom.PersonMapper;
import com.spring.second.model.Person;
import com.spring.second.repository.PersonRepository;
import com.spring.second.vo.v1.PersonVO;
import com.spring.second.vo.v2.PersonVOV2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;

    public List<PersonVO> findAll() {

        return ModelMapperUtils.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id) {

        logger.info("Finding one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        return ModelMapperUtils.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO person) {

     logger.info("Creating one person!");

     var entity = ModelMapperUtils.parseObject(person, Person.class);

     var vo = ModelMapperUtils.parseObject(repository.save(entity), PersonVO.class);

     return vo;

    }

    public PersonVOV2 createV2(PersonVOV2 person) {

        logger.info("Creating one person with V2!");

        var entity = mapper.convertVOtoEntity(person);

        var vo = mapper.convertEntityToVo(repository.save(entity));

        return vo;

    }

    public PersonVO update(PersonVO person) {

        logger.info("Updating one person!");

      var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstname(person.getFirstname());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = ModelMapperUtils.parseObject(repository.save(entity), PersonVO.class);
        return vo;

    }

    public void delete(Long id) {

        logger.info("Deleting one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(entity);

    }

}
