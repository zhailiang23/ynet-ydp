package com.ynet.iplatform.module.task.service.scoring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 客户数据加载器
 * 通过反射动态调用 CRM 模块的 CustomerMapper，避免循环依赖
 *
 * @author ynet
 */
@Component
@Slf4j
public class CustomerDataLoader {

    @Resource
    private ApplicationContext applicationContext;

    /**
     * 批量加载客户评分数据
     *
     * @param customerIds 客户 ID 集合
     * @return 客户数据映射（key: 客户ID, value: 客户评分数据）
     */
    public Map<Long, CustomerScoreData> loadCustomerData(Collection<Long> customerIds) {
        Map<Long, CustomerScoreData> customerDataMap = new HashMap<>();

        if (customerIds == null || customerIds.isEmpty()) {
            return customerDataMap;
        }

        try {
            // 动态获取 CRM 模块的 CustomerMapper Bean
            Object customerMapper = applicationContext.getBean("customerMapper");
            if (customerMapper == null) {
                log.warn("[loadCustomerData] CustomerMapper Bean 不存在，CRM 模块可能未启用");
                return customerDataMap;
            }

            // 通过反射调用 selectBatchIds 方法
            Method selectBatchIds = customerMapper.getClass().getMethod("selectBatchIds", Collection.class);
            @SuppressWarnings("unchecked")
            List<Object> customers = (List<Object>) selectBatchIds.invoke(customerMapper, customerIds);

            if (customers == null || customers.isEmpty()) {
                log.debug("[loadCustomerData] 未查询到客户数据");
                return customerDataMap;
            }

            log.info("[loadCustomerData] 查询到 {} 条客户数据", customers.size());

            // 通过反射提取客户数据
            for (Object customer : customers) {
                try {
                    Long customerId = getFieldValue(customer, "getId", Long.class);
                    BigDecimal creditScore = getFieldValue(customer, "getCreditScore", BigDecimal.class);
                    String customerLevel = getFieldValue(customer, "getCustomerLevel", String.class);
                    String creditLevel = getFieldValue(customer, "getCreditLevel", String.class);
                    LocalDateTime createTime = getFieldValue(customer, "getCreateTime", LocalDateTime.class);

                    CustomerScoreData scoreData = CustomerScoreData.builder()
                            .customerId(customerId)
                            .creditScore(creditScore)
                            .customerLevel(customerLevel)
                            .creditLevel(creditLevel)
                            .createTime(createTime)
                            .build();

                    customerDataMap.put(customerId, scoreData);
                } catch (Exception e) {
                    log.error("[loadCustomerData] 提取客户数据失败", e);
                }
            }

            log.info("[loadCustomerData] 成功加载 {} 条客户评分数据", customerDataMap.size());

        } catch (NoSuchMethodException e) {
            log.warn("[loadCustomerData] CustomerMapper.selectBatchIds 方法不存在: {}", e.getMessage());
        } catch (Exception e) {
            log.error("[loadCustomerData] 加载客户数据失败", e);
        }

        return customerDataMap;
    }

    /**
     * 通过反射获取字段值
     */
    @SuppressWarnings("unchecked")
    private <T> T getFieldValue(Object obj, String methodName, Class<T> returnType) throws Exception {
        Method method = obj.getClass().getMethod(methodName);
        Object value = method.invoke(obj);
        return (T) value;
    }
}
