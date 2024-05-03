package bookmanagementsystem.persistant.DAO;

import java.sql.*;
import java.util.*;

import bookmanagementsystem.persistant.DTO.*;

public class BookDAO {
	public static Connection con=null;
	static {
		con=MyConnection.getConnection();
	}
	//insert
	public int addBook(BookRequestDTO bookDTO) {
		int result=0;
		System.out.println(bookDTO.getAuthor_id());
		String sql="INSERT INTO book(code,name,author_id,price) VALUES(?,?,?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, bookDTO.getCode());
			ps.setString(2, bookDTO.getName());
			ps.setInt(3, bookDTO.getAuthor_id());
			ps.setDouble(4, bookDTO.getPrice());
			result=ps.executeUpdate();			
		}catch(SQLException e) {
			System.out.println("Insert error: "+e);
		}		
		return result;
	}
	//update
	public int editBook(BookRequestDTO bookDTO) {
		int result=0;
		System.out.println(bookDTO.getAuthor_id());
		String sql="UPDATE book SET name=?,author_id=?,price=? WHERE code=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);			
			ps.setString(1, bookDTO.getName());
			ps.setInt(2, bookDTO.getAuthor_id());
			ps.setDouble(3, bookDTO.getPrice());
			
			ps.setString(4, bookDTO.getCode());
			
			result=ps.executeUpdate();			
		}catch(SQLException e) {
			System.out.println("Update error: "+e);
		}		
		return result;
	}
	//delete
	public int deleteBook(String code) {
		int result=0;
		String sql="DELETE FROM book WHERE code=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);			
			ps.setString(1, code);
			
			result=ps.executeUpdate();			
		}catch(SQLException e) {
			System.out.println("Delete error: "+e);
		}		
		return result;
	}

	//selectByCode
	public BookResponseDTO getBookByCode(String code) {
		BookResponseDTO book=new BookResponseDTO();
		String sql="SELECT * FROM book WHERE code=?";
		
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, code);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				book.setCode(rs.getString("code"));
				book.setName(rs.getString("name"));
				book.setAuthor_id(rs.getInt("author_id"));				
				book.setPrice(rs.getDouble("price"));
			}				
		}catch(SQLException e) {
			System.out.println("select by code error"+e);
		}
		return book;
	}
	//selectAll
	public List<BookResponseDTO> getAllBooks() {
		List<BookResponseDTO> books=new ArrayList<BookResponseDTO>();
		String sql="SELECT * FROM book";
		
		try {
			PreparedStatement ps=con.prepareStatement(sql);			
			ResultSet rs=ps.executeQuery();			
			while(rs.next()) {
				BookResponseDTO book=new BookResponseDTO();
				book.setCode(rs.getString("code"));
				book.setName(rs.getString("name"));
				book.setAuthor_id(rs.getInt("author_id"));				
				book.setPrice(rs.getDouble("price"));
				
				books.add(book);
			}				
		}catch(SQLException e) {
			System.out.println("select all error: "+e);
		}
		return books;
	}

}
