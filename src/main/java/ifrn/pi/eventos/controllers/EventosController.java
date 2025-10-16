package ifrn.pi.eventos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ifrn.pi.eventos.models.Convidado;
import ifrn.pi.eventos.models.Evento;
import ifrn.pi.eventos.repositories.ConvidadoRepository;
import ifrn.pi.eventos.repositories.EventoRepository;

@Controller
@RequestMapping("/eventos")
public class EventosController {
	
	@Autowired
	private EventoRepository er;
	@Autowired
	private ConvidadoRepository cr;
	
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
	 @GetMapping("/{id}")
	 public ModelAndView detalhar (@PathVariable Long id) {
		 ModelAndView md = new ModelAndView();
		 Optional<Evento> opt = er.findById(id);
		 if (opt.isEmpty()) {
			 md.setViewName("redirect:/eventos");
			 return md;
		 }
		 md.setViewName("eventos/detalhes");
		 Evento evento = opt.get();
		 
		 md.addObject("evento", evento);
		 
		 return md;
	 }
	 
	 @PostMapping("/{idEvento}")
	 public String salvarConvidado(@PathVariable Long idEvento, Convidado convidado) {
		 
		 System.out.println("Id do evento:" + idEvento);
		 System.out.println(convidado);
		 
		 Optional<Evento> opt = er.findById(idEvento);
		 if(opt.isEmpty()) {
			 return "redirect:/eventos";
			 
		 }
		 
		 Evento evento = opt.get();
		 convidado.setEvento(evento);
		 
		cr.save(convidado);
		 
		 return "redirect:/eventos/{idEvento}";
		 
	 }
	 @GetMapping("/{id}/remover")
	 public String apagarEvento(@PathVariable Long id){
		 
		 Optional<Evento> opt = er.findById(id);
		 
		 if(!opt.isEmpty()) {
			 Evento evento = opt.get();
			 
			 
			List<Convidado> convidados = cr.findByEvento(evento);
			cr.deleteAll(convidados);
			er.delete(evento);
			 
		 }
		 
		 return "redirect:/eventos";
		 
	 }
	 

}
