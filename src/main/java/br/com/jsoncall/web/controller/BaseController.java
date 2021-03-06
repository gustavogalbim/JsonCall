package br.com.jsoncall.web.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseController<Dto, Entity> {

    @Autowired
    protected ModelMapper modelMapper;

    protected abstract Dto convertToDto(Entity entity);

    protected abstract Entity convertToEntity(Dto dto);

}
