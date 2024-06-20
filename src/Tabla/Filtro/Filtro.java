package Tabla.Filtro;

import java.util.List;

import Tabla.Columna;

public abstract class Filtro {

    public Filtro(){}

    public abstract List<Integer> filtrar(Columna<?> columna, String valor);

}
