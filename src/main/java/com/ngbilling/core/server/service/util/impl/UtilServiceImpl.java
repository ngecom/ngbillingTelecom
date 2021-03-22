package com.ngbilling.core.server.service.util.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.joda.time.DateMidnight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Service;

import com.ngbilling.core.payload.request.metafield.DataType;
import com.ngbilling.core.payload.request.metafield.MetaFieldType;
import com.ngbilling.core.payload.request.order.ApplyToOrder;
import com.ngbilling.core.payload.request.order.OrderStatusFlag;
import com.ngbilling.core.payload.request.util.NotificationMediumType;
import com.ngbilling.core.server.persistence.dao.invoice.InvoiceDeliveryMethodDAO;
import com.ngbilling.core.server.persistence.dao.metafield.MetaFieldDAO;
import com.ngbilling.core.server.persistence.dao.metafield.ValidationRuleDAO;
import com.ngbilling.core.server.persistence.dao.notification.NotificationMessageDAO;
import com.ngbilling.core.server.persistence.dao.notification.NotificationMessageSectionDAO;
import com.ngbilling.core.server.persistence.dao.order.OrderChangeStatusDAO;
import com.ngbilling.core.server.persistence.dao.order.OrderPeriodDAO;
import com.ngbilling.core.server.persistence.dao.order.OrderStatusDAO;
import com.ngbilling.core.server.persistence.dao.payment.PaymentMethodDAO;
import com.ngbilling.core.server.persistence.dao.payment.PaymentMethodTemplateDAO;
import com.ngbilling.core.server.persistence.dao.pluggableTask.PluggableTaskDAO;
import com.ngbilling.core.server.persistence.dao.pluggableTask.PluggableTaskParameterDAO;
import com.ngbilling.core.server.persistence.dao.pluggableTask.PluggableTaskTypeDAO;
import com.ngbilling.core.server.persistence.dao.process.BillingProcessConfigurationDAO;
import com.ngbilling.core.server.persistence.dao.report.ReportDAO;
import com.ngbilling.core.server.persistence.dao.user.AccountTypeDAO;
import com.ngbilling.core.server.persistence.dao.util.CurrencyDAO;
import com.ngbilling.core.server.persistence.dao.util.EnumerationDAO;
import com.ngbilling.core.server.persistence.dao.util.EnumerationValueDAO;
import com.ngbilling.core.server.persistence.dao.util.InternationalDescriptionDAO;
import com.ngbilling.core.server.persistence.dao.util.JbillingTableDAO;
import com.ngbilling.core.server.persistence.dao.util.LanguageDAO;
import com.ngbilling.core.server.persistence.dao.util.PreferenceDAO;
import com.ngbilling.core.server.persistence.dto.invoice.InvoiceDeliveryMethodDTO;
import com.ngbilling.core.server.persistence.dto.metafield.MetaField;
import com.ngbilling.core.server.persistence.dto.metafield.ValidationRule;
import com.ngbilling.core.server.persistence.dto.notification.NotificationMessageDTO;
import com.ngbilling.core.server.persistence.dto.notification.NotificationMessageLineDTO;
import com.ngbilling.core.server.persistence.dto.notification.NotificationMessageSectionDTO;
import com.ngbilling.core.server.persistence.dto.notification.NotificationMessageTypeDTO;
import com.ngbilling.core.server.persistence.dto.order.OrderChangeStatusDTO;
import com.ngbilling.core.server.persistence.dto.order.OrderPeriodDTO;
import com.ngbilling.core.server.persistence.dto.order.OrderStatusDTO;
import com.ngbilling.core.server.persistence.dto.payment.PaymentMethodDTO;
import com.ngbilling.core.server.persistence.dto.payment.PaymentMethodTemplateDTO;
import com.ngbilling.core.server.persistence.dto.pluggableTask.PluggableTaskDTO;
import com.ngbilling.core.server.persistence.dto.pluggableTask.PluggableTaskParameterDTO;
import com.ngbilling.core.server.persistence.dto.pluggableTask.PluggableTaskTypeDTO;
import com.ngbilling.core.server.persistence.dto.process.BillingProcessConfigurationDTO;
import com.ngbilling.core.server.persistence.dto.process.PeriodUnitDTO;
import com.ngbilling.core.server.persistence.dto.process.ProratingType;
import com.ngbilling.core.server.persistence.dto.report.ReportDTO;
import com.ngbilling.core.server.persistence.dto.user.AccountTypeDTO;
import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.persistence.dto.user.MainSubscriptionDTO;
import com.ngbilling.core.server.persistence.dto.user.UserDTO;
import com.ngbilling.core.server.persistence.dto.util.CurrencyDTO;
import com.ngbilling.core.server.persistence.dto.util.EntityType;
import com.ngbilling.core.server.persistence.dto.util.EnumerationDTO;
import com.ngbilling.core.server.persistence.dto.util.EnumerationValueDTO;
import com.ngbilling.core.server.persistence.dto.util.InternationalDescriptionDTO;
import com.ngbilling.core.server.persistence.dto.util.InternationalDescriptionId;
import com.ngbilling.core.server.persistence.dto.util.JbillingTable;
import com.ngbilling.core.server.persistence.dto.util.LanguageDTO;
import com.ngbilling.core.server.persistence.dto.util.PreferenceDTO;
import com.ngbilling.core.server.persistence.dto.util.PreferenceTypeDTO;
import com.ngbilling.core.server.service.util.UtilService;
import com.ngbilling.core.server.util.ServerConstants;
import com.ngbilling.core.server.validator.metafield.ValidationRuleType;

