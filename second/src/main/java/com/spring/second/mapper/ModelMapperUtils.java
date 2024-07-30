package com.spring.second.mapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModelMapperUtils {

    private static ModelMapper mapper;

    public ModelMapperUtils(ModelMapper mapper) {
        this.mapper = mapper;
    }


    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }


    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {

        List<D> destinationObjects = new ArrayList<>();

        for (O o : origin) {
            destinationObjects.add(mapper.map(o, destination));
        }

        return destinationObjects;
    }
}
