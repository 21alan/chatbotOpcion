package mx.uam.rectoria.dti.ddsae.proceso_admision.chatbot.utils;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "nodo")
public class Nodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nodo_actual")
    private Long idNodoActual;

    @Column(nullable = false)
    private String etiqueta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nodo_padre")
    private Nodo nodoPadre;

    @OneToMany(mappedBy = "nodoPadre", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Nodo> hijos = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "relaciones",
        joinColumns = @JoinColumn(name = "id_nodo_actual"),
        inverseJoinColumns = @JoinColumn(name = "id_nodo_relacion")
    )
    private Set<Nodo> relaciones = new HashSet<>();

    public Nodo() {}

    public Nodo(String etiqueta, Nodo nodoPadre) {
        this.etiqueta = etiqueta;
        this.nodoPadre = nodoPadre;
    }


    public Long getIdNodoActual() {
        return idNodoActual;
    }

    public void setIdNodoActual(Long idNodoActual) {
        this.idNodoActual = idNodoActual;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Nodo getNodoPadre() {
        return nodoPadre;
    }

    public void setNodoPadre(Nodo nodoPadre) {
        this.nodoPadre = nodoPadre;
    }

    public Set<Nodo> getHijos() {
        return hijos;
    }

    public void setHijos(Set<Nodo> hijos) {
        this.hijos = hijos;
    }

    public Set<Nodo> getRelaciones() {
        return relaciones;
    }

    public void setRelaciones(Set<Nodo> relaciones) {
        this.relaciones = relaciones;
    }


    public void addHijo(Nodo hijo) {
        hijos.add(hijo);
        hijo.setNodoPadre(this);
    }

    public void removeHijo(Nodo hijo) {
        hijos.remove(hijo);
        hijo.setNodoPadre(null);
    }

    public void addRelacion(Nodo nodoRelacionado) {
        relaciones.add(nodoRelacionado);
        nodoRelacionado.getRelaciones().add(this);
    }

    public void removeRelacion(Nodo nodoRelacionado) {
        relaciones.remove(nodoRelacionado);
        nodoRelacionado.getRelaciones().remove(this);
    }
}

