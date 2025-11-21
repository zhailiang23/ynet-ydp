package cn.iocoder.yudao.module.aicrm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 智能陪练配置属性
 *
 * @author 芋道源码
 */
@Component
@ConfigurationProperties(prefix = "yudao.practice")
@Data
public class PracticeProperties {

    /**
     * 培训人数统计配置
     */
    private TrainingCount trainingCount = new TrainingCount();

    @Data
    public static class TrainingCount {
        /**
         * 是否允许同一用户多次培训累加培训人数
         * 默认为 true，允许累加
         */
        private Boolean allowDuplicate = true;
    }
}
