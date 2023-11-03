package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Cafeteria {

    public List<Producto> listaProductos;
    public Queue<Pedido> pedidos;
    public List<Pedido> historialPedidos;

    public Cafeteria(){
        this.listaProductos = new ArrayList<Producto>();
        this.pedidos = new LinkedList<Pedido>();
        this.historialPedidos = new ArrayList<Pedido>();
    }
}
