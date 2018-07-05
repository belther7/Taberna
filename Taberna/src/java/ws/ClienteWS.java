/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import entidade.Cliente;
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
import javax.ws.rs.core.MediaType;
import rn.ClienteRN;


/**
 * REST Web Service
 *
 * @author anatoliandrei
 * @author lucasbeccon
 */
@Path("cliente")
public class ClienteWS {

   private ClienteRN clienteRN;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ClienteWS
     */
    public ClienteWS() {
        clienteRN = new ClienteRN();
    }

    //ok
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getListaCliente() {
        return clienteRN.listar();
    }
    
    
    //ok
    
    //@GET //para buscar o objeto pelo id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente getClientePorId(@PathParam("id") Long id) {
        Cliente cliente = clienteRN.buscarPorId(id);
        if (cliente == null) {
            throw new NotFoundException();
        }
        return cliente;
    }
    
    //@POST // para inserir
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente insereCliente(Cliente cliente, @Context HttpServletResponse response) {
        Cliente clienteGerado = clienteRN.inserir(cliente);
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        try {
            response.flushBuffer();
        } catch (IOException ex) {
            throw new InternalServerErrorException();
        }
        return clienteGerado;
    }

    //@PUT - atualizar
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente atualizaCliente(@PathParam("id") Long id, Cliente cliente) {

        Cliente clienteGerado;
        try {
            clienteGerado = clienteRN.atualizar(id, cliente);
        } catch (Exception ex) {
            throw new NotFoundException();
        }

        return clienteGerado;
    }
    
    //@DELETE - remover
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente removeCliente(@PathParam("id") Long id) {
        Cliente cliente = null;
        try {
            cliente = clienteRN.deletar(id);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
        return cliente;
    }
}
