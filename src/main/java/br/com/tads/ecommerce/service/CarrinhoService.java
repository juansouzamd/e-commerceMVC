package br.com.tads.ecommerce.service;

import br.com.tads.ecommerce.model.Carrinho;
import br.com.tads.ecommerce.model.ItemCarrinho;
import br.com.tads.ecommerce.model.Usuario;
import br.com.tads.ecommerce.repository.CarrinhoRepository;
import br.com.tads.ecommerce.repository.ItemCarrinhoRepository;
import br.com.tads.ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Carrinho getCarrinhoByUsuario(Usuario usuario) {
        Optional<Carrinho> optionalCarrinho = carrinhoRepository.findById(usuario.getId());
        return optionalCarrinho.orElseGet(Carrinho::new);
    }

    public void addItemToCarrinho(ItemCarrinho item, Usuario usuario) {
        Carrinho carrinho = getCarrinhoByUsuario(usuario);
        item.setUsuario(usuario);
        carrinho.addItem(item);
        itemCarrinhoRepository.save(item);
        carrinhoRepository.save(carrinho);
    }

    public double getTotalPrice(Carrinho carrinho) {
        return carrinho.getTotalPrice();
    }

    public double getShippingCost(Carrinho carrinho) {
        return carrinho.calculateShipping();
    }

    public void clearCarrinho(Usuario usuario) {
        Carrinho carrinho = getCarrinhoByUsuario(usuario);
        carrinho.getItensCarrinho().clear();
        carrinhoRepository.save(carrinho);
    }
}

