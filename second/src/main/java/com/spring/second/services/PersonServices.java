package com.spring.second.services;

import com.spring.second.controllers.PersonController;
import com.spring.second.exceptions.ResourceNotFoundException;
import com.spring.second.mapper.ModelMapperUtils;
import com.spring.second.mapper.custom.PersonMapper;
import com.spring.second.model.Person;
import com.spring.second.repository.PersonRepository;
import com.spring.second.vo.v1.PersonVO;
import com.spring.second.vo.v2.PersonVOV2;
import org.modelmapper.ModelMapper;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    private static ModelMapper mapper = new ModelMapper();

    static {
        mapper.createTypeMap(
                        Person.class,
                        PersonVO.class)
                .addMapping(Person::getId, PersonVO::setKey);
    }


    public List<PersonVO> findAll() {
        logger.info("Finding all people!");

        return repository.findAll().stream()
                .map(person -> {
                    PersonVO personVO = mapper.map(person, PersonVO.class);
                    try {
                        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return personVO;
                })
                .collect(Collectors.toList());
    }


    public PersonVO findById(Long id) throws Exception {

        logger.info("Finding one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        PersonVO vo = ModelMapperUtils.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO person) {

     logger.info("Creating one person!");

     var entity = ModelMapperUtils.parseObject(person, Person.class);

     var vo = ModelMapperUtils.parseObject(repository.save(entity), PersonVO.class);

     return vo;

    }


    public PersonVO update(PersonVO person) {

        logger.info("Updating one person!");

      var entity = repository.findById(person.getKey())
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
