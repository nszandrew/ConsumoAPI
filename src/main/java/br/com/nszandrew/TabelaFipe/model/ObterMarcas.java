package br.com.nszandrew.TabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ObterMarcas(@JsonAlias("codigo")String codigo,
                          @JsonAlias("nome")String marca) {
}
