package io.fabric8.maven.core.service.kubernetes;

import io.fabric8.maven.docker.config.BuildImageConfiguration;
import io.fabric8.maven.docker.config.ImageConfiguration;
import io.fabric8.maven.docker.service.BuildService;
import io.fabric8.maven.docker.util.ImageName;
import io.fabric8.maven.docker.util.Logger;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import mockit.VerificationsInOrder;
import org.junit.Test;
import io.fabric8.maven.core.service.BuildService.BuildServiceConfig;

import java.util.ArrayList;
import java.util.HashMap;

public class JibBuildServiceTest {

    @Tested
    private JibBuildService jibBuildService;

    @Mocked
    BuildServiceConfig config;

    @Mocked
    ImageConfiguration imageConfiguration;

    @Mocked
    private Logger logger;

    @Test
    public void testSuccessfulBuild() throws Exception {

        //Preparation Code For Testing The Class
        final BuildService.BuildContext context = new BuildService.BuildContext.Builder()
                .build();


        /**

         *                                                                                                         .ports(buildImageConfiguration.getPorts())
         *                                                                                                         .entrypoint(buildImageConfiguration.getEntryPoint())
         */
        final String imageName = "image-name";
        String fullName = new ImageName(imageName, null).getFullName();


        BuildImageConfiguration buildImageConfiguration = new BuildImageConfiguration.Builder()
                        .from("fromImage")
                .env(new HashMap<String, String>() {{
                    put("foo", "bar");
                    put("john", "doe");
                }})
                .ports(new ArrayList<String>() {{
                    add("80");
                    add("443");
                }})
                
                        .build();



        new Expectations() {{
            imageConfiguration.getBuildConfiguration();
            result = buildImageConfiguration;

            imageConfiguration.getName();
            result = fullName;
        }};

        //Code To Be Tested
        jibBuildService = new JibBuildService(config, logger);
        jibBuildService.build(imageConfiguration);

        new VerificationsInOrder() {{ // Verification Of The Executed Code To Be Tested
//           JibBuildConfiguration jibBuildConfiguration =  jibBuildServiceUtil.getJibBuildConfiguration(config, image, fullName, logger);
//            jibBuildServiceUtil.buildImage(jibBuildConfiguration, logger);
        }};
    }
}
