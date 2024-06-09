package br.com.nszandrew.TabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ObterInformacoes(  @JsonAlias("Valor")String valor,
                                 @JsonAlias("Marca") String marca,
                                 @JsonAlias("Modelo")String modelo,
                                 @JsonAlias("AnoModelo") Integer ano,
                                 @JsonAlias("Combustivel") String combustivel){
}
