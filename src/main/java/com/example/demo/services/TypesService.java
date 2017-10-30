package com.example.demo.services;

import com.example.demo.entities.Type;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TypesService {

    public List<Type> getAllTypes(){
        List<Type> types = new ArrayList<>();
        Collections.addAll(types, Type.values());
        return types;
    }
}
