package entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Table(name="productos")
@EntityListeners(ProductosLista.class)
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String procesador;

    @Column(nullable = false)
    private int ram; // en GB

    @Column(nullable = false)
    private int almacenamiento; // en GB

    @Column(nullable = false)
    private BigDecimal precio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(int almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Laptop: " +
                "\nId: " + id +
                "\nNombre: " + nombre +
                "\nProcesador: " + procesador +
                "\nRAM: " + ram + " GB" +
                "\nAlmacenamiento: " + almacenamiento + " GB" +
                "\nPrecio: " + precio;
    }
}
