import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class adminGui extends JFrame {

    private JPanel adp;
    private JTextArea output;
    private JTextField searchA, searchName, searchAdress, searchTelefonnummmer, searchMobilnummer, searchMånadslön, searchSemester;
    private JButton changeName, changeAdress, changeTelefonnummmer, changeMobilnummer, changeMånadslön, changeSemester, showA, logout;
    private  int anställningID = 0;

    public adminGui() {

        adp = new JPanel();
        adp.setBounds(100, 100, 100, 100);
        adp.setLayout(null);
        this.setTitle("Admin! ");
        this.setSize(800, 800);

        //this is the field that print everything out for you
        output = new JTextArea("Info output! ");
        output.setBounds(50, 300, 400, 450);
        output.setBackground(Color.gray);

        //this is the button you will use to look for all the employee before you search
        showA = new JButton("Alla anställda ");
        showA.setBounds(50, 50, 120, 30);
        showA.setBackground(Color.yellow);
        showA.addActionListener(e->{
            String ans = "";
            try {
                PreparedStatement p = Login.getCon().prepareStatement("SELECT * FROM Anställda ");
                ResultSet set = p.executeQuery();
                while (set.next()){
                    ans += ("\nNamn: " + set.getString(2) + " \nAdress: " + set.getString(3) + " \nTelefonnummer: " + set.getInt(4) + " \nMobilnummer: "
                            + set.getInt(5) + " \nMånadslön: " + set.getInt(6) + " \nSemesterdagar: " + set.getInt(7) + "\nAnställningsId: " + set.getInt(1));
                };
                output.setText(ans);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });

        //print in the employee
        searchA = new JTextField(" Vilken anställd? ");
        searchA.setBounds(180,50,300,30);
        searchA.setBackground(Color.white);
        searchA.addActionListener(e -> {

            try {
                PreparedStatement p = Login.getCon().prepareStatement("SELECT * FROM Anställda WHERE Namn =?");
                p.setString(1,searchA.getText());
                ResultSet set = p.executeQuery();
                while (set.next()){
                    anställningID=set.getInt(1);
                    searchName.setText(set.getString(2));
                    searchAdress.setText(set.getString(3));
                    searchTelefonnummmer.setText(set.getString(4));
                    searchMobilnummer.setText(set.getString(5));
                    searchMånadslön.setText(set.getString(6));
                    searchSemester.setText(set.getString(7));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        //button press on to change the field
        changeName = new JButton(" Ändra namn ");
        changeName.setBounds(50, 150, 120, 30);
        changeName.setBackground(Color.yellow);
        changeName.addActionListener(e -> {
            try {
                PreparedStatement p = Login.getCon().prepareStatement("UPDATE Anställda SET Namn=?  WHERE AnställdId =?");
                p.setString(1, searchName.getText());
                p.setInt(2, anställningID);
                p.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        //the field that you print in the name
        searchName = new JTextField(" ");
        searchName.setBounds(50, 100, 150, 30);
        searchName.setBackground(Color.white);

        changeAdress = new JButton(" Ändra adress ");
        changeAdress.setBounds(170, 150, 120, 30);
        changeAdress.setBackground(Color.yellow);
        changeAdress.addActionListener(e -> {
            try {
                PreparedStatement p = Login.getCon().prepareStatement("UPDATE Anställda SET Adress=?  WHERE AnställdId =?");
                p.setString(1, searchAdress.getText());
                p.setInt(2, anställningID);
                p.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        searchAdress = new JTextField(" ");
        searchAdress.setBounds(200, 100, 300, 30);
        searchAdress.setBackground(Color.white);

        changeTelefonnummmer = new JButton(" Ändra Telefonnummer ");
        changeTelefonnummmer.setBounds(290, 150, 170, 30);
        changeTelefonnummmer.setBackground(Color.yellow);
        changeTelefonnummmer.addActionListener(e -> {
            try {
                PreparedStatement p = Login.getCon().prepareStatement("UPDATE Anställda SET Telefonnummer=?  WHERE AnställdId =?");
                p.setString(1, searchTelefonnummmer.getText());
                p.setInt(2, anställningID);
                p.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        searchTelefonnummmer = new JTextField(" ");
        searchTelefonnummmer.setBounds(500, 100, 170, 30);
        searchTelefonnummmer.setBackground(Color.white);

        changeMobilnummer = new JButton(" Ändra Mobilnummer ");
        changeMobilnummer.setBounds(50, 250, 170, 30);
        changeMobilnummer.setBackground(Color.yellow);
        changeMobilnummer.addActionListener(e -> {
            try {
                PreparedStatement p = Login.getCon().prepareStatement("UPDATE Anställda SET Mobilnummer=?  WHERE AnställdId =?");
                p.setString(1, searchMobilnummer.getText());
                p.setInt(2, anställningID);
                p.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        searchMobilnummer = new JTextField(" ");
        searchMobilnummer.setBounds(50, 200, 150, 30);
        searchMobilnummer.setBackground(Color.white);

        changeMånadslön = new JButton(" Ändra Månadslön ");
        changeMånadslön.setBounds(200, 250, 170, 30);
        changeMånadslön.setBackground(Color.yellow);
        changeMånadslön.addActionListener(e -> {
            try {
                PreparedStatement p = Login.getCon().prepareStatement("UPDATE Anställda SET Månadslön = ?  WHERE AnställdId = ?");
                p.setString(1, searchMånadslön.getText());
                p.setInt(2, anställningID);
                p.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        searchMånadslön = new JTextField(" ");
        searchMånadslön.setBounds(200, 200, 150, 30);
        searchMånadslön.setBackground(Color.white);

        changeSemester = new JButton(" Ändra Semesterdagar ");
        changeSemester.setBounds(350, 250, 170, 30);
        changeSemester.setBackground(Color.yellow);
        changeSemester.addActionListener(e -> {
            try {
                PreparedStatement p = Login.getCon().prepareStatement("UPDATE Anställda SET Semesterdagar=?  WHERE AnställdId =?");
                p.setString(1, searchSemester.getText());
                p.setInt(2, anställningID);
                p.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        searchSemester = new JTextField(" ");
        searchSemester.setBounds(350, 200, 150, 30);
        searchSemester.setBackground(Color.white);

        logout = new JButton("Logga ut");
        logout.setBounds(500, 50, 100, 30);
        logout.setBackground(Color.blue);
        logout.addActionListener(e -> {
            try {
                Login.getCon().close();
                this.dispose();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Login.loginCheck = 0;
        });

        adp.add(showA);
        adp.add(searchA);
        adp.add(searchName);
        adp.add(changeName);
        adp.add(searchAdress);
        adp.add(changeAdress);
        adp.add(searchTelefonnummmer);
        adp.add(changeTelefonnummmer);
        adp.add(searchMobilnummer);
        adp.add(changeMobilnummer);
        adp.add(searchMånadslön);
        adp.add(changeMånadslön);
        adp.add(searchSemester);
        adp.add(changeSemester);
        adp.add(logout);
        adp.add(output);

        this.add(adp);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}
