package mx.uam.rectoria.dti.ddsae.proceso_admision.chatbot.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.uam.rectoria.dti.ddsae.proceso_admision.chatbot.utils.Chatbot;
import mx.uam.rectoria.dti.ddsae.proceso_admision.chatbot.utils.Nodo;

@Controller
public class VistasController {
	
	private final Chatbot chatbot;
	
	@Autowired 
	public VistasController(Chatbot chatbot) {
		this.chatbot=chatbot;
	}

	public List<String> conversation = new ArrayList<>();
	public long NodoPosicion=1;

    @GetMapping("/chat")
    public String showChat(Model model) {
    	
        conversation = new ArrayList<>();
        conversation.add(chatbot.ProcesamientoInicial());
        model.addAttribute("conversation", conversation);
        return "chatbot";
    }
    

    @PostMapping("/chatPost")
    @ResponseBody
    public Map<String, List<String>> handleUserMessage(@RequestParam("userMessage") String userMessage) {
        
        conversation.add("Tú: " + userMessage);
        conversation.add("Bot: " + chatbot.procesarNodoPorId(userMessage,NodoPosicion));


        return Collections.singletonMap("conversation", conversation);
    }
    
    
    @PostMapping("/limpiar")
    @ResponseBody
    public Map<String, List<String>> limpiar() {
        conversation = new ArrayList<>();
        conversation.add(chatbot.ProcesamientoInicial());
        return Collections.singletonMap("conversation", conversation);
    }
    
}
