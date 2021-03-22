package pl.edu.utp.psslab.model

import java.time.LocalDateTime

class DelegationDTO(
    var description: String,
    var dateTimeStart: LocalDateTime,
    var dateTimeStop: LocalDateTime,
    var travelDietAmount: Int,
    var breakfastNumber: Int,
    var dinnerNumber: Int,
    var supperNumber: Int,
    var transportType: TransportType?,
    var ticketPrice: Int?,
    var autoCapacity: AutoCapacity?,
    var km: Int?,
    var accomodationPrice: Int?,
    var otherTicketsPrice: Int?,
    var otherOutlayDesc: String?,
    var otherOutlayPrice: Int?
)