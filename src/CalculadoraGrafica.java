
//AUTOR: LUCAS FERNÁNDEZ CEDRÓN
//CLASE: CALCULADORAGRAFICA
//PRÁCTICA 3

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class CalculadoraGrafica implements ActionListener {

	private Calculadora c; // Objeto de la clase Calculadora
	private JTextField tfexp, tfx, tfy, tfz, tfres; // Campos de texto para las
													// expresiones y
	// los valores de las variables

	public CalculadoraGrafica() {
		// En el constructor se crean las componentes gráficas
		JFrame f = new JFrame(); // Objeto JFrame que contendrá todas las
									// componentes gráficas
		f.setTitle("Calculadora");
		f.setMinimumSize(new Dimension(300, 620));
		f.setLocation(50, 50);
		f.getContentPane().setLayout(new GridLayout(3, 1));

		JPanel panel1 = new JPanel();
		panel1.setLayout(null); // El JFrame estará dividido en tres paneles
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		JPanel panel3 = new JPanel();
		panel3.setLayout(null);

		JLabel l1 = new JLabel("CALCULADORA DE LUCAS"); // Etiqueta
		l1.setBounds(5, 0, 290, 30);
		l1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		l1.setForeground(Color.blue);
		panel1.add(l1);

		tfexp = new JTextField(); // Campo de texto para introducir la expresión
		tfexp.setBounds(5, 30, 270, 30);
		panel1.add(tfexp);
		JPanel panel11 = new JPanel();
		panel11.setLayout(new GridLayout(3, 6));
		JButton b;
		String[] buttons = { "x", "y", "z", "1", "2", "3", "+", "*", "-", "4", "5", "6", "(", ")", "0", "7", "8", "9" };
		for (int i = 0; i < buttons.length; i++) {
			b = new JButton(buttons[i]);
			b.addActionListener(this);
			panel11.add(b); // Botones para introducir la expresión
		}
		panel11.setBounds(15, 70, 250, 120);
		panel1.add(panel11);
		f.getContentPane().add(panel1);

		JLabel l2 = new JLabel("¿Que valores tomará la variable?"); // Etiqueta
		l2.setBounds(5, 51, 290, 30);
		l2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		l2.setForeground(Color.blue);
		panel2.add(l2);

		JPanel panel21 = new JPanel();
		panel21.setLayout(new GridLayout(3, 2));
		panel21.setBounds(40, 90, 130, 90);
		panel21.add(new JLabel("x  ", JLabel.CENTER));
		tfx = new JTextField();
		panel21.add(tfx);
		panel21.add(new JLabel("y  ", JLabel.CENTER));
		tfy = new JTextField();
		panel21.add(tfy);
		panel21.add(new JLabel("z  ", JLabel.CENTER));
		tfz = new JTextField();
		panel21.add(tfz);
		panel2.add(panel21);
		f.getContentPane().add(panel2);

		JLabel l3 = new JLabel("¿Que acción desea realizar?"); // Etiqueta
		l3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		l3.setBounds(5, 51, 290, 30);
		l3.setForeground(Color.blue);
		panel3.add(l3);

		// Botones para realizar las operaciones sobre la expresión
		b = new JButton("Sustituir");
		b.addActionListener(this);
		b.setBounds(5, 90, 87, 30);
		panel3.add(b);
		b = new JButton("Calcular");
		b.addActionListener(this);
		b.setBounds(97, 90, 87, 30);
		panel3.add(b);
		b = new JButton("Borrar");
		b.addActionListener(this);
		b.setBounds(189, 90, 87, 30);
		panel3.add(b);
		tfres = new JTextField();
		tfres.setBounds(5, 130, 270, 30);
		panel3.add(tfres);
		f.getContentPane().add(panel3);

		f.setVisible(true);
	}

	public void actionPerformed(ActionEvent ev) {
		String aux = ((JButton) ev.getSource()).getText();

		if (aux.equals("Sustituir")) {
			this.sustituir();
		} else {
			if (aux.equals("Calcular")) {
				this.calcular();
			} else {
				if (aux.equals("Borrar")) {
					this.reset();
				} else {
					String aux2 = tfexp.getText();
					tfexp.setText(aux2 + aux);
				}
			}
		}
	}

	private void calcular() {
		String s = tfexp.getText();
		Expresion exp = parser(s);
		Valoracion v = new Valoracion();
		v.cambiarValor("x", new Integer(tfx.getText()));
		v.cambiarValor("y", new Integer(tfy.getText()));
		v.cambiarValor("z", new Integer(tfz.getText()));
		c = new Calculadora(exp, v);
		int res = c.calcularValor();
		tfres.setText(Integer.toString(res));
	}

	private void sustituir() {
		String s = tfexp.getText();
		tfres.setText(s);
	}

	private void reset() {
		tfexp.setText("");
		tfx.setText("");
		tfy.setText("");
		tfz.setText("");
		tfres.setText("");
	}

	private static String getExp1(String s) {
		int i = 1, na = 1, nc = 0;

		while (na != nc) {
			if (s.charAt(i) == '(') {
				na++;
			} else {
				if (s.charAt(i) == ')') {
					nc++;
				}
			}
			i++;
		}
		return s.substring(0, i);
	}

	private static Expresion parser(String s) {
		if (s.charAt(0) == '(') {
			String s1 = getExp1(s);
			if (s1.length() == s.length()) {
				return parser(s.substring(1, s.length() - 1));
			}
			char op = s.charAt(s1.length());
			String s2 = s.substring(s1.length() + 1, s.length());
			if (op == '+')
				return new Suma(parser(s1), parser(s2));
			else
				return new Producto(parser(s1), parser(s2));
		} else {
			if (s.charAt(0) == '-') {
				if (s.charAt(1) == '(') {
					s = s.substring(2, s.length() - 1);
					return new ExpUnaria(parser(s));
				} else {
					s = s.substring(1, s.length());
					return new ExpUnaria(parser(s));
				}
			} else {
				int i1 = s.indexOf("+");
				int i2 = s.indexOf("*");
				if ((i1 == -1) && (i2 == -1)) {
					if (s.equals("x") || s.equals("y") || s.equals("z")) {
						return new ExpAtomica(s);
					} else {
						return new ExpAtomica(Integer.parseInt(s));
					}
				} else if ((i1 != -1) && ((i2 == -1) || (i1 < i2))) {
					String s1 = s.substring(0, i1);
					String s2 = s.substring(i1 + 1, s.length());
					return new Suma(parser(s1), parser(s2));
				} else {
					String s1 = s.substring(0, i2);
					String s2 = s.substring(i2 + 1, s.length());
					return new Producto(parser(s1), parser(s2));

				}
			}
		}
	}
}