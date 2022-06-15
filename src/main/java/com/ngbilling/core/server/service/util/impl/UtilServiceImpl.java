package com.ngbilling.core.server.service.util.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ngbilling.core.common.exception.NoSuchElementFoundException;
import com.ngbilling.core.payload.request.metafield.DataType;
import com.ngbilling.core.payload.request.metafield.MetaFieldType;
import com.ngbilling.core.payload.request.order.ApplyToOrder;
import com.ngbilling.core.payload.request.order.OrderStatusFlag;
import com.ngbilling.core.payload.request.util.ComboReferenceInput;
import com.ngbilling.core.payload.request.util.NotificationMediumType;
import com.ngbilling.core.server.persistence.dao.audit.EventLogAPIDAO;
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
import com.ngbilling.core.server.persistence.dao.util.CountryDAO;
import com.ngbilling.core.server.persistence.dao.util.CurrencyDAO;
import com.ngbilling.core.server.persistence.dao.util.EnumerationDAO;
import com.ngbilling.core.server.persistence.dao.util.EnumerationValueDAO;
import com.ngbilling.core.server.persistence.dao.util.InternationalDescriptionDAO;
import com.ngbilling.core.server.persistence.dao.util.JbillingTableDAO;
import com.ngbilling.core.server.persistence.dao.util.LanguageDAO;
import com.ngbilling.core.server.persistence.dao.util.PreferenceDAO;
import com.ngbilling.core.server.persistence.dto.audit.EventLogAPIDTO;
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
import com.ngbilling.core.server.persistence.dto.user.AccountTypeDTO;
import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.persistence.dto.user.MainSubscriptionDTO;
import com.ngbilling.core.server.persistence.dto.user.UserDTO;
import com.ngbilling.core.server.persistence.dto.util.CountryDTO;
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
public class UtilServiceImpl implements UtilService {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
    private LanguageDAO languageDAO;

    @Autowired
    private CurrencyDAO currencyDAO;

