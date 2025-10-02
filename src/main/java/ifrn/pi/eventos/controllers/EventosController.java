package ifrn.pi.eventos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ifrn.pi.eventos.models.Evento;
import ifrn.pi.eventos.repositories.EventoRepository;

@Controller
@RequestMapping("/eventos")
public class EventosController {
	
	@Autowired
	private EventoRepository er;
	
	@GetMapping("/form")
	public String form() {
		return "eventos/formEvento";
	}
	
	 @PostMapping("/salvar")
	    public String salvarEvento() {
	        System.out.println("Método chamado!");
	        return "eventos/evento-sucesso"; 
	    }
	 
	 @PostMapping("/salvar-com-parametros")
	    public String salvarEvento(String nome, String local, String data, String horario) {
	        System.out.println("Nome: " + nome);
	        System.out.println("Local: " + local);
	        System.out.println("Data: " + data);
	        System.out.println("Horário: " + horario);
	        return "eventos/evento-sucesso";
	    }
	 
	 @PostMapping("/salvar-com-objeto")
	    public String salvarEvento(Evento evento) {
	        System.out.println("Nome: " + evento.getNome());
	        System.out.println("Local: " + evento.getLocal());
	        System.out.println("Data: " + evento.getData());
	        System.out.println("Horário: " + evento.getHorario());
	        return "eventos/eventoSucesso";
	    }
	 
	 @PostMapping
	 public String adicionar(Evento evento) {
		 
		 System.out.println(evento);
		 er.save(evento);
		 
		 return "eventos/evento-adicionado";
	 }
	 
	 @GetMapping
	 public ModelAndView listar() {
		 List<Evento> eventos  = er.findAll();
		 ModelAndView mv = new ModelAndView("eventos/lista");
		 mv.addObject("eventos", eventos);
		 return mv;
		 
	 }

}
