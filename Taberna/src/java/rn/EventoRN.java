/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rn;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;
import entidade.Evento;

/**
 *
 * @author anatoliandrei
 * @author lucasbeccon
 */
public class EventoRN {

    public Evento inserir(Evento evento) {
        EntityManager manager = JPAUtil.getManager();

        manager.getTransaction().begin();
        manager.persist(evento);
        manager.getTransaction().commit();

        manager.close();

        return evento;
    }

    public List<Evento> listar() {
        EntityManager manager = JPAUtil.getManager();

        TypedQuery<Evento> query = manager.createQuery("SELECT e FROM Evento e", Evento.class);
        List<Evento> listaEventos = query.getResultList();

        System.out.println("Eventos:");
        for (Evento e : listaEventos) {
            System.out.println(e.getHorario() + "-" + e.getMinistrante() + "-" + e.getLocal() + "-" + e.getValor());
        }

        manager.close();

        return (listaEventos);
    }

    public Evento buscarPorId(Long id) {
        EntityManager manager = JPAUtil.getManager();

        Evento evento = manager.find(Evento.class, id);
        manager.close();
        return evento;
    }

    public Evento atualizar(Long id, Evento evento) throws Exception {
        EntityManager manager = JPAUtil.getManager();
        Evento eventoAtual = manager.find(Evento.class, id);

        if (eventoAtual == null) {
            throw new Exception();
        }

        manager.getTransaction().begin();
        if (evento.getHorario() != null && !evento.getHorario().isEmpty()) {
            eventoAtual.setHorario(evento.getHorario());
        }
        if (evento.getMinistrante() != null && !evento.getMinistrante().isEmpty()) {
            eventoAtual.setMinistrante(evento.getMinistrante());
        }
        if (evento.getLocal() != null && !evento.getLocal().isEmpty()) {
            eventoAtual.setLocal(evento.getLocal());
        }

        //Perguntar pro Ries pq faz verificacao se eh vazio 
        if (evento.getValor() != null) {
            eventoAtual.setValor(evento.getValor());
        }

        manager.getTransaction().commit();

        manager.close();

        return eventoAtual;
    }

    public Evento deletar(Long id) throws Exception {
        EntityManager manager = JPAUtil.getManager();
        Evento eventoAtual = manager.find(Evento.class, id);

        if (eventoAtual == null) {
            throw new Exception();
        }

        manager.getTransaction().begin();
        manager.remove(eventoAtual);
        manager.getTransaction().commit();

        manager.close();

        return (eventoAtual);

    }

}
