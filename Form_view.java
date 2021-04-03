package dataCollectionForm; 
import java.awt.event.*;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
public class Form_view extends JFrame {
	
	JLabel[] t;
	JTextField[] tf;
	JTextArea[] ta;
	JCheckBox[] ck;
	JRadioButton[] rd;
	JPasswordField[] ps;
	JComboBox[] cb;
	ButtonGroup[] bg;
	int[] y = {0};
	int tfc=0,tac=0,psc=0,tfi=0,tai=0,psi=0,k=0,d=0,b=0,cki=0,rdi=0,cbi=0;
	public  Form_view(String title,String[] s,String[] cks,String[] rds,String[] cbs,int index,int ckc,int rdc,int rdcc,int cbc,int[] label,int[] ckt,int[] rdt,int[] cbt) {
		
		//System.out.println("index:"+index+" ckc:"+ckc+" rdc:"+rdc+" cbc:"+cbc);
		//System.out.println("title:"+title);
		JPanel p1,p2;
		JLabel Title;
		JButton submit,Responses;		
		for(int i=0;i<index;i++) {
			if(label[i]==1)
				tfc++;
			if(label[i]==2)
				tac++;
			if(label[i]==5)
				psc++;
		}
		t = new JLabel[index];
		tf = new JTextField[tfc];
		String[] tfs = new String[tfc];
		ta = new JTextArea[tac];
		ck = new JCheckBox[ckc];
		rd = new JRadioButton[rdc];
		ps = new JPasswordField[psc];
		cb = new JComboBox[cbc];
		bg = new ButtonGroup[rdcc];
		Responses = new JButton("Check Responses");
		p1 = new JPanel();
		p2 = new JPanel();
		Font font1 = new Font(Font.SANS_SERIF, Font.BOLD, 25);
		Font font3 = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
		Font font2 = new Font(Font.SERIF, Font.PLAIN, 15);
		this.setSize(500,600);
		this.setTitle(title);
		this.setLayout(new BorderLayout());
		p1.setLayout(new GridBagLayout());
		JScrollPane p1s = new JScrollPane(p1, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		p2.setLayout(new FlowLayout(FlowLayout.CENTER));
		GridBagConstraints c = new GridBagConstraints();
		this.add(p1s,BorderLayout.CENTER);
		this.add(p2,BorderLayout.SOUTH);
		Title = new JLabel(title);
		Title.setFont(font1);
		Title.setHorizontalAlignment(JLabel.CENTER);
		this.add(Title,BorderLayout.NORTH);
		submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tfj=0,taj=0,ckj=0,ckcj=0,rdj=0,rdcj=0,psj=0,cbj=0;
				String submitData = "";
				for(int i=0;i<index;i++) {
					if(label[i]==1) {
						
						if(submitData.equals("")) { submitData = tf[tfj].getText() + "@"; }
						else { submitData =submitData + tf[tfj].getText() + "@"; }
						tfj++;
					}
					if(label[i]==2) {
						
						if(submitData.equals("")) { submitData = ta[taj].getText() + "@"; }
						else { submitData =submitData + ta[taj].getText() + "@"; }
						taj++;
					}
					if(label[i]==3) {
						int c=0,cc=0,temp = ckj;
						for(int j=0;j<ckt[ckcj];j++) {
							if(ck[ckj].isSelected()) {c++;}
							ckj++;
						}ckj=temp;
						//System.out.println("c:"+c);
						for(int j=0;j<ckt[ckcj];j++) {
							if(ck[ckj].isSelected()) {
								cc++;
								if(cc!=c) {
									if(submitData.equals("")) { submitData = ck[ckj].getText() + ",";}
									else { submitData =submitData + ck[ckj].getText() + ","; }
								}
								else {
									if(submitData.equals("")) { submitData = ck[ckj].getText() + "@";}
									else { submitData =submitData + ck[ckj].getText() + "@"; }
								}
							} 
							ckj++;
						}
						ckcj++;
						
					}
					if(label[i]==4) {
						for(int j=0;j<rdt[rdcj];j++) {
							if(rd[rdj].isSelected()) {
								System.out.println("working");
								if(submitData.equals("")) { submitData = rds[rdj] + "@";}
								else { submitData =submitData + rds[rdj] + "@"; }
							} 
							rdj++;	
						}	
						rdcj++;
					}
					if(label[i]==5) {
						
						if(submitData.equals("")) { submitData = ps[psj].getText() + "@"; }
						else { submitData =submitData + ps[psj].getText() + "@"; }
						psj++;
					}
					if(label[i]==6) {
						if(submitData.equals("")) { submitData = (String) cb[cbj].getSelectedItem() + "@"; }
						else { submitData =submitData + (String) cb[cbj].getSelectedItem() + "@"; }
						cbj++;
					}
				}
				//String ststs = "ssm@ert@";
				create_table c=new create_table();
				System.out.println("submitData:"+submitData);
				c.uploadData(title, submitData, "insertTableData");
				fetch f=new fetch();
				f.fetchData(title);
				submitData = "";
				for(int i=0;i<tfc;i++) {
					tf[i].setText("");
				}
				for(int i=0;i<tac;i++) {
					ta[i].setText("");
				}
				for(int i=0;i<psc;i++) {
					ps[i].setText("");
				}
				for(int i=0;i<ckc;i++)
				{
					if(ck[i].isSelected()) {ck[i].setSelected(false);}
				}
				for(int i=0;i<rdc;i++)
				{
					if(rd[i].isSelected()) {rd[i].setSelected(false);}
				}
			}
			
		});
		Responses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                try {
                	create_table c=new create_table();
                	String outputUrl="http://b383c2a5.ngrok.io/phpmyadmin/sql.php?db=java&table="+title+"&lang=en&pos=0";
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(outputUrl));
				} catch (Exception e) {
					System.out.println(e);
				}
			}
			
		});
		p2.add(submit);
		p2.add(Responses);
		c.insets = new Insets(10,10,10,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		for(int i=0;i<index;i++) {
			if(label[i] == 1)
			{
				t[i] = new JLabel(s[i]);
				c.gridy = y[0];
				c.ipady = 0;
				c.gridx = 0;
				c.gridwidth = 2;
				p1.add(t[i],c);
				y[0]++;
				tf[tfi] = new JTextField(20);
				c.gridy = y[0];
				c.ipady = 0;
				c.gridx = 0;
				c.gridwidth = 2;
				p1.add(tf[tfi],c);
				y[0]++;
				tfi++;
			}
			if(label[i] == 2)
			{
				t[i] = new JLabel(s[i]);
				c.gridy = y[0];
				c.ipady = 0;
				c.gridx = 0;
				c.gridwidth = 2;
				p1.add(t[i],c);
				y[0]+=4;
				ta[tai] = new JTextArea(4,20);
				c.gridy = y[0];
				c.ipady = 0;
				c.gridx = 0;
				c.gridwidth = 2;
				p1.add(ta[tai],c);
				y[0]++;
				tai++;
			}
			if(label[i] == 3)
			{
				t[i] = new JLabel(s[i]);
				c.gridy = y[0];
				c.ipady = 0;
				c.gridx = 0;
				c.gridwidth = 2;
				p1.add(t[i],c);
				y[0]++;
				for(int j=0;j<ckt[cki];j++) {
					ck[k] = new JCheckBox(cks[k]);
					c.gridy = y[0];
					c.ipady = 0;
					c.gridx = 0;
					c.gridwidth = 2;
					p1.add(ck[k],c);
					y[0]++;
					k++;
				}
				cki++;
			}
			if(label[i] == 4)
			{
				t[i] = new JLabel(s[i]);
				c.gridy = y[0];
				c.ipady = 0;
				c.gridx = 0;
				c.gridwidth = 2;
				p1.add(t[i],c);
				y[0]++;
				for(int j=0;j<rdc;j++) { }
				bg[rdi] = new ButtonGroup();
				for(int j=0;j<rdt[rdi];j++) {
					rd[d] = new JRadioButton(rds[d]);
					c.gridy = y[0];
					c.ipady = 0;
					c.gridx = 0;
					c.gridwidth = 2;
					bg[rdi].add(rd[j]);
					p1.add(rd[d],c);
					y[0]++;
					d++;
				}
				rdi++;
			}
			if(label[i] == 5)
			{
				t[i] = new JLabel(s[i]);
				c.gridy = y[0];
				c.ipady = 0;
				c.gridx = 0;
				c.gridwidth = 2;
				p1.add(t[i],c);
				y[0]++;
				ps[psi] = new JPasswordField("Enter Password",20);
				c.gridy = y[0];
				c.ipady = 0;
				c.gridx = 0;
				c.gridwidth = 2;
				p1.add(ps[psi],c);
				y[0]++;
				psi++;
			}
			if(label[i] == 6)
			{
				t[i] = new JLabel(s[i]);
				c.gridy = y[0];
				c.ipady = 0;
				c.gridx = 0;
				c.gridwidth = 2;
				p1.add(t[i],c);
				y[0]++;
				cb[cbi] = new JComboBox(); 
				for(int j=0;j<cbt[cbi];j++) {
					cb[cbi].addItem(cbs[b]);
					b++;
				}
				c.gridy = y[0];
				c.ipady = 0;
				c.gridx = 0;
				c.gridwidth = 2;
				p1.add(cb[cbi],c);
				y[0]++;
				cbi++;
			}
		}
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		Form obj = new Form();
		String title = obj.title;
		String[] s = obj.s;
		String[] cks = obj.cks;
		String[] rds = obj.rds;
		String[] cbs = obj.cbs;
		int index = obj.index;
		int ckc = obj.cki;
		int ckcc = obj.ckc+1;
		int rdc = obj.rdi;
		int rdcc = obj.rdc + 1;
		int cbc = obj.cbc + 1;
		int[] label = obj.label;
		int[] ckt = obj.ckt;
		int[] rdt = obj.rdt;
		int[] cbt = obj.cbt;
		
		Form_view fv = new Form_view(title,s,cks,rds,cbs,index,ckc,rdc,rdcc,cbc,label,ckt,rdt,cbt);
	}
	public static String refineText(String data) {//Function to refine string to get valid characters
		if(data==null) {
			return "NoValue";//In case if user enters no value
		}
		int length=data.length();
		String refinedValue="";
		String invalid="@ _";
		for(int i=0;i<length;i++) {
			if(data.charAt(i)==invalid.charAt(0)||data.charAt(i)==invalid.charAt(1)||data.charAt(i)==invalid.charAt(2)) {
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


