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
