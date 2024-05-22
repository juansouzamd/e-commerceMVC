/*
    Nome do Projeto: E-commerce
    Data de Criação: 15/02/2024
    Versão: 5
    Data da Última Modificação: 22/05/2024
    Equipe de Desenvolvimento:
        - Juan Souza Santos
        - Maria Helena dos Santos
        - Pedro Ferreira Lima
        - Thiago Shigueto Hossaka

    Este script é responsável por alterar dinamicamente a imagem principal com base nas miniaturas de imagem clicadas pelo usuário.
    Além disso, verifica o ID da rota para determinar se o produto é uma camiseta e, nesse caso, atualiza as opções de tamanho disponíveis.
    Por fim, valida se o usuário selecionou um tamanho antes de enviar o formulário.

*/


// Obtém a imagem principal
var mainImg = document.getElementById("MainImg");

// Obtém todas as miniaturas de imagem
var smallImgs = document.querySelectorAll(".small-img");

// Adiciona um evento de clique a cada miniatura de imagem
smallImgs.forEach(function(smallImg) {
    smallImg.addEventListener("click", function() {
        // Altera o src da imagem principal para o src da miniatura clicada
        mainImg.src = this.src;
    });
});

// Verifica se o ID da rota é maior ou igual a 8 para mudar os tamanhos para camisetas
if (id >= 8) {
    // Seleciona o elemento select pelo ID
    var select = document.querySelector('select');

    // Limpa as opções existentes
    select.innerHTML = '';

    // Adiciona novas opções para tamanhos de camisetas
    var tamanhos = ["PP", "P", "M", "G", "GG", "XG"];
    tamanhos.forEach(function(tamanho) {
        var option = document.createElement('option');
        option.textContent = tamanho;
        select.appendChild(option);
    });
}

// Esta função valida o formulário antes de ser enviado.
function validateForm(event) {
        var select = document.getElementById("sizeSelect");
        if (select.selectedIndex === 0) {
            event.preventDefault(); // Impede o envio do formulário se nenhuma opção for selecionada
            alert("Por favor, selecione um tamanho.");
        }
    }
