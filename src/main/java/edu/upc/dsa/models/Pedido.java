package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.Map;

public class Pedido {
    public Map<Producto, Integer> productos;
    public String usuario;
    public int realizado; //=1 realizado
    String id;

    public Pedido() {
        this.id = RandomUtils.getId();
    }


    public Pedido(Map productos, String usuario){
        this();
        this.productos = productos;
        this.usuario = usuario;
        this.realizado = 0;
    }

    public int GetRealizado (){
        return this.realizado;
    }

    public String GetUsuario (){
        return this.usuario;
    }

    public Map<Producto, Integer> GetProductos(){
        return this.productos;
    }
}
