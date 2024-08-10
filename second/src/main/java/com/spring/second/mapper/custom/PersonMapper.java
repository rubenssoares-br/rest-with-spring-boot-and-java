package com.spring.second.mapper.custom;

import com.spring.second.model.Person;
import com.spring.second.vo.v2.PersonVOV2;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonVOV2 convertEntityToVo(Person person) {
        PersonVOV2 vo = new PersonVOV2();
        vo.setId(person.getId());
        vo.setAddress(person.getAddress());
        vo.setBirthDay(new Date());
        vo.setFirstname(person.getFirstname());
        vo.setLastName(person.getLastName());
        vo.setGender(person.getGender());
        return vo;
    }

    public Person convertVOtoEntity(PersonVOV2 person) {
        Person vo = new Person();
        vo.setId(person.getId());
        vo.setAddress(person.getAddress());
//        vo.setBirthDay(new Date());
        vo.setFirstname(person.getFirstname());
        vo.setLastName(person.getLastName());
        vo.setGender(person.getGender());
        return vo;
    }
}
