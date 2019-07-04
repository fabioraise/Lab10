package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	
	private PortoDAO pdao;
	private Graph<Author,DefaultEdge> grafo;
	private Map<Integer, Author> aIdMap;
	private List<Author> authorList;

	public Model() {
		this.pdao = new PortoDAO();
		this.grafo = new SimpleGraph<Author, DefaultEdge>(DefaultEdge.class);
		this.aIdMap = new HashMap<Integer,Author>();
		this.authorList = new ArrayList<Author>();
	}

	public List<Author> getAutori() {
		authorList = pdao.getAllAuthors(aIdMap);
		Collections.sort(authorList);
		return authorList;
	}

	public List<Author> getCoautori(Author a) {
		Graphs.addAllVertices(grafo, authorList);
		List<Coautori> coautori = pdao.getCoautori(aIdMap);
		
		for(Coautori ca : coautori)
			grafo.addEdge(ca.getA1(), ca.getA2());
		
		List<Author> vicini = Graphs.neighborListOf(grafo, a);
		
		return vicini;
	}
	
	

}
