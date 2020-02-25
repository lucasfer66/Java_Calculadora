
//AUTOR: LUCAS FERNÁNDEZ CEDRÓN
//CLASE: VALORACION
//PRÁCTICA 3

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class Valoracion {

	// ATRIBUTOS
	// familia de parejas formadas por un nombre de variable y un valor entero
	private Map<String, Integer> variables;

	// CONSTRUCTOR
	public Valoracion() {
		variables = new Hashtable<String, Integer>();
	}

	// METODOS
	public void anadir(String var, int valor) {
		variables.put(var, valor);
	}

	public void eliminar(String var) {
		variables.remove(var);
	}

	public void cambiarValor(String var, int valor) {
		variables.remove(var);
		variables.put(var, valor);
	}

	public int valor(String var) {
		return variables.get(var);
	}

	public boolean tieneValor(String var) {
		return variables.containsKey(var);
	}

	public boolean esCoherente(Valoracion v) {
		ArrayList<String> listaVar = new ArrayList<String>(this.variables.keySet());
		for (int i = 0; i < this.variables.size(); i++) {
			if (v.variables.containsKey(listaVar.get(i))) {
				if (this.variables.get(listaVar.get(i)) != v.valor(listaVar.get(i))) {
					return false;
				}
			}
		}
		return true;
	}
}
