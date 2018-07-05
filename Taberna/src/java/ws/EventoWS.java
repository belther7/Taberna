/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import entidade.Evento;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import rn.EventoRN;

/**
 * REST Web Service
 *
 * @author anatoliandrei
 * @author lucasbeccon
 */
@Path("evento")
public class EventoWS {
    
    private EventoRN eventoRN;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EventoWS
     */
    public EventoWS() {
        eventoRN = new EventoRN();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Evento> getListaEventos() {
        return eventoRN.listar();
    }

    //@GET //para buscar o objeto pelo id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Evento getEventoPorId(@PathParam("id") Long id) {
        Evento evento = eventoRN.buscarPorId(id);
        if (evento == null) {
            throw new NotFoundException();
        }
        return evento;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Evento insereEvento(Evento evento, @Context HttpServletResponse response) {
        Evento eventoGerado = eventoRN.inserir(evento);
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        try {
            response.flushBuffer();
        } catch (IOException ex) {
            throw new InternalServerErrorException();
        }
        return eventoGerado;
    }

    //@PUT - atualizar
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Evento atualizaEvento(@PathParam("id") Long id, Evento evento) {
        Evento eventoGerado;

        try {
            eventoGerado = eventoRN.atualizar(id, evento);
        } catch (Exception ex) {
            throw new NotFoundException();
        }

        return eventoGerado;
    }

    //@DELETE - remover
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Evento removeProduto(@PathParam("id") Long id) {
        Evento evento = null;
        try {
            evento = eventoRN.deletar(id);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
        return evento;
    }

    
}
