package pl.edu.utp.psslab.repositories

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import pl.edu.utp.psslab.model.Delegation

@Repository
interface DelegationRepository : JpaRepository<Delegation, Long> {

    @Query(value = " SELECT * FROM delegation ORDER BY date_time_start DESC", nativeQuery = true)
    fun findAllDelegationsOrderByDateStartDesc(pageable: Pageable): Page<Delegation>

    @Query(value = " SELECT * FROM delegation WHERE user_user_id = ?1 ORDER BY date_time_start DESC", nativeQuery = true)
    fun findAllDelByUserOrderByDateStartDesc(userId: Long, pageable: Pageable): Page<Delegation>

}