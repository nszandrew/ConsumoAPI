package br.com.nszandrew.TabelaFipe.Principal;

import br.com.nszandrew.TabelaFipe.APIs.ConsumoAPI;
import br.com.nszandrew.TabelaFipe.APIs.ConverteDados;
import br.com.nszandrew.TabelaFipe.model.ObterCarros;
import br.com.nszandrew.TabelaFipe.model.ObterInformacoes;
import br.com.nszandrew.TabelaFipe.model.ObterMarcas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados converteDados = new ConverteDados();
    private final String URL_CODE = "https://parallelum.com.br/fipe/api/v1/";


    public void exibeMenu(){
        //Pedindo para o usuario digitar o tipo do veiculo
        System.out.println("""
                
                Bem vindo ao InfoFIPE
                
                *** OPCOES ***
                Carros
                Motos
                Caminhoes
                
                Digite sobre qual tipo de veiculo deseja consutar o preco
                """);

        //Pegando oq ele digitou e inserindo no metodo de consumo da API
        var opcao = leitura.nextLine();
        String marcas = URL_CODE + opcao.toLowerCase() + "/marcas/";
        var json = consumoAPI.obterDados(marcas);

        //Criando a lista para armazenar os dados e imprimindo os dados armazenados
        List<ObterMarcas> obterMarcas = converteDados.obterLista(json, ObterMarcas.class);
        obterMarcas.stream()
                .sorted(Comparator.comparing(ObterMarcas::codigo))
                .forEach(System.out::println);
        //Pedindo o codigo do modelo do veiculo que foi pedido
        System.out.println("Digite o codigo do modelo do veiculo");
        String codigo = leitura.nextLine();

        //Consumindo a API
        String modelos = marcas + codigo + "/modelos/";
        var json1 = consumoAPI.obterDados(modelos);

        System.out.println("\nModelos dessa marca\n");
        //Nessa caso nao precisamos criar a lista, pq o Record da classe ObterMarcas ja está usando o Array criado da classe ObterMarcas acima
        var obterModelos = converteDados.obterDados(json1, ObterCarros.class);
        obterModelos.modelos().stream()
                .sorted(Comparator.comparing(ObterMarcas::codigo))
                .forEach(System.out::println);
        //
        System.out.println("Digite o nome do carro desejado");
        String nome = leitura.nextLine();

        List<ObterMarcas> modelosFiltrados = obterModelos.modelos().stream()
                .filter(o -> o.marca().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
        modelosFiltrados.forEach(System.out::println);

        System.out.println("\nDigite o codigo do veiculo");
        String codigoVeiculo = leitura.nextLine();
        String veiculo = modelos + codigoVeiculo + "/anos/";
        var json2 = consumoAPI.obterDados(veiculo);

        List<ObterMarcas> anos = converteDados.obterLista(json2, ObterMarcas.class);
        List<ObterInformacoes> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++) {
            var enderecoAnos = veiculo + anos.get(i).codigo() + "/";
            json = consumoAPI.obterDados(enderecoAnos);
            ObterInformacoes informacoes = converteDados.obterDados(json, ObterInformacoes.class);
            veiculos.add(informacoes);
        }

        System.out.println("Veiculos filtrados com avaliacoes por ano");
        veiculos.forEach(System.out::println);

    }
}
