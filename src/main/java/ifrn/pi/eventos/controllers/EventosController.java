package ifrn.pi.eventos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ifrn.pi.eventos.models.Evento;

@Controller
public class EventosController {
	
	@RequestMapping("/eventos/form")
	public String form() {
		return "formEvento";
	}
	
	 @PostMapping("/salvar")
	    public String salvarEvento() {
	        System.out.println("Método chamado!");
	        return "evento-sucesso"; 
	    }
	 
	 @PostMapping("/salvar-com-parametros")
	    public String salvarEvento(String nome, String local, String data, String horario) {
	        System.out.println("Nome: " + nome);
	        System.out.println("Local: " + local);
	        System.out.println("Data: " + data);
	        System.out.println("Horário: " + horario);
	        return "evento-sucesso";
	    }
	 
	 @PostMapping("/salvar-com-objeto")
	    public String salvarEvento(Evento evento) {
	        System.out.println("Nome: " + evento.getNome());
	        System.out.println("Local: " + evento.getLocal());
	        System.out.println("Data: " + evento.getData());
	        System.out.println("Horário: " + evento.getHorario());
	        return "eventoSucesso";
	    }

}
