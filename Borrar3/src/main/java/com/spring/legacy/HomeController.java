package com.spring.legacy;

import java.text.DateFormat;
import java.util.Collections;
import java.util.Date;

import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.spring.bean.Empresa;
import com.spring.bean.Persona;
import com.spring.bean.PersonaTarea;

/**
 * Handles requests for the application home page.
 */


@Controller
public class HomeController implements ServletContextAware{
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private ServletContext sc;
	
	
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.sc = servletContext;
	}
	
	
	@RequestMapping(value = "/ronnie", method = RequestMethod.GET)
	@ResponseBody
	public String alguienselaCome() {
		
		XmlWebApplicationContext ac = new XmlWebApplicationContext();
		ac.setConfigLocation("/WEB-INF/spring/appServlet/servlet-context.xml");
		ac.setServletContext(this.sc);
		ac.refresh();
		
		Persona p = ac.getBean("ronnie", Persona.class);
		
		return p.getNombre() + " se la come";
	}
	
	@RequestMapping(value = "/hector", method = RequestMethod.GET)
	@ResponseBody
	public String alguiennoselaCome() {
		
		XmlWebApplicationContext ac = new XmlWebApplicationContext();
		ac.setConfigLocation("/WEB-INF/spring/appServlet/servlet-context.xml");
		ac.setServletContext(this.sc);
		ac.refresh();
		
		Persona p = ac.getBean("hector", Persona.class);
		
		return p.getNombre() + " es chidori";
	}
	
	@RequestMapping(value = "/empresa", method = RequestMethod.GET)
	@ResponseBody
	public String alguienesCeo() {
		
		XmlWebApplicationContext ac = new XmlWebApplicationContext();
		ac.setConfigLocation("/WEB-INF/spring/appServlet/servlet-context.xml");
		ac.setServletContext(this.sc);
		ac.refresh();
		
		Empresa e = ac.getBean("empresa", Empresa.class);
		//Persona p = e.getCeo();
		
		return "La empresa es: " + e.getNombre() + "\nEl CEO es: " + e.getCeo().getNombre();
	}
	
	@RequestMapping(value = "/empleados", method = RequestMethod.GET)
	@ResponseBody
	public String verEmplaedos() {
		
		XmlWebApplicationContext ac = new XmlWebApplicationContext();
		ac.setConfigLocation("/WEB-INF/spring/appServlet/servlet-context.xml");
		ac.setServletContext(this.sc);
		ac.refresh();
		
		Empresa e = ac.getBean("empresa", Empresa.class);
		List<Persona> lis = e.getEmpleados();
		Persona p = lis.get(0);
		
		
		return "El primer empleado es: " + p.getNombre();
	}
	
	@RequestMapping(value = "/tareaBeans", method = RequestMethod.GET)
	//@ResponseBody
	public String tareaBeans(Locale locale, Model model) {
		
		XmlWebApplicationContext ac = new XmlWebApplicationContext();
		ac.setConfigLocation("/WEB-INF/spring/appServlet/servlet-context.xml");
		ac.setServletContext(this.sc);
		ac.refresh();
		PersonaTarea pt = ac.getBean("emmanuelGomez", PersonaTarea.class);
		
		Collections.sort(pt.getSubordinados(), new ArrangeMm());
		
		String pTarea = pt.toString();
		model.addAttribute("personas", pTarea);
		
		return "tareaBeans";
	}
	
}
