
//AUTOR: LUCAS FERNÁNDEZ CEDRÓN
//CLASE: EXPRESIONBINARIA
//PRÁCTICA 3

import java.util.ArrayList;

public abstract class ExpBinaria extends Expresion {

	// ARIBUTOS
	protected Expresion exp1, exp2;

	// CONSTRUCTOR
	public ExpBinaria(Expresion e1, Expresion e2) {
		exp1 = e1;
		exp2 = e2;

		ArrayList<String> l = exp2.variables();

		for (int i = 0; i < e2.numVariables(); i++) {
			if (!e1.estaVariable(l.get(i))) {
				super.variables().add(l.get(i));
			}
		}
	}

	// METODOS
	public void sustituirExpresion(String var, int n) {
		exp1.sustituirExpresion(var, n);
		exp2.sustituirExpresion(var, n);
		super.variables().remove(var);
	}

	public void renombrarExpresion(String var, String var2) {
		if (super.estaVariable(var) && !super.estaVariable(var2)) {
			exp1.renombrarExpresion(var, var2);
			exp2.renombrarExpresion(var, var2);
			super.variables().remove(var);
			super.variables().add(var2);
		} else {
			System.out.println("ERROR: Ya existe una variable con el nuevo nombre.");
		}
	}

	// Metodos abstractos (Definiremos en las subclases)

	public abstract int calcularExpresion(Valoracion v);

	public abstract Expresion copiarExpresion();

	public abstract String toString();

}
