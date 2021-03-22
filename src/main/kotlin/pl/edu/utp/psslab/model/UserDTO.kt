package pl.edu.utp.psslab.model

import javax.persistence.Column

class UserDTO(
    var companyName: String,
    var companyAddress: String,
    var companyNip: String,
    var name: String,
    var lastName: String,
    var email: String,
    var password: String
)