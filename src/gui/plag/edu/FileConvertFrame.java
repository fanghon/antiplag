package gui.plag.edu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import utils.edu.AntFile;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FileConvertFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtSrc;
	private JTextField txtDest;
	private JComboBox combType;

	private PlagGUI pgui ;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileConvertFrame frame = new FileConvertFrame();
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
	public FileConvertFrame() {
		setTitle("\u63D0\u53D6\u6587\u4EF6");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 300, 450, 300);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtSrc = new JTextField();
		txtSrc.setBounds(21, 54, 240, 21);
		contentPane.add(txtSrc);
		txtSrc.setColumns(10);
		
		JButton btnNewButton = new JButton("\u9009\u62E9\u6E90\u76EE\u5F55");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//选择源目录
				 JFileChooser dlg = new JFileChooser(".");  //起始路径是程序当前路径
				 dlg.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
				 dlg.setDialogTitle("选择源路径 "); 
				 int result = dlg.showOpenDialog(null); 
				 if (result == JFileChooser.APPROVE_OPTION) { 
				   String path = dlg.getSelectedFile().getAbsolutePath(); 
				   txtSrc.setText(path);
				 }
			}
		});
		btnNewButton.setBounds(306, 53, 114, 23);
		contentPane.add(btnNewButton);
		
		txtDest = new JTextField();
		txtDest.setBounds(20, 131, 241, 21);
		contentPane.add(txtDest);
		txtDest.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("\u9009\u62E9\u76EE\u6807\u76EE\u5F55");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//选择目标目录
				 JFileChooser dlg = new JFileChooser(".");  //起始路径是程序当前路径
				 dlg.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
				 dlg.setDialogTitle("选择目标路径 "); 
				 int result = dlg.showOpenDialog(null); 
				 if (result == JFileChooser.APPROVE_OPTION) { 
				   String path = dlg.getSelectedFile().getAbsolutePath(); 
				   txtDest.setText(path);
				 }				
			}
		});
		btnNewButton_1.setBounds(306, 130, 114, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u63D0 \u53D6");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			  //转换文件
				String srcstr = txtSrc.getText();
				String deststr = txtDest.getText();
				File srcf = new File(srcstr);
				File destf = new File(deststr);
				if(srcf.exists() && destf.exists() && destf.isDirectory()){
				    String type =  (String)combType.getSelectedItem();
					AntFile.unzip(srcf,false); //先将srcf下的zip文件解压，源zip保留 
					
					String[] filter = new String[1];
					filter[0]="**/*."+type; //扫描所有子目录指定扩展名
					if("doc".equals(type)){  //文档类型，支持doc txt docx
						filter = new String[3];
						filter[0] = "**/*.doc";
						filter[1] = "**/*.txt";
						filter[2] = "**/*.docx";
					}
					
					String[] filestrs = AntFile.scanFiles(srcf, filter); //返回含子目录的相对文件名
					if(filestrs!=null){
						 int cnt1 = 0;
						 int cnt2 = 0;
						 for(String str:filestrs){
							String filename = null;
							if(str.indexOf("\\")>=0)
							  filename = str.substring(str.lastIndexOf("\\")); 
							if(filename.indexOf("_")>0 || filename.indexOf("-")>0){ //文件名有姓名的情况
								cnt1++;
								if(cnt1>1){  //文件名前有姓名标注的情况，将原文件直接复制到目标目录
								  for(int i=0;i<filestrs.length;i++){ 	
									AntFile.copy(new File(srcf.getAbsoluteFile()+"\\"+filestrs[i]), destf);
								  }
								  break; //复制完后，直接退出外层循环
								}									
							}else{ //文件名中无_- ,说明文件名未加姓名拼音，针对java作业类型2、3中文件名无姓名前缀的情况
								cnt2++;
								if(cnt2>1){
									File[] dics = srcf.listFiles(); // 提取子目录名，以子目录名作为前缀
									for(int i=0;i<dics.length;i++){
									 if(dics[i].isDirectory()){	
										String dicname = dics[i].getName()+"_";
										String[] fstrs = AntFile.scanFiles(dics[i], filter);
										if(fstrs!=null){
										 for(String tempstr:fstrs){  //先逐个复制到目标目录，再改名
											String fn = tempstr;
								 		    if(tempstr.indexOf("\\")>=0)
												  fn = tempstr.substring(tempstr.lastIndexOf("\\")+1); 
											AntFile.copy(new File(dics[i].getAbsoluteFile()+"\\"+ tempstr), destf);
											AntFile.rename(new File(destf.getAbsoluteFile()+"\\"+fn), 
													        new File(destf.getAbsoluteFile()+"\\"+dicname+fn));
										 }
										}
									 }
									}
									
								  break;	//退出最外层循环
								}//		
							}							
						 }//for
						 JOptionPane.showMessageDialog(FileConvertFrame.this, "文件转换结束");
					     pgui.getTxtAssignPath().setText(txtDest.getText());  //将目标路径设置到主窗口的作业路径中
					}else{
						JOptionPane.showMessageDialog(FileConvertFrame.this, "指定类型的文件不存在");
					}
					
					
				}else{
					JOptionPane.showMessageDialog(FileConvertFrame.this, "源路径、目标路径选择错误，请重新选择");
				}
					
					
				
			}
		});
		btnNewButton_2.setBounds(306, 199, 114, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("\u63D0\u53D6\u7684\u6587\u4EF6\u7C7B\u578B");
		lblNewLabel.setBounds(21, 203, 101, 15);
		contentPane.add(lblNewLabel);
		
		combType = new JComboBox();
		combType.setModel(new DefaultComboBoxModel(new String[] {"java", "c", "cpp", "doc"}));
		combType.setBounds(170, 200, 91, 21);
		contentPane.add(combType);
	}

	public void setPgui(PlagGUI pgui) {
		this.pgui = pgui;
	}
}
