
//AUTOR: LUCAS FERN�NDEZ CEDR�N
//CLASE: SUMA
//PR�CTICA 3

public class Suma extends ExpBinaria {

	// CONSTRUCTOR
	public Suma(Expresion e1, Expresion e2) {
		super(e1, e2);
	}

	// METODOS
	public int calcularExpresion(Valoracion v) {
		return super.exp1.calcularExpresion(v) + super.exp2.calcularExpresion(v);
	}

	public Expresion copiarExpresion() {
		Suma s = new Suma(super.exp1, super.exp2);
		return s;
	}

	public String toString() {
		String s = ("(" + super.exp1.toString() + "+" + super.exp2.toString() + ")");
		return s;
	}
}
