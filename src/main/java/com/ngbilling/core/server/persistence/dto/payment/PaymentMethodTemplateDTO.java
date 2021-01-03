package com.ngbilling.core.server.persistence.dto.payment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;


/**
 * 
 * @author khobab
 *
 */
@Entity
@TableGenerator(
        name="payment_method_template_GEN",
        table="jbilling_seqs",
        pkColumnName = "name",
        valueColumnName = "next_id",
        pkColumnValue="payment_method_template",
        allocationSize = 10
        )
@Table(name = "payment_method_template")
public class PaymentMethodTemplateDTO implements Serializable{

	private static final long serialVersionUID = 6278737291727940386L;
	
	private int id;
	private String templateName;
	
	private int version;
	
	@Id @GeneratedValue(strategy = GenerationType.TABLE, generator = "payment_method_template_GEN")
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }
	
	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "template_name", length = 50)
    public String getTemplateName() {
        return this.templateName;
    }
	
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	@Version
    @Column(name = "OPTLOCK")
    public int getVersion() {
        return version;
    }
	
	public void setVersion(int version) {
		this.version = version;
	}

}
