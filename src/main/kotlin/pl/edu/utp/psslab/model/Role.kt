package pl.edu.utp.psslab.model

import javax.persistence.*

@Entity
class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var roleId: Long = 0

    @Column
    var roleName: String? = null

    @ManyToMany
    var users: Set<User>? = null

    constructor(roleName: String?) {
        this.roleName = roleName
    }
}