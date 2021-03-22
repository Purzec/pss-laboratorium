package pl.edu.utp.psslab.model

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long = 0

    @Column(nullable = false)
    var companyName: String

    @Column(nullable = false)
    var companyAddress: String

    @Column(nullable = false)
    var companyNip: String

    @Column(nullable = false)
    var name: String

    @Column(nullable = false)
    var lastName: String

    @Column(nullable = false)
    var email: String

    @Column(nullable = false)
    var password: String

    @Column(nullable = false)
    var status: Boolean = true

    @Column
    var registrationDate: LocalDateTime

    @ManyToMany(cascade = arrayOf(CascadeType.ALL))
    var roles: Set<Role>

    @OneToMany(cascade = arrayOf(CascadeType.REMOVE))
    //@OnDelete(action = OnDeleteAction.CASCADE) // TODO nie dziala
    var delegations: Set<Delegation>? = null

    constructor(
        companyName: String,
        companyAddress: String,
        companyNip: String,
        name: String,
        lastName: String,
        email: String,
        password: String,
    ) {
        this.companyName = companyName
        this.companyAddress = companyAddress
        this.companyNip = companyNip
        this.name = name
        this.lastName = lastName
        this.email = email
        this.password = password
        this.status = true
        this.registrationDate = LocalDateTime.now()
        this.roles = setOf(Role("User"))
    }

    // konstruktor DTO
    constructor(
        userDTO: UserDTO
    ) {
        this.companyName = userDTO.companyName
        this.companyAddress = userDTO.companyAddress
        this.companyNip = userDTO.companyNip
        this.name = userDTO.name
        this.lastName = userDTO.lastName
        this.email = userDTO.email
        this.password = userDTO.password
        this.status = true
        this.registrationDate = LocalDateTime.now()
        this.roles = setOf(Role("User"))
    }
}