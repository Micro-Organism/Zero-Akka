package com.zero.akka.common.config;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(akka.stream.javadsl.Source.class)
public class AkkaConfig {

  private final ActorSystem system;
  private final ActorMaterializer mat;

  @Autowired
  public AkkaConfig(ActorSystem system, ActorMaterializer mat) {
    this.system = ActorSystem.create("SpringWebAkkaStreamsSystem");
    this.mat = ActorMaterializer.create(system);
  }

  @Bean
  @ConditionalOnMissingBean(ActorSystem.class)
  public ActorSystem getActorSystem() {
    return system;
  }

  @Bean
  @ConditionalOnMissingBean(Materializer.class)
  public ActorMaterializer getMaterializer() {
    return mat;
  }
}