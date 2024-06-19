package Tabla;

import java.util.List;

public abstract class Medida {
    
    public Medida(){
    }

    public abstract double operar(Columna<?> columna, List<Integer> indicesFilas);

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Medida otraMedida = (Medida) obj;
        return getClass().equals(otraMedida.getClass());
    }
}
