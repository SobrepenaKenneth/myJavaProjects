package Swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentFinalScore extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfStudentName;
	private JTextField tfQuizScore;
	private JTextField tfExamScore;
	private JTextField tfAverage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentFinalScore frame = new StudentFinalScore();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentFinalScore() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHeader = new JLabel("Student Final Score");
		lblHeader.setBounds(95, 11, 232, 29);
		lblHeader.setForeground(new Color(255, 128, 64));
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblHeader.setBackground(new Color(255, 128, 0));
		contentPane.add(lblHeader);
		
		JLabel lblStudentName = new JLabel("Student Name: ");
		lblStudentName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStudentName.setBounds(25, 52, 115, 14);
		contentPane.add(lblStudentName);
		
		JLabel lblQuizScore = new JLabel("Quiz Score: ");
		lblQuizScore.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQuizScore.setBounds(25, 77, 115, 14);
		contentPane.add(lblQuizScore);
		
		JLabel lblExamScore = new JLabel("Exam Score: ");
		lblExamScore.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblExamScore.setBounds(25, 102, 115, 14);
		contentPane.add(lblExamScore);
		
		JLabel lblAverage = new JLabel("Average: ");
		lblAverage.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAverage.setBounds(25, 127, 115, 14);
		contentPane.add(lblAverage);
		
		tfStudentName = new JTextField();
		tfStudentName.setBounds(150, 51, 123, 20);
		contentPane.add(tfStudentName);
		tfStudentName.setColumns(10);
		
		tfQuizScore = new JTextField();
		tfQuizScore.setBounds(150, 76, 123, 20);
		contentPane.add(tfQuizScore);
		tfQuizScore.setColumns(10);
		
		tfExamScore = new JTextField();
		tfExamScore.setBounds(150, 101, 123, 20);
		contentPane.add(tfExamScore);
		tfExamScore.setColumns(10);
		
		tfAverage = new JTextField();
		tfAverage.setBounds(150, 126, 123, 20);
		contentPane.add(tfAverage);
		tfAverage.setColumns(10);
		
		JButton btnCompute = new JButton("Compute");
		btnCompute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int quizScore = Integer.parseInt(tfQuizScore.getText());
				int examScore = Integer.parseInt(tfExamScore.getText());
				int average = (quizScore + examScore) / 2;
				
				tfAverage.setText(String.valueOf(average));
				
			}
		});
		btnCompute.setForeground(new Color(255, 0, 128));
		btnCompute.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCompute.setBounds(150, 157, 123, 23);
		contentPane.add(btnCompute);

	}

}
