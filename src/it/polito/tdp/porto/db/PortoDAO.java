package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Coautori;
import it.polito.tdp.porto.model.Paper;

public class PortoDAO {

	/*
	 * Dato l'id ottengo l'autore.
	 */
	public Author getAutore(int id) {

		final String sql = "SELECT * FROM author where id=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				return autore;
			}

			return null;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato l'id ottengo l'articolo.
	 */
	public Paper getArticolo(int eprintid) {

		final String sql = "SELECT * FROM paper where eprintid=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, eprintid);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				return paper;
			}

			return null;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public List<Author> getAllAuthors(Map<Integer,Author> aIdMap) {
		String sql = "SELECT * FROM author";
		List<Author> autori = new ArrayList<Author>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Author a = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				autori.add(a);
				aIdMap.put(a.getId(), a);
			}
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return autori;
	}

	public List<Coautori> getCoautori(Map<Integer, Author> aIdMap) {
		String sql = "SELECT c1.authorid AS a1, c2.authorid AS a2 " + 
				"FROM creator AS c1, creator AS c2 " + 
				"WHERE c1.eprintid = c2.eprintid AND c1.authorid < c2.authorid";
		
		List<Coautori> coautori = new ArrayList<Coautori>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Author a1 = aIdMap.get(rs.getInt("a1"));
				Author a2 = aIdMap.get(rs.getInt("a2"));
				
				Coautori ca = new Coautori(a1, a2);
				
				coautori.add(ca);
			}
			
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return coautori;
	}
}