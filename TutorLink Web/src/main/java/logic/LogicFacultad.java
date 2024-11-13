package logic;

import data.DataFacultad;
import entities.Facultad;
import java.util.LinkedList;

public class LogicFacultad {
    private DataFacultad dataFacultad = new DataFacultad();

    public LinkedList<Facultad> getAllFacultades() {
        return dataFacultad.getAll();
    }

    public Facultad getFacultadById(int id) {
        Facultad f = new Facultad();
        f.setId(id);
        return dataFacultad.getById(f);
    }

    public void addFacultad(Facultad facultad) {
        dataFacultad.add(facultad);
    }

    public void updateFacultad(Facultad facultad) {
        dataFacultad.update(facultad);
    }

    public void deleteFacultad(int id) {
        Facultad f = new Facultad();
        f.setId(id);
        dataFacultad.remove(f);
    }
}