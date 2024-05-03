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

import bookmanagementsystem.models.AuthorViewModel;
import bookmanagementsystem.models.BookBean;
import bookmanagementsystem.persistant.DAO.AuthorDAO;
import bookmanagementsystem.persistant.DAO.AuthorMapper;
import bookmanagementsystem.persistant.DAO.BookDAO;
import bookmanagementsystem.persistant.DAO.BookMapper;
import bookmanagementsystem.persistant.DTO.AuthorResponseDTO;
import bookmanagementsystem.persistant.DTO.BookRequestDTO;
import bookmanagementsystem.persistant.DTO.BookResponseDTO;

@Controller
@RequestMapping("/books")
public class BookController {
	@Autowired
	BookDAO bookDAO;
	@Autowired
	AuthorDAO authorDAO;
	@Autowired
	BookMapper mapper;
	@Autowired
	AuthorMapper authorMapper;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String displayBooks(ModelMap m) {
		List<BookResponseDTO>dtos=bookDAO.getAllBooks();
		
		List<BookBean>books=mapper.mapToListBean(dtos);		
		m.addAttribute("books", books);
		return "displaybooks";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView addBook(ModelMap model) {
				
		List<AuthorViewModel> authors=authorMapper.mapToListViewModel(authorDAO.getAuthors());		
		model.addAttribute("authors",authors);
				
		return new ModelAndView("addbook","book",new BookBean());
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addBook(@ModelAttribute("book") @Validated BookBean book,BindingResult bResult,ModelMap model) {
		if(bResult.hasErrors()) {
			return "addbook";
		}
		
		BookRequestDTO dto=mapper.mapToRequestDTO(book);
		
		int rs=bookDAO.addBook(dto);
		if(rs==0) {
			model.addAttribute("error","Insert Fail(SQL Error)");
			return "addbook"; 
		}
		return "redirect:/books/";
	}
	
	@RequestMapping(value="/edit/{code}",method=RequestMethod.GET)
	public ModelAndView editBook(@PathVariable String code,ModelMap model) {
		BookResponseDTO dto=bookDAO.getBookByCode(code);
		BookBean updatedBook=mapper.mapToBean(dto);
		
		List<AuthorViewModel> authors=authorMapper.mapToListViewModel(authorDAO.getAuthors());		
		model.addAttribute("authors",authors);
		
		return new ModelAndView("editbook","book",updatedBook);
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String editBook(@ModelAttribute("book") @Validated BookBean book,BindingResult bResult,ModelMap model) {
		if(bResult.hasErrors()) {
			List<AuthorViewModel> authors=authorMapper.mapToListViewModel(authorDAO.getAuthors());		
			model.addAttribute("authors",authors);
			return "editbook";
			
		}		
		BookRequestDTO dto=mapper.mapToRequestDTO(book);		
		int rs=bookDAO.editBook(dto);
		if(rs==0) {
			model.addAttribute("error","Update Fail(SQL Error)");
			return "editbook"; 
		}
		return "redirect:/books/";
	}
	
	@RequestMapping(value="/delete/{code}",method=RequestMethod.GET)
	public String deleteBook(@PathVariable String code,ModelMap model) {
						
		int result=bookDAO.deleteBook(code);
		if(result==0) {
			model.addAttribute("error","Delete Fail(SQL Error)");
			return "displaybooks"; 
		}
		
		return "redirect:/";		
	}
		

}
