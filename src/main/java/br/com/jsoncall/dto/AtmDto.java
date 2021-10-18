package br.com.jsoncall.dto;


import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtmDto {

	@Id
    private String id;

    private String method;

    private String params;


}
