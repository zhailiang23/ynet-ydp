-- 为客户认领申请表添加审批意见和审批时间字段

ALTER TABLE crm_customer_claim_application
ADD COLUMN approval_comment VARCHAR(500) NULL COMMENT '审批意见' AFTER process_status,
ADD COLUMN approval_time DATETIME NULL COMMENT '审批时间' AFTER approval_comment;
