/**
 *
 */
package com.ngbilling.core.server.persistence.dto.util;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * @author Vikas Bodani
 * @since 08-Aug-2011
 *
 */

@Entity
@Table(name = "enumeration_values")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EnumerationValueDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private String value;
    private EnumerationDTO enumeration;
    private int versionNum;

    public EnumerationValueDTO() {
    }

    public EnumerationValueDTO(int id) {
        this.id = id;
    }

    public EnumerationValueDTO(int id, String value, EnumerationDTO enumeration) {
        this.id = id;
        this.value = value;
        this.enumeration = enumeration;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "value", length = 50)
    @NotNull(message = "validation.error.notnull")
    @Size(min = 1, max = 50, message = "validation.error.size,1,50")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enumeration_id")
    public EnumerationDTO getEnumeration() {
        return enumeration;
    }

    public void setEnumeration(EnumerationDTO enumeration) {
        this.enumeration = enumeration;
    }

    @Version
    @Column(name = "OPTLOCK")
    public Integer getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    @Override
    public String toString() {
        return "EnumerationValueDTO [id=" + id + ", value=" + value
                + ", enumeration=" + enumeration + "]";
    }

}
