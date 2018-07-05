/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rn;

import entidade.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author anatoliandrei
 * @author lucasbeccon
 */
public class ProdutoRN {

    public Produto inserir(Produto produto) {
        EntityManager manager = JPAUtil.getManager();

        manager.getTransaction().begin();
        manager.persist(produto);
        manager.getTransaction().commit();

        manager.close();

        return produto;

    }

    public List<Produto> listar() {
        EntityManager manager = JPAUtil.getManager();

        TypedQuery<Produto> query = manager.createQuery("SELECT p FROM Produto p", Produto.class);
        List<Produto> listaProdutos = query.getResultList();

        System.out.println("Produtos:");
        for (Produto p : listaProdutos) {
            System.out.println(p.getNome() + "-" + p.getMarca() + "-" + p.getTipo() + "-" + p.getDescricao() + "-" + p.getValor());
        }

        manager.close();

        return (listaProdutos);

    }

    public Produto buscarPorId(Long id) {
        EntityManager manager = JPAUtil.getManager();

        Produto produto = manager.find(Produto.class, id);
        manager.close();
        return produto;

    }

    public Produto atualizar(Long id, Produto produto) throws Exception {
        EntityManager manager = JPAUtil.getManager();

        Produto produtoAtual = manager.find(Produto.class, id);

        if (produtoAtual == null) {
            throw new Exception();
        }

        manager.getTransaction().begin();
        if (produto.getNome() != null && !produto.getNome().isEmpty()) {
            produtoAtual.setNome(produto.getNome());
        }
        if (produto.getMarca() != null && !produto.getMarca().isEmpty()) {
            produtoAtual.setMarca(produto.getMarca());
        }
        if (produto.getTipo() != null && !produto.getTipo().isEmpty()) {
            produtoAtual.setTipo(produto.getTipo());
        }
        if (produto.getDescricao() != null && !produto.getDescricao().isEmpty()) {
            produtoAtual.setDescricao(produto.getDescricao());
        }

        //Perguntar pro Ries pq faz verificacao se eh vazio 
        if (produto.getValor() != null) {
            produtoAtual.setValor(produto.getValor());
        }

        manager.getTransaction().commit();

        manager.close();

        return produtoAtual;
    }

    public Produto deletar(Long id) throws Exception {
        EntityManager manager = JPAUtil.getManager();
        Produto produtoAtual = manager.find(Produto.class, id);

        if (produtoAtual == null) {
            throw new Exception();
        }

        manager.getTransaction().begin();
        manager.remove(produtoAtual);
        manager.getTransaction().commit();

        manager.close();

        return (produtoAtual);

    }

}