    @Autowired
    private CountryDAO countryDAO;

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

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private EventLogAPIDAO eventLogAPIDAO;

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
    @Transactional
    public void initEntityDefault(UserDTO rootUser, Locale locale) {
        OrderStatusDTO invoiceOS = new OrderStatusDTO();
        invoiceOS.setOrderStatusFlag(OrderStatusFlag.INVOICE);
        invoiceOS.setEntity(rootUser.getCompany());
        invoiceOS = orderStatusDAO.save(invoiceOS);
        setDescription(ServerConstants.TABLE_ORDER_STATUS, invoiceOS.getId(), "description",rootUser.getLanguage().getId(),"Active");

        invoiceOS = new OrderStatusDTO();
        invoiceOS.setOrderStatusFlag(OrderStatusFlag.FINISHED);
        invoiceOS.setEntity(rootUser.getCompany());
        orderStatusDAO.save(invoiceOS);
        setDescription(ServerConstants.TABLE_ORDER_STATUS, invoiceOS.getId(), "description",rootUser.getLanguage().getId(),"Finished");
        invoiceOS = new OrderStatusDTO();
        invoiceOS.setOrderStatusFlag(OrderStatusFlag.NOT_INVOICE);
        invoiceOS.setEntity(rootUser.getCompany());
        orderStatusDAO.save(invoiceOS);
        setDescription(ServerConstants.TABLE_ORDER_STATUS, invoiceOS.getId(), "description",rootUser.getLanguage().getId(),"Suspended");
        invoiceOS = new OrderStatusDTO();
        invoiceOS.setOrderStatusFlag(OrderStatusFlag.SUSPENDED_AGEING);
        invoiceOS.setEntity(rootUser.getCompany());
        orderStatusDAO.save(invoiceOS);
        setDescription(ServerConstants.TABLE_ORDER_STATUS, invoiceOS.getId(), "description",rootUser.getLanguage().getId(),"Suspended ageing(auto)");

        OrderPeriodDTO orderPeriodDTO = new OrderPeriodDTO(rootUser.getCompany(), new PeriodUnitDTO(ServerConstants.PERIOD_UNIT_MONTH), 1);
        orderPeriodDTO = orderPeriodDAO.save(orderPeriodDTO);
        setDescription(ServerConstants.TABLE_ORDER_PERIOD, orderPeriodDTO.getId(), "description",rootUser.getLanguage().getId(),"Monthly");

        Integer orderChangeStatusId = orderChangeStatusDAO.getMaxStatusId();
        OrderChangeStatusDTO orderChangeStatusDTO = new OrderChangeStatusDTO();
        orderChangeStatusDTO.setCompany(rootUser.getCompany());
        orderChangeStatusDTO.setApplyToOrder(ApplyToOrder.YES);
        orderChangeStatusDTO.setDeleted(0);
        orderChangeStatusDTO.setOrder(1);
        orderChangeStatusDTO.setId(orderChangeStatusId + 1);
        orderChangeStatusDAO.save(orderChangeStatusDTO);
        setDescription(ServerConstants.TABLE_ORDER_CHANGE_STATUS, orderChangeStatusDTO.getId(), "description",rootUser.getLanguage().getId(),getMessage("order.change.status.default.apply", locale));

        MainSubscriptionDTO mainSubscriptionDTO = new MainSubscriptionDTO(orderPeriodDTO, Integer.valueOf(1));
        AccountTypeDTO accountTypeDTO = new AccountTypeDTO();
        accountTypeDTO.setCompany(rootUser.getCompany());
        accountTypeDTO.setBillingCycle(mainSubscriptionDTO);
        accountTypeDTO = accountTypeDAO.save(accountTypeDTO);
        setDescription(ServerConstants.TABLE_ACCOUNT_TYPE, accountTypeDTO.getId(), "description",rootUser.getLanguage().getId(),getMessage("default.account.type.name", locale));

        PaymentMethodDTO paymentMethodDTO = paymentMethodDAO.findById(ServerConstants.PAYMENT_METHOD_CHEQUE).get();
        paymentMethodDTO.getEntities().add(rootUser.getCompany());

        PaymentMethodDTO paymentMethodVisaDTO = paymentMethodDAO.findById(ServerConstants.PAYMENT_METHOD_VISA).get();
        paymentMethodVisaDTO.getEntities().add(rootUser.getCompany());

        PaymentMethodDTO paymentMethodMasterCardDTO = paymentMethodDAO.findById(ServerConstants.PAYMENT_METHOD_MASTERCARD).get();
        paymentMethodMasterCardDTO.getEntities().add(rootUser.getCompany());

        InvoiceDeliveryMethodDTO InvoiceDeliveryMethodDTOEmail = invoiceDeliveryMethodDAO.findById(ServerConstants.D_METHOD_EMAIL).get();
        InvoiceDeliveryMethodDTOEmail.getEntities().add(rootUser.getCompany());

        InvoiceDeliveryMethodDTO InvoiceDeliveryMethodDTOPaper = invoiceDeliveryMethodDAO.findById(ServerConstants.D_METHOD_PAPER).get();
        InvoiceDeliveryMethodDTOPaper.getEntities().add(rootUser.getCompany());

        InvoiceDeliveryMethodDTO InvoiceDeliveryMethodDTOEmailAndPaper = invoiceDeliveryMethodDAO.findById(ServerConstants.D_METHOD_EMAIL_AND_PAPER).get();
        InvoiceDeliveryMethodDTOEmailAndPaper.getEntities().add(rootUser.getCompany());
    	
    	/* Reports code is blocked and added later
    	List<ReportDTO> reportDTOList =reportDAO.findAll();
    	for (ReportDTO reportDTO : reportDTOList)
    	{
    		//company.getReports().add(reportDTO);

    	}*/

        BillingProcessConfigurationDTO billingProcessConfigurationDTO = new BillingProcessConfigurationDTO();
        billingProcessConfigurationDTO.setEntity(rootUser.getCompany());
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
        billingProcessConfigurationDTO.setNextRunDate(new DateTime().plusMonths(1).toDate());
        billingProcessConfigurationDAO.save(billingProcessConfigurationDTO);

        PluggableTaskDTO paymentPluggableTaskDTO = new PluggableTaskDTO();
        paymentPluggableTaskDTO.setEntityId(rootUser.getCompany().getId());
        paymentPluggableTaskDTO.setProcessingOrder(1);
        paymentPluggableTaskDTO.setType(new PluggableTaskTypeDTO(21));
        paymentPluggableTaskDTO = pluggableTaskDAO.save(paymentPluggableTaskDTO);
        PluggableTaskParameterDTO pluggableTaskParameterDTO = new PluggableTaskParameterDTO("all", "yes", paymentPluggableTaskDTO);
        pluggableTaskParameterDAO.save(pluggableTaskParameterDTO);

        PluggableTaskDTO emailPluggableTaskDTO = new PluggableTaskDTO();
        emailPluggableTaskDTO.setEntityId(rootUser.getCompany().getId());
        emailPluggableTaskDTO.setProcessingOrder(1);
        emailPluggableTaskDTO.setType(new PluggableTaskTypeDTO(9));
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

        PluggableTaskDTO notificationPluggableTaskDTO = new PluggableTaskDTO();
        notificationPluggableTaskDTO.setEntityId(rootUser.getCompany().getId());
        notificationPluggableTaskDTO.setProcessingOrder(2);
        notificationPluggableTaskDTO.setType(new PluggableTaskTypeDTO(12));
        Set<PluggableTaskParameterDTO> notificationPluggableTaskParameterDTOSet = new HashSet<>();
        notificationPluggableTaskParameterDTOSet.add(new PluggableTaskParameterDTO("design", "simple_invoice_b2b"));
        notificationPluggableTaskDTO.setParameters(notificationPluggableTaskParameterDTOSet);
        notificationPluggableTaskDTO = pluggableTaskDAO.save(notificationPluggableTaskDTO);

        updateParametersForTask(emailPluggableTaskDTO);


        PluggableTaskDTO pluggableTaskBasicLineTotalTaskDTO = new PluggableTaskDTO();
        pluggableTaskBasicLineTotalTaskDTO.setEntityId(rootUser.getCompany().getId());
        pluggableTaskBasicLineTotalTaskDTO.setType(pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.pluggableTask.BasicLineTotalTask"));
        pluggableTaskBasicLineTotalTaskDTO.setProcessingOrder(1);
        pluggableTaskDAO.save(pluggableTaskBasicLineTotalTaskDTO);


        PluggableTaskDTO pluggableTaskCalculateDueDateDTO = new PluggableTaskDTO();
        pluggableTaskCalculateDueDateDTO.setEntityId(rootUser.getCompany().getId());
        pluggableTaskCalculateDueDateDTO.setType(pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.pluggableTask.CalculateDueDate"));
        pluggableTaskCalculateDueDateDTO.setProcessingOrder(1);
        pluggableTaskDAO.save(pluggableTaskCalculateDueDateDTO);

        PluggableTaskDTO pluggableTaskBasicCompositionTaskDTO = new PluggableTaskDTO();
        pluggableTaskBasicCompositionTaskDTO.setEntityId(rootUser.getCompany().getId());
        pluggableTaskBasicCompositionTaskDTO.setType(pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.pluggableTask.BasicCompositionTask"));
        pluggableTaskBasicCompositionTaskDTO.setProcessingOrder(2);
        pluggableTaskDAO.save(pluggableTaskBasicCompositionTaskDTO);

        PluggableTaskDTO pluggableTaskBasicOrderFilterTaskDTO = new PluggableTaskDTO();
        pluggableTaskBasicOrderFilterTaskDTO.setEntityId(rootUser.getCompany().getId());
        pluggableTaskBasicOrderFilterTaskDTO.setType(pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.pluggableTask.BasicOrderFilterTask"));
        pluggableTaskBasicOrderFilterTaskDTO.setProcessingOrder(1);
        pluggableTaskDAO.save(pluggableTaskBasicOrderFilterTaskDTO);

        PluggableTaskDTO pluggableTaskBasicInvoiceFilterTaskDTO = new PluggableTaskDTO();
        pluggableTaskBasicInvoiceFilterTaskDTO.setEntityId(rootUser.getCompany().getId());
        pluggableTaskBasicInvoiceFilterTaskDTO.setType(pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.pluggableTask.BasicInvoiceFilterTask"));
        pluggableTaskBasicInvoiceFilterTaskDTO.setProcessingOrder(1);
        pluggableTaskDAO.save(pluggableTaskBasicInvoiceFilterTaskDTO);


        PluggableTaskDTO pluggableTaskBasicOrderPeriodTaskDTO = new PluggableTaskDTO();
        pluggableTaskBasicOrderPeriodTaskDTO.setEntityId(rootUser.getCompany().getId());
        pluggableTaskBasicOrderPeriodTaskDTO.setType(pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.pluggableTask.BasicOrderPeriodTask"));
        pluggableTaskBasicOrderPeriodTaskDTO.setProcessingOrder(1);
        pluggableTaskDAO.save(pluggableTaskBasicOrderPeriodTaskDTO);
        PluggableTaskDTO pluggableTaskBasicPaymentInfoTaskDTO = new PluggableTaskDTO();
        pluggableTaskBasicPaymentInfoTaskDTO.setEntityId(rootUser.getCompany().getId());
        pluggableTaskBasicPaymentInfoTaskDTO.setType(pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.pluggableTask.BasicPaymentInfoTask"));
        pluggableTaskBasicPaymentInfoTaskDTO.setProcessingOrder(1);
        pluggableTaskDAO.save(pluggableTaskBasicPaymentInfoTaskDTO);
        PluggableTaskDTO pluggableTaskNoAsyncParametersDTO = new PluggableTaskDTO();
        pluggableTaskNoAsyncParametersDTO.setEntityId(rootUser.getCompany().getId());
        pluggableTaskNoAsyncParametersDTO.setType(pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.payment.NoAsyncParameters"));
        pluggableTaskNoAsyncParametersDTO.setProcessingOrder(1);
        pluggableTaskDAO.save(pluggableTaskNoAsyncParametersDTO);
        PluggableTaskDTO pluggableTaskBasicItemManagerDTO = new PluggableTaskDTO();
        pluggableTaskBasicItemManagerDTO.setEntityId(rootUser.getCompany().getId());
        pluggableTaskBasicItemManagerDTO.setType(pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.item.BasicItemManager"));
        pluggableTaskBasicItemManagerDTO.setProcessingOrder(1);
        pluggableTaskDAO.save(pluggableTaskBasicItemManagerDTO);
        PluggableTaskDTO pluggableTaskDynamicBalanceManagerTaskDTO = new PluggableTaskDTO();
        pluggableTaskDynamicBalanceManagerTaskDTO.setEntityId(rootUser.getCompany().getId());
        pluggableTaskDynamicBalanceManagerTaskDTO.setType(pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.user.DynamicBalanceManagerTask"));
        pluggableTaskDynamicBalanceManagerTaskDTO.setProcessingOrder(1);
        pluggableTaskDAO.save(pluggableTaskDynamicBalanceManagerTaskDTO);
        PluggableTaskDTO pluggableTaskBillingProcessTaskDTO = new PluggableTaskDTO();
        pluggableTaskBillingProcessTaskDTO.setEntityId(rootUser.getCompany().getId());
        pluggableTaskBillingProcessTaskDTO.setType(pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.billing.BillingProcessTask"));
        pluggableTaskBillingProcessTaskDTO.setProcessingOrder(1);
        pluggableTaskDAO.save(pluggableTaskBillingProcessTaskDTO);
        PluggableTaskDTO pluggableTaskAgeingProcessTaskDTO = new PluggableTaskDTO();
        pluggableTaskAgeingProcessTaskDTO.setEntityId(rootUser.getCompany().getId());
        pluggableTaskAgeingProcessTaskDTO.setType(pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.process.AgeingProcessTask"));
        pluggableTaskAgeingProcessTaskDTO.setProcessingOrder(2);
        pluggableTaskDAO.save(pluggableTaskAgeingProcessTaskDTO);
        PluggableTaskDTO pluggableTaskBasicAgeingTaskDTO = new PluggableTaskDTO();
        pluggableTaskBasicAgeingTaskDTO.setEntityId(rootUser.getCompany().getId());
        pluggableTaskBasicAgeingTaskDTO.setType(pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.process.BasicAgeingTask"));
        pluggableTaskBasicAgeingTaskDTO.setProcessingOrder(1);
        pluggableTaskDAO.save(pluggableTaskBasicAgeingTaskDTO);
        PluggableTaskDTO pluggableTaskBasicBillingProcessFilterTaskDTO = new PluggableTaskDTO();
        pluggableTaskBasicBillingProcessFilterTaskDTO.setEntityId(rootUser.getCompany().getId());
        pluggableTaskBasicBillingProcessFilterTaskDTO.setType(pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.process.BasicBillingProcessFilterTask"));
        pluggableTaskBasicBillingProcessFilterTaskDTO.setProcessingOrder(2);
        pluggableTaskDAO.save(pluggableTaskBasicBillingProcessFilterTaskDTO);
        PluggableTaskDTO pluggableTaskCreateOrderForResellerTaskDTO = new PluggableTaskDTO();
        pluggableTaskCreateOrderForResellerTaskDTO.setEntityId(rootUser.getCompany().getId());
        pluggableTaskCreateOrderForResellerTaskDTO.setType(pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.order.CreateOrderForResellerTask"));
        pluggableTaskCreateOrderForResellerTaskDTO.setProcessingOrder(2);
        pluggableTaskDAO.save(pluggableTaskCreateOrderForResellerTaskDTO);
        PluggableTaskDTO pluggableTaskDeleteResellerOrderTaskDTO = new PluggableTaskDTO();
        pluggableTaskDeleteResellerOrderTaskDTO.setEntityId(rootUser.getCompany().getId());
        pluggableTaskDeleteResellerOrderTaskDTO.setType(pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.invoice.DeleteResellerOrderTask"));
        pluggableTaskDeleteResellerOrderTaskDTO.setProcessingOrder(3);
        pluggableTaskDAO.save(pluggableTaskDeleteResellerOrderTaskDTO);
        PluggableTaskDTO pluggableTaskOrderChangeApplyOrderStatusTaskDTO = new PluggableTaskDTO();
        pluggableTaskOrderChangeApplyOrderStatusTaskDTO.setEntityId(rootUser.getCompany().getId());
        pluggableTaskOrderChangeApplyOrderStatusTaskDTO.setType(pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.order.OrderChangeApplyOrderStatusTask"));
        pluggableTaskOrderChangeApplyOrderStatusTaskDTO.setProcessingOrder(7);
        pluggableTaskDAO.save(pluggableTaskOrderChangeApplyOrderStatusTaskDTO);

        PluggableTaskDTO pluggableTaskTestNotificationTaskDTO = new PluggableTaskDTO();
        pluggableTaskTestNotificationTaskDTO.setEntityId(rootUser.getCompany().getId());
        pluggableTaskTestNotificationTaskDTO.setType(pluggableTaskTypeDAO.findByClassName("com.ngbilling.core.server.persistence.dto.notification.TestNotificationTask"));
        pluggableTaskTestNotificationTaskDTO.setProcessingOrder(3);
        Set<PluggableTaskParameterDTO> notificationParameter = new HashSet<>();
        notificationParameter.add(new PluggableTaskParameterDTO("from", "admin@jbilling.com"));
        pluggableTaskTestNotificationTaskDTO.setParameters(notificationParameter);
        pluggableTaskDAO.save(pluggableTaskTestNotificationTaskDTO);


        updateParametersForTask(pluggableTaskTestNotificationTaskDTO);


        JbillingTable jBillingTable = jbillingTableDAO.findByName(ServerConstants.TABLE_ENTITY);


        PreferenceDTO preferenceDTO = new PreferenceDTO();
        preferenceDTO.setJbillingTable(jBillingTable);
        preferenceDTO.setForeignId(rootUser.getCompany().getId());
        preferenceDTO.setValue(1);
        PreferenceTypeDTO preferenceTypeDTO = new PreferenceTypeDTO();
        preferenceTypeDTO.setId(ServerConstants.PREFERENCE_SHOW_NOTE_IN_INVOICE);
        preferenceDTO.setPreferenceType(preferenceTypeDTO);
        preferenceDAO.save(preferenceDTO);

        PreferenceDTO preferenceInvoicePrefixDTO = new PreferenceDTO();
        preferenceInvoicePrefixDTO.setJbillingTable(jBillingTable);
        preferenceInvoicePrefixDTO.setForeignId(rootUser.getCompany().getId());
        preferenceInvoicePrefixDTO.setValue(0);
        PreferenceTypeDTO preferenceTypeInvoicePrefixDTO = new PreferenceTypeDTO();
        preferenceTypeInvoicePrefixDTO.setId(ServerConstants.PREFERENCE_INVOICE_PREFIX);
        preferenceInvoicePrefixDTO.setPreferenceType(preferenceTypeInvoicePrefixDTO);
        preferenceDAO.save(preferenceInvoicePrefixDTO);

        PreferenceDTO preferenceInvoiceNumberDTO = new PreferenceDTO();
        preferenceInvoiceNumberDTO.setJbillingTable(jBillingTable);
        preferenceInvoiceNumberDTO.setForeignId(rootUser.getCompany().getId());
        preferenceInvoiceNumberDTO.setValue(0);
        PreferenceTypeDTO preferenceTypeInvoiceNumberDTO = new PreferenceTypeDTO();
        preferenceTypeInvoiceNumberDTO.setId(ServerConstants.PREFERENCE_INVOICE_PREFIX);
        preferenceInvoiceNumberDTO.setPreferenceType(preferenceTypeInvoiceNumberDTO);
        preferenceDAO.save(preferenceInvoiceNumberDTO);

        createNotificationMessage(ServerConstants.NOTIFICATION_TYPE_INVOICE_EMAIL, "signup.notification.email.title", "signup.notification.email", rootUser.getCompany(), rootUser.getLanguage(), locale);
        createNotificationMessage(ServerConstants.NOTIFICATION_TYPE_USER_REACTIVATED, "signup.notification.user.reactivated.title", "signup.notification.user.reactivated", rootUser.getCompany(), rootUser.getLanguage(), locale);
        createNotificationMessage(ServerConstants.NOTIFICATION_TYPE_USER_OVERDUE, "signup.notification.overdue.title", "signup.notification.overdue", rootUser.getCompany(), rootUser.getLanguage(), locale);
        createNotificationMessage(ServerConstants.NOTIFICATION_TYPE_ORDER_EXPIRE_1, "signup.notification.order.expire.1.title", "signup.notification.order.expire.1", rootUser.getCompany(), rootUser.getLanguage(), locale);
        createNotificationMessage(ServerConstants.NOTIFICATION_TYPE_PAYMENT_SUCCESS, "signup.notification.payment.success.title", "signup.notification.payment.success", rootUser.getCompany(), rootUser.getLanguage(), locale);
        createNotificationMessage(ServerConstants.NOTIFICATION_TYPE_PAYMENT_FAILED, "signup.notification.payment.failed.title", "signup.notification.payment.failed", rootUser.getCompany(), rootUser.getLanguage(), locale);
        createNotificationMessage(ServerConstants.NOTIFICATION_TYPE_INVOICE_REMINDER, "signup.notification.invoice.reminder.title", "signup.notification.invoice.reminder", rootUser.getCompany(), rootUser.getLanguage(), locale);
        createNotificationMessage(ServerConstants.NOTIFICATION_TYPE_CREDIT_CARD_UPDATE, "signup.notification.credit.card.update.title", "signup.notification.credit.card.update", rootUser.getCompany(), rootUser.getLanguage(), locale);
        createNotificationMessage(ServerConstants.NOTIFICATION_TYPE_LOST_PASSWORD, "signup.notification.lost.password.update.title", "signup.notification.lost.password.update", rootUser.getCompany(), rootUser.getLanguage(), locale);
        createNotificationMessage(ServerConstants.NOTIFICATION_TYPE_INITIAL_CREDENTIALS, "signup.notification.initial.credentials.update.title", "signup.notification.initial.credentials.update", rootUser.getCompany(), rootUser.getLanguage(), locale);

        PaymentMethodTemplateDTO paymentMethodTemplateCardDTO = paymentMethodTemplateDAO.findByName(ServerConstants.PAYMENT_CARD);
        paymentMethodTemplateCardDTO.addPaymentTemplateMetaField(createMetaField(MetaFieldType.TITLE,"cc.cardholder.name",1,rootUser.getCompany(),DataType.STRING,false,true,true));

        MetaField metaField = createMetaField(MetaFieldType.PAYMENT_CARD_NUMBER,"cc.number",2,rootUser.getCompany(),DataType.STRING,false,true,true);
        ValidationRule validationRule = new ValidationRule();
        validationRule.setRuleType(ValidationRuleType.PAYMENT_CARD);
        validationRule.setEnabled(true);
        validationRule = validationRuleDAO.save(validationRule);
        metaField.setValidationRule(validationRule);
        paymentMethodTemplateCardDTO.addPaymentTemplateMetaField(metaField);


        int valRulId = jbillingTableDAO.findByName(ServerConstants.TABLE_VALIDATION_RULE).getId();
        InternationalDescriptionId internationalDescriptionId = new InternationalDescriptionId(valRulId, validationRule.getId(),
                "errorMessage", rootUser.getLanguage().getId());
        //internationalDescriptionDAO.save(internationalDescriptionId);
        InternationalDescriptionDTO internationalDescriptionDTO = new InternationalDescriptionDTO(internationalDescriptionId,
                getMessage("validation.payment.card.number.invalid", locale));
        internationalDescriptionDAO.save(internationalDescriptionDTO);


        metaField = createMetaField(MetaFieldType.DATE,"cc.expiry.date",3,rootUser.getCompany(),DataType.STRING,false,true,true);
//        SortedMap<String, String> attributes = new TreeMap<String, String>();
//        attributes.put("regularExpression", "(?:0[1-9]|1[0-2])/[0-9]{4}");
//        ValidationRule validationRuleRegex = new ValidationRule();
//        validationRuleRegex.setRuleType(ValidationRuleType.REGEX);
//        validationRuleRegex.setEnabled(true);
//        validationRuleRegex.setRuleAttributes(attributes);
//        validationRuleRegex = validationRuleDAO.save(validationRuleRegex);
//        metaField.setValidationRule(validationRuleRegex);
        paymentMethodTemplateCardDTO.addPaymentTemplateMetaField(metaField);
//        InternationalDescriptionId internationalDescriptionIdRegex = new InternationalDescriptionId(valRulId, validationRuleRegex.getId(),
//                "errorMessage", rootUser.getLanguage().getId());
//
//        InternationalDescriptionDTO internationalDescriptionExpiryDTO = new InternationalDescriptionDTO(internationalDescriptionIdRegex,
//                getMessage("validation.payment.card.expiry.date.invalid", locale));
//        internationalDescriptionDAO.save(internationalDescriptionExpiryDTO);
        paymentMethodTemplateCardDTO.addPaymentTemplateMetaField(createMetaField(MetaFieldType.GATEWAY_KEY,"cc.gateway.key",4,rootUser.getCompany(),DataType.STRING,false,true,true));
        paymentMethodTemplateCardDTO.addPaymentTemplateMetaField(createMetaField(MetaFieldType.CC_TYPE,"cc.type",5,rootUser.getCompany(),DataType.INTEGER,false,true,true));


        PaymentMethodTemplateDTO paymentMethodTemplateAchDTO = paymentMethodTemplateDAO.findByName(ServerConstants.ACH);
        metaField = createMetaField(MetaFieldType.BANK_ROUTING_NUMBER,"ach.routing.number",1,rootUser.getCompany(),DataType.STRING,false,true,true);
//        SortedMap<String, String> attributesRouting = new TreeMap<String, String>();
//        attributes.put("regularExpression", "(?<=\\\\s|^)\\\\d+(?=\\\\s|$)");
//        ValidationRule validationRuleRouting = new ValidationRule();
//        validationRuleRouting.setRuleType(ValidationRuleType.REGEX);
//        validationRuleRouting.setEnabled(true);
//        validationRuleRouting.setRuleAttributes(attributesRouting);
//        validationRuleRouting = validationRuleDAO.save(validationRuleRouting);
//        metaField.setValidationRule(validationRuleRouting);
        paymentMethodTemplateAchDTO.addPaymentTemplateMetaField(metaField);
//        InternationalDescriptionId internationalDescriptionIdRouting = new InternationalDescriptionId(valRulId, validationRuleRouting.getId(),
//                "errorMessage", rootUser.getLanguage().getId());
//        InternationalDescriptionDTO internationalDescriptionRoutingDTO = new InternationalDescriptionDTO(internationalDescriptionIdRouting,
//                getMessage("validation.ach.aba.routing.number.invalid", locale));
//        internationalDescriptionDAO.save(internationalDescriptionRoutingDTO);

        paymentMethodTemplateAchDTO.addPaymentTemplateMetaField(createMetaField(MetaFieldType.TITLE,"ach.customer.name",2,rootUser.getCompany(),DataType.STRING,false,true,true));
        paymentMethodTemplateAchDTO.addPaymentTemplateMetaField(createMetaField(MetaFieldType.BANK_ACCOUNT_NUMBER,"ach.account.number",3,rootUser.getCompany(),DataType.STRING,false,true,true));
        paymentMethodTemplateAchDTO.addPaymentTemplateMetaField(createMetaField(MetaFieldType.BANK_NAME,"ach.bank.name",4,rootUser.getCompany(),DataType.STRING,false,true,true));
        paymentMethodTemplateAchDTO.addPaymentTemplateMetaField(createMetaField(MetaFieldType.BANK_ACCOUNT_TYPE,"ach.account.type",5,rootUser.getCompany(),DataType.ENUMERATION,false,true,true));


        EnumerationDTO enumerationDTO = new EnumerationDTO();
        enumerationDTO.setName("ach.account.type");
        enumerationDTO.setEntity(rootUser.getCompany());
        enumerationDTO = enumerationDAO.save(enumerationDTO);
        EnumerationValueDTO enumerationValueCheckingDTO = new EnumerationValueDTO();
        enumerationValueCheckingDTO.setValue("CHECKING");
        enumerationValueCheckingDTO.setEnumeration(enumerationDTO);
        enumerationValueDAO.save(enumerationValueCheckingDTO);
        EnumerationValueDTO enumerationValueSavingsDTO = new EnumerationValueDTO();
        enumerationValueSavingsDTO.setValue("SAVINGS");
        enumerationValueSavingsDTO.setEnumeration(enumerationDTO);
        enumerationValueDAO.save(enumerationValueSavingsDTO);

        paymentMethodTemplateAchDTO.addPaymentTemplateMetaField(createMetaField(MetaFieldType.GATEWAY_KEY,"ach.gateway.key",6,rootUser.getCompany(),DataType.STRING,true,false,true));

        PaymentMethodTemplateDTO paymentMethodTemplateChequeDTO = paymentMethodTemplateDAO.findByName(ServerConstants.CHEQUE);
        paymentMethodTemplateChequeDTO.addPaymentTemplateMetaField(createMetaField(MetaFieldType.BANK_NAME,"cheque.bank.name",1,rootUser.getCompany(),DataType.STRING,false,true,true));
        paymentMethodTemplateChequeDTO.addPaymentTemplateMetaField(createMetaField(MetaFieldType.CHEQUE_NUMBER,"cheque.number",2,rootUser.getCompany(),DataType.STRING,false,true,true));
        paymentMethodTemplateChequeDTO.addPaymentTemplateMetaField(createMetaField(MetaFieldType.DATE,"cheque.date",3,rootUser.getCompany(),DataType.DATE,false,true,true));

        paymentMethodTemplateDAO.save(paymentMethodTemplateChequeDTO);
        paymentMethodTemplateDAO.save(paymentMethodTemplateAchDTO);
        paymentMethodTemplateDAO.save(paymentMethodTemplateCardDTO);

    }

    private MetaField createMetaField(MetaFieldType metaFieldType,String name,Integer order,CompanyDTO companyDTO,DataType dataType ,boolean disabled,boolean mandatory,boolean primary){
        MetaField metaField = new MetaField();
        metaField.setFieldUsage(metaFieldType);
        metaField.setName(name);
        metaField.setDisplayOrder(order);
        metaField.setEntity(companyDTO);
        metaField.setEntityType(EntityType.PAYMENT_METHOD_TEMPLATE);
        metaField.setDataType(dataType);
        metaField.setDisabled(disabled);
        metaField.setMandatory(mandatory);
        metaField.setPrimary(primary);
        metaFieldDAO.save(metaField);
        return metaField;
    }
    @Override
    public EventLogAPIDTO createEventLogAPI(EventLogAPIDTO eventLogAPIDTO) {
        EventLogAPIDTO eventAPIDTO  = eventLogAPIDAO.save(eventLogAPIDTO);
        return eventAPIDTO;
    }

    public void updateParametersForTask(PluggableTaskDTO taskDTO) {
        for (PluggableTaskParameterDTO parameter : taskDTO.getParameters()) {
            parameter.setTask(taskDTO);
            pluggableTaskParameterDAO.save(parameter);
        }
    }

    public void createNotificationMessage(Integer typeId, String titleCode, String bodyCode, CompanyDTO company, LanguageDTO language, Locale locale) {
        NotificationMessageDTO notificationMessageDTO = new NotificationMessageDTO();
        notificationMessageDTO.setEntity(company);
        notificationMessageDTO.setLanguage(language);
        notificationMessageDTO.setUseFlag((short) 1);
        NotificationMessageTypeDTO notificationMessageTypeDTO = new NotificationMessageTypeDTO();
        notificationMessageDTO.setNotificationMessageType(new NotificationMessageTypeDTO(typeId));

        List<NotificationMediumType> notificationMediumTypeList = new ArrayList<NotificationMediumType>(Arrays.asList(NotificationMediumType.values()));
        notificationMessageDTO.setMediumTypes(notificationMediumTypeList);
        notificationMessageDTO = notificationMessageDAO.save(notificationMessageDTO);

        NotificationMessageSectionDTO notificationMessageSectionDTO = new NotificationMessageSectionDTO();
        notificationMessageSectionDTO.setNotificationMessage(notificationMessageDTO);
        notificationMessageSectionDTO.setSection(1);

        NotificationMessageLineDTO notificationMessageLineDTO = new NotificationMessageLineDTO();
        notificationMessageLineDTO.setNotificationMessageSection(notificationMessageSectionDTO);
        notificationMessageLineDTO.setContent(getMessage(titleCode, locale));
        notificationMessageSectionDTO.getNotificationMessageLines().add(notificationMessageLineDTO);
        notificationMessageDTO.getNotificationMessageSections().add(notificationMessageSectionDTO);
        notificationMessageSectionDAO.save(notificationMessageSectionDTO);

        NotificationMessageSectionDTO notificationMessageSectionBodyDTO = new NotificationMessageSectionDTO();
        notificationMessageSectionBodyDTO.setNotificationMessage(notificationMessageDTO);
        notificationMessageSectionBodyDTO.setSection(2);

        NotificationMessageLineDTO notificationMessageLineBodyDTO = new NotificationMessageLineDTO();
        notificationMessageLineBodyDTO.setNotificationMessageSection(notificationMessageSectionBodyDTO);
        notificationMessageLineBodyDTO.setContent(getMessage(bodyCode, locale));
        notificationMessageSectionBodyDTO.getNotificationMessageLines().add(notificationMessageLineBodyDTO);
        notificationMessageDTO.getNotificationMessageSections().add(notificationMessageSectionBodyDTO);
        notificationMessageSectionDAO.save(notificationMessageSectionBodyDTO);
    }

    public String getMessage(String code, Locale locale) {
        return messageSource.getMessage(code, new Object[0], code, locale);
    }

    @Override
    public List<CountryDTO> findAllCountries(Integer languageId) {
       List<CountryDTO> countryList = countryDAO.findAll();
       if(countryList ==null||countryList.size()==0){
                throw new NoSuchElementFoundException(getMessage(ServerConstants.TABLE_COUNTRY, Locale.ENGLISH));
       }else{
           for(CountryDTO countryDTO:countryList){
               countryDTO.setDescription(getDescription(ServerConstants.TABLE_COUNTRY, countryDTO.getId(), "description",languageId));
           }
       }
        return countryList;
    }

    @Override
    public List<ComboReferenceInput> findAllCurrencies(Integer languageId) {
        List<Object[]> currencyList = currencyDAO.findAllCurrencies();
        List<ComboReferenceInput> comboCurrencyList = new ArrayList<>();
        if(currencyList ==null||currencyList.size()==0){
            throw new NoSuchElementFoundException(getMessage(ServerConstants.TABLE_CURRENCY, Locale.ENGLISH));
        }else{
            for(Object[] currencyDTO:currencyList){
                ComboReferenceInput comboReferenceInput =  new ComboReferenceInput();
                comboReferenceInput.setId((Integer) currencyDTO[0]);
                comboReferenceInput.setCode((String) currencyDTO[1]);
                comboReferenceInput.setDescription(getDescription(ServerConstants.TABLE_CURRENCY, Integer.valueOf(""+currencyDTO[0]), "description",languageId));
                comboCurrencyList.add(comboReferenceInput);
            }
        }
        return comboCurrencyList;
    }

    @Override
    public List<ComboReferenceInput> findAllLanguages(Integer languageId) {
        List<ComboReferenceInput> languageList = languageDAO.findAllLanguages();
       // List<ComboReferenceInput> comboLanguageList = new ArrayList<>();
        if(languageList ==null||languageList.size()==0){
            throw new NoSuchElementFoundException(getMessage(ServerConstants.TABLE_LANGUAGE, Locale.ENGLISH));
        }
        return languageList;
    }

    @Override
    public String getDescription(String tableName, Integer foreignId, String label,Integer languageId) {
        JbillingTable table = jbillingTableDAO.findByName(tableName);
        InternationalDescriptionDTO internationalDescriptionDTO  = internationalDescriptionDAO.findIt(table.getId(), foreignId, label, languageId==null?ServerConstants.LANGUAGE_ENGLISH_ID:languageId);
        return internationalDescriptionDTO.getContent();
    }

    @Override
    public void setDescription(String tableName, Integer foreignId, String label,Integer languageId,String content) {
        JbillingTable table = jbillingTableDAO.findByName(tableName);
        InternationalDescriptionId id = new InternationalDescriptionId(table.getId(), foreignId, label, languageId);
        InternationalDescriptionDTO desc = new InternationalDescriptionDTO(id, content);
        internationalDescriptionDAO.save(desc);
    }
    
    @Override
    public boolean isAllowSignup(Integer languageId) {
    	return jdbcTemplate.queryForObject("SELECT jb_allow_signup FROM jb_host_master", Boolean.class);
    }

    @Override
    public List<ComboReferenceInput> findEntityAccountTypes(Integer entityId) {
        List<Object[]> accountTypeList = accountTypeDAO.findEntityAccountTypes(entityId);
        List<ComboReferenceInput> comboAccountTypeList = new ArrayList<>();
        if(accountTypeList ==null||accountTypeList.size()==0){
            throw new NoSuchElementFoundException(getMessage(ServerConstants.TABLE_ACCOUNT_TYPE, Locale.ENGLISH));
        }else{
            for(Object[] accountTypeDTO:accountTypeList){
                ComboReferenceInput comboReferenceInput =  new ComboReferenceInput();
                comboReferenceInput.setId((Integer) accountTypeDTO[0]);
                comboReferenceInput.setDescription(getDescription(ServerConstants.TABLE_ACCOUNT_TYPE, Integer.valueOf(""+accountTypeDTO[0]), "description",ServerConstants.LANGUAGE_ENGLISH_ID));
                comboAccountTypeList.add(comboReferenceInput);
            }
        }
        return comboAccountTypeList;
    }
}
