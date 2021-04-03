package dataCollectionForm; 
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
public class Form extends JFrame{
	CardLayout cl;
	JTextField t2,ft2,cb;
	JTextField[] t,ck,rd;
	JTextArea ta1,fta1;
	JButton add,edit,check,done;
	JCheckBox ck1,fck1;
	JRadioButton rd1,frd1;
	JPasswordField ps1,fps1;
	JComboBox cb2,fcb2;
	JLabel cbl;
	ButtonGroup bg;
	JLabel[] ft,fck,frd;
	String fstr = "";
	public String[] s = new String[100];
	public String[] cks = new String[200];
	public String[] rds = new String[200];
	public String[] cbs = new String[500];
	public String title;
	int component = 0;
	int temp=0,tp=0;
	int[] y= {1,0};
	public int index=0,cki=0,rdi=0,cbi=0,ckc=-1,rdc=-1,cbc=-1;
	public int[] label = new int[100];
	public int[] ckt = new int[100];
	public int[] rdt = new int[100];
	public int[] cbt = new int[100];
	public void addInP2() {
		
	}
	Form() {
		String[] str = {"Default","Text Box","Text Area","Checkbox","Multiple Choice","Password","Dropdown Box"};
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel card = new JPanel();
		cl = new CardLayout();
		JComboBox cb1;
		JLabel l1;
		JTextField t1;
		Font font1 = new Font(Font.SANS_SERIF, Font.BOLD, 25);
		Font font3 = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
		Font font2 = new Font(Font.SERIF, Font.PLAIN, 15);
		this.setLayout(new BorderLayout());
		p1.setLayout(new GridBagLayout());
		p2.setLayout(new GridBagLayout());
		p3.setLayout(new FlowLayout(FlowLayout.CENTER));
		card.setLayout(cl);
		GridBagConstraints c = new GridBagConstraints();
		JScrollPane p1s = new JScrollPane(p1, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		JScrollPane p2s = new JScrollPane(p2, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.add(card,BorderLayout.CENTER);
		card.add(p1s,"1");
		card.add(p2s,"2");
		this.setSize(500,600);
		this.setTitle("Form");
		Border blackline = BorderFactory.createLineBorder(Color.black);
		p1.setBorder(blackline);
		t1 = new JTextField("Untitled Form");
		t1.setFont(font1);
		t1.setHorizontalAlignment(JTextField.CENTER);
		this.add(t1,BorderLayout.NORTH);
		l1 = new JLabel("To Select New Question:");
		l1.setFont(font2);
		cb1 = new JComboBox(str);
		cb1.setFont(font2);
		ft = new JLabel[100];
		t = new JTextField[100];
		fck = new JLabel[100];
		ck = new JTextField[100];
		frd = new JLabel[100];
		rd = new JTextField[100];
		done = new JButton("Done");
		check = new JButton("Check");
		edit = new JButton("Edit");
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(card,"2");
				title = t1.getText();
				title = refineText(title);
				for(int i=0;i<index;i++) {
					s[i] = t[i].getText();
					ft[i].setText(s[i]);
				}
				for(int j=0;j<index;j++) {
					if(label[j] == 3) {
						for(int i=0;i<cki;i++) {
							cks[i] = ck[i].getText();
							fck[i].setText(cks[i]);
						}
					}
					if(label[j] == 4) {
						for(int i=0;i<rdi;i++) {
							rds[i] = rd[i].getText();
							frd[i].setText(rds[i]);
						}
					}
				}
			}
		});
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(card,"1");
			}
		});
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				title = t1.getText();
				title = refineText(title);
				for(int i=0;i<index;i++) {
					s[i] = t[i].getText();
					ft[i].setText(s[i]);
				}
				for(int j=0;j<index;j++) {
					if(label[j] == 3) {
						for(int i=0;i<cki;i++) {
							cks[i] = ck[i].getText();
							fck[i].setText(cks[i]);
						}
					}
					if(label[j] == 4) {
						for(int i=0;i<rdi;i++) {
							rds[i] = rd[i].getText();
							frd[i].setText(rds[i]);
						}
					}
				}
				new Form_view(title,s,cks,rds,cbs,index,cki,rdi,rdc + 1,cbc + 1,label,ckt,rdt,cbt).setVisible(true);
				for(int i=0;i<index;i++) {
					 if(fstr.equals("")) { fstr = refineText(s[i]) + "_varchar(255)@"; }
					 else {
						 fstr = fstr + refineText(s[i])+ "_varchar(255)@";
					 }
						 
				}
				//String kai_pn="lion_varchar(255)@tiger_varchar(255)@";
				create_table c=new create_table();
				title = refineText(title);
				//fstr = refineText(fstr);
				//System.out.println(fstr);
				c.uploadData(title, fstr, "createTable");//TODO provide here tble name
				dispose();
			}
		});
		p3.add(edit);
		p3.add(check);
		p3.add(done);
		this.add(p3,BorderLayout.SOUTH);
		c.insets = new Insets(10,10,10,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		
		cb1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if ( e.getStateChange() == ItemEvent.SELECTED && e.getItem().equals("Text Box") ) {
					label[index] = 1;
					t[index] = new JTextField("Enter Question:");
					t[index].setFont(font3);
					t[index].setBackground(null);
					t[index].setBorder(null);
					c.gridy = y[0];
					c.ipady = 0;
					c.gridx = 0;
					c.gridwidth = 2;
					p1.add(t[index],c);
					y[0]++;
					t2 = new JTextField();
					t2.setFont(font2);
					c.gridy = y[0];
					c.ipady = 0;
					c.gridwidth = 2;
					c.gridx = 0;
					p1.add(t2,c);
					y[0]++;
					s[index] = t[index].getText();
					ft[index] = new JLabel();
					ft[index].setFont(font3);
					ft2 = new JTextField(20);
					ft2.setFont(font2);
					ft[index].setText(s[index]);
					c.gridy = y[1];
					c.ipady = 0;
					c.gridx = 0;
					c.gridwidth = 2;
					p2.add(ft[index],c);
					y[1]++;
					c.gridy = y[1];
					c.ipady = 0;
					c.gridx = 0;
					c.gridwidth = 3;
					p2.add(ft2,c);
					y[1]++;
					p2.revalidate();
					cb1.setSelectedItem("Default");
					p1.revalidate();
					index++;
					//System.out.println("working:"+y[0]+","+y[1]);
				}
				if ( e.getStateChange() == ItemEvent.SELECTED && e.getItem().equals("Text Area") ) {
					label[index]=2;
					t[index] = new JTextField("Enter Question:");
					t[index].setFont(font3);
					t[index].setBackground(null);
					t[index].setBorder(null);
					c.gridy = y[0];
					c.ipady = 0;
					c.gridx = 0;
					c.gridwidth = 2;
					p1.add(t[index],c);
					y[0]++;
					ta1 = new JTextArea(4,20);
					ta1.setFont(font2);
					c.gridy = y[0];
					c.ipady = 0;
					c.gridwidth = 2;
					c.gridx = 0;
					p1.add(ta1,c);
					y[0]+=4;
					s[index] = t[index].getText();
					ft[index] = new JLabel();
					ft[index].setFont(font3);
					ft[index].setText(s[index]);
					c.gridy = y[1];
					c.ipady = 0;
					c.gridx = 0;
					c.gridwidth = 2;
					p2.add(ft[index],c);
					y[1]++;
					fta1 = new JTextArea(4,20);
					fta1.setFont(font2);
					c.gridy = y[1];
					c.ipady = 0;
					c.gridx = 0;
					c.gridwidth = 3;
					p2.add(fta1,c);
					y[1]++;
					p2.revalidate();
					//System.out.println("working:"+y[0]+","+y[1]);
					cb1.setSelectedItem("Default");
					p1.revalidate();
					index++;
				}
				if ( e.getStateChange() == ItemEvent.SELECTED && e.getItem().equals("Checkbox") ) {
					label[index]=3;
					//if(ckc >=0){System.out.println(ckt[ckc]);}
					ckc++;
					t[index] = new JTextField("Enter Question:");
					t[index].setFont(font3);
					t[index].setBackground(null);
					t[index].setBorder(null);
					c.gridy = y[0];
					c.ipady = 0;
					c.gridx = 0;
					c.gridwidth = 1;
					p1.add(t[index],c);
					add = new JButton("Add Options");
					add.setFont(font2);;
					c.gridy = y[0];
					c.ipady = 0;
					c.gridx = 1;
					c.gridwidth  = 1;
					p1.add(add,c);
					y[0]++;
					s[index] = t[index].getText();
					ft[index] = new JLabel();
					ft[index].setFont(font3);
					ft[index].setText(s[index]);
					c.gridy = y[1];
					c.ipady = 0;
					c.gridx = 0;
					c.gridwidth = 2;
					p2.add(ft[index],c);
					y[1]++;
					ck1 = new JCheckBox();
					ck1.setFont(font2);
					c.gridy = y[0];
					c.ipady = 0;
					c.gridx = 0;
					c.gridwidth = 1;
					p1.add(ck1,c);
					ck[cki] = new JTextField("Option Name:");
					ck[cki].setFont(font2);
					ck[cki].setBackground(null);
					ck[cki].setBorder(null);
					c.gridy = y[0];
					c.ipady = 0;
					c.gridx = 1;
					c.gridwidth = 1;
					y[0]++;
					tp = y[0];
					p1.add(ck[cki],c);
					fck1 = new JCheckBox();
					c.gridy = y[1];
					c.ipady = 0;
					c.gridx = 0;
					p2.add(fck1,c);
					fck[cki] = new JLabel();
					fck[cki].setFont(font2);
					cks[cki] = ck[cki].getText();
					fck[cki].setText(cks[cki]);
					c.gridy = y[1];
					c.ipady = 0;
					c.gridx = 1;
					c.gridwidth = 1;
					p2.add(fck[cki],c);
					cki++;
					ckt[ckc]++;
					y[1]++;
					add.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent ev) {
							if(tp==y[0]) {
								ck1 = new JCheckBox();
								ck1.setFont(font2);
								c.gridy = y[0];
								c.ipady = 0;
								c.gridx = 0;
								c.gridwidth = 1;
								p1.add(ck1,c);
								ck[cki] = new JTextField("Option Name:");
								ck[cki].setFont(font2);
								ck[cki].setBackground(null);
								ck[cki].setBorder(null);
								c.gridy = y[0];
								c.ipady = 0;
								c.gridx = 1;
								c.gridwidth = 1;
								y[0]++;
								tp = y[0];
								p1.add(ck[cki],c);
								fck1 = new JCheckBox();
								c.gridy = y[1];
								c.ipady = 0;
								c.gridx = 0;
								c.gridwidth = 1;
								p2.add(fck1,c);
								fck[cki] = new JLabel();
								fck[cki].setFont(font2);
								cks[cki] = ck[cki].getText();
								fck[cki].setText(cks[cki]);
								c.gridy = y[1];
								c.ipady = 0;
								c.gridx = 1;
								c.gridwidth = 1;
								p2.add(fck[cki],c);
								cki++;
								ckt[ckc]++;
								y[1]++;
								p1.revalidate();
								p2.revalidate();;
							}
						}
						
					});
					//System.out.println("working:"+ckc);
					cb1.setSelectedItem("Default");
					p1.revalidate();
					p2.revalidate();
					index++;
				}
				if ( e.getStateChange() == ItemEvent.SELECTED && e.getItem().equals("Multiple Choice") ) {
					label[index]=4;
					rdc++;
					t[index] = new JTextField("Enter Question:");
					t[index].setFont(font3);
					t[index].setBackground(null);
					t[index].setBorder(null);
					c.gridy = y[0];
					c.ipady = 0;
					c.gridx = 0;
					c.gridwidth = 1;
					p1.add(t[index],c);
					s[index] = t[index].getText();
					ft[index] = new JLabel();
					ft[index].setFont(font3);
					ft[index].setText(s[index]);
					c.gridy = y[1];
					c.ipady = 0;
					c.gridx = 0;
					c.gridwidth = 2;
					p2.add(ft[index],c);
					y[1]++;
					add = new JButton("Add Options");
					add.setFont(font2);
					c.gridy = y[0];
					c.ipady = 0;
					c.gridx = 1;
					c.gridwidth = 1;
					p1.add(add,c);
					y[0]++;
					rd1 = new JRadioButton();
					rd1.setFont(font2);
					c.gridy = y[0];
					c.ipady = 0;
					c.gridx = 0;
					c.gridwidth = 1;
					p1.add(rd1,c);
					rd[rdi] = new JTextField("Option Name:");
					rd[rdi].setFont(font2);
					rd[rdi].setBackground(null);
					rd[rdi].setBorder(null);
					c.gridy = y[0];
					c.ipady = 0;
					c.gridx = 1;
					c.gridwidth = 1;
					p1.add(rd[rdi],c);
					y[0]++;
					bg = new ButtonGroup();
					frd1 = new JRadioButton();
					bg.add(frd1);
					c.gridy = y[1];
					c.ipady = 0;
					c.gridx = 0;
					p2.add(frd1,c);
					frd[rdi] = new JLabel();
					frd[rdi].setFont(font2);
					rds[rdi] = rd[rdi].getText();
					
					frd[rdi].setText(rds[rdi]);
					c.gridy = y[1];
					c.ipady = 0;
					c.gridx = 1;
					p2.add(frd[rdi],c);
					y[1]++;
					rdi++;
					rdt[rdc]++;
					temp = y[0];
					
					add.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							if(temp == y[0]) {
								rd1 = new JRadioButton();
								rd1.setFont(font2);
								c.gridy = y[0];
								c.ipady = 0;
								c.gridx = 0;
								c.gridwidth = 1;
								p1.add(rd1,c);
								rd[rdi] = new JTextField("Option Name:");
								rd[rdi].setFont(font2);
								rd[rdi].setBackground(null);
								rd[rdi].setBorder(null);
								c.gridy = y[0];
								c.ipady = 0;
								c.gridx = 1;
								c.gridwidth = 1;
								y[0]++;
								p1.add(rd[rdi],c);
								frd1 = new JRadioButton();
								bg.add(frd1);
								c.gridy = y[1];
								c.ipady = 0;
								c.gridx = 0;
								p2.add(frd1,c);
								frd[rdi] = new JLabel();
								frd[rdi].setFont(font2);
								rds[rdi] = rd[rdi].getText();
								frd[rdi].setText(rds[rdi]);
								c.gridy = y[1];
								c.ipady = 0;
								c.gridx = 1;
								p2.add(frd[rdi],c);
								y[1]++;
								rdi++;
								rdt[rdc]++;
								temp = y[0];
								p1.revalidate();
								p2.revalidate();
							}
						}
						
					});
					//System.out.println("working:"+y[0]);
					cb1.setSelectedItem("Default");
					p1.revalidate();
					p2.revalidate();
					index++;
				}
				if ( e.getStateChange() == ItemEvent.SELECTED && e.getItem().equals("Password") ) {
					label[index]=5;
					t[index] = new JTextField("Enter Question:");
					t[index].setFont(font3);
					t[index].setBackground(null);
					t[index].setBorder(null);
					c.gridy = y[0];
					c.ipady = 0;
					c.gridx = 0;
					c.gridwidth = 2;
					p1.add(t[index],c);
					y[0]++;
					ft[index] = new JLabel();
					ft[index].setFont(font3);
					s[index] = t[index].getText();
					ft[index].setText(s[index]);
					c.gridy = y[1];
					c.ipady = 0;
					c.gridx = 0;
					c.gridwidth = 2;
					p2.add(ft[index],c);
					y[1]++;
					ps1 = new JPasswordField("enter password",20);
					ps1.setFont(font2);
					c.gridy = y[0];
					c.ipady = 0;
					c.gridwidth = 3;
					c.gridx = 0;
					p1.add(ps1,c);
					y[0]++;
					fps1 = new JPasswordField("enter password",20);
					fps1.setFont(font2);
					c.gridy = y[1];
					c.ipady = 0;
					c.gridwidth = 2;
					c.gridx = 0;
					p2.add(fps1,c);
					y[1]++;
					//System.out.println("working:"+y[0]);
					cb1.setSelectedItem("Default");
					p1.revalidate();
					p2.revalidate();
					index++;
				}
				if ( e.getStateChange() == ItemEvent.SELECTED && e.getItem().equals("Dropdown Box") ) {
					label[index] = 6;
					cbc++;
					cb2 = new JComboBox();
					fcb2 = new JComboBox();
					t[index] = new JTextField("Enter Question:");
					t[index].setFont(font3);
					t[index].setBackground(null);
					t[index].setBorder(null);
					c.gridy = y[0];
					c.ipady = 0;
					c.gridx = 0;
					c.gridwidth = 1;
					p1.add(t[index],c);
					y[0]++;
					ft[index] = new JLabel();
					ft[index].setFont(font3);
					s[index] = t[index].getText();
					ft[index].setText(s[index]);
					c.gridy = y[1];
					c.ipady = 0;
					c.gridx = 0;
					c.gridwidth = 2;
					y[1]++;
					p2.add(ft[index],c);
					cbl = new JLabel("enter below name to add it in dropdown list:");
					cbl.setFont(font2);
					c.gridy = y[0];
					c.ipady = 0;
					c.gridx = 0;
					c.gridwidth = 2;
					p1.add(cbl,c);
					y[0]++;
					cb = new JTextField("");
					cb.setFont(font2);
					cb.setBackground(null);
					c.gridy = y[0];
					c.ipady = 0;
					c.gridx = 0;
					c.gridwidth = 1;
					p1.add(cb,c);
					add = new JButton("Add Element");
					add.setFont(font2);
					c.gridy = y[0];
					c.ipady = 0;
					c.gridx = 1;
					c.gridwidth = 1;
					p1.add(add,c);
					y[0]++;
					add.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							if(!cb.getText().equals("")) {
								cb2.addItem(cb.getText());
								cb2.setFont(font2);
								c.gridy = y[0];
								c.ipady = 0;
								c.gridx = 0;
								c.gridwidth = 1;
								p1.add(cb2,c);
								y[0]++;
								cbs[cbi] = cb.getText();
								fcb2.addItem(cb.getText());
								fcb2.setFont(font2);
								c.gridy = y[1];
								c.ipady = 0;
								c.gridx = 0;
								c.gridwidth = 2;
								p2.add(fcb2,c);
								y[1]++;
								cb.setText("");
								p1.revalidate();
								p2.revalidate();
								cbi++;
								cbt[cbc]++;
							}
						}
						
					});
					cb1.setSelectedItem("Default");
					p1.revalidate();
					index++;
				}
			}
		});
		c.ipady = 0;
		c.gridy = 0;
		c.gridx = 0;
		p1.add(l1,c);
		c.ipady = 0;
		c.gridy = 0;
		c.gridx = 1;
		p1.add(cb1,c);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		    Form F = new Form();
	}
	public static String refineText(String data) {//Function to refine string to get valid characters
		if(data==null) {
			return "NoValue";//In case if user enters no value
		}
		int length=data.length();
		String refinedValue="";
		String invalid="@ _:;,.?";
		int invalidLength=invalid.length();
		boolean a=false;
		for(int i=0;i<length;i++) {
			a=false;
			for(int j=0;j<invalidLength;j++) {
				if(data.charAt(i)==invalid.charAt(j)) {
					a=true;
				}
			}
			if(a==true) {
				continue;
			}
			refinedValue+=data.charAt(i);
		}
		if(refinedValue=="") {
			return "Empty";//in case user enters all invalid characters
		}
		return refinedValue;
	}

}