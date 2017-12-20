package com.minimalsoft.smsmx.Utils;

/**
 * Created by David Morales on 18/12/17.
 */

public enum SMStatus {
    PENDING,
    SENDING,
    SENDED,
    DELIVERED,
    NOT_DELIVERED,
    FAILED_GENERIC,
    FAILED_NO_SERVICE,
    FAILED_NULL_PDU,
    FAILED_RADIO_OFF,
    UNKNOWN
}
