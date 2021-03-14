import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {

    private JPanel jp;
    private JPasswordField password;
    private JTextField userName;
    private JButton login;
    private JCheckBox checkboxUser, checkboxEmployee, checkboxAdmin;

    public Gui(){

        jp = new JPanel();
        jp.setBounds(100, 100, 100, 100);
        jp.setVisible(true);
        jp.setLayout(null);
        this.setTitle("Bibliotek");
        this.setSize(800, 800);

        userName = new JTextField("Ditt användarnamn: ");
        userName.setBounds(50,50,200,50);

        password = new JPasswordField("Ditt lösenord: ");
        password.setBounds(50, 100, 200, 50);
        password.setEchoChar('*');

        //depends on what you click on you come to a new window for right
        login = new JButton("Logga in ");
        login.setBounds(50,150,100,30);
        login.setBackground(Color.red);
        login.addActionListener(e->{
            if(checkboxUser.isSelected()){
                Login.con(userName.getText(),password.getText());
                if(Login.getLoginCheck()==1) {
                    userGui userlog = new userGui();
                }
            }
            if(checkboxEmployee.isSelected()){
                Login.con(userName.getText(),password.getText());
                if(Login.getLoginCheck()==1) {
                    employeeGui employeelog = new employeeGui();
                }
            }
            if(checkboxAdmin.isSelected()){
                Login.con(userName.getText(),password.getText());
                if(Login.getLoginCheck()==1) {
                    adminGui adminlog = new adminGui();
                }
            }
        });

        //the checkbox the user must click to login
        checkboxUser = new JCheckBox("Användare");
        checkboxUser.setBounds(50,200,100,30);
        checkboxUser.addActionListener(e -> {
            checkboxEmployee.setSelected(false);
            checkboxAdmin.setSelected(false);
        });

        checkboxEmployee = new JCheckBox("Anställd");
        checkboxEmployee.setBounds(150,200,100,30);
        checkboxEmployee.addActionListener(e -> {
            checkboxAdmin.setSelected(false);
            checkboxUser.setSelected(false);
        });

        checkboxAdmin = new JCheckBox("Admin");
        checkboxAdmin.setBounds(250,200,100,30 );
        checkboxAdmin.addActionListener(e -> {
            checkboxUser.setSelected(false);
            checkboxEmployee.setSelected(false);
        });

        jp.add(checkboxUser);
        jp.add(checkboxEmployee);
        jp.add(checkboxAdmin);
        jp.add(userName);
        jp.add(password);
        jp.add(login);

        this.add(jp);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

