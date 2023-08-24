import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class panel extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	//Veritabanından geleceği için satır sayısı belli değil. Bu yüzden boş tasarladım.
	
	//Object projedeki nesne olan her şeyi kapsıyor.
	Object[] columns = {"Id" , "İsim" , "Soyisim", "Unvan", "Yer", "Numara"};
	Object[] rows = new Object[6];
	DefaultTableModel model = new DefaultTableModel();
	private JTextField txt_isim;
	private JTextField txt_soyisim;
	private JTextField txt_unvan;
	private JTextField txt_yer;
	private JTextField txt_numara;
	private JTextField txt_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					panel frame = new panel();
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
	public panel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		 
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 45, 263, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		//Tabloya column ve rowlarımı tanımladım.
		model.setColumnIdentifiers(columns);
		table.setBounds(113, 327, 156, 68);
		scrollPane.setViewportView(table);
		
		//Veritabanına erişimi buton ile sağladım.
		JButton btnList = new JButton("Listele");
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						
				/*Ekleme, silme, güncelleme işlemlerinden sonra tablo temizlenmeli ki tekrar liste 
				butonuna bastığımızda tekrar tekrar baştan yazdırmasın.*/
				model.setRowCount(0);
						
				ResultSet rs = db.connect();
				//herhangi bir veri tabanı bağlantısı sağlanamazsa hata vererek uyarmalı. 
				try {
					while(rs.next()) {
						rows[0] = rs.getString("id");
						rows[1] = rs.getString("isim");
						rows[2] = rs.getString("soyisim");
						rows[3] = rs.getString("unvan");
						rows[4] = rs.getString("yer");
						rows[5] = rs.getString("numara");
						model.addRow(rows);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				table.setModel(model);
			}
		});
		btnList.setBounds(216, 11, 89, 23);
		contentPane.add(btnList);
				
		txt_id = new JTextField();
		txt_id.setBounds(270, 294, 19, 20);
		contentPane.add(txt_id);
		txt_id.setColumns(10);
				
		txt_isim = new JTextField();
		txt_isim.setBounds(82, 294, 86, 20);
		contentPane.add(txt_isim);
		txt_isim.setColumns(10);
				
		txt_soyisim = new JTextField();
		txt_soyisim.setBounds(82, 325, 86, 20);
		contentPane.add(txt_soyisim);
		txt_soyisim.setColumns(10);
				
		txt_unvan = new JTextField();
		txt_unvan.setBounds(82, 356, 86, 20);
		contentPane.add(txt_unvan);
		txt_unvan.setColumns(10);
				
		txt_yer = new JTextField();
		txt_yer.setBounds(82, 387, 86, 20);
		contentPane.add(txt_yer);
		txt_yer.setColumns(10);
				
		txt_numara = new JTextField();
		txt_numara.setBounds(82, 418, 86, 20);
		contentPane.add(txt_numara);
		txt_numara.setColumns(10);
				
		JButton btnSave = new JButton("Kaydet");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Hepsini tek tek tanımladım ki sql sorgusu yazarken getText() lere boğulmasın.
				String name, surname, title, location, number, sql_query;
				name = txt_isim.getText();
				surname = txt_soyisim.getText();
				title = txt_unvan.getText();
				location = txt_yer.getText();
				number = txt_numara.getText();
						
				sql_query = "INSERT INTO rehber (isim, soyisim, unvan, yer, numara) VALUES (" + 
				"'" + name + "', " + "'" + surname + "', " + "'" + title + "', " + "'" + 
				location + "', " + "'" + number + "')" ;
						
				db.add(sql_query);
			}
		});
		btnSave.setBounds(216, 355, 89, 23);
		contentPane.add(btnSave);
				
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(235, 297, 25, 14);
		contentPane.add(lblNewLabel);
				
		JLabel lblIsim = new JLabel("İsim");
		lblIsim.setBounds(26, 294, 46, 14);
		contentPane.add(lblIsim);
				
		JLabel lblSoyisim = new JLabel("Soyisim");
		lblSoyisim.setBounds(26, 328, 46, 14);
		contentPane.add(lblSoyisim);
				
		JLabel lblUnvan = new JLabel("Unvan");
		lblUnvan.setBounds(26, 359, 46, 14);
		contentPane.add(lblUnvan);
				
		JLabel lblYer = new JLabel("Yer");
		lblYer.setBounds(26, 390, 46, 14);
		contentPane.add(lblYer);
				
		JLabel lblNumara = new JLabel("Numara");
		lblNumara.setBounds(26, 421, 46, 14);
		contentPane.add(lblNumara);
				
		JButton btnUpdate = new JButton("Güncelle");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Kullanıcının hangi alanda update yapacağını bilemeyiz. Bu yüzden tekrar tanımlama yaptım.
				String id, name, surname, title, location, number, sql_query;
				id = txt_id.getText();
				name = txt_isim.getText();
				surname = txt_soyisim.getText();
				title = txt_unvan.getText();
				location = txt_yer.getText();
				number = txt_numara.getText();
						
				sql_query = "UPDATE rehber SET isim= '" + name + "', " + "soyisim= '" + surname + "', " 
							+ "unvan= '" + title + "', " + "yer= '" + location + "', " + "numara= '" + number + "' 	WHERE id=" + id;
									
				db.update(sql_query);
			}
		});
		btnUpdate.setBounds(216, 384, 89, 23);
		contentPane.add(btnUpdate);
				
		JButton btnDelete = new JButton("Sil");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						
				String id, sql_query;
				id=txt_id.getText();
						
				sql_query = "DELETE FROM rehber WHERE id= " + id ;
						
				db.del(sql_query);
			}
		});
		btnDelete.setBounds(216, 411, 89, 23);
		contentPane.add(btnDelete);
				
				
		//Onclick ekledim ki listeden kişi seçtiğimde verileri gelsin ve onları değiştirebileyim.
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txt_id.setText((String) model.getValueAt(table.getSelectedRow(),0));
				txt_isim.setText((String) model.getValueAt(table.getSelectedRow(),1));
				txt_soyisim.setText((String) model.getValueAt(table.getSelectedRow(),2));
				txt_unvan.setText((String) model.getValueAt(table.getSelectedRow(),3));
				txt_yer.setText((String) model.getValueAt(table.getSelectedRow(),4));
				txt_numara.setText((String) model.getValueAt(table.getSelectedRow(),5));
			}
		});
		
	}
}
