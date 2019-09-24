import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.awt.event.ActionEvent;

public class Cekilis extends JFrame {
    private String dosya_yolu="";
    private ArrayList<String> katilanlar=new ArrayList<String>();
    private Set<String> kazananlarListesi=new TreeSet<String>();
    
    private DefaultListModel<String> model=new DefaultListModel();
    
	private JPanel contentPane;
	private JTextField aramaCubugu;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cekilis frame = new Cekilis();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void cekilisYap()
	{
		try(BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(dosya_yolu),"UTF-8")))
		{
			String kisi;
			
			while((kisi=reader.readLine())!=null)
			{
				katilanlar.add(kisi);
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		while(kazananlarListesi.size()!=10)
		{
			Random random=new Random();
			int kazanan_indeks=random.nextInt(katilanlar.size());
			kazananlarListesi.add(katilanlar.get(kazanan_indeks));
			
			
			
		}
		
	}
	
	

	
	public Cekilis() {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 371);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(178, 34, 34));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList kazananlar = new JList();
		kazananlar.setBounds(10, 103, 254, 175);
		contentPane.add(kazananlar);
		
		aramaCubugu = new JTextField();
		aramaCubugu.setEditable(false);
		aramaCubugu.setBounds(10, 26, 254, 20);
		contentPane.add(aramaCubugu);
		aramaCubugu.setColumns(10);
		
		kazananlar.setModel(model);
		
		JButton gozat = new JButton("G\u00F6z At");
		gozat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				JFileChooser fc=new JFileChooser();
				int i=fc.showOpenDialog(null);
				
				if(i==JFileChooser.APPROVE_OPTION)
				{
					dosya_yolu=fc.getSelectedFile().getPath();
					aramaCubugu.setText(dosya_yolu);
					
					
				}
				
			}
		});
		gozat.setBounds(284, 25, 140, 23);
		contentPane.add(gozat);
		
		
		
		JLabel kznnlar = new JLabel("Kazananlar:");
		kznnlar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		kznnlar.setBounds(10, 66, 172, 14);
		contentPane.add(kznnlar);
		
		
		
		
		JButton cekilis = new JButton("\u00C7ekili\u015F Yap");
		cekilis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(dosya_yolu.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Dosya seçilmedi..");
				}
				else
				{
					cekilisYap();
					for(String kazanan:kazananlarListesi)
					{
						model.addElement(kazanan);
					}
				}
			}
		});
		cekilis.setBounds(284, 100, 140, 23);
		contentPane.add(cekilis);
	}

	
	
	
}
