package io.fabric8.maven.core.service.kubernetes;

import io.fabric8.maven.docker.config.BuildImageConfiguration;
import io.fabric8.maven.docker.config.ImageConfiguration;
import io.fabric8.maven.docker.service.BuildService;
import io.fabric8.maven.docker.util.ImageName;
import io.fabric8.maven.docker.util.Logger;
import mockit.Mocked;
import mockit.Tested;
import mockit.VerificationsInOrder;
import org.junit.Test;

public class JibBuildServiceTest {

    @Tested
    private JibBuildService jibBuildService;

    @Mocked
    private JibBuildServiceUtil jibBuildServiceUtil;

    @Mocked
    private Logger logger;

    @Test
    public void testSuccessfulBuild() throws Exception {

        //Preparation Code For Testing The Class
        final BuildService.BuildContext context = new BuildService.BuildContext.Builder()
                .build();

        final io.fabric8.maven.core.service.BuildService.BuildServiceConfig config = new io.fabric8.maven.core.service.BuildService.BuildServiceConfig.Builder()
                .dockerBuildContext(context)
                .build();

        final String imageName = "image-name";
        final ImageConfiguration image = new ImageConfiguration.Builder()
                .name(imageName)
                .buildConfig(new BuildImageConfiguration.Builder()
                        .from("from")
                        .build()
                ).build();

        String fullName = new ImageName(image.getName(), null).getFullName();

        //Code To Be Tested
         jibBuildService = new JibBuildService(config, logger);
        jibBuildService.build(image);

        new VerificationsInOrder() {{ // Verification Of The Executed Code To Be Tested
           JibBuildConfiguration jibBuildConfiguration =  jibBuildServiceUtil.getJibBuildConfiguration(config, image, fullName, logger);
            jibBuildServiceUtil.buildImage(jibBuildConfiguration, logger);
        }};
    }
}
