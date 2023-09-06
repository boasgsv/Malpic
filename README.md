# Integrantes

## Gabriele Souza Vilas Bôas           745738

## Geovanne Mansano Fritch da Silva    791072

## Pedro Gonçalves dos Santos          794042

# Linguagem de Pipeline para Machine Learning

## Propósito

A linguagem consiste em algo semalhante a um arquivo ```.yaml``` onde definimos etapas para treinamento de dataframe com técnicas de machine learning. O objetivo principal é simplificar esse workflow, fornecendo uma linguagem que converte todas as etapas para um código em python para o jupyter com poucas linhas.

## Exemplos de código

**Carregamento dos dados**

- Na nossa linguagem:
```
pipeline "MyMLPipeline":

   dataset VariavelDoDataset "nome do dataset":
      from "path" with
         target as y = "nomeColuna"
         features as X = remaining
```
- No código python gerado:

```
import numpy as np
import pandas as pd

df = pd.read_csv("https://link_para_o_seu_dataset.com")
df

y = df["nomeColuna"]
y

X = df.drop("nomeColuna", axis=1)
X

```

Exemplos com códigos completos podem ser encontrados no diretório `tests`

## Pré-requisitos

- Java 11 ou superior (ver [Java: Can't use ANTLR 4.11.1 on Java 8 #3994] https://github.com/antlr/antlr4/issues/3994 )

- Apache Maven 3.6.3 ou superior

## Saídas

Por padrão, as saídas encontram-se no diretório de [output](saídas). Lá você encontra tanto as saídas para [analisador léxico](output/lexical.out), [analisador sintático](output/syntactical.out) e [analisador semântico](output/semantical.out). 

Também lá você encontrará [o código gerado](output/output.ipynb)

## Compilar e rodar com o script de teste

Criamos um script de teste para rodar os testes presentes no [diretório de testes](tests/entrada). 

Para utilizá-lo, basta rodar

      chmod +x runscript.sh
      ./runscript <nome_do_teste>

Importante! O nome do teste não contem o sufixo .txt.

## Compilação manual

É necessário gerar os arquivos para compilação com

    mvn generate-sources

Em sequência, para empacotar o projeto:

    mvn package

É necessário compilar o projeto via Maven com:

    mvn compile
    
## Rodar manualmente

Para rodar para um par `entrada` e `saida` arbitrário, basta seguir o código abaixo e indicar caminho dos arquivos de entrada e saída

    java -jar target/lalex-1.0-SNAPSHOT-jar-with-dependencies.jar path_entrada path_saida
