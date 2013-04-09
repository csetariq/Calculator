package com.inter;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

/**
 * @author Tariq
 * A Calculator application the performs basic arithmetic operations.
 */
public class BasicCalc {

	private JFrame frmCalculator;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton btnNewButton;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JButton button_6;
	private JButton button_7;
	private JButton button_8;
	private JButton button_9;
	private JButton button_10;
	private JButton btnC;
	private JButton button_12;
	private JButton button_13;
	private JButton button_14;
	private JButton btnNewButton_1;
	private JTextField textField;

	private double oprnd1;
	private double oprnd2;
	private double result;
	private char oprtr;
	private boolean newNumMode;

	private enum Input {
		NUM, OP, EQ
	};

	Input lastInput;

	private NumericalInput nlistener;
	private OperatorInput olistener;

	private int ptr = 0;
	private int size;
	private LookAndFeelInfo[] lnfinfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					/**
					 * Initializing the look-and-feel of the application
					 */
					UIManager
							.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					BasicCalc window = new BasicCalc();
					window.frmCalculator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BasicCalc() {
		lnfinfo = UIManager.getInstalledLookAndFeels();
		size = lnfinfo.length;
		initialize();
		reset();
	}

	/**
	 * to reset every variables to its initial values.
	 */
	private void reset() {
		oprnd1 = 0.0;
		oprnd2 = 0.0;
		result = 0.0;
		oprtr = '\0';
		newNumMode = false;
		textField.setText("0");
		lastInput = Input.NUM;
	}

	/**
	 * Initializes the contents of the frame.
	 */
	private void initialize() {
		nlistener = new NumericalInput();
		olistener = new OperatorInput();

		// Setup the basic frame
		this.frmCalculator = new JFrame();
		this.frmCalculator.setAlwaysOnTop(true);
		this.frmCalculator.setCursor(Cursor
				.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		this.frmCalculator.setResizable(false);
		this.frmCalculator.setBounds(new Rectangle(400, 400, 215, 210));
		this.frmCalculator.setTitle("Calculator");
		this.frmCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.panel = new JPanel();
		this.panel.setBorder(new EmptyBorder(3, 3, 3, 3));
		this.frmCalculator.getContentPane().add(this.panel, BorderLayout.NORTH);
		this.panel.setLayout(new GridLayout(0, 1, 0, 0));

		this.textField = new JTextField();
		this.textField.addKeyListener(new KeyAdapter() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent) to
			 * enumerate through the available look and feel of the system.
			 */
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					try {
						String lnf = lnfinfo[ptr++ % size].getClassName();
						UIManager.setLookAndFeel(lnf);
						SwingUtilities.updateComponentTreeUI(frmCalculator);
						// JOptionPane.showMessageDialog(frmCalculator, lnf);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InstantiationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		this.textField.setMargin(new Insets(22, 2, 2, 2));
		this.textField.setPreferredSize(new Dimension(200, 50));
		this.textField.setEditable(false);
		this.textField.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		this.textField.setHorizontalAlignment(SwingConstants.RIGHT);
		this.textField.setText("0");
		this.panel.add(this.textField);
		this.textField.setColumns(10);

		this.panel_1 = new JPanel();
		this.panel_1.setBorder(new EmptyBorder(0, 0, 2, 2));
		this.frmCalculator.getContentPane()
				.add(this.panel_1, BorderLayout.EAST);
		this.panel_1.setLayout(new GridLayout(0, 1, 2, 2));

		this.btnNewButton_1 = new JButton("=");
		this.btnNewButton_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.btnNewButton_1.addActionListener(new Equals());
		this.panel_1.add(this.btnNewButton_1);

		this.panel_2 = new JPanel();
		this.panel_2.setBorder(new EmptyBorder(0, 2, 2, 0));
		this.frmCalculator.getContentPane().add(this.panel_2,
				BorderLayout.CENTER);
		this.panel_2.setLayout(new GridLayout(4, 4, 0, 0));

		this.btnNewButton = new JButton("7");
		this.panel_2.add(this.btnNewButton);
		this.btnNewButton.addActionListener(nlistener);

		this.button = new JButton("8");
		this.panel_2.add(this.button);
		this.button.addActionListener(nlistener);

		this.button_1 = new JButton("9");
		this.panel_2.add(this.button_1);
		this.button_1.addActionListener(nlistener);

		this.button_2 = new JButton("+");
		this.panel_2.add(this.button_2);
		this.button_2.addActionListener(olistener);

		this.button_3 = new JButton("4");
		this.panel_2.add(this.button_3);
		this.button_3.addActionListener(nlistener);

		this.button_4 = new JButton("5");
		this.panel_2.add(this.button_4);
		this.button_4.addActionListener(nlistener);

		this.button_5 = new JButton("6");
		this.panel_2.add(this.button_5);
		this.button_5.addActionListener(nlistener);

		this.button_6 = new JButton("-");
		this.panel_2.add(this.button_6);
		this.button_6.addActionListener(olistener);

		this.button_7 = new JButton("1");
		this.panel_2.add(this.button_7);
		this.button_7.addActionListener(nlistener);

		this.button_8 = new JButton("2");
		this.panel_2.add(this.button_8);
		this.button_8.addActionListener(nlistener);

		this.button_9 = new JButton("3");
		this.panel_2.add(this.button_9);
		this.button_9.addActionListener(nlistener);

		this.button_10 = new JButton("*");
		this.panel_2.add(this.button_10);
		this.button_10.addActionListener(olistener);

		this.btnC = new JButton("C");
		this.btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		this.panel_2.add(this.btnC);

		this.button_12 = new JButton("0");
		this.panel_2.add(this.button_12);
		this.button_12.addActionListener(nlistener);

		this.button_13 = new JButton(".");
		this.button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String preText = textField.getText();
				if (lastInput == Input.NUM && !preText.contains("."))
					textField.setText(preText + e.getActionCommand());
				else if (lastInput == Input.OP)
					textField.setText("0" + e.getActionCommand());
				else if (lastInput == Input.EQ) {
					reset();
					textField.setText("0" + e.getActionCommand());
				}

				lastInput = Input.NUM;
				newNumMode = false;
			}
		});
		this.panel_2.add(this.button_13);

