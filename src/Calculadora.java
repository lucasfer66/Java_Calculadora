
//AUTOR: LUCAS FERNÁNDEZ CEDRÓN
//CLASE: CALCULADORA
//PRÁCTICA 3

public class Calculadora {

	// ATRIBUTOS
	private Expresion exp;
	private Valoracion val;

	// CONSTRUCTOR
	// constructor vacio
	public Calculadora() {
		this.val = new Valoracion();
	}

	// constructor con paso de parámetros
	public Calculadora(Expresion e, Valoracion v) {
		this.exp = e;
		this.val = v;
	}

	// METODOS
	public int calcularValor() {
		// calcula el valor de la expresion
		return this.exp.calcularExpresion(this.val);
	}

	public void modificarExpresion() {
		while (this.exp.numVariables() != 0) {
			// sustituimos cada variable por su valoracion
			this.exp.sustituirExpresion(this.exp.variables().get(0), this.val.valor(this.exp.variables().get(0)));
		}
	}

	public void anade(String var, int n) {
		this.val.anadir(var, n);
	}

	public void elimina(String var) {
		this.val.eliminar(var);
	}

	public void cambiaValor(String var, int n) {
		this.val.cambiarValor(var, n);
	}

	public void sustituye(String var) {
		// mirar el valor en la valoracion
		this.exp.sustituirExpresion(var, val.valor(var));

	}

	public void renombra(String var, String var2) {
		this.exp.renombrarExpresion(var, var2);
	}

	public boolean esCalculable() {
		return this.exp.esCalculable(val);
	}

	public void mostrarExpresion() {
		System.out.println(this.exp.toString());
	}
}
