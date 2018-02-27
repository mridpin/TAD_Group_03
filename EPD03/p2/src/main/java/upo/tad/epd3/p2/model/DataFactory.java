/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.epd3.p2.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ridao
 */
public class DataFactory {

    public List<Trabajador> generarDatos() {
        List<Trabajador> res = new ArrayList<>();

        Trabajador tr1 = new Trabajador("Manuel", "Ridao", "15411173T", "08.59", "17.00", "2018-02-27");
        res.add(tr1);
        Trabajador tr2 = new Trabajador("Juan Antonio", "Rodríguez", "12345678A", "09.01", "17.00", "2018-02-26");
        res.add(tr2);
        Trabajador tr3 = new Trabajador("Antonio José", "Herrera", "87654321B", "09.00", "16.59", "2018-02-28");
        res.add(tr3);
        
        return res;
    }
}
