alter table "role" add column "role" varchar(15);
update "role" set "role"='ROLE_ADMIN' where role_type_id=2;
update "role" set "role"='ROLE_CLERK' where role_type_id=3;
update "role" set "role"='ROLE_CUSTOMER' where role_type_id=5;
CREATE SEQUENCE hibernate_sequence START 300 INCREMENT 1;
CREATE SEQUENCE event_logApi_GEN START 1 INCREMENT 1;
CREATE SEQUENCE event_log_GEN START 1 INCREMENT 1;
CREATE SEQUENCE contact_map_GEN START 1 INCREMENT 1;
CREATE SEQUENCE contact_type_GEN START 1 INCREMENT 1;
CREATE SEQUENCE contact_GEN START 1 INCREMENT 1;
CREATE SEQUENCE invoice_delivery_method_GEN START 1 INCREMENT 1;
CREATE SEQUENCE invoice_GEN START 1 INCREMENT 1;
CREATE SEQUENCE invoice_line_GEN START 1 INCREMENT 1;
CREATE SEQUENCE asset_assignment_GEN START 1 INCREMENT 1;
CREATE SEQUENCE asset_GEN START 1 INCREMENT 1;
CREATE SEQUENCE asset_reservation_GEN START 1 INCREMENT 1;
CREATE SEQUENCE asset_status_GEN START 1 INCREMENT 1;
CREATE SEQUENCE asset_transition_GEN START 1 INCREMENT 1;
CREATE SEQUENCE item_dependency_GEN START 1 INCREMENT 1;
CREATE SEQUENCE item_GEN START 200 INCREMENT 1;
CREATE SEQUENCE item_price_GEN START 200 INCREMENT 1;
CREATE SEQUENCE item_type_GEN START 200 INCREMENT 1;
CREATE SEQUENCE genericStatus_GEN START 50 INCREMENT 1;
CREATE SEQUENCE mediation_process_GEN START 1 INCREMENT 1;
CREATE SEQUENCE mediation_record_GEN START 1 INCREMENT 1;
CREATE SEQUENCE mediation_record_line_GEN START 1 INCREMENT 1;
CREATE SEQUENCE customer_account_info_type_timeline_GEN START 1 INCREMENT 1;
CREATE SEQUENCE meta_field_GEN START 200 INCREMENT 1;
CREATE SEQUENCE metafield_group_GEN START 100 INCREMENT 1;
CREATE SEQUENCE meta_field_value_GEN START 100 INCREMENT 1;
CREATE SEQUENCE validation_rule_GEN START 100 INCREMENT 1;
CREATE SEQUENCE notification_message_arch_GEN START 100 INCREMENT 1;
CREATE SEQUENCE notification_message_arch_line_GEN START 100 INCREMENT 1;
CREATE SEQUENCE notification_message_GEN START 100 INCREMENT 1;
CREATE SEQUENCE notification_message_line_GEN START 100 INCREMENT 1;
CREATE SEQUENCE notification_message_section_GEN START 100 INCREMENT 1;
CREATE SEQUENCE notification_message_type_GEN START 100 INCREMENT 1;
CREATE SEQUENCE order_change_GEN START 200 INCREMENT 1;
CREATE SEQUENCE order_change_type_GEN START 200 INCREMENT 1;
CREATE SEQUENCE purchase_order_GEN START 200 INCREMENT 1;
CREATE SEQUENCE order_line_GEN START 200 INCREMENT 1;
CREATE SEQUENCE order_period_GEN START 200 INCREMENT 1;
CREATE SEQUENCE order_process_GEN START 200 INCREMENT 1;
CREATE SEQUENCE order_status_GEN START 200 INCREMENT 1;
CREATE SEQUENCE payment_authorization_GEN START 100 INCREMENT 1;
CREATE SEQUENCE payment_GEN START 100 INCREMENT 1;
CREATE SEQUENCE payment_information_GEN START 100 INCREMENT 1;
CREATE SEQUENCE payment_instrument_info_GEN START 100 INCREMENT 1;
CREATE SEQUENCE payment_invoice_GEN START 100 INCREMENT 1;
CREATE SEQUENCE payment_method_template_GEN START 100 INCREMENT 1;
CREATE SEQUENCE payment_method_type_GEN START 100 INCREMENT 1;
CREATE SEQUENCE ageing_entity_step_GEN START 100 INCREMENT 1;
CREATE SEQUENCE billing_process_configuration_GEN START 101 INCREMENT 1;
CREATE SEQUENCE billing_process_GEN START 100 INCREMENT 1;
CREATE SEQUENCE process_run_GEN START 100 INCREMENT 1;
CREATE SEQUENCE paper_invoice_batch_GEN START 100 INCREMENT 1;
CREATE SEQUENCE process_run_total_GEN START 100 INCREMENT 1;
CREATE SEQUENCE process_run_total_pm_GEN START 100 INCREMENT 1;
CREATE SEQUENCE process_run_user_GEN START 100 INCREMENT 1;
CREATE SEQUENCE customer_GEN START 100 INCREMENT 1;
CREATE SEQUENCE customer_notes_GEN START 100 INCREMENT 1;
CREATE SEQUENCE role_GEN START 100 INCREMENT 1;
CREATE SEQUENCE base_user_GEN START 100 INCREMENT 1;
CREATE SEQUENCE reset_password_code_GEN START 100 INCREMENT 1;
CREATE SEQUENCE user_status_GEN START 100 INCREMENT 1;













































