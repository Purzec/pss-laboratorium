package pl.edu.utp.psslab.model

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Delegation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var delegationId = 0

    @Column
    var description: String

    @Column(nullable = false)
    var dateTimeStart: LocalDateTime

    @Column(nullable = false)
    var dateTimeStop: LocalDateTime

    @Column(nullable = false)
    var travelDietAmount: Int = 0

    @Column(nullable = false)
    var breakfastNumber: Int = 0

    @Column(nullable = false)
    var dinnerNumber: Int = 0

    @Column(nullable = false)
    var supperNumber: Int = 0

    @Column
    var transportType: TransportType? = null

    @Column
    var ticketPrice: Int? = null

    @Column
    var autoCapacity: AutoCapacity? = null

    @Column
    var km: Int? = null

    @Column
    var accomodationPrice: Int? = null

    @Column
    var otherTicketsPrice: Int? = null

    @Column
    var otherOutlayDesc: String? = null

    @Column
    var otherOutlayPrice: Int? = null

    @ManyToOne(optional = false)
    var user: User? = null

    constructor(
        description: String,
        dateTimeStart: LocalDateTime,
        dateTimeStop: LocalDateTime,
        travelDietAmount: Int,
        breakfastNumber: Int,
        dinnerNumber: Int,
        supperNumber: Int,
        transportType: TransportType?,
        ticketPrice: Int?,
        autoCapacity: AutoCapacity?,
        km: Int?,
        accomodationPrice: Int?,
        otherTicketsPrice: Int?,
        otherOutlayDesc: String?,
        otherOutlayPrice: Int?
    ) {
        this.description = description
        this.dateTimeStart = dateTimeStart
        this.dateTimeStop = dateTimeStop
        this.travelDietAmount = travelDietAmount
        this.breakfastNumber = breakfastNumber
        this.dinnerNumber = dinnerNumber
        this.supperNumber = supperNumber
        this.transportType = transportType
        this.ticketPrice = ticketPrice
        this.autoCapacity = autoCapacity
        this.km = km
        this.accomodationPrice = accomodationPrice
        this.otherTicketsPrice = otherTicketsPrice
        this.otherOutlayDesc = otherOutlayDesc
        this.otherOutlayPrice = otherOutlayPrice
    }

    constructor(
        description: String,
        dateTimeStart: LocalDateTime,
        dateTimeStop: LocalDateTime,
        transportType: TransportType?,
        ticketPrice: Int?,
        autoCapacity: AutoCapacity?,
        km: Int?,
        accomodationPrice: Int?,
        otherTicketsPrice: Int?,
        otherOutlayDesc: String?,
        otherOutlayPrice: Int?
    ) {
        this.description = description
        this.dateTimeStart = dateTimeStart
        this.dateTimeStop = dateTimeStop
        this.travelDietAmount = 30
        this.breakfastNumber = 0
        this.dinnerNumber = 0
        this.supperNumber = 0
        this.transportType = transportType
        this.ticketPrice = ticketPrice
        this.autoCapacity = autoCapacity
        this.km = km
        this.accomodationPrice = accomodationPrice
        this.otherTicketsPrice = otherTicketsPrice
        this.otherOutlayDesc = otherOutlayDesc
        this.otherOutlayPrice = otherOutlayPrice
    }

    // konstruktor DTO
    constructor(
        delegationDTO: DelegationDTO
    ) {
        this.description = delegationDTO.description
        this.dateTimeStart = delegationDTO.dateTimeStart
        this.dateTimeStop = delegationDTO.dateTimeStop
        this.travelDietAmount = delegationDTO.travelDietAmount
        this.breakfastNumber = delegationDTO.breakfastNumber
        this.dinnerNumber = delegationDTO.dinnerNumber
        this.supperNumber = delegationDTO.supperNumber
        this.transportType = delegationDTO.transportType
        this.ticketPrice = delegationDTO.ticketPrice
        this.autoCapacity = delegationDTO.autoCapacity
        this.km = delegationDTO.km
        this.accomodationPrice = delegationDTO.accomodationPrice
        this.otherTicketsPrice = delegationDTO.otherTicketsPrice
        this.otherOutlayDesc = delegationDTO.otherOutlayDesc
        this.otherOutlayPrice = delegationDTO.otherOutlayPrice
    }

    fun updateValues(delegationDTO: DelegationDTO) {
        this.description = delegationDTO.description
        this.dateTimeStart = delegationDTO.dateTimeStart
        this.dateTimeStop = delegationDTO.dateTimeStop
        this.travelDietAmount = delegationDTO.travelDietAmount
        this.breakfastNumber = delegationDTO.breakfastNumber
        this.dinnerNumber = delegationDTO.dinnerNumber
        this.supperNumber = delegationDTO.supperNumber
        this.transportType = delegationDTO.transportType
        this.ticketPrice = delegationDTO.ticketPrice
        this.autoCapacity = delegationDTO.autoCapacity
        this.km = delegationDTO.km
        this.accomodationPrice = delegationDTO.accomodationPrice
        this.otherTicketsPrice = delegationDTO.otherTicketsPrice
        this.otherOutlayDesc = delegationDTO.otherOutlayDesc
        this.otherOutlayPrice = delegationDTO.otherOutlayPrice
    }
}