package io.fabric8.maven.core.service.kubernetes;

import io.fabric8.maven.core.service.BuildService;
import io.fabric8.maven.docker.config.BuildImageConfiguration;
import io.fabric8.maven.docker.util.Logger;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class JibBuildServiceUtilTest {

    @Mocked
    Logger logger;

    @Mocked
    BuildImageConfiguration buildImageConfiguration;

    @Test
    public void TestGetJibBuildConfigurationSimple() {
        BuildService.BuildServiceConfig buildServiceConfig;

        new Expectations() {{

            buildImageConfiguration.getFrom();
            result = "base-image";

            buildImageConfiguration.getEnv();
            result = "my-env";

            List<String> ports = new ArrayList<>();
            ports.add("80");
            ports.add("443");
            buildImageConfiguration.getPorts();
            result = ports;

            buildImageConfiguration.getEntryPoint();
            result = null;

            buildServiceConfig.getDockerBuildContext().getRegistryConfig();
            result =
        }};

        String imageName = "sample";


        JibBuildServiceUtil.getJibBuildConfiguration(buildServiceConfig, buildImageConfiguration, imageName, logger);
    }

}
