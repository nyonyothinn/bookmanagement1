package bookmanagementsystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bookmanagementsystem.models.*;
import bookmanagementsystem.persistant.DAO.*;
import bookmanagementsystem.persistant.DTO.*;


@Controller
@RequestMapping("/authors")
public class AuthorController {
	@Autowired
	AuthorDAO authorDAO;
	@Autowired
	AuthorMapper authorMapper;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String displayAuhtors(ModelMap m) {
		List<AuthorResponseDTO>dtos=authorDAO.getAuthors();		
		List<AuthorViewModel>authors=authorMapper.mapToListViewModel(dtos);		
		m.addAttribute("authors", authors);
		return "displayauthors";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView addAuthor() {						
		return new ModelAndView("addauthor","author",new AuthorViewModel());
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addAuthor(@ModelAttribute("author") @Validated AuthorViewModel author,BindingResult bResult,ModelMap model) {
		if(bResult.hasErrors()) {
			return "addauthor";
		}
		
		AuthorRequestDTO dto=authorMapper.mapViewModelToRequestDTO(author);
		
		int rs=authorDAO.addAuthor(dto);
		if(rs==0) {
			model.addAttribute("error","Author Insert Fail(SQL Error)");
			return "addauthor"; 
		}
		return "redirect:/authors/";
	}
	
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public ModelAndView editAuthor(@PathVariable int id) {
		AuthorResponseDTO dto=authorDAO.getAuthorById(id);
		AuthorViewModel author=authorMapper.mapDTOToViewModel(dto);
		
		return new ModelAndView("editauthor","author",author);
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String editAuthor(@ModelAttribute("author") @Validated AuthorViewModel author,BindingResult bResult,ModelMap model) {
		if(bResult.hasErrors()) {
			return "editauthor";
		}		
		AuthorRequestDTO dto=authorMapper.mapViewModelToRequestDTO(author);		
		int rs=authorDAO.editAuthor(dto);
		if(rs==0) {
			model.addAttribute("error","AuthorUpdate Fail(SQL Error)");
			return "editauthor"; 
		}
		return "redirect:/authors/";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String deleteAuthor(@PathVariable int id,ModelMap model) {
						
		int result=authorDAO.deleteAuthor(id);
		if(result==0) {
			model.addAttribute("error","Author Delete Fail(SQL Error)");
			return "displayauthors"; 
		}
		
		return "redirect:/authors/";		
	}
}
