package com.ngbilling.core.server.persistence.dto.metafield;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortNatural;
import org.hibernate.annotations.SortType;

import com.ngbilling.core.server.persistence.dto.util.AbstractDescription;
import com.ngbilling.core.server.util.ServerConstants;
import com.ngbilling.core.server.validator.metafield.ValidationRuleType;

/**
 *  Validation rule entity
 *  </p>
 *  Consist of error message and validation rule type
 *  along with rules attributes used in validation
 *
 *  @author Panche Isajeski
 */
@Entity
@Table(name = "validation_rule")
@TableGenerator(
        name = "validation_rule_GEN",
        table = "jbilling_seqs",
        pkColumnName = "name",
        valueColumnName = "next_id",
        pkColumnValue = "validation_rule",
        allocationSize = 10
)
public class ValidationRule extends AbstractDescription implements Serializable {

    public static final String ERROR_MSG_LABEL= "errorMessage";

    private int id;

    private ValidationRuleType ruleType;

	private SortedMap<String, String> ruleAttributes = new TreeMap<String, String>();
    private boolean enabled = true;

    // transient, used for errors international description
    private Map<Integer, String> errors = new HashMap<Integer, String>();

    private Integer versionNum;

    public ValidationRule() {
    }
    

    public ValidationRule(ValidationRuleType ruleType, boolean enabled) {
		super();
		this.ruleType = ruleType;
		this.enabled = enabled;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "validation_rule_GEN")
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "rule_type", nullable = false, length = 25)
    public ValidationRuleType getRuleType() {
        return ruleType;
    }

    public void setRuleType(ValidationRuleType ruleType) {
        this.ruleType = ruleType;
    }

    @ElementCollection
    @JoinTable(name = "validation_rule_attributes", joinColumns = @JoinColumn(name = "validation_rule_id"))
    @MapKeyColumn(name = "attribute_name", insertable = false, updatable = false)
    @Column(name = "attribute_value", nullable = true, length = 255)
    @SortNatural
    @Fetch(FetchMode.SELECT)
    public SortedMap<String, String> getRuleAttributes() {
        return ruleAttributes;
    }

    public void setRuleAttributes(SortedMap<String, String> ruleAttributes) {
        this.ruleAttributes = ruleAttributes;
    }

    @Column(name = "enabled", nullable = false)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Version
    @Column(name="OPTLOCK")
    public Integer getVersionNum() {
        return versionNum;
    }
    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    @Transient
    public String getErrorMessage(int langId) {
        return getDescription(langId, ERROR_MSG_LABEL);
    }

    @Transient
    public void setErrorMessage(Integer languageId, String error) {
        setDescription(ERROR_MSG_LABEL, languageId, error);
    }

    // temp methods
    @Transient
    public Map<Integer, String> getErrors() {
        return errors;
    }

    @Transient
    public void setErrors(Map<Integer, String> errors) {
        this.errors = errors;
    }

    @Transient
    public void addError(Integer languageId, String error) {
        getErrors().put(languageId, error);
    }

    @Transient
    protected String getTable() {
        return ServerConstants.TABLE_VALIDATION_RULE;
    }
}
