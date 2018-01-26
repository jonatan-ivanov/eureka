package eureka.config;

import com.netflix.appinfo.AmazonInfo;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Jonatan Ivanov
 */
@Configuration
public class EurekaConfig {
    @Bean
    @Profile("!default")
    public EurekaInstanceConfigBean eurekaInstanceConfig(InetUtils inetUtils) {
        EurekaInstanceConfigBean instanceConfigBean = new EurekaInstanceConfigBean(inetUtils);
        AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
        instanceConfigBean.setDataCenterInfo(info);
        instanceConfigBean.setHostname(info.get(AmazonInfo.MetaDataKey.publicHostname));
        instanceConfigBean.setIpAddress(info.get(AmazonInfo.MetaDataKey.publicIpv4));

        return instanceConfigBean;
    }
}
