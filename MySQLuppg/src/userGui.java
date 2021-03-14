import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class userGui extends JFrame{

    private JPanel up;
    private JTextField searchB, searchMagaz;
    private JTextArea output;
    private JButton searchBook, searchMagazin, showM, showB, borrow,borrowM, logout, leave;
    private JLabel bookId, magazineId;

    public userGui() {

        up = new JPanel();
        up.setBounds(100, 100, 100, 100);
        up.setVisible(true);
        up.setLayout(null);
        this.setTitle("Användare! ");
        this.setSize(800, 800);

        //this is the search field that you use to search for one book or that book you want to borrow
        searchB = new JTextField(" Sök på den boken du vill låna ");
        searchB.setBounds(50, 50, 200, 50);
        searchB.setBackground(Color.white);

        bookId = new JLabel("<html>Skriv id på den bok du vill låna!<br> Eller id på boken/tidskriften du vill lämna. ");
        bookId.setBounds(50,100,200,50);
        bookId.setBackground(Color.lightGray);

        //this is the search field that you use to search for the magazine or that magazine you want to borrow
        searchMagaz = new JTextField(" Sök på den tidskriften du vill låna ");
        searchMagaz.setBounds(250, 50, 200, 50);
        searchMagaz.setBackground(Color.white);

        magazineId = new JLabel("Skriv id på den Tidskrift du vill låna! ");
        magazineId.setBounds(250,80,200,50);
        magazineId.setBackground(Color.lightGray);

        //this is the field that print everything out for you
        output = new JTextArea("Info output! ");
        output.setBounds(50, 300, 400, 450);
        output.setBackground(Color.gray);
        output.setText(searchB.getText());

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
                    ans += (" \nTitel: " + set.getString(2) + " \nFörfattare: " + set.getString(3) + " \nSidor: "
                            + set.getInt(4) + " \nKlassifikation: " + set.getString(5) + " \nUtlånad: "
                            + (set.getInt(6) == Login.UTLÅNAD ? "Utlånad " : "Ej utlånad ") + "\n BokId: " + set.getInt(1));
                }
                output.setText(ans);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        //this is the button you will use to look for all the magazine before you search
        showM = new JButton("Alla Tidskrifter ");
        showM.setBounds(150, 200, 130, 30);
        showM.setBackground(Color.yellow);
        showM.addActionListener(e->{
            String ans = "";
            try {
                PreparedStatement p = Login.getCon().prepareStatement("SELECT * FROM Tidskrifter ");
                ResultSet set = p.executeQuery();
                while (set.next()){
                    ans += (" \n Titel: " + set.getString(2) + " \n Utgivningsdatum: " + set.getString(3) + " \n Hyllplats: "
                            + set.getString(4) + " \n Utlånad: " + (set.getInt(5) == Login.UTLÅNAD ? "Utlånad " : "Ej utlånad ") + "\n TidskiftsId: " + set.getInt(1));
                }
                output.setText(ans);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        //press to search for the book
        searchBook = new JButton("Sök Böcker ");
        searchBook.setBounds(50, 150, 120, 30);
        searchBook.setBackground(Color.yellow);
        searchBook.addActionListener(e-> {
                try {
                    PreparedStatement p = Login.getCon().prepareStatement("SELECT * FROM Böcker where Title = ?");
                    p.setString(1, searchB.getText());
                    ResultSet set = p.executeQuery();
                    while (set.next()){
                        output.setText(" \nTitel: " + set.getString(2) + " \nFörfattare: " + set.getString(3) + " \nSidor: "
                                + set.getInt(4) + " \nKlassifikation: " + set.getString(5) + " \nUtlånad: "
                                + (set.getInt(6) == Login.UTLÅNAD ? "Utlånad " : "Ej utlånad ") + "\n BokId: " + set.getInt(1));
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
        });

        //press to search for the magazine
        searchMagazin = new JButton("Sök Tidskrifter ");
        searchMagazin.setBounds(170, 150, 130, 30);
        searchMagazin.setBackground(Color.yellow);
        searchMagazin.addActionListener(e -> {
            String ans = "";
            try {
                PreparedStatement p = Login.getCon().prepareStatement("SELECT * FROM Tidskrifter where Titel = ?");
                p.setString(1, searchMagaz.getText());
                ResultSet set = p.executeQuery();
                while (set.next()){
                    ans += (" \n Titel: " + set.getString(2) + " \n Utgivningsdatum: " + set.getString(3) + " \n Hyllplats: "
                            + set.getString(4) + " \n Utlånad: " + (set.getInt(5) == Login.UTLÅNAD ? "Utlånad " : "Ej utlånad ") + "\n TidskiftsId: " + set.getInt(1));
                }
                output.setText(ans);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        //print the id and press this to loan a book
        borrow = new JButton("Låna Böcker ");
        borrow.setBounds(300, 150, 110, 30);
        borrow.setBackground(Color.yellow);
        borrow.addActionListener(e->{
            PreparedStatement p = null;
            try {
                {
                    p = Login.getCon().prepareStatement("INSERT INTO Lånadeböcker (LåntagareId, BokId, TidskriftsId, StartDatum, SlutDatum) VALUE (?, ?, NULL, ?, ?)");
                    p.setInt(1, Login.getLåntagareId());
                    p.setInt(2, Integer.parseInt(searchB.getText()));
                    p.setDate(3, new Date(System.currentTimeMillis()));
                    p.setDate(4, new Date(System.currentTimeMillis()+30));
                    p.executeUpdate();
                }
                {
                    p = Login.getCon().prepareStatement("UPDATE Böcker SET Utlånad = ? WHERE BokId = ?");
                    p.setInt(2, Integer.parseInt(searchB.getText()));
                    p.setInt(1, Login.UTLÅNAD);
                    p.executeUpdate();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        //print the id and press this to loan a magazine
        borrowM = new JButton("Låna Tidskrifter ");
        borrowM.setBounds(410, 150, 130, 30);
        borrowM.setBackground(Color.yellow);
        borrowM.addActionListener(e->{
            PreparedStatement p = null;
            try {
                {
                    p = Login.getCon().prepareStatement("INSERT INTO Lånadeböcker (LåntagareId, BokId, TidskriftsId, StartDatum, SlutDatum) VALUE (?, NULL, ?, ?, ?)");
                    p.setInt(1, Login.getLåntagareId());
                    p.setInt(2, Integer.parseInt(searchMagaz.getText()));
                    p.setDate(3, new Date(System.currentTimeMillis()));
                    p.setDate(4, new Date(System.currentTimeMillis()+30));
                    p.executeUpdate();
                }
                {
                    p = Login.getCon().prepareStatement("UPDATE Tidskifter SET  Utlånad = ? WHERE TidskiftsId = ?");
                    p.setInt(1, Integer.parseInt(searchMagaz.getText()));
                    p.setInt(2, Login.UTLÅNAD);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        leave = new JButton("Lämna ");
        leave.setBounds(540,150,100,30);
        leave.setBackground(Color.yellow);
        leave.addActionListener(e->{
            int LånadeböckerId = 0;
            PreparedStatement p = null;
            try{
                p = Login.getCon().prepareStatement("UPDATE Böcker, Tidskrifter SET  Utlånad = ? ");
                p.setString(1, searchB.getText());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });

        logout = new JButton("Logga ut ");
        logout.setBounds(280,200,100,30);
        logout.setBackground(Color.blue);
        logout.addActionListener(e-> {
            try {
                Login.getCon().close();
                this.dispose();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Login.loginCheck = 0;
        });

        up.add(searchBook);
        up.add(searchMagazin);
        up.add(leave);
        up.add(borrow);
        up.add(borrowM);
        up.add(logout);
        up.add(searchB);
        up.add(searchMagaz);
        up.add(showB);
        up.add(showM);
        up.add(output);
        up.add(magazineId);
        up.add(bookId);

        this.add(up);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
