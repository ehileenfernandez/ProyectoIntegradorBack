package MallaDeTurnos.example.mallaDeTurnos.Modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "login_user")
public class MLoginUser {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_user_id", nullable = false)
    Integer loginUserId;
    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    Integer userId;
    @Column(name="user_email",length =100, nullable = false,unique = true)
    String userEmail;
    @Column(name = "user_password", length = 100,nullable = false)
    String userPassword;

    //Relaciones

    //1 user, tiene una opcion de logueo

    @OneToOne
    @JoinColumn(name = "user_id",
                referencedColumnName = "user_id")
    MUser mUser;

    //Constructor


    public MLoginUser(Integer loginUserId, String userEmail, Integer userId, String userPassword) {
        this.loginUserId = loginUserId;
        this.userEmail = userEmail;
        this.userId = userId;
        this.userPassword = userPassword;
    }

    public MLoginUser() {
    }
    //Encapsulamiento


    public Integer getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(Integer loginUserId) {
        this.loginUserId = loginUserId;
    }

    public MUser getmUser() {
        return mUser;
    }

    public void setmUser(MUser mUser) {
        this.mUser = mUser;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
