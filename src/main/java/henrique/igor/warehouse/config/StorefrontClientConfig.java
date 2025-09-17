package henrique.igor.warehouse.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class StorefrontClientConfig {

    RestClient storefrontClient(@Value("${storefront.base-path}") final String basePath) {
        return RestClient.create(basePath);
    }
}
