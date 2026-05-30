package MallaDeTurnos.example.mallaDeTurnos.Servicios;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOLoginUser;
import MallaDeTurnos.example.mallaDeTurnos.Mappers.LoginUserMapper;
import MallaDeTurnos.example.mallaDeTurnos.Modelo.MLoginUser;
import MallaDeTurnos.example.mallaDeTurnos.Modelo.MUser;
import MallaDeTurnos.example.mallaDeTurnos.Repositorio.ILoginUser;
import MallaDeTurnos.example.mallaDeTurnos.Repositorio.IUser;
import MallaDeTurnos.example.mallaDeTurnos.Utilidades.EntityResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SLoginUser {
    @Autowired
    ILoginUser iLoginUser;
    @Autowired
    IUser iUser;
    @Autowired
    LoginUserMapper loginUserMapper;
    @Autowired
    EntityResolver entityResolver;
    //BCrypt para tener un manejo seguro de password, se usa encriptacion tanto para el guardado, salvado y comparativa
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    //Constructor


    public SLoginUser(EntityResolver entityResolver, ILoginUser iLoginUser, IUser iUser, LoginUserMapper loginUserMapper) {
        this.entityResolver = entityResolver;
        this.iLoginUser = iLoginUser;
        this.iUser = iUser;
        this.loginUserMapper = loginUserMapper;
    }

    //Proceso de login verificar correo y comparar con password
    public MLoginUser userLoginConfirmation (String userEmail, String userPassword) throws Exception{
        try {
            Optional<MLoginUser> findUserEmail = iLoginUser.findByUserEmail(userEmail);
            //Si encuentra el usuario, verificar el password
            if (findUserEmail.isPresent()){
                MLoginUser userEmailFound = findUserEmail.get();
                if (encoder.matches(userPassword,userEmailFound.getUserPassword())){
                    //Buscar el usuario
                    Optional<MUser> findUser = iUser.findById(userEmailFound.getUserId());
                    if (findUser.isPresent()){
                        MUser userFound = findUser.get();
                        if(userFound.getStatus()){
                            return userEmailFound;
                        } else throw new Exception("El usuario ya no se encuentra activo");
                    } else throw new Exception("No se encuentra el usuario");
                } else throw new Exception("La contraseña no coincide");
            } else throw new Exception("El correo de usuario no existe");
        } catch (Exception error) {
            throw new Exception(error);
        }
    }

    //Nuevo usuario
    public MLoginUser newUserLogin(DTOLoginUser dtoLoginUser) throws Exception{
        try{
            //Encriptar password antes de guardar
            MLoginUser mLoginUser = loginUserMapper.toModel(dtoLoginUser);
            mLoginUser.setmUser(entityResolver.resolveUser(dtoLoginUser.getUserId()));
            String encriptedPassword = encoder.encode(mLoginUser.getUserPassword());
            mLoginUser.setUserPassword(encriptedPassword);
            return iLoginUser.save(mLoginUser);
        } catch (Exception error) {
            throw new Exception(error);
        }
    }
    //Actualizar usuario
    public MLoginUser updateUserLogin(Integer userLoginId, DTOLoginUser dtoLoginUser) throws Exception{
        try{
            Optional<MLoginUser> findUserLogin = iLoginUser.findById(userLoginId);
            if (findUserLogin.isPresent()){
                MLoginUser userLoginFound = findUserLogin.get();
                //Actualizar registro
                userLoginFound.setUserEmail(dtoLoginUser.getUserEmail());
                //Encriptar la clave antes de actualizar
                String encryptedPassword = encoder.encode(dtoLoginUser.getUserPassword());
                userLoginFound.setUserPassword(encryptedPassword);
                //Guardar registro
                return iLoginUser.save(userLoginFound);
            } else throw new Exception("No se encuentra este usuario para login");
        } catch (Exception error) {
            throw new Exception(error);
        }
    }
}
