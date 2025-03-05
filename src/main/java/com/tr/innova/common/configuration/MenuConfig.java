package com.tr.innova.common.configuration;

import com.tr.innova.dto.menu.MenuDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Configuration
public class MenuConfig {

    @Bean
    public MenuDto menuDto() {

        Yaml yaml = new Yaml();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("menu.yaml");
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        return yaml.loadAs(reader, MenuDto.class);

    }
}
