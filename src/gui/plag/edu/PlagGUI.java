package gui.plag.edu;

import java.awt.BorderLayout;
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

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.BusinessBlueSteelSkin;

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
		setTitle("\u4F5C\u4E1A\u67E5\u91CD\u7CFB\u7EDF");
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
				
				//选择作业路径
				 JFileChooser dlg = new JFileChooser(".");  //起始路径是程序当前路径
				 dlg.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
				 dlg.setDialogTitle("选择作业路径 "); 
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
				//程序作业被选中
				if(radBntProgram.isSelected() && combMethod!=null
						&& combLang!=null){
					combMethod.setEnabled(true); // 使能算法选择按钮
					combLang.setEnabled(true);
				}
			}
		});
		radBntProgram.setBounds(6, 26, 98, 23);
		radBntProgram.setSelected(true);
		panel.add(radBntProgram);
		
		radBntText = new JRadioButton("\u6587\u672C\u4F5C\u4E1A");
		radBntText.setToolTipText("\u652F\u6301\u6587\u6863\u7C7B\u578B\uFF1Adoc docx txt");
		radBntText.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				//文本作业按钮被选择
				if(radBntText.isSelected()){
					combMethod.setEnabled(false); //禁止算法选择按钮
					combLang.setEnabled(false);
				}
			}
		});
		radBntText.setBounds(158, 26, 98, 23);
		ButtonGroup rbGroup = new ButtonGroup();
		rbGroup.add(radBntProgram);
		rbGroup.add(radBntText);
		
		panel.add(radBntText);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u53C2\u6570", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(51, 181, 305, 95);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("\u76F8\u4F3C\u5EA6\u9650\u503C");
		label.setBounds(10, 28, 74, 15);
		panel_1.add(label);
		
		txtThreshold = new JTextField();
		txtThreshold.setText("30");
		txtThreshold.setToolTipText("\u8BF7\u8F93\u51650-100\u4E4B\u95F4\u7684\u503C");
		txtThreshold.setBounds(80, 26, 70, 21);
		panel_1.add(txtThreshold);
		txtThreshold.setColumns(10);
		
		JLabel label_1 = new JLabel("\u7A0B\u5E8F\u8BED\u8A00");
		label_1.setBounds(160, 54, 60, 15);
		panel_1.add(label_1);
		
		combLang = new JComboBox();
		combLang.setModel(new DefaultComboBoxModel(new String[] {"java", "c", "csharp", "javascript"}));
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
					combLang.addItem("csharp");	
					combLang.addItem("javascript");
					
				}else if("sim".equals(method)){
					combLang.removeAllItems();
					combLang.addItem("java");
					combLang.addItem("c");
				}
			}
		});
		combMethod.setModel(new DefaultComboBoxModel(new String[] {"moss", "sim"}));
		combMethod.setBounds(80, 51, 70, 21);
		panel_1.add(combMethod);
		
		JButton button = new JButton("\u6267\u884C\u6BD4\u8F83");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//执行比较
				//检查参数
                String path = txtAssignPath.getText();
                File f = new File(path);
				if(f.exists() && f.isDirectory() ){ //是否有效路径
					String threshold = txtThreshold.getText();
					int value = -1; 
					try {
						 value = Integer.valueOf(threshold);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(value>=0 && value<=100){  //是否有效相似度限值
						if(radBntText.isSelected()){ //比较文本
						  String[] args = new String[2];
						  args[0] = path;
						  args[1] = threshold;
						  ShingleSim.main(args)	;  // 执行比较
						  JOptionPane.showMessageDialog(PlagGUI.this, "执行完毕，请查看结果");	
						}else if(radBntProgram.isSelected()){ //比较代码程序
							String methodtype = (String)combMethod.getSelectedItem();
							String lang = (String)combLang.getSelectedItem();
							//检查网络						
							if("moss".equals(methodtype)){
								Http http = new Http();
								String url = "http://moss.stanford.edu/";
								String result = http.Get(url);
								if("".equals(result)){
									JOptionPane.showMessageDialog(PlagGUI.this, "目标主机不可访问，请检查网络连通性");
								    return;
								}
								
							}
							WinCMD cmd = new WinCMD();
							int res = cmd.exec(methodtype, lang, value, f.getAbsolutePath());
							if(res==0){
								JOptionPane.showMessageDialog(PlagGUI.this, "执行完毕，请查看结果");
							}else if(res<0){
								JOptionPane.showMessageDialog(PlagGUI.this, "执行失败，请重试");
							}else if(res>0){
								JOptionPane.showMessageDialog(PlagGUI.this, "执行完毕，未发现符合限值要求的结果");
							}
						}			
						
						
					}else{
						JOptionPane.showMessageDialog(PlagGUI.this, "相似度限值输入错误，请重新输入");	
						txtThreshold.requestFocus(); //焦点定位到相似值输入框
					}		
					
					
				}else{
					JOptionPane.showMessageDialog(PlagGUI.this, "作业路径错误，请重新选择");
					txtAssignPath.requestFocus(); 
				}
			}
		});
		button.setBounds(385, 117, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u67E5\u770B\u7ED3\u679C");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//查看结果
				CompareResultFrame crf = new CompareResultFrame();
				crf.setVisible(true);
			}
		});
		button_1.setBounds(385, 190, 93, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("\u5173\u4E8E");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//关于
				String info = "作业查重系统\r\n 联系方式： fanghong_jmi@sina.com";
				JOptionPane.showMessageDialog(PlagGUI.this, info, "系统信息", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		button_2.setBounds(385, 313, 93, 23);
		contentPane.add(button_2);
		
		JButton btnNewButton_1 = new JButton("\u63D0\u53D6\u6587\u4EF6");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//转换文件，将提交的作业文件转成适合比较的形式
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
