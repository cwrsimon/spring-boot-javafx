/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wesim.springbootjavafx.backend;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author christian
 */
@Service
public class ZinsenCalculationService {

//    private final int restSchuld;
//    private final int years;
//    private final int darlehenssumme;

    public ZinsenCalculationService() {
    }
    
    
//    public ZinsenCalculationService(int years, int darlehenssumme, double rate) {
//        this.years = years;
//        this.restSchuld = darlehenssumme;
//        this.darlehenssumme = darlehenssumme;
//
//    }

    /* 
    def calculateMonthly(schuld, rate, tilgungs_rate):
    original_schuld = schuld
    tilgungs_rate = float(tilgungs_rate)
    #sondertilgung = 1500

    # monthly
    return schuld * (tilgungs_rate + rate) / 100 / 12
     */
    public static BigDecimal calculateMonthly(BigDecimal schuld, BigDecimal rate, BigDecimal tilgungs_rate) {
        BigDecimal foo = rate.add(tilgungs_rate);
        return schuld.multiply(foo).divide(new BigDecimal(1200), 2, RoundingMode.HALF_UP);
    }

//    def reduceSchuld(rate, monthly, schuld, months):
//    monthly_rate = rate / float(100) / 12
//    for i in range(1, months + 1):
//        zinsen = round(float(schuld) * monthly_rate, 2)
//        tilgung = monthly - zinsen
//        schuld = schuld - tilgung
//    return schuld
    public static ResultObject reduceSchuld(BigDecimal rate, BigDecimal monthly, BigDecimal schuld, int months) {
        BigDecimal tilgungGes = new BigDecimal(0);
        BigDecimal newSchuld = schuld;
        for (int i = 1; i <= months; i++) {
            BigDecimal zinsen = newSchuld.multiply(rate).divide(new BigDecimal(1200), 2, RoundingMode.UP);
            BigDecimal tilgung = monthly.subtract(zinsen);
            newSchuld = newSchuld.subtract(tilgung);
            tilgungGes = tilgungGes.add(tilgung);
            System.out.println(i + "\t" + zinsen + "\t" + tilgung + "\t" + newSchuld);
        }
        ResultObject res = new ResultObject();
        res.restSchuld = newSchuld;
        res.tilgungGesamt = tilgungGes;
        return res;
    }

    /*
    def generateCombinations(darlehenssumme, rate):
    tilgung = 1.0
    tilgung_max = 4.00
    while tilgung <= tilgung_max:
        monthly = calculateMonthly(darlehenssumme, rate, tilgung)
        print "Tilgung:" + str(tilgung)  + "\tMonthly Rate:" + str(monthly)
        #result = main(darlehenssumme, rate, years, tilgung, sondertilgung)
        #printResult(result)
        #outputLine(result)
        tilgung = tilgung + 0.25
     */
    public static List<String> generateCombinations(BigDecimal darlehenssumme, BigDecimal rate) {
        List<String> combinations = new ArrayList<>();
        for (BigDecimal tilgung = new BigDecimal(1.0f); tilgung.intValue() <= 10; tilgung = tilgung.add(new BigDecimal(0.25))) {
            BigDecimal monthly = calculateMonthly(darlehenssumme, rate, tilgung);
            combinations.add(monthly.toString() + " (" + tilgung  + " %)");
            System.out.println("Tilgung: " + tilgung + "\tMonthly Rate:" + monthly);

        }
        return combinations;
    }

    
}
