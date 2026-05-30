package MallaDeTurnos.example.mallaDeTurnos.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class MUser {

    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "user_id", nullable = false)
    Integer userId;
    @Column (name = "role_id", nullable = false, insertable = false, updatable = false)
    Integer roleId;
    @Column (name = "team_leader_id", insertable = false, updatable = false)
    Integer teamLeaderId;
    @Column (name = "user_full_name", length = 100 ,nullable = false)
    String userFullName;
    @Column (name = "id_type_id", nullable = false, insertable = false, updatable = false)
    Integer idTypeId;
    @Column (name = "number_id", length = 30 ,nullable = false,unique = true)
    String numberId;
    @Column( nullable = false)
    Boolean status;

    // Relaciones
    // Relacion con roles 1 usuario tiene 1 rol, pero varios usuarios pueden tener el mismo rol
    @ManyToOne
    @JoinColumn(name = "role_id",
                referencedColumnName = "role_id")
    MRoles mRoles;
    // Relacion Team Leader, 1 usuario tiene 1 team leader, pero varios usuarios pueden tener 1 team leader
    @ManyToOne
    @JoinColumn(name = "team_leader_id",
                referencedColumnName = "user_id")
    MUser teamLeader;
    // Relacion team leader con los equipos, referecia en la misma base
    @OneToMany(mappedBy = "teamLeader")
    @JsonIgnore
    List<MUser> team;

    // 1 usuario puede tener varias campañas
    @OneToMany(mappedBy = "mUser")
    @JsonIgnore
    List<MUserCampaigns> mUserCampaigns;

    // 1 usuario puede tener 1 tipo de id
    @ManyToOne
    @JoinColumn(name = "id_type_id",
                referencedColumnName = "id_type_id")
    MTypeId mTypeId;


    //1 usuario puede tener muchos turnos
    @OneToMany(mappedBy = "mUser")
    @JsonIgnore
    List<MShiftSchedules> mShiftSchedules;

    //1 aprobador puede tener multiples aprobaciones
    @OneToMany(mappedBy = "mUser")
    @JsonIgnore
    List<MShiftChanges> mShiftChanges;

    //1 user, tiene 1 logueo
    @OneToOne(mappedBy = "mUser")
    @JsonIgnore
    MLoginUser mLoginUser;

    //Constructor


    public MUser(Integer idTypeId, String numberId, Integer roleId, Boolean status, Integer teamLeaderId, String userFullName, Integer userId) {
        this.idTypeId = idTypeId;
        this.numberId = numberId;
        this.roleId = roleId;
        this.status = status;
        this.teamLeaderId = teamLeaderId;
        this.userFullName = userFullName;
        this.userId = userId;
    }

    public MUser() {
    }

    //Encapsulamiento


    public Integer getIdTypeId() {
        return idTypeId;
    }

    public void setIdTypeId(Integer idTypeId) {
        this.idTypeId = idTypeId;
    }

    public MLoginUser getmLoginUser() {
        return mLoginUser;
    }

    public void setmLoginUser(MLoginUser mLoginUser) {
        this.mLoginUser = mLoginUser;
    }

    public MRoles getmRoles() {
        return mRoles;
    }

    public void setmRoles(MRoles mRoles) {
        this.mRoles = mRoles;
    }

    public List<MShiftChanges> getmShiftChanges() {
        return mShiftChanges;
    }

    public void setmShiftChanges(List<MShiftChanges> mShiftChanges) {
        this.mShiftChanges = mShiftChanges;
    }

    public List<MShiftSchedules> getmShiftSchedules() {
        return mShiftSchedules;
    }

    public void setmShiftSchedules(List<MShiftSchedules> mShiftSchedules) {
        this.mShiftSchedules = mShiftSchedules;
    }

    public MTypeId getmTypeId() {
        return mTypeId;
    }

    public void setmTypeId(MTypeId mTypeId) {
        this.mTypeId = mTypeId;
    }

    public List<MUserCampaigns> getmUserCampaigns() {
        return mUserCampaigns;
    }

    public void setmUserCampaigns(List<MUserCampaigns> mUserCampaigns) {
        this.mUserCampaigns = mUserCampaigns;
    }

    public String getNumberId() {
        return numberId;
    }

    public void setNumberId(String numberId) {
        this.numberId = numberId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<MUser> getTeam() {
        return team;
    }

    public void setTeam(List<MUser> team) {
        this.team = team;
    }

    public MUser getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(MUser teamLeader) {
        this.teamLeader = teamLeader;
    }

    public Integer getTeamLeaderId() {
        return teamLeaderId;
    }

    public void setTeamLeaderId(Integer teamLeaderId) {
        this.teamLeaderId = teamLeaderId;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