		this.button_14 = new JButton("/");
		this.panel_2.add(this.button_14);
		this.button_14.addActionListener(olistener);
	}

	/**
	 * calculates the current basic arithmetic operation
	 * 
	 * @return arithmetic result
	 */
	private double calculate() {
		switch (oprtr) {
		case '+':
			return oprnd1 + oprnd2;
		case '-':
			return oprnd1 - oprnd2;
		case '*':
			return oprnd1 * oprnd2;
		case '/':
			return oprnd1 / oprnd2;
		default:
			return oprnd2;
		}
	}

	/**
	 * @author Tariq Event Listener for numerical keys
	 */
	private class NumericalInput implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String preText;

			if (lastInput == Input.EQ)
				reset();

			if (newNumMode) {
				textField.setText("");
				newNumMode = false;
			}

			preText = textField.getText();
			if (preText.equals("0"))
				preText = "";
			textField.setText(preText + e.getActionCommand());
			lastInput = Input.NUM;
		}

	}

	/**
	 * @author Tariq Event Listener for operator input
	 */
	private class OperatorInput implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String preText = textField.getText();
			if (lastInput == Input.NUM) {
				oprnd2 = Double.parseDouble(preText);
				result = BasicCalc.this.calculate();
				oprnd1 = result;
				oprtr = e.getActionCommand().charAt(0);
				textField.setText(BasicCalc.this.formatValue(result));
			} else if (lastInput == Input.EQ || lastInput == Input.OP) {
				oprtr = e.getActionCommand().charAt(0);
			}
			newNumMode = true;
			lastInput = Input.OP;
		}
	}

	/**
	 * @author Tariq Event Listener for 'Equals' key
	 */
	private class Equals implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (lastInput == Input.EQ) {
				result = BasicCalc.this.calculate();
				oprnd1 = result;
				textField.setText(BasicCalc.this.formatValue(result));
			} else {
				String preText = textField.getText();
				oprnd2 = Double.parseDouble(preText);
				result = BasicCalc.this.calculate();
				oprnd1 = result;
				textField.setText(BasicCalc.this.formatValue(result));
			}
			newNumMode = true;
			lastInput = Input.EQ;
		}

	}

	/**
	 * formats the given value to by cutting out the unnecessary precision
	 * 
	 * @param value
	 *            value to be parsed
	 * @return well formatted String form of the value
	 */
	private String formatValue(double value) {
		String val = String.valueOf(value);
		return (val.endsWith(".0")) ? val.substring(0, val.length() - 2) : val;
	}
}
