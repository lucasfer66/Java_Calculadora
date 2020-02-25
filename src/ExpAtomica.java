
//AUTOR: LUCAS FERNÁNDEZ CEDRÓN
//CLASE: EXPRESIONATOMICA
//PRÁCTICA 3

public class ExpAtomica extends Expresion {

	// ATRIBUTOS
	private int valor;

	// CONSTRUCTORES
	// para variables
	public ExpAtomica(String var) {
		super.variables().add(var);
	}

	// para constantes
	public ExpAtomica(int v) {
		this.valor = v;
	}

	// METODOS
	public int calcularExpresion(Valoracion v) {
		if (super.numVariables() == 0) {
			return valor;
		} else {
			return v.valor(super.variables().get(0));
		}
	}

	public void sustituirExpresion(String var, int n) {
		if (super.estaVariable(var)) {
			this.valor = n;
			super.variables().remove(var);
		}
	}

	public void renombrarExpresion(String var, String var2) {
		if (super.estaVariable(var) && !super.estaVariable(var2)) {
			super.variables().remove(var);
			super.variables().add(var2);
		} else {
			System.out.println("ERROR: Ya existe una variable con el nuevo nombre.");
		}
	}

	public Expresion copiarExpresion() {
		if (super.numVariables() == 0) {
			ExpAtomica expa = new ExpAtomica(this.valor);
			return expa;
		} else {
			ExpAtomica expa = new ExpAtomica(variables().get(0));
			return expa;
		}
	}

	public String toString() {
		return super.variables().get(0);
	}
}
