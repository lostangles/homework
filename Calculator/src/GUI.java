import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GUI {

	public static void main(String[] args) {

		GUI gui = new GUI();
		// set up frames, components, etc
		JFrame frame = new JFrame("Best Calculator Ever");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(new Dimension(300, 400));
		frame.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 4));

		JTextArea nLabel = new JTextArea("0", 3, 20);
		//	JLabel cLabel = new JLabel("", SwingConstants.CENTER);
		//	JLabel sLabel = new JLabel("What fun!", SwingConstants.CENTER);
		JButton OneButton = new JButton("1");
		JButton TwoButton = new JButton("2");
		JButton ThreeButton = new JButton("3");
		JButton FourButton = new JButton("4");
		JButton FiveButton = new JButton("5");
		JButton SixButton = new JButton("6");
		JButton SevenButton = new JButton("7");
		JButton EightButton = new JButton("8");
		JButton NineButton = new JButton("9");
		JButton ZeroButton = new JButton("0");
		JButton DivButton = new JButton("/");
		JButton MultButton = new JButton("*");
		JButton MinButton = new JButton("-");
		JButton PlusButton = new JButton("+");
		JButton ClrButton = new JButton("C");
		JButton EqlButton = new JButton("=");
		JButton mod = new JButton("%");
		JButton exp = new JButton("^");
		JButton blank = new JButton("");
		JButton blank2 = new JButton("");

		// layout
		frame.add(nLabel, BorderLayout.NORTH);
		//	frame.add(sLabel);
		frame.add(panel, BorderLayout.CENTER);

		panel.add(SevenButton);
		panel.add(EightButton);
		panel.add(TwoButton);
		panel.add(NineButton);
		panel.add(ClrButton);
		panel.add(FourButton);
		panel.add(FiveButton);
		panel.add(SixButton);
		panel.add(MinButton);
		panel.add(OneButton);
		panel.add(TwoButton);
		panel.add(ThreeButton);
		panel.add(MultButton);
		panel.add(ZeroButton);
		panel.add(PlusButton);
		panel.add(DivButton);
		panel.add(mod);
		panel.add(blank2);
		panel.add(blank);
		panel.add(exp);
		panel.add(EqlButton);

		//	panel.add(cLabel);

		// event listeners
		MyListener listen = new MyListener(nLabel);
		OneButton.addActionListener(listen);
		TwoButton.addActionListener(listen);
		ThreeButton.addActionListener(listen);
		FourButton.addActionListener(listen);
		FiveButton.addActionListener(listen);
		SixButton.addActionListener(listen);
		SevenButton.addActionListener(listen);
		EightButton.addActionListener(listen);
		NineButton.addActionListener(listen);
		ZeroButton.addActionListener(listen);
		DivButton.addActionListener(listen);
		MultButton.addActionListener(listen);
		MinButton.addActionListener(listen);
		PlusButton.addActionListener(listen);
		EqlButton.addActionListener(listen);
		ClrButton.addActionListener(listen);
		mod.addActionListener(listen);
		exp.addActionListener(listen);

		frame.setVisible(true);
	}
}

class MyListener implements ActionListener {

	private JTextArea label;
	Scanner in = new Scanner(System.in);
	Evaluator eval = new Evaluator();
	String result;

	public MyListener(JTextArea label) {
		this.label = label;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JButton button = (JButton) event.getSource();
		String text = button.getText();
		if (!text.equals("C") && !button.getText().equals("=")) {
			if (label.getText().equals("0")) {
				label.setText("");
			}

			label.append(text);
		} else if (text.equals("C")) {
			label.setText("0");
		} else if (text.equals("=")) {
			result = eval.evaluate(label.getText());
			label.setText(result);
		}
	}
}