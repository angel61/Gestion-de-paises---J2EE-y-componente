/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantalla;

/**
 *
 * @author angel
 */
public class Municipio {

    private String nombre;
    private int codpais;
    private double tasapago; //tasa a pagar por las viviendas
    private double sumaimpuestos; //suma impuestos de las vivienda
    private int numerohabitantes; //numero de personas en el municipio

    String modificar;
    String eliminar;
    String insertar;

    public Municipio() {
        super();
    }

    public Municipio(String nombre, int codpais, double tasapago, double sumaimpuestos, int numerohabitantes) {
        super();
        this.nombre = nombre;
        this.codpais = codpais;
        this.tasapago = tasapago;
        this.sumaimpuestos = sumaimpuestos;
        this.numerohabitantes = numerohabitantes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodpais() {
        return codpais;
    }

    public void setCodpais(int codpais) {
        this.codpais = codpais;
    }

    public double getTasapago() {
        return tasapago;
    }

    public void setTasapago(double tasapago) {
        this.tasapago = tasapago;
    }

    public double getSumaimpuestos() {
        return sumaimpuestos;
    }

    public void setSumaimpuestos(double sumaimpuestos) {
        this.sumaimpuestos = sumaimpuestos;
    }

    public int getNumerohabitantes() {
        return numerohabitantes;
    }

    public void setNumerohabitantes(int numerohabitantes) {
        this.numerohabitantes = numerohabitantes;
    }

    public String getModificar() {
        return modificar;
    }

    public void setModificar(String modificar) {
        this.modificar = modificar;
    }

    public String getEliminar() {
        return eliminar;
    }

    public void setEliminar(String eliminar) {
        this.eliminar = eliminar;

    }

    public String getInsertar() {
        return insertar;
    }

    public void setInsertar(String insertar) {
        this.insertar = insertar;
    }

}
