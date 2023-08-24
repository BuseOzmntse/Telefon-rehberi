import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField txt_name;
	private JTextField txt_password;
	static String name;
	static String password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txt_name = new JTextField();
		txt_name.setBounds(146, 152, 101, 20);
		contentPane.add(txt_name);
		txt_name.setColumns(10);
		
		txt_password = new JTextField();
		txt_password.setBounds(146, 183, 101, 20);
		contentPane.add(txt_password);
		txt_password.setColumns(10);
		
		JLabel lblName = new JLabel("Kullanıcı Adı:");
		lblName.setBounds(70, 155, 66, 14);
		contentPane.add(lblName);
		
		JLabel lblPassword = new JLabel("Şifre:");
		lblPassword.setBounds(70, 186, 46, 14);
		contentPane.add(lblPassword);
		
		JButton btnLogin = new JButton("Giriş");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = txt_name.getText();
				password = txt_password.getText();
				String sql_query = "select count(u_id) as login from kullanici where u_name= '" + name + "' and u_pass= '" + password + "'";
				
				ResultSet rs = db.connect();
				rs= db.ask(sql_query);
				
				try {
					while(rs.next()) {
						if(rs.getInt("login")==1) {
							panel pnl = new panel();
							pnl.setVisible(true);
							setVisible(false);
						}else {
							btnLogin.setText("Hatalı Giriş!");
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(112, 265, 89, 23);
		contentPane.add(btnLogin);
		
		
	}
}
