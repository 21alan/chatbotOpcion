package mx.uam.rectoria.dti.ddsae.proceso_admision.chatbot.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

import mx.uam.rectoria.dti.ddsae.proceso_admision.chatbot.repository.NodoRepository;

@Service
public class Chatbot {
	
	
	
	private final  ResponseChat responseChat;
	@Autowired
    public Chatbot(ResponseChat responseChat) {
        this.responseChat = responseChat;
    }
	
	@Autowired
	private NodoRepository nodoRepository;
	@Transactional
	public String ProcesamientoInicial(){
		String texto="";
		Optional<Nodo> optionalNodo = nodoRepository.findById(1L);
		
		if (optionalNodo.isPresent()) {
			
            Nodo nodoActual = optionalNodo.get();
            texto=texto+nodoActual.getEtiqueta()+"<br>";
            
            Set<Nodo> nodosRelacionados = nodoActual.getRelaciones();

            if (nodosRelacionados.isEmpty()) {
                texto="No hay respuestas disponibles actualmente";
            } else {
                for (Nodo relacionado : nodosRelacionados) {
                    texto=texto+relacionado.getIdNodoActual()+"-"+relacionado.getEtiqueta()+"<br>";
                }
            }
        } else {
            texto="No hay respuestas disponibles actualmente";
        }
		return texto;
	}
	
    @Transactional
    public String procesarNodoPorId(String respuesta, long NodoPosicion) {
    	String texto="";
    	
    	try {

    		long numero = Long.parseLong(respuesta);
    		
    	    Optional<Nodo> optionalNodo = nodoRepository.findById(numero);
    	    if (optionalNodo.isPresent()) {
    	    	NodoPosicion=numero;
                Nodo nodoActual = optionalNodo.get();
                texto=texto+nodoActual.getEtiqueta()+"<br>";
                
                Set<Nodo> nodosRelacionados = nodoActual.getRelaciones();

                if (nodosRelacionados.isEmpty()) {
                    texto="Respuesta ultima";
                } else {
                    for (Nodo relacionado : nodosRelacionados) {
                        texto=texto+relacionado.getIdNodoActual()+"-"+relacionado.getEtiqueta()+"<br>";
                    }
                }
            } else {
                texto="No hay respuestas disponibles actualmente";
            }
    	    
    	} catch (NumberFormatException e) {
    		texto="Respuesta no valida";
    	}
		
		
		return texto;
    }
    
    
    
	
	
}
