package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Producto {
    public String producto;
    String id;
    public int precio;
    public int ventas;

    public Producto() {
        this.id = RandomUtils.getId();
    }


    public Producto(String producto, int precio){
        this();
        this.precio = precio;
        this.producto = producto;
        this.ventas = 0;
    }

    public int GetPrecio(){
        return this.precio;
    }


    public int GetVentas(){
        return this.ventas;
    }
    public String GetProducto(){
        return this.producto;
    }

    public void SetVentas(int ventas){
        this.ventas = ventas;
    }


    @Override
    public String toString() {
        return "Producto [id="+producto+", precio=" + precio + ", ventas=" + ventas +"]";
    }

}