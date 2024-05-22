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

    Este script é responsável por abrir e fechar dinamicamente o menu de navegação em um site de e-commerce.
    Ele adiciona um evento de clique ao botão de abertura do menu ('bar') e ao botão de fechamento do menu ('close').
    Quando o botão de abertura do menu é clicado, ele adiciona a classe 'active' ao elemento de navegação principal ('nav'),
    tornando-o visível para o usuário. Quando o botão de fechamento do menu é clicado, ele remove a classe 'active'
    do elemento de navegação principal, ocultando-o novamente.
*/

const bar = document.getElementById('bar');
const close = document.getElementById('close');
const nav = document.getElementById('navbar');

if (bar) {
    bar.addEventListener('click', () => {
        nav.classList.add('active');
    })
}

if (close) {
    close.addEventListener('click', () => {
        nav.classList.remove('active');
    })
}