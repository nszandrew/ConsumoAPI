# InfoFIPE API

## Descrição / Description

Projeto para consultar a Tabela Fipe de veículos (carros, motos e caminhões) via API. Este projeto permite obter informações sobre os preços de veículos com base nos dados fornecidos pela Tabela Fipe.

Project to query the Fipe Table of vehicles (cars, motorcycles, and trucks) via API. This project allows you to get information about vehicle prices based on data provided by the Fipe Table.

## Funcionalidades Principais / Main Features

- Consultar marcas de veículos (carros, motos e caminhões).
- Consultar modelos de uma marca específica.
- Consultar anos de um modelo específico.
- Filtrar e exibir informações detalhadas de um veículo.

- Query vehicle brands (cars, motorcycles, and trucks).
- Query models of a specific brand.
- Query years of a specific model.
- Filter and display detailed information of a vehicle.

## Instalação / Installation

1. Clone o repositório / Clone the repository:
    ```bash
    git clone https://github.com/nszandrew/TabelaFIPE_API.git
    ```

## Uso / Usage

### Executar o Menu Principal / Run the Main Menu

```java
1. Menu
System.out.println("""
                
    Bem vindo ao InfoFIPE
    
    *** OPCOES ***
    Carros
    Motos
    Caminhoes
    
    Digite sobre qual tipo de veiculo deseja consutar o preco
    """);

2. Consultar marcas / Query brands
Após escolher o tipo de veículo, o usuário pode consultar as marcas disponíveis.
After choosing the type of vehicle, the user can query the available brands.

String marcas = URL_CODE + opcao.toLowerCase() + "/marcas/";
var json = consumoAPI.obterDados(marcas);
List<ObterMarcas> obterMarcas = converteDados.obterLista(json, ObterMarcas.class);
obterMarcas.stream()
        .sorted(Comparator.comparing(ObterMarcas::codigo))
        .forEach(System.out::println);

3. Consultar modelos de uma marca específica / Query models of a specific brand
Após selecionar a marca, o usuário pode consultar os modelos disponíveis dessa marca.
After selecting the brand, the user can query the available models of that brand.

System.out.println("Digite o codigo do modelo do veiculo");
String codigo = leitura.nextLine();
String modelos = marcas + codigo + "/modelos/";
var json1 = consumoAPI.obterDados(modelos);
var obterModelos = converteDados.obterDados(json1, ObterCarros.class);
obterModelos.modelos().stream()
        .sorted(Comparator.comparing(ObterMarcas::codigo))
        .forEach(System.out::println);

4. Consultar anos de um modelo específico / Query years of a specific model
Após selecionar o modelo, o usuário pode consultar os anos disponíveis desse modelo.
After selecting the model, the user can query the available years of that model.

System.out.println("Digite o codigo do veiculo");
String codigoVeiculo = leitura.nextLine();
String veiculo = modelos + codigoVeiculo + "/anos/";
var json2 = consumoAPI.obterDados(veiculo);
List<ObterMarcas> anos = converteDados.obterLista(json2, ObterMarcas.class);

5. Exibir informações detalhadas / Display detailed information
Após selecionar o ano, o usuário pode visualizar informações detalhadas do veículo.
After selecting the year, the user can view detailed information about the vehicle.

List<ObterInformacoes> veiculos = new ArrayList<>();
for (int i = 0; i < anos.size(); i++) {
    var enderecoAnos = veiculo + anos.get(i).codigo() + "/";
    json = consumoAPI.obterDados(enderecoAnos);
    ObterInformacoes informacoes = converteDados.obterDados(json, ObterInformacoes.class);
    veiculos.add(informacoes);
}
System.out.println("Veiculos filtrados com avaliacoes por ano");
veiculos.forEach(System.out::println);
```
## Licença / License

Distributed under the MIT license. See LICENSE for more information.
