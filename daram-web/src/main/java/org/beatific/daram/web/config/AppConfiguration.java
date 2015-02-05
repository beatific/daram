package org.beatific.daram.web.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DatabaseConfig.class)
public class AppConfiguration {

	@Inject DatabaseConfig dataConfig;
}
