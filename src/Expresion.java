
//AUTOR: LUCAS FERNÁNDEZ CEDRÓN
//CLASE: EXPRESION
//PRÁCTICA 3

import java.util.ArrayList;

public abstract class Expresion {

	// ATRIBUTOS
	protected ArrayList<String> listaVar;

	// CONSTRUCTOR
	public Expresion() {
		listaVar = new ArrayList<String>();
	}

	// METODOS
	public int numVariables() {
		return listaVar.size();
	}

	public boolean estaVariable(String var) {
		return listaVar.contains(var);
	}

	public boolean esCalculable(Valoracion v) {
		// Comprobamos que todas las variables tengan valoracion
		for (int i = 0; i < listaVar.size(); i++) {
			if (!v.tieneValor(listaVar.get(i))) {
				return false;
			}
		}
		return true;
	}

	public ArrayList<String> variables() {
		return listaVar;
	}

	// Metodos abstractos (Definiremos en las subclases)
	public abstract int calcularExpresion(Valoracion v);

	public abstract void sustituirExpresion(String var, int n);

	public abstract void renombrarExpresion(String var, String var2);

	public abstract Expresion copiarExpresion();

	// Metodo para mostrar la lista de las varibles
	public String toString() {
		String mensaje = "Lista de variables: " + listaVar.toString();
		return mensaje;
	}
}
