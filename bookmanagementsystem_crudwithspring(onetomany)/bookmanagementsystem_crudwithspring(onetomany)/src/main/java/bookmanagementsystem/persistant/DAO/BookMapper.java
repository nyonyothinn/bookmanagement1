package bookmanagementsystem.persistant.DAO;

import java.util.ArrayList;
import java.util.List;

import bookmanagementsystem.models.BookBean;
import bookmanagementsystem.persistant.DTO.BookRequestDTO;
import bookmanagementsystem.persistant.DTO.BookResponseDTO;

public class BookMapper {
	public BookRequestDTO mapToRequestDTO(BookBean bean) {
		BookRequestDTO dto=new BookRequestDTO();
		dto.setCode(bean.getCode());
		dto.setName(bean.getName());
		dto.setAuthor_id(bean.getAuthor_id());
		dto.setPrice(bean.getPrice());
		return dto;
	}	
	public BookBean mapToBean(BookResponseDTO dto) {
		AuthorDAO authorDAO=new AuthorDAO();
		
		BookBean bean=new BookBean();
		bean.setCode(dto.getCode());
		bean.setName(dto.getName());
		bean.setAuthor_id(dto.getAuthor_id());
		String author_name=authorDAO.getAuthorNameById(dto.getAuthor_id());
		bean.setAuthor_name(author_name);
		bean.setPrice(dto.getPrice());
		return bean;
	}
	public List<BookBean> mapToListBean(List<BookResponseDTO> dtos) {
		List<BookBean> beans=new ArrayList<BookBean>();
		for(BookResponseDTO dto:dtos) {
			BookBean bean=mapToBean(dto);
			
			beans.add(bean);
		}
		return beans;
	}
	
}
