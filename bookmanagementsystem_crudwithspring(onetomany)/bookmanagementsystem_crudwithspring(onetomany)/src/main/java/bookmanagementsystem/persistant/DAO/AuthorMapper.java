package bookmanagementsystem.persistant.DAO;

import java.util.ArrayList;
import java.util.List;

import bookmanagementsystem.models.*;
import bookmanagementsystem.persistant.DTO.*;


public class AuthorMapper {
	
	public AuthorRequestDTO mapViewModelToRequestDTO(AuthorViewModel author) {
		AuthorRequestDTO dto=new AuthorRequestDTO();
		dto.setId(author.getId());
		dto.setName(author.getName());
		dto.setEmail(author.getEmail());
		dto.setPhno(author.getPhno());
		return dto;
	}	
	public AuthorViewModel mapDTOToViewModel(AuthorResponseDTO dto) {
		AuthorViewModel author=new AuthorViewModel();
		author.setId(dto.getId());
		author.setName(dto.getName());
		author.setEmail(dto.getEmail());
		author.setPhno(dto.getPhno());
		return author;
	}
	public List<AuthorViewModel> mapToListViewModel(List<AuthorResponseDTO> dtos) {
		List<AuthorViewModel> authors=new ArrayList<AuthorViewModel>();
		for(AuthorResponseDTO dto:dtos) {
			AuthorViewModel author=mapDTOToViewModel(dto);
			authors.add(author);
		}
		return authors;
	}
}
