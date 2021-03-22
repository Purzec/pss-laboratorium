package pl.edu.utp.psslab.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import pl.edu.utp.psslab.model.Delegation
import pl.edu.utp.psslab.repositories.DelegationRepository
import pl.edu.utp.psslab.repositories.UserRepository

@Service
class DelegationService {
    @Autowired
    private lateinit var delegationRepository: DelegationRepository
    @Autowired
    private lateinit var userRepository: UserRepository

    // todo czy ma sprawdzać, czy user jest null tutaj?
    fun setDelegation(delegation: Delegation) = delegationRepository.save(delegation)

    fun getDelegation(delegationId: Long) =
        if(delegationRepository.existsById(delegationId)) {
            delegationRepository.findById(delegationId).get()
        } else null

    fun getAllDelegations(pageable: Pageable) = delegationRepository.findAll(pageable)

    /*
    fun getAllDelegationsOrderByDateStartDesc(pageable: Pageable): Page<Delegation> =
        delegationRepository.findAllDelegationsOrderByDateStartDesc(pageable)

    */ // TODO jak zrobić pageable z kwerendy?

    fun getAllDelegationsOrderByDateStartDesc(pageable: Pageable) =
        delegationRepository.findAllDelegationsOrderByDateStartDesc(pageable)

    fun getAllDelByUserOrderByDateStartDesc(userId: Long, pageable: Pageable) =
        delegationRepository.findAllDelByUserOrderByDateStartDesc(userId, pageable)

    fun deleteDelegation(delegationId: Long) =
    if(delegationRepository.existsById(delegationId)) {
        delegationRepository.deleteById(delegationId)
        true
    } else false


}