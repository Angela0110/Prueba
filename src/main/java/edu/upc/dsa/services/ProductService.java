package edu.upc.dsa.services;


import edu.upc.dsa.ProductManagerImpl;
import edu.upc.dsa.models.*;
import edu.upc.dsa.ProductManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Api(value = "/tracks", description = "Endpoint to Track Service")
@Path("/tracks")
public class ProductService {

    private ProductManager pm;

    public ProductService() {
        this.pm = ProductManagerImpl.getInstance();
        if (pm.size()==0) {
            this.pm.AñadirProducto("Cafe", 1);
            this.pm.AñadirProducto("Te", 1);
            this.pm.AñadirProducto("Pizza", 5);
            HashMap<Producto, Integer> hashMap = new HashMap<>();

            // Agregar elementos al HashMap
            hashMap.put(this.pm.GetProducto("Pizza"), 3);
            this.pm.RealizarPedido("Paca",hashMap);
        }

    }

    @GET
    @ApiOperation(value = "Listado de productos por precio")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Producto.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "No hay productos")

    })
    @Path("/a")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductosPrecio() {

        List<Producto> lista = this.pm.DameListaPrecio();

        GenericEntity<List<Producto>> entity = new GenericEntity<List<Producto>>(lista) {};
        return Response.status(201).entity(entity).build() ;
    }

    @GET
    @ApiOperation(value = "Listado de productos por ventas")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Producto.class),
            @ApiResponse(code = 404, message = "No hay productos")
    })
    @Path("/b")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductoVentas() {

        List<Producto> lista  = this.pm.DameListaVentas();
        GenericEntity<List<Producto>> entity = new GenericEntity<List<Producto>>(lista) {};
        return Response.status(201).entity(entity).build() ;
    }

    @GET
    @ApiOperation(value = "Lista de los pedidos de un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Pedido.class),
            @ApiResponse(code = 404, message = "Usuario no encontrado")
    })
    @Path("/c/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPedidos(@PathParam("id") String id) {
        List<Pedido> l = this.pm.DameListaDePedidos(id);
        GenericEntity<List<Pedido>> entity = new GenericEntity<List<Pedido>>(l) {};
        if (l == null) return Response.status(404).build();
        else  return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "Servir un pedido")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Cafeteria.class),
            @ApiResponse(code = 404, message = "Usuario no encontrado")
    })
    @Path("/d")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPedido() {
        Pedido p = this.pm.ServirPedido();
        if (p == null) return Response.status(404).build();
        else  return Response.status(201).entity(p).build();
    }



    @POST
    @ApiOperation(value = "Realizar un pedido")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Pedido.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/e")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newPedido(Pedido pedido) {
        this.pm.RealizarPedido(pedido);

        if (pedido.GetUsuario()==null || pedido.GetProductos()==null)  {return Response.status(500).entity(pedido).build();}
        this.pm.RealizarPedido(pedido);
        return Response.status(201).entity(pedido).build();
    }

}