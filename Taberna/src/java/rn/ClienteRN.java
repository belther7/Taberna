/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rn;

import entidade.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author anatoliandrei
 * @author lucasbeccon
 */
public class ClienteRN {

    public Cliente inserir(Cliente cliente) {
        EntityManager manager = JPAUtil.getManager();

        manager.getTransaction().begin();
        manager.persist(cliente);
        manager.getTransaction().commit();

        manager.close();

        return cliente;

    }

    public List<Cliente> listar() {

        EntityManager manager = JPAUtil.getManager();
        TypedQuery<Cliente> query = manager.createQuery("SELECT c FROM Cliente c", Cliente.class);
        List<Cliente> listaClientes = query.getResultList();

        System.out.println("Clientes: ");
        for (Cliente c : listaClientes) {
            System.out.println(c.getNome() + "-" + c.getTelefone() + "-" + c.getEndereco() + "-" + c.getCpf() + "-" + c.getEmail());
        }
        manager.close();

        return (listaClientes);

    }
    
    public Cliente buscarPorId(Long id){
    EntityManager manager = JPAUtil.getManager();

        Cliente cliente = manager.find(Cliente.class, id);
        manager.close();
        return cliente;
    }
    
    public Cliente atualizar(Long id, Cliente cliente) throws Exception {
        EntityManager manager = JPAUtil.getManager();

        Cliente clienteAtual = manager.find(Cliente.class, id);

        if (clienteAtual == null) {
            throw new Exception();
        }

        manager.getTransaction().begin();
        if (cliente.getNome() != null && !cliente.getNome().isEmpty()) {
            clienteAtual.setNome(cliente.getNome());
        }
        if (cliente.getTelefone() != null && !cliente.getTelefone().isEmpty()) {
            clienteAtual.setTelefone(cliente.getTelefone());
        }
        if (cliente.getEndereco() != null && !cliente.getEndereco().isEmpty()) {
            clienteAtual.setEndereco(cliente.getEndereco());
        }
        if (cliente.getCpf() != null && !cliente.getCpf().isEmpty()) {
            clienteAtual.setCpf(cliente.getCpf());
        }

        //Perguntar pro Ries pq faz verificacao se eh vazio 
        
        if (cliente.getEmail() != null && !cliente.getEmail().isEmpty()) {
            clienteAtual.setEmail(cliente.getEmail());
        }

        manager.getTransaction().commit();

        manager.close();

        return clienteAtual;
    }

    public Cliente deletar(Long id) throws Exception {
        EntityManager manager = JPAUtil.getManager();
        Cliente clienteAtual = manager.find(Cliente.class, id);

        if (clienteAtual == null) {
            throw new Exception();
        }

        manager.getTransaction().begin();
        manager.remove(clienteAtual);
        manager.getTransaction().commit();

        manager.close();

        return (clienteAtual);

    }


}