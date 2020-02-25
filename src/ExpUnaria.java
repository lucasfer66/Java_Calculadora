
//AUTOR: LUCAS FERNÁNDEZ CEDRÓN
//CLASE: EXPRESIONUNARIA
//PRÁCTICA 3

public class ExpUnaria extends Expresion {

	// ATRIBUTOS
	private Expresion exp;

	// CONSTRUCTOR
	public ExpUnaria(Expresion e) {
		this.exp = e;
		super.variables().addAll(e.variables());
	}

	// METODOS
	public int calcularExpresion(Valoracion v) {
		return -exp.calcularExpresion(v);
	}

	public void sustituirExpresion(String var, int n) {
		exp.sustituirExpresion(var, n);
		super.variables().remove(var);
	}

	public void renombrarExpresion(String var, String var2) {
		if (super.estaVariable(var) && !super.estaVariable(var2)) {
			exp.renombrarExpresion(var, var2);
			super.variables().remove(var);
			super.variables().add(var2);
		} else {
			System.out.println("ERROR: Ya existe una variable con el nuevo nombre.");
		}
	}

	public Expresion copiarExpresion() {
		return new ExpUnaria(exp.copiarExpresion());
	}

	public String toString() {
		String s = ("-" + this.exp.toString());
		return s;
	}
}
