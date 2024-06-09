package br.com.nszandrew.TabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ObterCarros(List<ObterMarcas> modelos){
}