@Service
public class UtilServiceImpl implements UtilService{

	@Autowired
	private LanguageDAO languageDAO;
	
	@Autowired
	private CurrencyDAO currencyDAO;
	
	@Autowired
	private JbillingTableDAO jbillingTableDAO;
	
	@Autowired
	private OrderStatusDAO orderStatusDAO;
	
	@Autowired
	private OrderPeriodDAO orderPeriodDAO;
	
	@Autowired
	private OrderChangeStatusDAO orderChangeStatusDAO;
	
	@Autowired
    private AccountTypeDAO accountTypeDAO;
    
	@Autowired
    private PaymentMethodDAO paymentMethodTaskDAO;
    
    @Autowired
    private PluggableTaskDAO pluggableTaskDAO;
    
    @Autowired
    private PluggableTaskParameterDAO pluggableTaskParameterDAO;
    
    
    @Autowired
    private PluggableTaskTypeDAO pluggableTaskTypeDAO;
    
    @Autowired
    private MetaFieldDAO metaFieldDAO;
    
    @Autowired
    private PaymentMethodTemplateDAO paymentMethodTemplateDAO;
    
    @Autowired
    private ValidationRuleDAO validationRuleDAO;
    
    @Autowired
    private InternationalDescriptionDAO internationalDescriptionDAO;
    
    @Autowired
    private EnumerationDAO enumerationDAO;
    
    @Autowired
    private EnumerationValueDAO enumerationValueDAO;
    
    @Autowired
    private PreferenceDAO preferenceDAO;
    
    @Autowired
    private NotificationMessageDAO notificationMessageDAO;
    
    @Autowired
    private BillingProcessConfigurationDAO billingProcessConfigurationDAO;
    
    @Autowired
    private NotificationMessageSectionDAO notificationMessageSectionDAO;
    
    @Autowired
    private ReportDAO reportDAO;

    @Autowired
    private PaymentMethodDAO paymentMethodDAO;
    
    @Autowired
    private InvoiceDeliveryMethodDAO invoiceDeliveryMethodDAO;
    
    AbstractMessageSource messageSource;
    
    Locale locale;
    
	@Override
	public LanguageDTO findByLanguageCode(String code) {
		return languageDAO.findByCode(code);
	}

	@Override
	public CurrencyDTO findByCurrencyCode(String code) {
		return currencyDAO.findByCode(code);
	}

	@Override
	public JbillingTable findByName(String tableName) {
		// TODO Auto-generated method stub
		return jbillingTableDAO.findByName(tableName);
	}

