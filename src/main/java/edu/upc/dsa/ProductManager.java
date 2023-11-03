package edu.upc.dsa;

import edu.upc.dsa.models.Producto;
import edu.upc.dsa.models.*;

import java.util.List;
import java.util.Map;

public interface ProductManager {

    public Producto AñadirProducto(String producto, int precio);
    public Producto AñadirProducto(Producto producto);
    public List<Producto> DameListaPrecio();
    public List<Producto> DameListaVentas();
    public Pedido ServirPedido();
    public List<Pedido> DameListaDePedidos(String usuario);
    public Pedido RealizarPedido(String usuario, Map<Producto, Integer> productos);
    public Pedido RealizarPedido(Pedido pedido);
    public Producto GetProducto(String producto);

        public int size();
}
