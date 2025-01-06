package mx.uam.rectoria.dti.ddsae.proceso_admision.chatbot.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.uam.rectoria.dti.ddsae.proceso_admision.chatbot.utils.Nodo;

public interface NodoRepository extends JpaRepository<Nodo, Long>{

}
