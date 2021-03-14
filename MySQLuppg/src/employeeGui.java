import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class employeeGui extends JFrame {

    private JPanel ep;
    private JTextArea output;
    private JButton searchBook, searchMagazin, searchUser, showB, showM, showA, logout;
    private JTextField search;

    public employeeGui() {

        ep = new JPanel();
        ep.setBounds(100, 100, 100, 100);
        ep.setVisible(true);
        ep.setLayout(null);
        this.setTitle("Anställd! ");
        this.setSize(800, 800);

        search = new JTextField("Vad vill du söka på ....");
        search.setBounds(50, 50, 200, 50);
        search.setBackground(Color.lightGray);

        //this is the field that print everything out for you
        output = new JTextArea("Info output! ");
        output.setBounds(50, 300, 400, 450);
        output.setBackground(Color.gray);
        output.setText(search.getText());

        //this is the button you will use to look for all the book before you search
        showB = new JButton("Alla böcker ");
        showB.setBounds(50, 200, 100, 30);
        showB.setBackground(Color.yellow);
        showB.addActionListener(e->{
            String ans = "";
            try {
                PreparedStatement p = Login.getCon().prepareStatement("SELECT * FROM Böcker ");
                ResultSet set = p.executeQuery();
                while (set.next()){
                    ans += (" \nTitel: " + set.getString(2) + " \nFörfattare: " + set.getString(3) + " \nSidor: " + set.getInt(4) + " \nKlassifikation: " + set.getString(5) + " \nUtlånad: " + set.getString(6));
                };
                output.setText(ans);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });

        //this is the button you will use to look for all the magazine before you search
        showM = new JButton("Alla Tidskrifter ");
        showM.setBounds(150, 200, 150, 30);
        showM.setBackground(Color.yellow);
        showM.addActionListener(e->{
            String ans = "";
            try {
                PreparedStatement p = Login.getCon().prepareStatement("SELECT * FROM Tidskrifter ");
                ResultSet set = p.executeQuery();
                while (set.next()){
                    ans += (" \nTitel: " + set.getString(2) + " \nDatum: " + set.getDate(3) + " \nHyllplats: "
                            + set.getString(4) + " \nUtlånad: " + set.getString(5)+ " \nTidskriftsId " + set.getInt(1));
                };
                output.setText(ans);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        //this is the button you will use to look what employee before you search
        showA = new JButton("Alla Låntagare ");
        showA.setBounds(300, 200, 150, 30);
        showA.setBackground(Color.yellow);
        showA.addActionListener(e->{
            String ans = "";
            try {
                PreparedStatement p = Login.getCon().prepareStatement("SELECT * FROM Låntagare");
                ResultSet set = p.executeQuery();
                while (set.next()){
                    ans += (" \nNamn: " + set.getString(2) + " \nAdress: " + set.getString(3) + " \nTelefonnummer: "
                            + set.getString(4) + " \nMobilnummer: " + set.getString(5)+ " \nLånekortsnummer" + set.getInt(6)+ " \nLån " + set.getString(7)+ " \nLåntagareId " + set.getInt(1));
                };
                output.setText(ans);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        //press to search for the book
        searchBook = new JButton("Sök Böcker ");
        searchBook.setBounds(50, 100, 120, 30);
        searchBook.setBackground(Color.yellow);
        searchBook.addActionListener(e -> {
            try {
                PreparedStatement p = Login.getCon().prepareStatement("SELECT * FROM Böcker where Title = ?");
                p.setString(1, search.getText());
                ResultSet set = p.executeQuery();
                while (set.next()) {
                    output.setText(" \nTitel: " + set.getString(2) + " \nFörfattare: " + set.getString(3) + " \nSidor: "
                            + set.getInt(4) + " \nKlassifikation: " + set.getString(5) + " \nUtlånad: " + set.getInt(6));
                }
                ;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                String exception = e.toString();
                output.setText("<html> " + exception);
            }
        });

        //press to search for the magazine
        searchMagazin = new JButton("Sök Tidskrifter ");
        searchMagazin.setBounds(170, 100, 150, 30);
        searchMagazin.setBackground(Color.yellow);
        searchMagazin.addActionListener(e -> {
            try {
                PreparedStatement p = Login.getCon().prepareStatement("SELECT * FROM Tidskrifter where Titel = ?");
                p.setString(1, search.getText());
                ResultSet set = p.executeQuery();
                while (set.next()) {
                    output.setText(" \nTitel: " + set.getString(2) + " \nDatum: " + set.getDate(3) + " \nHyllplats: " + set.getString(4) + " \nUtlånad: " + set.getString(5));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        //press to search for the user
        searchUser = new JButton("Sök Låntagare ");
        searchUser.setBounds(300, 100, 150, 30);
        searchUser.setBackground(Color.yellow);
        searchUser.addActionListener(e -> {
            try {
                PreparedStatement p = Login.getCon().prepareStatement("SELECT * FROM Låntagare where Name = ?");
                p.setString(1, search.getText());
                ResultSet set = p.executeQuery();
                String ans = " ";
                int LånId = 0;
                while (set.next()) {
                    ans = " \nName: " + set.getString(2) + " \nAdress: " + set.getString(3) + " \nTelefonnummer: " + set.getString(4) + " \nMobilnummer: "
                            + set.getString(5) + "\n Lånadeböcker: ";
                    LånId = set.getInt(1);
                }
                PreparedStatement p1 = Login.getCon().prepareStatement("SELECT*FROM Lånadeböcker JOIN Böcker ON Lånadeböcker.BokId=Böcker.BokId WHERE Lånadeböcker.LåntagareId=?");
                //get the first value and the next in here
                p1.setInt(1, LånId);
                ResultSet set1 = p1.executeQuery();
                while (set1.next()) {
                    ans += "\n " + set1.getString(9);
                }
                output.setText(ans);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        //logout
        logout = new JButton("Logga ut");
        logout.setBounds(50, 150, 100, 30);
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

        ep.add(search);
        ep.add(searchBook);
        ep.add(searchMagazin);
        ep.add(searchUser);
        ep.add(showB);
        ep.add(showM);
        ep.add(showA);
        ep.add(logout);
        ep.add(output);

        this.add(ep);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
