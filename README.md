# Integrantes

## Gabriele Souza Vilas Bôas           745738

## Geovanne Mansano Fritch da Silva    791072

## Pedro Gonçalves dos Santos          794042

# Linguagem de Pipeline para Machine Learning

## Propósito

A linguagem consiste em algo semalhante a um arquivo ```.yaml``` onde definimos etapas para treinamento de dataset com técnicas de machine learning. O objetivo principal é simplificar esse workflow, fornecendo uma linguagem que converte todas as etapas para um código em python para o jupyter com poucas linhas.

## Exemplos de código

**Carregamento dos dados**

- Na nossa linguagem:
```
pipeline "MyMLPipeline":

   data "https://link_para_o_seu_dataset.com"
```
- No código python gerado:

```
import pandas as pd

data_url = "https://link_para_o_seu_dataset.com"
df = pd.read_csv(data_url)
```

Exemplos com códigos completos podem ser encontrados no diretório `tests`

## Pré-requisitos

- Java 11 ou superior (ver [Java: Can't use ANTLR 4.11.1 on Java 8 #3994] https://github.com/antlr/antlr4/issues/3994 )

- Apache Maven 3.6.3 ou superior

## Compilação

É necessário gerar os arquivos para compilação com

    mvn generate-sources

Em sequência, para empacotar o projeto:

    mvn package

É necessário compilar o projeto via Maven com:

    mvn compile
    
## Rodar

Para rodar para um par `entrada` e `saida` arbitrário, basta seguir o código abaixo e indicar caminho dos arquivos de entrada e saída

    java -jar target/lalex-1.0-SNAPSHOT-jar-with-dependencies.jar path_entrada path_saida
