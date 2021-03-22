package pl.edu.utp.psslab.repositories

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import pl.edu.utp.psslab.model.User
import kotlin.streams.toList

@Repository
interface UserRepository : JpaRepository<User, Long> {

    /*
    fun findAllUsersByRoleNameFiltered(roleName: String): List<User> {
        return this.findAll()
            .stream()
            .filter { it.roles.any { it.roleName == roleName } }
            .toList()
    }
    // TODO nie dziala
    */


    @Query(value = "SELECT DISTINCT User.* FROM User " +
            "INNER JOIN user_roles " +
            "ON user_roles.user_user_id = user.user_id " +
            "INNER JOIN role " +
            "ON user_roles.roles_role_id = role.role_id " +
            "WHERE role_Name = ?1", nativeQuery = true)
    fun findAllUsersByRoleName(roleName: String): List<User>

    @Query(value = "SELECT DISTINCT User.* FROM User " +
            "INNER JOIN user_roles " +
            "ON user_roles.user_user_id = user.user_id " +
            "INNER JOIN role " +
            "ON user_roles.roles_role_id = role.role_id " +
            "WHERE role_Name = ?1", nativeQuery = true)
    fun findAllUsersByRoleName(roleName: String, pageable: Pageable): Page<User>

}