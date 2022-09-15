type Flight = {
    "type": string,
    "id": string,
    "source": string,
    "instantTicketingRequired": boolean,
    "disablePricing": boolean,
    "nonHomogeneous": boolean,
    "oneWay": boolean,
    "paymentCardRequired": boolean,
    "lastTicketingDate": string,
    "numberOfBookableSeats": number,
    "itineraries": Itineraries[],
    "price": {
        "currency": string,
        "total": string,
        "base": string,
        "fees": Fees[],
        "grandTotal": string,
        "additionalServices": AdditionalServices[]
    },
    "pricingOptions": {
        "includedCheckedBagsOnly": boolean,
        "fareType": string[],
        "refundableFare": boolean,
        "noRestrictionFare": boolean,
        "noPenaltyFare": boolean
    },
    "validatingAirlineCodes": string[],
    "travelerPricings": TravelerPricings[]
}

export default Flight;


type Itineraries = {
    "duration": string,
    "segments": Segments[
        ]
}

type Segments = {
    "departure": {
        "iataCode": string,
        "terminal": string,
        "at": string
    },
    "arrival": {
        "iataCode": string,
        "terminal": string,
        "at": string
    },
    "carrierCode": string,
    "number": string,
    "aircraft": {
        "code": string
    },
    "operating": {
        "carrierCode": string
    },
    "duration": string,
    "id": string,
    "numberOfStops": number,
    "blacklistedInEU": boolean
}

type Fees = {
    "amount": string,
    "type": string
}

type AdditionalServices = {
    "amount": string,
    "type": string
}

type TravelerPricings = {
    "travelerId": string,
    "fareOption": string,
    "travelerType": string,
    "price": {
        "currency": string,
        "total": string,
        "base": string
    },
    "fareDetailsBySegment": FareDetailsBySegment[]
}

type FareDetailsBySegment = {
    "segmentId": string,
    "cabin": string,
    "fareBasis": string,
    "brandedFare": string,
    "class": string,
    "isAllotment": boolean,
    "includedCheckedBags": {
        "quantity": number,
        "weight": number
    }
}
