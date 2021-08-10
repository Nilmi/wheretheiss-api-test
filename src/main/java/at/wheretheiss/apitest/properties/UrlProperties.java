package at.wheretheiss.apitest.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "url")
public class UrlProperties {

    private String base;
    private String positionsPath;
    private String tlesPath;

    public String getTlesPath() {
        return tlesPath;
    }

    public void setTlesPath(String tlesPath) {
        this.tlesPath = tlesPath;
    }

    public String getPositionsPath() {
        return positionsPath;
    }

    public void setPositionsPath(String positionsPath) {
        this.positionsPath = positionsPath;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }
}
