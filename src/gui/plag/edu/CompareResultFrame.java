package gui.plag.edu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CompareResultFrame extends JFrame {

	private JPanel contentPane;
	private JEditorPane editPane;
    private File resfile;
    private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompareResultFrame frame = new CompareResultFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	String getResult(){
		FileReader fr = null;
		try {
			if(resfile!=null){
				fr = new FileReader(resfile);
				char[] chs = new char[(int)resfile.length()];
				fr.read(chs);
				return new String(chs);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(fr!=null)
					fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * Create the frame.
	 */
	public CompareResultFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				if(resfile==null){
					resfile  = new File("out.txt");
				}
				editPane.setText(getResult());
				System.out.println(editPane.getText());
				
			}
		});
		setTitle("\u6BD4\u8F83\u7ED3\u679C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 557, 506);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("\u5173\u95ED");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//¹Ø±Õ´°¿Ú
				CompareResultFrame.this.dispose();
				
			}
		});
		panel.add(btnNewButton);
		
		editPane = new JEditorPane();
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(editPane);		
		contentPane.add(scrollPane, BorderLayout.CENTER);
	}

	public File getResfile() {
		return resfile;
	}

	public void setResfile(File resfile) {
		this.resfile = resfile;
	}
}
