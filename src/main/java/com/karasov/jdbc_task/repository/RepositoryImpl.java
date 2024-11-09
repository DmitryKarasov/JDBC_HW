package com.karasov.jdbc_task.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class RepositoryImpl implements MyRepository {
    private final String script;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public RepositoryImpl(
            NamedParameterJdbcTemplate jdbcTemplate,
            @Value("${script.name}") String scriptFileName
    ) {
        this.script = read(scriptFileName);
        this.jdbcTemplate = jdbcTemplate;
    }

    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getProductNameByCustomerName(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name.toLowerCase());
        List<String> result = jdbcTemplate.queryForList(script, params, String.class);
        return result.isEmpty() ? null : result.get(0);
    }
}
