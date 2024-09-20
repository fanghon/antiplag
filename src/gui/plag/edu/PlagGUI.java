package gui.plag.edu;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.BusinessBlueSteelSkin;

import imghash.plag.edu.ImageSim;
import moss.plag.edu.Http;

import shingle.plag.edu.ShingleSim;
import utils.edu.WinCMD;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class PlagGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtAssignPath;
	private JRadioButton radBntProgram;
	private JRadioButton radBntText;
	private JTextField txtThreshold;
	private JComboBox combMethod;
	private JComboBox combLang;

	
	WinCMD cmd;
	private JRadioButton radBntImage;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlagGUI frame = new PlagGUI();
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
	public PlagGUI() {
		setTitle("Antiplag\u4F5C\u4E1A\u67E5\u91CD\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 200, 537, 423);
		setLocationRelativeTo(null);
		
		JFrame.setDefaultLookAndFeelDecorated(true);  
        JDialog.setDefaultLookAndFeelDecorated(true);  
        SubstanceLookAndFeel.setSkin(new BusinessBlueSteelSkin()); 
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtAssignPath = new JTextField();
		txtAssignPath.setBounds(51, 49, 305, 21);
		contentPane.add(txtAssignPath);
		txtAssignPath.setColumns(10);
		
		JButton btnNewButton = new JButton("\u9009\u62E9\u4F5C\u4E1A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//ѡ����ҵ·��
				 JFileChooser dlg = new JFileChooser(".");  //��ʼ·���ǳ���ǰ·��
				 dlg.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
				 dlg.setDialogTitle("ѡ����ҵ·�� "); 
				 int result = dlg.showOpenDialog(null); 
				 if (result == JFileChooser.APPROVE_OPTION) { 
				   String path = dlg.getSelectedFile().getAbsolutePath(); 
				   txtAssignPath.setText(path);
				} 					
			}
		});
		btnNewButton.setBounds(385, 48, 93, 23);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u4F5C\u4E1A\u7C7B\u578B", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(51, 95, 305, 53);
		contentPane.add(panel);
		panel.setLayout(null);
		
		radBntProgram = new JRadioButton("\u7A0B\u5E8F\u4F5C\u4E1A ");
		radBntProgram.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				//������ҵ��ѡ��
				if(radBntProgram.isSelected() && combMethod!=null
						&& combLang!=null){
					combMethod.setEnabled(true); // ʹ���㷨ѡ��ť
					combLang.setEnabled(true);
					txtThreshold.setText("50");
				}
			}
		});
		radBntProgram.setBounds(6, 26, 98, 23);
		radBntProgram.setSelected(true);
		panel.add(radBntProgram);
		
		radBntText = new JRadioButton("\u6587\u672C\u4F5C\u4E1A");
		radBntText.setToolTipText("\u652F\u6301\u6587\u6863\u7C7B\u578B\uFF1Adoc docx txt pdf html\u7B49");
		radBntText.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				//�ı���ҵ��ť��ѡ��
				if(radBntText.isSelected()){
					combMethod.setEnabled(false); //��ֹ�㷨ѡ��ť
					combLang.setEnabled(false);
					txtThreshold.setText("50");
				}
			}
		});
		radBntText.setBounds(106, 26, 98, 23);
		ButtonGroup rbGroup = new ButtonGroup();
		rbGroup.add(radBntProgram);
		rbGroup.add(radBntText);
		
		panel.add(radBntText);
		
		radBntImage = new JRadioButton("\u56FE\u7247\u4F5C\u4E1A");
		radBntImage.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//ͼƬ��ť��ѡ��
				if(radBntImage.isSelected()){
					combMethod.setEnabled(false); //��ֹ�㷨ѡ��ť
					combLang.setEnabled(false);
					txtThreshold.setText("80");
				}
			}
			
		});
		radBntImage.setToolTipText("\u652F\u6301\u56FE\u7247\u7C7B\u578B\uFF1Apng jpeg gif");
		radBntImage.setBounds(201, 26, 98, 23);
		panel.add(radBntImage);
		
		ButtonGroup g1=new ButtonGroup();
		g1.add(radBntProgram);
        g1.add(radBntText);
		g1.add(radBntImage);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u53C2\u6570", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(51, 181, 305, 95);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("\u76F8\u4F3C\u5EA6\u9650\u503C");
		label.setBounds(10, 28, 74, 15);
		panel_1.add(label);
		
		txtThreshold = new JTextField();
		txtThreshold.setText("50");
		txtThreshold.setToolTipText("\u8BF7\u8F93\u51650-100\u4E4B\u95F4\u7684\u503C");
		txtThreshold.setBounds(80, 26, 70, 21);
		panel_1.add(txtThreshold);
		txtThreshold.setColumns(10);
		
		JLabel label_1 = new JLabel("\u7A0B\u5E8F\u8BED\u8A00");
		label_1.setBounds(160, 54, 60, 15);
		panel_1.add(label_1);
		
		combLang = new JComboBox();
		combLang.setModel(new DefaultComboBoxModel(new String[] {"java", "c", "python", "csharp", "javascript"}));
		combLang.setBounds(220, 51, 75, 21);
		panel_1.add(combLang);
		
		JLabel lblNewLabel = new JLabel("\u68C0\u6D4B\u5DE5\u5177");
		lblNewLabel.setBounds(10, 54, 60, 15);
		panel_1.add(lblNewLabel);
		
		combMethod = new JComboBox();
		combMethod.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String method = (String)combMethod.getSelectedItem();
				if("moss".equals(method)){
					combLang.removeAllItems();
					combLang.addItem("java");
					combLang.addItem("c");
					combLang.addItem("python");
					combLang.addItem("csharp");	
					combLang.addItem("javascript");
					
				}else if("sim".equals(method)){
					combLang.removeAllItems();
					combLang.addItem("java");
					combLang.addItem("c");
				}else if("jplag".equals(method)) {
					combLang.removeAllItems();
					combLang.addItem("java");
					combLang.addItem("c/c++");
					combLang.addItem("python3");
					combLang.addItem("text");
					combLang.addItem("doc");
				}
			}
		});
		combMethod.setModel(new DefaultComboBoxModel(new String[] {"moss", "jplag", "sim"}));
		combMethod.setBounds(80, 51, 70, 21);
		panel_1.add(combMethod);
		
		JButton button = new JButton("\u6267\u884C\u6BD4\u8F83");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//ִ�бȽ�
				//������
                String path = txtAssignPath.getText();
                File f = new File(path);
				if(f.exists() && f.isDirectory() ){ //�Ƿ���Ч·��
					String threshold = txtThreshold.getText();
					int value = -1; 
					try {
						 value = Integer.valueOf(threshold);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(value>=0 && value<=100){  //�Ƿ���Ч���ƶ���ֵ
						if(radBntText.isSelected()){ //�Ƚ��ı�
						  String[] args = new String[2];
						  args[0] = path;
						  args[1] = threshold;
						  ShingleSim.main(args)	;  // ִ�бȽ�
						  JOptionPane.showMessageDialog(PlagGUI.this, "ִ����ϣ���鿴�����\r\n������Ϊ�գ����Գ��Ե������ƶ���ֵ");	
						}else if(radBntProgram.isSelected()){ //�Ƚϴ������
							String methodtype = (String)combMethod.getSelectedItem();
							String lang = (String)combLang.getSelectedItem();
							//�������						
							if("moss".equals(methodtype)){
								Http http = new Http();
								String url = "http://moss.stanford.edu/";
								String result = http.Get(url);
								if("".equals(result)){
									JOptionPane.showMessageDialog(PlagGUI.this, "Ŀ���������ɷ��ʣ�����������ͨ��");
								    return;
								}
								
							}
							cmd = new WinCMD();
							int res = cmd.exec(methodtype, lang, value, f.getAbsolutePath());
							if(res==0){
								JOptionPane.showMessageDialog(PlagGUI.this, "ִ����ϣ���鿴�����\r\n������Ϊ�գ����Գ��Ե������ƶ���ֵ");
							}else if(res<0){
								JOptionPane.showMessageDialog(PlagGUI.this, "ִ��ʧ�ܣ�������");
							}else if(res>0){
								JOptionPane.showMessageDialog(PlagGUI.this, "ִ����ϣ�δ���ַ�����ֵҪ��Ľ�������Գ��Ե������ƶ���ֵ");
							}
						}else if(radBntImage.isSelected()){ //�Ƚ�ͼƬ
							  String[] args = new String[2];
							  args[0] = path;
							  args[1] = threshold;
							  ImageSim.main(args)	;  // ִ�бȽ�
							  JOptionPane.showMessageDialog(PlagGUI.this, "ִ����ϣ���鿴�����\r\n������Ϊ�գ����Գ��Ե������ƶ���ֵ");	
							
							
							
						}
						
						
					}else{
						JOptionPane.showMessageDialog(PlagGUI.this, "���ƶ���ֵ�����������������");	
						txtThreshold.requestFocus(); //���㶨λ������ֵ�����
					}		
					
					
				}else{
					JOptionPane.showMessageDialog(PlagGUI.this, "��ҵ·������������ѡ��");
					txtAssignPath.requestFocus(); 
				}
			}
		});
		button.setBounds(385, 117, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u67E5\u770B\u7ED3\u679C");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//�鿴���
				String methodtype = (String)combMethod.getSelectedItem();
				String lang = (String)combLang.getSelectedItem();

				CompareResultFrame crf = new CompareResultFrame();
				if(radBntProgram.isSelected()) {
					if("jplag".equals(methodtype)) {
						File rf = new File("jplagresult/matches_avg.csv");
						crf.setResfile(rf);
						
						rf = new File("jplagresult/index.html");
						try {  //����Ĭ�����������ʾ�����ҳ
							Desktop.getDesktop().browse(rf.toURI());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();							
						} 
					}else if("moss".equals(methodtype)) {
						try {  //����Ĭ�����������ʾ�����ҳ
							if(cmd!=null) {
							   String url = cmd.getMoss().getUrl();
							   if(url!=null) {
							     Desktop.getDesktop().browse(new URI(url));
							   }
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();							
						} 						
					}
					
					
				}

				crf.setVisible(true);
			}
		});
		button_1.setBounds(385, 190, 93, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("\u5E2E\u52A9");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//����
				//String info = "��ҵ����ϵͳ\r\n ��ϵ��ʽ�� fanghong_jmi@sina.com";
				//JOptionPane.showMessageDialog(PlagGUI.this, info, "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
                CompareResultFrame crf = new CompareResultFrame();
                crf.setTitle("ʹ�ð���");
                File helpfile = new File("help.txt");
                crf.setResfile(helpfile);                
                crf.setVisible(true);
			}
		});
		button_2.setBounds(385, 313, 93, 23);
		contentPane.add(button_2);
		
		JButton btnNewButton_1 = new JButton("\u63D0\u53D6\u6587\u4EF6");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//ת���ļ������ύ����ҵ�ļ�ת���ʺϱȽϵ���ʽ
				FileConvertFrame fcframe = new FileConvertFrame();
				fcframe.setPgui(PlagGUI.this);
				fcframe.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(385, 251, 93, 23);
		contentPane.add(btnNewButton_1);
	}

	public JTextField getTxtAssignPath() {
		return txtAssignPath;
	}

	public void setTxtAssignPath(JTextField txtAssignPath) {
		this.txtAssignPath = txtAssignPath;
	}
}
