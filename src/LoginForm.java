import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JDialog {
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton btnOK;
    private JButton btnCancel;
    private JPanel loginPanel;


    public LoginForm(JFrame parent){
        super(parent);
        setTitle("login");
        setContentPane(loginPanel);
        setSize(450 , 450);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email=tfEmail.getText();
                String password=String.valueOf(pfPassword.getPassword());
              user =  getAuthenticatedUser(email,password);
              if (user!=null){
                  dispose();
              }
              else {
                  JOptionPane.showMessageDialog(LoginForm.this,"invalid password",
                          "try again",JOptionPane.ERROR_MESSAGE);
              }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
    public User user;
    private User getAuthenticatedUser(String email , String password){
        User user = null;

        final String DB_URL = "jdbc:mysql://localhost:3306/application?serverTimezone=UTC";
        final String USERNAME ="root";
        final String PASSWORD ="";

        try {

            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stnt = conn.createStatement();
            String sql = "SELECT * FROM projet WHERE email=? AND password=?";
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                user = new User();
                user.name=resultSet.getString("name");
                user.email=resultSet.getString("email");
                user.adress=resultSet.getString("adress");
                user.password=resultSet.getString("password");
                user.phone=resultSet.getString("phone");

            }
            stnt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();

        }

        return user;
    }
    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm(null);
        User user=loginForm.user;
        if (user != null) {
            System.out.println("valid inscription of :" + user.name);
            System.out.println("email :"  + user.email);
            System.out.println("phone :" + user.phone);
            System.out.println("adress  :" + user.adress);



        }
        else {
            System.out.println(" invalid ");
        }

    }
}
