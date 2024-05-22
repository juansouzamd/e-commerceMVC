/*
    Nome do Projeto: E-commerce
    Data de Criação: 15/02/2024
    Versão: 5
    Data da Última Modificação: 22/05/2024
    Versão do Java: 17
    Versão do Spring Boot: 3.2.4
    Banco de dados: H2
    Equipe de Desenvolvimento:
        - Juan Souza Santos
        - Maria Helena dos Santos
        - Pedro Ferreira Lima
        - Thiago Shigueto Hossaka

    Descrição: Este programa é uma plataforma de e-commerce destinada à venda de produtos variados.
        A aplicação permite aos usuários navegar por uma lista de produtos, adicionar itens ao carrinho
        de compras, calcular o custo total incluindo frete e finalizar a compra.

    Funcionalidades incluem cadastro e login de usuários, exibição de produtos, cálculo de preços,
        gerenciamento do carrinho de compras, checkout e armazenamento dos resultados em um banco de dados.
*/

package br.com.tads.ecommerce.service;

import br.com.tads.ecommerce.model.ProductImage;
import br.com.tads.ecommerce.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;

    public List<String> getPathsByProductId(Long productId) {
        // Obtém todas as imagens associadas a um determinado ID de produto
        List<ProductImage> images = productImageRepository.findByProductId(productId);

        // Extrai os caminhos das imagens e adiciona-os a uma lista de strings
        List<String> paths = new ArrayList<>();
        for (ProductImage image : images) {
            paths.add(image.getPath());
        }

        return paths;
    }
}

