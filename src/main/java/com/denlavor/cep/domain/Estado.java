package com.denlavor.cep.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Estado {
    private int id;
    private String nome;
    private String sigla;
    private Object regiao;
}
