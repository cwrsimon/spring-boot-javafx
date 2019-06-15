package de.wesim.springbootjavafx;

/*
 * Copyright (c) 2012, 2014 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
import javafx.application.Application;
import static javafx.geometry.HPos.RIGHT;

import javax.annotation.PostConstruct;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Component
public class MainView extends GridPane {

   

    @Autowired
    private ViewModel vm;
    
	private TextField zinssatzField;
	private ComboBox monthlyField;
	private TextField schuldTextField;
	private TextField laufzeitField;
	private Text actiontarget;
	private TextField darlehensSummeField;
	private Text tilgungGesamt;

    public MainView() {
    }


    @PostConstruct
    private void init2() {
    	
   	 this.zinssatzField = new TextField();
	    this.monthlyField = new ComboBox();
	    this.schuldTextField = new TextField();
	   this.laufzeitField = new TextField();
	  this.actiontarget = new Text();
	    this.tilgungGesamt = new Text();
	 this.darlehensSummeField = new TextField();

        darlehensSummeField.textProperty().bindBidirectional(vm.darlehensSumme);
        zinssatzField.textProperty().bindBidirectional(vm.zinssatz);

        monthlyField.itemsProperty().bindBidirectional(vm.combinations);
        monthlyField.setEditable(true);
        monthlyField.valueProperty().bindBidirectional(vm.monthlyRate);
                
        laufzeitField.textProperty().bindBidirectional(vm.laufzeit);
        schuldTextField.textProperty().bindBidirectional(vm.currentSchuld);

        tilgungGesamt.textProperty().bind(vm.tilgungGesamt);
        actiontarget.textProperty().bind(vm.restSchuld);
        actiontarget.setFill(Color.FIREBRICK);
    
        //GridPane grid = new GridPane();
        setAlignment(Pos.CENTER_LEFT);
        setHgap(10);
        setVgap(10);
        
        Text scenetitle = new Text("Zinsenberechnung");
        //scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        add(scenetitle, 0, 0, 2, 1);

        Label darlehensSummeLabel = new Label("Darlehenssumme:");
        add(darlehensSummeLabel, 0, 1);
        add(darlehensSummeField, 1, 1);

        Label userName = new Label("Aktuelle Schuld:");
        add(userName, 0, 2);
        add(schuldTextField, 1, 2);

        Label pw = new Label("Zinssatz:");
        add(pw, 0, 3);
        add(zinssatzField, 1, 3);

        Label monthlyLabel = new Label("Monatliche Rate:");
        add(monthlyLabel, 0, 4);
        add(monthlyField, 1, 4);

        Label laufzeitLabel = new Label("Laufzeit in Monaten:");
        add(laufzeitLabel, 0, 5);
        add(laufzeitField, 1, 5);

        Button btn = new Button("Los");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        add(hbBtn, 1, 6);
add(tilgungGesamt, 0, 8);

        add(actiontarget, 1, 8);
        setColumnSpan(actiontarget, 1);
        setHalignment(actiontarget, RIGHT);
        setHalignment(tilgungGesamt, RIGHT);

        actiontarget.setId("actiontarget");

        btn.setOnAction(e ->  {
                vm.recalculate();
            }
        );


    }

}
