package it.polito.tdp.porto;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;

public class PortoController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Button coautoriButton;
    
    @FXML
    private Button sequenzaButton;

    @FXML
    private ComboBox<Author> boxPrimo;

    @FXML
    private ComboBox<?> boxSecondo;

    @FXML
    private TextArea txtResult;

    @FXML
    void handleCoautori(ActionEvent event) {
    	txtResult.clear();
    	Author a = boxPrimo.getValue();
    	
    	List<Author> coautori = model.getCoautori(a);
    	for(Author ca : coautori)
    		txtResult.appendText(ca.toString()+"\n");
    }

    @FXML
    void handleSequenza(ActionEvent event) {
    	
    }

    @FXML
    void initialize() {
    	assert boxPrimo != null : "fx:id=\"boxPrimo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert boxSecondo != null : "fx:id=\"boxSecondo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert coautoriButton != null : "fx:id=\"coautoriButton\" was not injected: check your FXML file 'Porto.fxml'.";
        assert sequenzaButton != null : "fx:id=\"sequenzaButton\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	boxPrimo.getItems().addAll(model.getAutori());
    }
    
}
