# DbPokeApiKotlinClient - PokeAPI Client for DBServer
Projeto de App Android escrito em Kotlin acessando a API de Pokemon PokeAPI.

## Descrição do projeto
Projeto para teste na DBServer, criado utilizando Android Studio e Kotlin como liguagem principal para a aplicação.
A aplicação consiste em um Cliente para a PokeAPI, uma API que apresenta informações sobre os Pokemon e permite compartilhar a informação textual com os contatos presentes no celular do usuário.

## Decrição das tecnologias utilizadas
Foram utilizadas bibliotecas conhecidas para tratar dos comportamentos necessários para comunicação de rede (Retrofit 2 e OKHttp 3)

## Informações sobre a Aplicação
Ao iniciar o App, será realizada a busca pelos tipos de Pokemon e a montagem da lista com estas informações. Ao clicar em um dos itens, será realizada outra busca, desta vez pelos Pokemon daquele tipo específico.

Terminando esta busca, será montada uma nova tela de listagem, com todos os Pokemon que se encaixarem no tipo selecionado.

Selecionando um item desta lista de Pokemon, será aberta uma tela de detalhamento, com informações sobre o nome, tamanho, peso, imagem e habilidades do Pokemon. Nesta tela de detalhamento, será possível compartilhar as informações outras aplicações sociais instaladas no aparelho celular do usuário.

## Informações técnicas sobre o build
O prjeto foi desenvolvido utilizando a minSdkVersion 26 (Android 8.0) e o targetSdkVersion 28 (Android 9.0).
