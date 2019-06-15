/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wesim.springbootjavafx;

import de.wesim.springbootjavafx.backend.ResultObject;
import de.wesim.springbootjavafx.backend.ZinsenCalculationService;
import java.math.BigDecimal;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author christian
 */
@Component
public class ViewModel {

    @Autowired
    private ZinsenCalculationService zinsenCalculationService;
    
    final ObjectProperty<ObservableList> combinations = 
            new SimpleObjectProperty<>(FXCollections.observableArrayList());

    protected StringProperty darlehensSumme = new SimpleStringProperty();
    protected StringProperty currentSchuld = new SimpleStringProperty();
    protected StringProperty zinssatz = new SimpleStringProperty();
    protected StringProperty restSchuld = new SimpleStringProperty();
    protected StringProperty tilgungGesamt = new SimpleStringProperty();
    protected StringProperty laufzeit = new SimpleStringProperty();
    protected StringProperty monthlyRate = new SimpleStringProperty();

    public ViewModel() {
        zinssatz.set("2.25");

        darlehensSumme.addListener((observable, oldValue, newValue) -> {
            if (oldValue == null || newValue == null) return;
            if (!oldValue.equals(newValue)) {
                updateComboBox();
            }
        });
        zinssatz.addListener((observable, oldValue, newValue) -> {
            if (oldValue == null || newValue == null) return;
            if (!oldValue.equals(newValue)) {
                updateComboBox();
            }
        });
        laufzeit.addListener((observable, oldValue, newValue) -> {
            //if (oldValue == null) return;
            if (newValue == null || newValue.isEmpty()) {
                restSchuld.set("");
                return;
            }
            if (oldValue != null && 
                    oldValue.equals(newValue)) return;
            recalculate();
        });
    }

    private void updateComboBox() {
        BigDecimal rateText = new BigDecimal(zinssatz.get());
        BigDecimal schuld = new BigDecimal(darlehensSumme.get());
        List<String> newCombs = zinsenCalculationService.generateCombinations(schuld, rateText);
        combinations.get().clear();
        combinations.get().addAll(newCombs);
    }

    protected void recalculate() {
        final BigDecimal rate = new BigDecimal(zinssatz.get());
        final BigDecimal schuld = new BigDecimal(currentSchuld.get());
        final String monthlyRateText = monthlyRate.get();
        final String[] items = monthlyRateText.split(" ");

        final BigDecimal localMonthlyRate = new BigDecimal(items[0]);
        final int months = Integer.valueOf(laufzeit.get());
        final ResultObject result = 
                zinsenCalculationService.reduceSchuld(rate, localMonthlyRate, schuld, months);
        restSchuld.set(result.restSchuld.toString());
        tilgungGesamt.set(result.tilgungGesamt.toString());
    }
    
}
