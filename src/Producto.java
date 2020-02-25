
//AUTOR: LUCAS FERNÁNDEZ CEDRÓN
//CLASE: PRODUCTO
//PRÁCTICA 3

public class Producto extends ExpBinaria {

	// CONSTRUCTOR
	public Producto(Expresion e1, Expresion e2) {
		super(e1, e2);
	}

	// METODOS
	public int calcularExpresion(Valoracion v) {
		return super.exp1.calcularExpresion(v) * super.exp2.calcularExpresion(v);
	}

	public Expresion copiarExpresion() {
		Producto p = new Producto(super.exp1, super.exp2);
		return p;
	}

	public String toString() {
		String s = ("(" + super.exp1.toString() + "*" + super.exp2.toString() + ")");
		return s;
	}
}
