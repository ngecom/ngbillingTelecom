/**
 *
 */
package com.ngbilling.core.server.persistence.dto.util;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * @author Vikas Bodani
 * @since 08-Aug-2011
 *
 */

@Entity
@TableGenerator(
        name = "enumerationValues_GEN",
        table = "jbilling_seqs",
        pkColumnName = "name",
        valueColumnName = "next_id",
        pkColumnValue = "enumeration_values",
        allocationSize = 1000
)
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
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "enumerationValues_GEN")
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
