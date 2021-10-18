package br.com.jsoncall.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Atm {

    @Id
    private String id;

    private String method;

    private String params;

}
