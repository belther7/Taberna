package ws;

import entidade.Produto;
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
import rn.ProdutoRN;

/**
 * REST Web Service
 *
 * @author anatoliandrei
 * @author lucasbeccon
 */
@Path("produto")
public class ProdutoWS {

    private ProdutoRN produtoRN;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ProdutoWS
     */
    public ProdutoWS() {
        produtoRN = new ProdutoRN();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Produto> getListaProdutos() {
        return produtoRN.listar();
    }

    //@GET //para buscar o objeto pelo id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Produto getProdutoPorId(@PathParam("id") Long id) {
        Produto produto = produtoRN.buscarPorId(id);
        if (produto == null) {
            throw new NotFoundException();
        }
        return produto;
    }

    //@GET //para buscar por nome
    // @GET
    //@Path("/query")
    //@Produces(MediaType.APPLICATION_JSON)
    //public List<Produto> getProduto(@QueryParam("nome") String nome) {
    //    List<Produto> listaProdutosBusca = new ArrayList<>();
    //    for(Produto m: listaProdutos){
    //        if(m.getNome().contains(nome)){
    //            listaProdutosBusca.add(m);
    //        }
    //    }
    //    if(listaProdutosBusca.isEmpty())
    //        throw new NotFoundException();
    //    else
    //        return listaProdutosBusca;
    //}        
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Produto insereProduto(Produto produto, @Context HttpServletResponse response) {
        Produto produtoGerado = produtoRN.inserir(produto);
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        try {
            response.flushBuffer();
        } catch (IOException ex) {
            throw new InternalServerErrorException();
        }
        return produtoGerado;
    }

    //@PUT - atualizar
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Produto atualizaProduto(@PathParam("id") Long id, Produto produto) {
        Produto produtoGerado;

        try {
            produtoGerado = produtoRN.atualizar(id, produto);
        } catch (Exception ex) {
            throw new NotFoundException();
        }

        return produtoGerado;
    }

    //@DELETE - remover
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Produto removeProduto(@PathParam("id") Long id) {
        Produto produto = null;
        try {
            produto = produtoRN.deletar(id);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
        return produto;
    }
}
