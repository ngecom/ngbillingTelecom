package com.ngbilling.core.common.exception;

import java.io.Serializable;

/**
 * Session internal error messages
 * </p>
 * Provides the error messages returned from the jBIlling API
 *
 * @author: Panche.Isajeski
 * @since: 01/23/13
 */
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "errorMessages"
})
@XmlRootElement(name = "SessionInternalErrorMessages")
public class SessionInternalErrorMessages implements Serializable {

    @XmlElement(required = true)
    private String errorMessages[] = null;

    public String[] getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(String[] errorMessages) {
        this.errorMessages = errorMessages;
    }
}


