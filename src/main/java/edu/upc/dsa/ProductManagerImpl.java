package edu.upc.dsa;

import edu.upc.dsa.models.Producto;
import edu.upc.dsa.models.*;

import java.util.*;

import org.apache.log4j.Logger;

public class ProductManagerImpl implements ProductManager {
    protected Cafeteria cafe = new Cafeteria();

    private static ProductManager instance;
    final static Logger logger = Logger.getLogger(ProductManagerImpl.class);

    public static ProductManager getInstance() {
        if (instance==null) instance = new ProductManagerImpl();
        return instance;
    }

    public int size() {
        int ret = this.cafe.listaProductos.size();
        logger.info("size " + ret);

        return ret;
    }

    public Producto AñadirProducto(Producto producto) {
        logger.info("Nuevo producto " + producto);

        this.cafe.listaProductos.add (producto);
        logger.info("nuevo producto añadido");
        return producto;
    }

    public  Producto AñadirProducto(String producto, int precio){
        return this.AñadirProducto(new Producto(producto,precio));
    }

    public Producto GetProducto(String producto){
        for (Producto p:this.cafe.listaProductos){
            if (p.GetProducto().equals(producto)){
                return p;
            }
        }
        return null;
    }
    public List<Producto> DameListaPrecio(){
        logger.info(this.cafe.listaProductos);
        List<Producto> lista = this.cafe.listaProductos;
        logger.info(lista);
        Collections.sort(lista, Comparator.comparingInt(Producto::GetPrecio));
        logger.info("esta es la litsa");
        return lista;
    }

    public List<Producto> DameListaVentas(){
        List<Producto> lista = this.cafe.listaProductos;
        Collections.sort(lista, Comparator.comparingInt(Producto::GetVentas).reversed());
        logger.info(lista);
        return lista;
    }

    public Pedido ServirPedido(){
        Pedido servido = cafe.pedidos.poll();
        servido.realizado = 1;
        return servido;

    }

    public Pedido RealizarPedido(String usuario, Map<Producto, Integer> productos){
        return this.RealizarPedido(new Pedido(productos, usuario));
    }
    public Pedido RealizarPedido(Pedido pedido) {
        logger.info("Nuevo pedido " + pedido);
        Iterator<Map.Entry<Producto, Integer>> iterador = pedido.GetProductos().entrySet().iterator();
        while (iterador.hasNext()) {
            Map.Entry<Producto, Integer> entry = iterador.next();
            Producto p = entry.getKey();
            int cantidad = entry.getValue();
            logger.info(cantidad);
            int ventas = p.GetVentas();
            p.SetVentas(ventas + cantidad);
        }
        cafe.pedidos.add(pedido);
        cafe.historialPedidos.add(pedido);
        logger.info("nuevo producto añadido");
        return pedido;
    }


    public List<Pedido> DameListaDePedidos(String usuario){
        List<Pedido> miLista = new ArrayList<>();
        for (Pedido p:cafe.historialPedidos){
            if (p.GetUsuario().equals(usuario) && p.GetRealizado() == 1) {
                miLista.add(p);
            }
        }
        logger.info(miLista);
        return miLista;
    }
}