	@Override
	public void initEntityDefault(CompanyDTO company, UserDTO rootUser, LanguageDTO language) {
		
		/*
        Order Status
        */
		orderStatusDAO.save(new OrderStatusDTO(OrderStatusFlag.INVOICE, company, "Active", language.getId()));		
		orderStatusDAO.save(new OrderStatusDTO(OrderStatusFlag.FINISHED, company, "Finished", language.getId()));		
		orderStatusDAO.save(new OrderStatusDTO(OrderStatusFlag.NOT_INVOICE, company, "Suspended", language.getId()));		
		orderStatusDAO.save(new OrderStatusDTO(OrderStatusFlag.SUSPENDED_AGEING, company, "Suspended ageing(auto)", language.getId()));		  
		
		company.getCurrency().getEntities_1().add(company);

		/*
        Order periods
        */
		OrderPeriodDTO orderPeriodDTO = new OrderPeriodDTO(company, new PeriodUnitDTO(ServerConstants.PERIOD_UNIT_MONTH), 1);
    	orderPeriodDTO = orderPeriodDAO.save(orderPeriodDTO);
    	orderPeriodDTO.setDescription("Monthly", language.getId());
    	
    	
    	Integer orderChangeStatusId = orderChangeStatusDAO.getMaxStatusId();
    	OrderChangeStatusDTO orderChangeStatusDTO = new OrderChangeStatusDTO();
    	orderChangeStatusDTO.setCompany(company);
    	orderChangeStatusDTO.setApplyToOrder(ApplyToOrder.YES);
    	orderChangeStatusDTO.setDeleted(0);
    	orderChangeStatusDTO.setOrder(1);
    	orderChangeStatusDTO.setId(++orderChangeStatusId);
    	orderChangeStatusDTO.setDescription(getMessage("order.change.status.default.apply"),ServerConstants.LANGUAGE_ENGLISH_ID);
    	orderChangeStatusDAO.save(orderChangeStatusDTO);
    	
    	/*
        default account type
        */
    	MainSubscriptionDTO mainSubscriptionDTO = new MainSubscriptionDTO(orderPeriodDTO, Integer.valueOf(1));
    	AccountTypeDTO accountTypeDTO = new AccountTypeDTO();
    	accountTypeDTO.setCompany(company);
    	accountTypeDTO.setBillingCycle(mainSubscriptionDTO);
    	accountTypeDTO.setDescription("description", ServerConstants.LANGUAGE_ENGLISH_ID, getMessage("default.account.type.name"));
    	accountTypeDAO.save(accountTypeDTO);
    	
    	/*
        Payment methods
    	*/
    	PaymentMethodDTO paymentMethodDTO=paymentMethodDAO.findById(ServerConstants.PAYMENT_METHOD_CHEQUE).get();
    	paymentMethodDTO.getEntities().add(company);
    	
    	PaymentMethodDTO paymentMethodVisaDTO=paymentMethodDAO.findById(ServerConstants.PAYMENT_METHOD_VISA).get();
    	paymentMethodVisaDTO.getEntities().add(company);
    	
    	PaymentMethodDTO paymentMethodMasterCardDTO=paymentMethodDAO.findById(ServerConstants.PAYMENT_METHOD_MASTERCARD).get();
    	paymentMethodMasterCardDTO.getEntities().add(company);
    	
    	
    	/*
        Invoice delivery methods
        */
    	InvoiceDeliveryMethodDTO InvoiceDeliveryMethodDTOEmail=invoiceDeliveryMethodDAO.findById(ServerConstants.D_METHOD_EMAIL).get();
    	InvoiceDeliveryMethodDTOEmail.getEntities().add(company);
    	
    	InvoiceDeliveryMethodDTO InvoiceDeliveryMethodDTOPaper=invoiceDeliveryMethodDAO.findById(ServerConstants.D_METHOD_PAPER).get();
    	InvoiceDeliveryMethodDTOPaper.getEntities().add(company);
    	
    	InvoiceDeliveryMethodDTO InvoiceDeliveryMethodDTOEmailAndPaper=invoiceDeliveryMethodDAO.findById(ServerConstants.D_METHOD_EMAIL_AND_PAPER).get();
    	InvoiceDeliveryMethodDTOEmailAndPaper.getEntities().add(company);
    	
    	/*
        Reports
        */
    	reportDAO.findAll().forEach(report -> company.getReports().add(report));
    	
    	
    	/*
        Billing process configuration
        */
    	BillingProcessConfigurationDTO billingProcessConfigurationDTO = new BillingProcessConfigurationDTO();
    	billingProcessConfigurationDTO.setEntity(company);
    	billingProcessConfigurationDTO.setGenerateReport(1);
    	billingProcessConfigurationDTO.setRetries(0);
    	billingProcessConfigurationDTO.setDaysForRetry(1);
    	billingProcessConfigurationDTO.setDaysForReport(3);
    	billingProcessConfigurationDTO.setReviewStatus(1);
    	billingProcessConfigurationDTO.setDueDateUnitId(1);
    	billingProcessConfigurationDTO.setDueDateValue(1);
    	billingProcessConfigurationDTO.setOnlyRecurring(1);
    	billingProcessConfigurationDTO.setInvoiceDateProcess(0);
    	billingProcessConfigurationDTO.setMaximumPeriods(99);
    	billingProcessConfigurationDTO.setAutoPaymentApplication(1);
    	billingProcessConfigurationDTO.setProratingType(ProratingType.PRORATING_AUTO_OFF);
    	billingProcessConfigurationDTO.setPeriodUnit(new PeriodUnitDTO(ServerConstants.PERIOD_UNIT_MONTH));
    	billingProcessConfigurationDTO.setNextRunDate(new DateMidnight().plusMonths(1).toDate());
    	billingProcessConfigurationDAO.save(billingProcessConfigurationDTO);
    	
    	/*
        Pluggable tasks
        */
    	
    	// PaymentFakeTask
    	PluggableTaskDTO paymentPluggableTaskDTO = new PluggableTaskDTO(company.getId(), 1, new PluggableTaskTypeDTO(21));
    	pluggableTaskDAO.save(paymentPluggableTaskDTO);
    	pluggableTaskParameterDAO.save(new PluggableTaskParameterDTO("all", "yes", paymentPluggableTaskDTO));
    	
    	// BasicEmailNotificationTask
    	PluggableTaskDTO emailPluggableTaskDTO = new PluggableTaskDTO(company.getId(), 1, new PluggableTaskTypeDTO(9));
    	Set<PluggableTaskParameterDTO> pluggableTaskParameterDTOSet = new HashSet<>();
    	pluggableTaskParameterDTOSet.add(new PluggableTaskParameterDTO("smtp_server", ""));
    	pluggableTaskParameterDTOSet.add(new PluggableTaskParameterDTO("port", ""));
    	pluggableTaskParameterDTOSet.add(new PluggableTaskParameterDTO("ssl_auth", "false"));
    	pluggableTaskParameterDTOSet.add(new PluggableTaskParameterDTO("tls", "false"));
    	pluggableTaskParameterDTOSet.add(new PluggableTaskParameterDTO("username", ""));
    	pluggableTaskParameterDTOSet.add(new PluggableTaskParameterDTO("password", ""));
    	emailPluggableTaskDTO.setParameters(pluggableTaskParameterDTOSet);
    	emailPluggableTaskDTO = pluggableTaskDAO.save(emailPluggableTaskDTO);
    	    	
    	
    	updateParametersForTask(emailPluggableTaskDTO);
    	
    	// PaperInvoiceNotificationTask
    	PluggableTaskDTO notificationPluggableTaskDTO = new PluggableTaskDTO(company.getId(), 2, new PluggableTaskTypeDTO(12));
    	Set<PluggableTaskParameterDTO> notificationPluggableTaskParameterDTOSet = new HashSet<>();
    	notificationPluggableTaskParameterDTOSet.add(new PluggableTaskParameterDTO("design", "simple_invoice_b2b"));
    	notificationPluggableTaskDTO.setParameters(notificationPluggableTaskParameterDTOSet);
    	notificationPluggableTaskDTO = pluggableTaskDAO.save(notificationPluggableTaskDTO);
    	
    	updateParametersForTask(emailPluggableTaskDTO);
    	
    	//The IDs may not be hard coded. They may be different under different circumstances
    	pluggableTaskDAO.save(new PluggableTaskDTO(company.getId(), 1, pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.pluggableTask.BasicLineTotalTask")));
    	pluggableTaskDAO.save(new PluggableTaskDTO(company.getId(), 1, pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.pluggableTask.CalculateDueDate")));
    	pluggableTaskDAO.save(new PluggableTaskDTO(company.getId(), 2, pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.pluggableTask.BasicCompositionTask")));
    	pluggableTaskDAO.save(new PluggableTaskDTO(company.getId(), 1, pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.pluggableTask.BasicOrderFilterTask")));
    	pluggableTaskDAO.save(new PluggableTaskDTO(company.getId(), 1, pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.pluggableTask.BasicInvoiceFilterTask")));
    	
    	pluggableTaskDAO.save(new PluggableTaskDTO(company.getId(), 1, pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.pluggableTask.BasicOrderPeriodTask")));
    	pluggableTaskDAO.save(new PluggableTaskDTO(company.getId(), 1, pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.pluggableTask.BasicPaymentInfoTask")));
    	pluggableTaskDAO.save(new PluggableTaskDTO(company.getId(), 1, pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.payment.tasks.NoAsyncParameters")));
    	pluggableTaskDAO.save(new PluggableTaskDTO(company.getId(), 1, pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.item.tasks.BasicItemManager")));
    	pluggableTaskDAO.save(new PluggableTaskDTO(company.getId(), 1, pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.user.balance.DynamicBalanceManagerTask")));
    	
    	pluggableTaskDAO.save(new PluggableTaskDTO(company.getId(), 1, pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.billing.task.BillingProcessTask")));
    	pluggableTaskDAO.save(new PluggableTaskDTO(company.getId(), 2, pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.process.task.AgeingProcessTask")));
    	pluggableTaskDAO.save(new PluggableTaskDTO(company.getId(), 1, pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.process.task.BasicAgeingTask")));
    	pluggableTaskDAO.save(new PluggableTaskDTO(company.getId(), 1, pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.process.task.BasicBillingProcessFilterTask")));
    	pluggableTaskDAO.save(new PluggableTaskDTO(company.getId(), 2, pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.order.task.CreateOrderForResellerTask")));
    	pluggableTaskDAO.save(new PluggableTaskDTO(company.getId(), 3, pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.invoice.task.DeleteResellerOrderTask")));
    	pluggableTaskDAO.save(new PluggableTaskDTO(company.getId(), 7, pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.order.task.OrderChangeApplyOrderStatusTask")));
    	
    	/*	This is for testing the initial email sent when the company is created
        	Otherwise, admin users WILL NOT have passwords configured
        	And the SMTP server will need to be configured*/
    	PluggableTaskDTO pluggableTaskTestNotificationTaskDTO = new PluggableTaskDTO(company.getId(), 3, pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.notification.task.TestNotificationTask"));
    	Set<PluggableTaskParameterDTO> notificationParameter = new HashSet<>();
    	notificationParameter.add(new PluggableTaskParameterDTO("from", "admin@jbilling.com"));
    	pluggableTaskTestNotificationTaskDTO.setParameters(notificationParameter);
    	pluggableTaskDAO.save(pluggableTaskTestNotificationTaskDTO);
    	
    	updateParametersForTask(pluggableTaskTestNotificationTaskDTO);
        
    	/*
        Preferences
        */
        JbillingTable jBillingTable = jbillingTableDAO.findByName(ServerConstants.TABLE_ENTITY);

        preferenceDAO.save(new PreferenceDTO(jBillingTable, new PreferenceTypeDTO(ServerConstants.PREFERENCE_SHOW_NOTE_IN_INVOICE), company.getId(), "1"));
        preferenceDAO.save(new PreferenceDTO(jBillingTable, new PreferenceTypeDTO(ServerConstants.PREFERENCE_INVOICE_PREFIX), company.getId(), ""));
        preferenceDAO.save(new PreferenceDTO(jBillingTable, new PreferenceTypeDTO(ServerConstants.PREFERENCE_INVOICE_NUMBER), company.getId(), "1"));
        
    	/*
        Notification messages
        */
    	createNotificationMessage(ServerConstants.NOTIFICATION_TYPE_INVOICE_EMAIL, "signup.notification.email.title", "signup.notification.email", company, language);
        createNotificationMessage(ServerConstants.NOTIFICATION_TYPE_USER_REACTIVATED, "signup.notification.user.reactivated.title", "signup.notification.user.reactivated",company, language);
        createNotificationMessage(ServerConstants.NOTIFICATION_TYPE_USER_OVERDUE, "signup.notification.overdue.title", "signup.notification.overdue",company, language);
        createNotificationMessage(ServerConstants.NOTIFICATION_TYPE_ORDER_EXPIRE_1, "signup.notification.order.expire.1.title", "signup.notification.order.expire.1",company, language);
        createNotificationMessage(ServerConstants.NOTIFICATION_TYPE_PAYMENT_SUCCESS, "signup.notification.payment.success.title", "signup.notification.payment.success",company, language);
        createNotificationMessage(ServerConstants.NOTIFICATION_TYPE_PAYMENT_FAILED, "signup.notification.payment.failed.title", "signup.notification.payment.failed",company, language);
        createNotificationMessage(ServerConstants.NOTIFICATION_TYPE_INVOICE_REMINDER, "signup.notification.invoice.reminder.title", "signup.notification.invoice.reminder",company, language);
        createNotificationMessage(ServerConstants.NOTIFICATION_TYPE_CREDIT_CARD_UPDATE, "signup.notification.credit.card.update.title", "signup.notification.credit.card.update",company, language);
 		createNotificationMessage(ServerConstants.NOTIFICATION_TYPE_LOST_PASSWORD, "signup.notification.lost.password.update.title", "signup.notification.lost.password.update",company, language);
        createNotificationMessage(ServerConstants.NOTIFICATION_TYPE_INITIAL_CREDENTIALS, "signup.notification.initial.credentials.update.title", "signup.notification.initial.credentials.update",company, language);

        /*
         * Create payment method template meta fields for entity
         */
		// Credit card meta file
        PaymentMethodTemplateDTO paymentMethodTemplateCardDTO = paymentMethodTemplateDAO.findByName(ServerConstants.PAYMENT_CARD);
     	MetaField metaField = new MetaField(company, "cc.cardholder.name", EntityType.PAYMENT_METHOD_TEMPLATE,
     			DataType.STRING, false, true, 1, true, MetaFieldType.TITLE);
     	//metaFieldDAO.save(metaField);
     	paymentMethodTemplateCardDTO.addPaymentTemplateMetaField(metaField);
     	
     	ValidationRule validationRule = new ValidationRule(ValidationRuleType.PAYMENT_CARD, true);
     	validationRuleDAO.save(validationRule);
     	metaField = new MetaField(company, "cc.number", EntityType.PAYMENT_METHOD_TEMPLATE,
     			DataType.STRING, false, true, 2, true, MetaFieldType.PAYMENT_CARD_NUMBER);
     	metaField.setValidationRule(validationRule);
     	//metaFieldDAO.save(metaField);
     	paymentMethodTemplateCardDTO.addPaymentTemplateMetaField(metaField);
     	int valRulId = jbillingTableDAO.findByName(ServerConstants.TABLE_VALIDATION_RULE).getId();
     	InternationalDescriptionId internationalDescriptionId = new InternationalDescriptionId(valRulId, validationRule.getId(), 
     			"errorMessage", language.getId());
     	InternationalDescriptionDTO internationalDescriptionDTO = new InternationalDescriptionDTO(internationalDescriptionId,
     			getMessage("validation.payment.card.number.invalid"));
     	internationalDescriptionDAO.save(internationalDescriptionDTO);
     	
     	
     	SortedMap<String, String> attributes = new TreeMap<String, String>();
     	attributes.put("regularExpression", "(?:0[1-9]|1[0-2])/[0-9]{4}");
     	ValidationRule validationRuleRegex = new ValidationRule(ValidationRuleType.REGEX, true);
     	validationRuleRegex.setRuleAttributes(attributes);
     	validationRuleDAO.save(validationRuleRegex);
     	
     	metaField = new MetaField(company, "cc.expiry.date", EntityType.PAYMENT_METHOD_TEMPLATE,
     			DataType.STRING, false, true, 3, true, MetaFieldType.DATE);
     	metaField.setValidationRule(validationRuleRegex);
     	//metaFieldDAO.save(metaField);
     	paymentMethodTemplateCardDTO.addPaymentTemplateMetaField(metaField);
     	InternationalDescriptionId internationalDescriptionIdRegex = new InternationalDescriptionId(valRulId, validationRuleRegex.getId(), 
     			"errorMessage", language.getId());     	
     	InternationalDescriptionDTO internationalDescriptionExpiryDTO = new InternationalDescriptionDTO(internationalDescriptionIdRegex,
     			getMessage("validation.payment.card.expiry.date.invalid"));
     	internationalDescriptionDAO.save(internationalDescriptionExpiryDTO);
     	
     	
     	metaField = new MetaField(company, "cc.gateway.key", EntityType.PAYMENT_METHOD_TEMPLATE,
     			DataType.STRING, true, false, 4, true, MetaFieldType.GATEWAY_KEY);
     	//metaFieldDAO.save(metaField);
     	paymentMethodTemplateCardDTO.addPaymentTemplateMetaField(metaField);
     	
     	metaField = new MetaField(company, "cc.type", EntityType.PAYMENT_METHOD_TEMPLATE,
     			DataType.INTEGER, true, false, 5, true, MetaFieldType.CC_TYPE);
     	//metaFieldDAO.save(metaField);
     	paymentMethodTemplateCardDTO.addPaymentTemplateMetaField(metaField);
     	
     	
     	PaymentMethodTemplateDTO paymentMethodTemplateAchDTO = paymentMethodTemplateDAO.findByName(ServerConstants.ACH);
     	
     	// Ach template meta fields 
     	SortedMap<String, String> attributesRouting = new TreeMap<String, String>();
     	attributes.put("regularExpression", "(?<=\\\\s|^)\\\\d+(?=\\\\s|$)");
     	ValidationRule validationRuleRouting = new ValidationRule(ValidationRuleType.REGEX, true);
     	validationRuleRouting.setRuleAttributes(attributesRouting);
     	validationRuleDAO.save(validationRuleRouting);     	
     	metaField = new MetaField(company, "ach.routing.number", EntityType.PAYMENT_METHOD_TEMPLATE,
     			DataType.STRING, false, true, 1, true, MetaFieldType.BANK_ROUTING_NUMBER);
     	metaField.setValidationRule(validationRuleRouting);
     	//metaFieldDAO.save(metaField);     	
     	paymentMethodTemplateAchDTO.addPaymentTemplateMetaField(metaField);
     	InternationalDescriptionId internationalDescriptionIdRouting = new InternationalDescriptionId(valRulId, validationRuleRouting.getId(), 
     			"errorMessage", language.getId());
     	InternationalDescriptionDTO internationalDescriptionRoutingDTO = new InternationalDescriptionDTO(internationalDescriptionIdRouting,
     			getMessage("validation.ach.aba.routing.number.invalid"));
     	internationalDescriptionDAO.save(internationalDescriptionRoutingDTO);
     	
     	
     	metaField = new MetaField(company, "ach.customer.name", EntityType.PAYMENT_METHOD_TEMPLATE,
     			DataType.STRING, false, true, 2, true, MetaFieldType.TITLE);
     	//metaFieldDAO.save(metaField);
     	paymentMethodTemplateAchDTO.addPaymentTemplateMetaField(metaField);
     	
     	metaField = new MetaField(company, "ach.account.number", EntityType.PAYMENT_METHOD_TEMPLATE,
     			DataType.STRING, false, true, 3, true, MetaFieldType.BANK_ACCOUNT_NUMBER);
     	//metaFieldDAO.save(metaField);
     	paymentMethodTemplateAchDTO.addPaymentTemplateMetaField(metaField);
     	
     	metaField = new MetaField(company, "ach.bank.name", EntityType.PAYMENT_METHOD_TEMPLATE,
     			DataType.STRING, false, true, 4, true, MetaFieldType.BANK_NAME);
     	//metaFieldDAO.save(metaField);
     	paymentMethodTemplateAchDTO.addPaymentTemplateMetaField(metaField);
     	
     	metaField = new MetaField(company, "ach.account.type", EntityType.PAYMENT_METHOD_TEMPLATE,
     			DataType.ENUMERATION, false, true, 5, true, MetaFieldType.BANK_ACCOUNT_TYPE);
     	//metaFieldDAO.save(metaField);
     	paymentMethodTemplateAchDTO.addPaymentTemplateMetaField(metaField);
     	
     	
     	EnumerationDTO enumerationDTO = new EnumerationDTO();
     	enumerationDTO.setName("ach.account.type");
     	enumerationDTO.setEntity(company);
     	enumerationDAO.save(enumerationDTO);
     	
     	EnumerationValueDTO enumerationValueCheckingDTO = new EnumerationValueDTO();
     	enumerationValueCheckingDTO.setValue("CHECKING");
     	enumerationValueCheckingDTO.setEnumeration(enumerationDTO);
     	enumerationValueDAO.save(enumerationValueCheckingDTO);
     	
     	EnumerationValueDTO enumerationValueSavingsDTO = new EnumerationValueDTO();
     	enumerationValueSavingsDTO.setValue("SAVINGS");
     	enumerationValueSavingsDTO.setEnumeration(enumerationDTO);
     	enumerationValueDAO.save(enumerationValueSavingsDTO);
     	
     	metaField = new MetaField(company, "ach.gateway.key", EntityType.PAYMENT_METHOD_TEMPLATE,
     			DataType.STRING, true, false, 6, true, MetaFieldType.GATEWAY_KEY);
     	//metaFieldDAO.save(metaField);
     	paymentMethodTemplateAchDTO.addPaymentTemplateMetaField(metaField);

     	PaymentMethodTemplateDTO paymentMethodTemplateChequeDTO = paymentMethodTemplateDAO.findByName(ServerConstants.CHEQUE);

     	metaField = new MetaField(company, "cheque.bank.name", EntityType.PAYMENT_METHOD_TEMPLATE,
     			DataType.STRING, false, true, 1, true, MetaFieldType.BANK_NAME);
     	//metaFieldDAO.save(metaField);
     	paymentMethodTemplateChequeDTO.addPaymentTemplateMetaField(metaField);

     	metaField = new MetaField(company, "cheque.number", EntityType.PAYMENT_METHOD_TEMPLATE,
     			DataType.STRING, false, true, 2, true, MetaFieldType.CHEQUE_NUMBER);
     	//metaFieldDAO.save(metaField);
     	paymentMethodTemplateChequeDTO.addPaymentTemplateMetaField(metaField);

     	metaField = new MetaField(company, "cheque.date", EntityType.PAYMENT_METHOD_TEMPLATE,
     			DataType.STRING, false, true, 3, true, MetaFieldType.DATE);
     	//metaFieldDAO.save(metaField);
     	paymentMethodTemplateChequeDTO.addPaymentTemplateMetaField(metaField);

     	paymentMethodTemplateDAO.save(paymentMethodTemplateChequeDTO);
     	paymentMethodTemplateDAO.save(paymentMethodTemplateAchDTO);
     	paymentMethodTemplateDAO.save(paymentMethodTemplateCardDTO);
 
	}

	public void updateParametersForTask(PluggableTaskDTO taskDTO) {
        for (PluggableTaskParameterDTO parameter : taskDTO.getParameters()) {
            parameter.setTask(taskDTO);
            pluggableTaskParameterDAO.save(parameter);
        }
    }
	public void createNotificationMessage(Integer typeId, String titleCode, String bodyCode,CompanyDTO company, LanguageDTO language)
    {
    	NotificationMessageDTO notificationMessageDTO = new NotificationMessageDTO();
    	notificationMessageDTO.setEntity(company);
    	notificationMessageDTO.setLanguage(language);
    	notificationMessageDTO.setUseFlag((short) 1);
    	notificationMessageDTO.setNotificationMessageType(new NotificationMessageTypeDTO(typeId));
    	
    	List<NotificationMediumType> notificationMediumTypeList = new ArrayList<NotificationMediumType>(Arrays.asList(NotificationMediumType.values()));
    	notificationMessageDTO.setMediumTypes(notificationMediumTypeList);
    	notificationMessageDAO.save(notificationMessageDTO);
    	
    	NotificationMessageSectionDTO notificationMessageSectionDTO = new NotificationMessageSectionDTO();
    	notificationMessageSectionDTO.setNotificationMessage(notificationMessageDTO);
    	notificationMessageSectionDTO.setSection(1);

    	NotificationMessageLineDTO notificationMessageLineDTO = new NotificationMessageLineDTO();
    	notificationMessageLineDTO.setNotificationMessageSection(notificationMessageSectionDTO);
    	notificationMessageLineDTO.setContent(getMessage(titleCode));
    	notificationMessageSectionDTO.getNotificationMessageLines().add(notificationMessageLineDTO);
    	notificationMessageDTO.getNotificationMessageSections().add(notificationMessageSectionDTO);
    	notificationMessageSectionDAO.save(notificationMessageSectionDTO);
    	
    	NotificationMessageSectionDTO notificationMessageSectionBodyDTO = new NotificationMessageSectionDTO();
    	notificationMessageSectionBodyDTO.setNotificationMessage(notificationMessageDTO);
    	notificationMessageSectionBodyDTO.setSection(2);

    	NotificationMessageLineDTO notificationMessageLineBodyDTO = new NotificationMessageLineDTO();
    	notificationMessageLineBodyDTO.setNotificationMessageSection(notificationMessageSectionBodyDTO);
    	notificationMessageLineBodyDTO.setContent(getMessage(bodyCode));
    	notificationMessageSectionBodyDTO.getNotificationMessageLines().add(notificationMessageLineBodyDTO);
    	notificationMessageDTO.getNotificationMessageSections().add(notificationMessageSectionBodyDTO);
    	notificationMessageSectionDAO.save(notificationMessageSectionBodyDTO);
    }
	
	public String getMessage(String code) {
        return messageSource.getMessage(code, new Object[0], code, locale);
    }
	 
	    
}
