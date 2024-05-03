package bookmanagementsystem.persistant.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmanagementsystem.persistant.DTO.*;

public class AuthorDAO {
	public static Connection con=null;
	static {
		con=MyConnection.getConnection();
	}	
	//insert
	public int addAuthor(AuthorRequestDTO authorDTO) {
		int result = 0;
		String sql = "INSERT INTO author(name,email,phno) VALUES(?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, authorDTO.getName());
			ps.setString(2, authorDTO.getEmail());
			ps.setString(3, authorDTO.getPhno());			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Author Insert error: " + e);
		}
		return result;
	}
	//update
	public int editAuthor(AuthorRequestDTO authorDTO) {
		int result = 0;
		String sql = "UPDATE author SET name=?,email=?,phno=? WHERE id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, authorDTO.getName());
			ps.setString(2, authorDTO.getEmail());
			ps.setString(3, authorDTO.getPhno());

			ps.setInt(4, authorDTO.getId());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Author Update error: " + e);
		}
		return result;
	}
	//delete
	public int deleteAuthor(int id) {
		int result = 0;
		String sql = "DELETE FROM author WHERE id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Author Delete error: " + e);
		}
		return result;
	}
	//selectById
	public AuthorResponseDTO getAuthorById(int id) {
		AuthorResponseDTO author = new AuthorResponseDTO();
		String sql = "SELECT * FROM author WHERE id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				author.setId(rs.getInt("id"));
				author.setName(rs.getString("name"));
				author.setEmail(rs.getString("email"));
				author.setPhno(rs.getString("phno"));
			}
		} catch (SQLException e) {
			System.out.println("select author by id error" + e);
		}
		return author;
	}
	//selectById
	public String getAuthorNameById(int id) {
			String name="";
			String sql = "SELECT name FROM author WHERE id=?";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {					
					name=rs.getString("name");					
				}
			} catch (SQLException e) {
				System.out.println("select author by id error" + e);
			}
			return name;
		}
	//selectAll
	public List<AuthorResponseDTO> getAuthors() {
		List<AuthorResponseDTO> authors = new ArrayList<AuthorResponseDTO>();
		String sql = "SELECT * FROM author";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AuthorResponseDTO author = new AuthorResponseDTO();
				author.setId(rs.getInt("id"));
				author.setName(rs.getString("name"));
				author.setEmail(rs.getString("email"));
				author.setPhno(rs.getString("phno"));

				authors.add(author);
			}
		} catch (SQLException e) {
			System.out.println("select author all error: " + e);
		}
		return authors;
	}
}
