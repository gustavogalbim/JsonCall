package br.com.jsoncall.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jsoncall.dto.AtmDto;
import br.com.jsoncall.entity.Atm;
import br.com.jsoncall.service.AtmService;

@Validated
@RestController
@RequestMapping("/methods")
public class AtmController extends BaseController<AtmDto, Atm> {

    @Autowired
    private AtmService atmService;

    @Override
    protected AtmDto convertToDto(Atm atm) {
        return modelMapper.map(atm, AtmDto.class);
    }

    @Override
    protected Atm convertToEntity(AtmDto atmDto) {
        return modelMapper.map(atmDto, Atm.class);
    }

    @GetMapping
    public List<Atm> findAll() {
        return atmService.findAll();
    }

    @GetMapping("/{id}")
    public AtmDto findById(@PathVariable String id) {
        return convertToDto(atmService.findById(id));
    }

    @PostMapping
    public AtmDto save(@RequestBody @Validated AtmDto atmDto) {
        return convertToDto(atmService.save(convertToEntity(atmDto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
    	atmService.deleteById(id);
    }

}
