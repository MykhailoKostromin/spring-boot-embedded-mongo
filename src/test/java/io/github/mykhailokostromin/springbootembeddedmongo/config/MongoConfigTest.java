package io.github.mykhailokostromin.springbootembeddedmongo.config;

import de.flapdoodle.embed.mongo.Command;
import de.flapdoodle.embed.mongo.config.DownloadConfigBuilder;
import de.flapdoodle.embed.mongo.config.ExtractedArtifactStoreBuilder;
import de.flapdoodle.embed.mongo.config.RuntimeConfigBuilder;
import de.flapdoodle.embed.process.builder.AbstractBuilder;
import de.flapdoodle.embed.process.config.IRuntimeConfig;
import de.flapdoodle.embed.process.config.store.IDownloadConfig;
import de.flapdoodle.embed.process.store.IArtifactStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class MongoConfigTest {

    private static final String MAVEN_TEST_RESOURCES = "src/test/resources";
    private static final String MONGO_DATA = "/mongodb/data/";

    // this bean is needed for test data population
    @Bean
    public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator() throws Exception {
        Resource[] resources = Files.walk(Paths.get(MAVEN_TEST_RESOURCES + MONGO_DATA))
                .filter(Files::isRegularFile)
                .map(path -> new ClassPathResource(MONGO_DATA + path.getFileName().toString()))
                .toArray(Resource[]::new);
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
        factory.setResources(resources);
        return factory;
    }

//    @Bean   // uncomment to enable custom download
    public IRuntimeConfig embeddedMongoRuntimeConfig() {
        boolean isLocalDownload = true;
        String mongodbDowloadPath = isLocalDownload ?
                Thread.currentThread() // downloads from src/main/resources/mongodb/win32/mongodb-win32-x86_64-3.5.5.zip
                    .getContextClassLoader()
                    .getResource("mongodb/")
                    .toString()
                : "https://downloads.mongodb.org/"; // downloads from https://downloads.mongodb.org/win32/mongodb-win32-x86_64-3.5.5.zip
        Command command = Command.MongoD;
        IDownloadConfig downloadConfig = new DownloadConfigBuilder()
                .packageResolverForCommand(command)
                .defaults()
                .downloadPath(mongodbDowloadPath) // win32/mongodb-win32-x86_64-3.5.5.zip is added automatically to this path
                .build();
        AbstractBuilder<IArtifactStore> abstractBuilder = new ExtractedArtifactStoreBuilder()
                .defaults(command)
                .download(downloadConfig);
        IRuntimeConfig runtimeConfig = new RuntimeConfigBuilder()
                .defaults(command)
                .artifactStore(abstractBuilder)
                .build();
        return runtimeConfig;
    }
}